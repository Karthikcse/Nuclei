package nuclei;

import java.io.IOException;
import java.util.Arrays;
import javax.annotation.PostConstruct;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;  
import org.json.simple.parser.JSONParser;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableNeo4jRepositories(basePackages = "nuclei.repository", queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
@EnableTransactionManagement
@SpringBootApplication
public class Application extends Neo4jConfiguration{

	private final Logger log = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	private Environment env;	
		
	@Override
	@Bean
	public Neo4jServer neo4jServer() {
		log.info("Initialising server connection");
		/*String url = null;
		try {

			JSONObject vcap = (JSONObject) new JSONParser().parse(System.getenv("VCAP_SERVICES"));
			JSONArray userProvidedServices = (JSONArray) (vcap.get("user-provided"));
			JSONObject userProvidedService = (JSONObject) userProvidedServices.get(0);
			JSONObject credentials = (JSONObject) (userProvidedService.get("credentials"));
			String host = (String) credentials.get("host");
			String port = (String) credentials.get("port");
			url = host + ":" + port;
			System.out.println("URL : " + url);
			return new RemoteServer(url);

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		 return new RemoteServer("http://192.168.1.179:7474/"); // Karthik
		// return new RemoteServer("http://192.168.1.39:7474/"); // Fariz
		 
		 //return new RemoteServer("http://192.237.253.117:7474/");		
		//return new RemoteServer("http://50.57.127.125:7474/");
		// return new RemoteServer("http://184.106.189.143:7474/");
			
		//return new RemoteServer(url);
	}

	@Override
	@Bean
	public SessionFactory getSessionFactory() {
		log.info("Initialising Session Factory");
	/*	try {
			JSONObject vcap = (JSONObject) new JSONParser().parse(System.getenv("VCAP_SERVICES"));
			JSONArray userProvidedServices = (JSONArray) (vcap.get("user-provided"));
			JSONObject userProvidedService = (JSONObject) userProvidedServices.get(0);
			JSONObject credentials = (JSONObject) (userProvidedService.get("credentials"));
			String user = (String) credentials.get("username");
			String pwd = (String) credentials.get("password");
			System.setProperty("username", user);
			System.setProperty("password", pwd);
			return new SessionFactory("nuclei/domain");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	// System.setProperty("username","neo4j");		// server
	 //System.setProperty("password","neo4j@root");	// server	
		return new SessionFactory("nuclei/domain");
	}

	@Override
	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Session getSession() throws Exception {

		log.info("Initialising session-scoped Session Bean");
		return super.getSession();
	}

	/**
	 * Initializes registrar.
	 * <p/>
	 * Spring profiles can be configured with a program arguments
	 * --spring.profiles.active=your-active-profile
	 * <p/>
	 */
	@PostConstruct
	public void initApplication() throws IOException {
		if (env.getActiveProfiles().length == 0) {
			log.warn("No Spring profile configured, running with default configuration");
		} else {
			log.info("Running with Spring profile(s) : {}",
					Arrays.toString(env.getActiveProfiles()));
		}
	}

	/**
	 * Main method, used to run the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Set a default profile if it has not been set
	 */
	@SuppressWarnings("unused")
	private static void addDefaultProfile(SpringApplication app,
			SimpleCommandLinePropertySource source) {
		if (!source.containsProperty("spring.profiles.active")) {
			app.setAdditionalProfiles(Constants.SPRING_PROFILE_DEVELOPMENT);
		}
	}

}
