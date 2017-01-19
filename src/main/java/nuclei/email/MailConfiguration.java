package nuclei.email;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfiguration {

	private final Logger logger = LoggerFactory.getLogger(MailConfiguration.class);
	
	@Value("${mail.smtp.host}")
	private String host;
	
	@Value("${mail.smtp.port}")
	private int port;
	
	@Value("${mail.smtp.starttls}")
	private String starttls;
	
	@Value("${mail.smtp.auth}")
	private boolean auth;
	
	@Value("${mail.smtp.protocol}")
	private String protocol;
	
	/*@Value("${mail.from}")
	private String from;*/
	
	@Value("${mail.username}")
	private String username;
	
	@Value("${mail.password}")
	private String password;
	
	@Bean
	public JavaMailSender javaMailSender(){
		logger.info("Mail configuration loaded");
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", auth);
		mailProperties.put("mail.smtp.starttls.enable", starttls);
		mailProperties.put("mail.smtp.protocol", protocol);
		mailSender.setJavaMailProperties(mailProperties);
		mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        
		return mailSender;
	}
}
