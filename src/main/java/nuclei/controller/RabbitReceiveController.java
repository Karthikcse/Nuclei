package nuclei.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import nuclei.CloudAMQPFactoryUtil;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * @author Karthikeyan
 *
 */
@RestController
public class RabbitReceiveController {

	private String clientId = "";
	private String message = "";
	private Connection connection;
	private Channel channel;
	private Channel mainAppChannel;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public @ResponseBody JSONObject get()
			throws KeyManagementException, NoSuchAlgorithmException,
			IOException, URISyntaxException, ShutdownSignalException,
			ConsumerCancelledException, InterruptedException, TimeoutException, ParseException {

		JSONObject consumedJsonData = new JSONObject();
		try {
			ConnectionFactory factory = CloudAMQPFactoryUtil.getConnectionFactory();
			connection = factory.newConnection();
			channel = connection.createChannel();

			channel.exchangeDeclare("Nuclei.fanout", "fanout");
			String queueName = "executor";
			channel.queueBind(queueName, "Nuclei.fanout", clientId);

			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(queueName, true, consumer);
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			message = new String(delivery.getBody());
			consumedJsonData = (JSONObject)new JSONParser().parse(message);
			consumedJsonData = (JSONObject) consumedJsonData.get("Transaction");

			/*if(consumedJsonData.get("sequence_num").equals("7")){
				consumedJsonData.put("Status", "NOT-OK");
			}else {
				consumedJsonData.put("Status", "OK");
			}	*/		
			
			consumedJsonData.put("Status", "OK");
			
			channel.close();
			connection.close();
			publishToMainApp(consumedJsonData);

			//return mainAppData;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return consumedJsonData;
	}

	@SuppressWarnings("unchecked")
	public void publishToMainApp(JSONObject consumedData) throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException, ShutdownSignalException, ConsumerCancelledException, URISyntaxException, InterruptedException, ParseException{
		//System.out.println("consumedData..."+consumedData);
		JSONObject dataToMainApp = new JSONObject();
		dataToMainApp.put("id", consumedData.get("id"));
		dataToMainApp.put("command-type", consumedData.get("command_type"));
		dataToMainApp.put("command", consumedData.get("command"));
		dataToMainApp.put("trax-type", "response");
		dataToMainApp.put("trax-uuid", consumedData.get("trax_uuid"));
		dataToMainApp.put("Status", consumedData.get("Status"));
		dataToMainApp.put("text-response", "0");

		// Send To MainApp
		ConnectionFactory factory = CloudAMQPFactoryUtil.getConnectionFactory();
		connection = factory.newConnection();
		mainAppChannel = connection.createChannel();
		String EXCHANGE_NAME = "Nuclei-MainApp.fanout";
		String queueName = "main-app"; //queue name
		boolean durable = false; //durable - RabbitMQ will never lose the queue if a crash occurs
		boolean exclusive = false; //exclusive - if queue only will be used by one connection
		boolean autoDelete = false; //autodelete - queue is deleted when last consumer unsubscribes

		mainAppChannel.queueDeclare(queueName, durable, exclusive, autoDelete, null);
		mainAppChannel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		mainAppChannel.queueBind(queueName, EXCHANGE_NAME, "");
		mainAppChannel.basicPublish(EXCHANGE_NAME, "", null, dataToMainApp.toJSONString().getBytes());
		mainAppChannel.close();
		connection.close();
		get();
		//System.out.println(" [x] Sent To MainApp '" + dataToMainApp.toJSONString() + "'");
		//return dataToMainApp;
	}
}
