package nuclei.core;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import nuclei.CloudAMQPFactoryUtil;
import nuclei.controller.DeploymentController;
import nuclei.controller.RabbitReceiveController;
import nuclei.controller.TransactionController;
import nuclei.controller.TransactionLifecycleController;
import nuclei.service.OrderService;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
/**
 * @author Karthikeyan
 *
 */
@Controller
public class DeployAddRabbitMQList {

	@Autowired
	private DeploymentController deploymentController;

	@Autowired
	private OrderService orderService;

	@Autowired
	private TransactionLifecycleController transactionLifecycleController;

	@Autowired
	private RabbitReceiveController rabbitReceiveController;

	@Autowired
	private TransactionController transactionController;

	private static final String EXCHANGE_NAME = "Nuclei.fanout";
	private Connection connection;
	private Connection mainAppConnection;
	private Channel channel;
	private Channel mainAppChannel;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getTransactionList(Long id){
		Iterable<Map<String, Object>> entity = null;
		int count=0;
		String deployId="";
		try{
			entity=orderService.generateTransactionList(id);
			//System.out.println("Entity : "+entity);
			HashMap deploymentArrayList = new HashMap();
			HashMap deploymentArrayValue = new HashMap();	
			ArrayList transLogList = new ArrayList();	
			HashMap transactionArray = new HashMap();

			ArrayList transactionLifecycleArray = new ArrayList();	
			HashMap transactionLifecycleValue=new HashMap();
			
			ConnectionFactory factory = CloudAMQPFactoryUtil.getConnectionFactory();
			connection = factory.newConnection();
			channel = connection.createChannel();
			
			
			
			for (Map<String, Object> map : entity) {
				for (Entry<String, Object> entry : map.entrySet()) {
					deploymentArrayList = (HashMap) entry.getValue();
					deploymentArrayValue = (HashMap) deploymentArrayList.get("Deployment");	
					deployId=deploymentArrayValue.get("id").toString();	// deployment Id
					transLogList=(ArrayList) deploymentArrayValue.get("TransactionLifecycle");		
					for(int j=0; j<transLogList.size(); j++){

						transactionArray=(HashMap) transLogList.get(j);
						String lifecycleId=transactionArray.get("id").toString(); // status lifeCycle Id
						transactionLifecycleArray=(ArrayList) transactionArray.get("Transaction");					
						for(int i=0; i<transactionLifecycleArray.size(); i++){

							if(transactionLifecycleArray.get(i)!=null){

								transactionLifecycleValue=(HashMap) transactionLifecycleArray.get(i);

								String transid=transactionLifecycleValue.get("id").toString(); //transaction Id

								ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
								ObjectOutputStream out = new ObjectOutputStream(byteOut);
								out.writeObject(transactionLifecycleArray.get(i));	

								String queueName = "executor"; //queue name
								boolean durable = false; //durable - RabbitMQ will never lose the queue if a crash occurs
								boolean exclusive = false; //exclusive - if queue only will be used by one connection
								boolean autoDelete = false; //autodelete - queue is deleted when last consumer unsubscribes

								channel.queueDeclare(queueName, durable, exclusive, autoDelete, null);
								channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
								channel.queueBind(queueName, EXCHANGE_NAME, "");

								JSONObject obj = new JSONObject();
								obj.put("Transaction",transactionLifecycleArray.get(i));
								channel.basicPublish(EXCHANGE_NAME, "", null, obj.toJSONString().getBytes());
						System.out.println(" [x] Sent '" + obj.toJSONString() + "'");								
								transactionLifecycleController.update(lifecycleId, "executing");	

								//Receive
								//JSONObject jsonMessage=rabbitReceiveController.get();

								String mainAppQueueName = "main-app";
								mainAppConnection = factory.newConnection();
								mainAppChannel = mainAppConnection.createChannel();
								mainAppChannel.queueDeclare(mainAppQueueName, durable, exclusive, autoDelete, null);
								mainAppChannel.exchangeDeclare("Nuclei-MainApp.fanout", "fanout");
								
								mainAppChannel.queueBind(mainAppQueueName, "Nuclei-MainApp.fanout", "");

								QueueingConsumer consumer = new QueueingConsumer(mainAppChannel);
								mainAppChannel.basicConsume(mainAppQueueName, true, consumer);
								QueueingConsumer.Delivery delivery = consumer.nextDelivery();
								String consumedMessage = new String(delivery.getBody());
								JSONObject message = (JSONObject)new JSONParser().parse(consumedMessage);
								mainAppChannel.close();
								mainAppConnection.close();
								if(message.get("Status").equals("OK")){
									count++;
						System.out.println(" [Y] Received From MainApp '" + message.toString() + "'");
									transactionLifecycleController.update(lifecycleId, "executed");	
									transactionController.addTransactionLog(transid, "OK", "", String.valueOf(count));	

									continue;
								}else {
									count++;
									i--;
									transactionController.addTransactionLog(transid, "NOT-OK", "", String.valueOf(count));	

									continue;
								}	
							}
						}
						count=0;
					}	
				}
			}
			channel.close();
			connection.close();
			
			String deploStatus=deploymentController.checkStatus(deployId).toString();
			deploymentController.updateStatus(deployId,deploStatus);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
