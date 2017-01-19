/*package nuclei.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
	
	private final Logger logger = LoggerFactory.getLogger(MailController.class);
	
	
	

	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public SimpleMailMessage send(){
		logger.info("Mail request processing");
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo("divakar.t@optisolbusiness.com");
		mailMessage.setReplyTo("noreply.com");
		mailMessage.setFrom("test@optisolbusiness.com");
		mailMessage.setSubject("Invitation for nuclei");
		mailMessage.setText("Test");
		javaMailSender.send(mailMessage);
		return mailMessage;
	}
	
	@RequestMapping(value = "/invite", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> inviteUser(HttpServletRequest request){
		String text = "Click on the URL to access the nuclei app";
		String username = request.getParameter("username");
		logger.info("Send email to " + username);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setTo(username);
		mailMessage.setReplyTo("noreply.com");
		mailMessage.setFrom("test@optisolbusiness.com");
		mailMessage.setSubject("Invitation for nuclei");
		mailMessage.setText(text +"http://localhost:8081");
		javaMailSender.send(mailMessage);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
*/