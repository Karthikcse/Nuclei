package nuclei;

import java.net.URI;
import java.net.URISyntaxException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Karthikeyan
 *
 */
public class CloudAMQPFactoryUtil {
	
	/**
     * Establish the CloudAMQP message queue
	 * @throws ParseException 
     */     
	public static ConnectionFactory getConnectionFactory() {
		ConnectionFactory factory = null;
		try{
			factory = new ConnectionFactory();			
			//Cloud
			/*JSONObject vcap = (JSONObject) new JSONParser().parse(System.getenv("VCAP_SERVICES"));
			if(!vcap.isEmpty() || vcap != null){
				JSONArray jsonArr = (JSONArray)(vcap.get("cloudamqp"));
				JSONObject jsonObj = (JSONObject) jsonArr.get(0);
				JSONObject amqpUri = (JSONObject) jsonObj.get("credentials");
				String mqUri = (String) amqpUri.get("uri");
				URI uri = new URI(mqUri);
				factory.setUri(uri);
			}*/
			
			//Local Test start
			String mqUri ="amqp://kwrbstaw:rlGWlvRyTzmbyy3rVxYK9gmCdSs0uhtR@baboon.rmq.cloudamqp.com/kwrbstaw";
			URI uri = new URI(mqUri);
			factory.setUri(uri);
			//Local Test End
			
		}catch(URISyntaxException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return factory;
	}
}