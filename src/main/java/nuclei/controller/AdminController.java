package nuclei.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.jersey.multipart.FormDataParam;

import nuclei.domain.Organization;
import nuclei.domain.Role;
import nuclei.domain.User;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.response.UserMessage;
import nuclei.response.UsersMessage;
import nuclei.service.AdminService;
import nuclei.service.MainService;
import nuclei.service.OrganizationService;

@RestController
public class AdminController extends MainController<User> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AdminController.class);

	private final JavaMailSender javaMailSender;

	@Autowired
	public AdminController(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private OrganizationService organizationService;

	/**
	 * Method to create the user by admin
	 * 
	 * @param email
	 * @param password
	 * @param role
	 * @param org
	 * @return
	 */
	@RequestMapping(value = "/createuser", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public UserMessage createUser(String name, String email, String role) {
		LOGGER.info("Edit user request processing :: " + email);
		ResponseStatus status = new ResponseStatus(
				ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");

		User entity = new User();
		String password = "obs" + ((Double) (Math.random() * 1000)).longValue();
		entity.setName(name);
		entity.setEmail(email);
		entity.setPassword(password);
		entity.setDelete(false);
		User availUser = adminService.getUserByEmail(email);
		if (availUser != null && availUser.getEmail() != null) {
			status = new ResponseStatus(ResponseStatusCode.STATUS_DUPLICATE,
					"DUPLICATE");
		} else {
			List<Role> rollList = new ArrayList<Role>();

			for (String roleValue : role.split(",")) {
				Long roleId = Long.parseLong(roleValue);
				if (roleId != null) {
					Role roleObj = adminService.getRoleById(roleId);
					if (roleObj != null) {
						rollList.add(roleObj);
					}
				}
			}
			entity.setRoles(rollList);

			super.create(entity);
			// Org Id
			//String orgnationid = "164"; // 50 server
			String orgnationid = "251";  // 184 server
			User relationEntity = adminService.find(entity.getId());
			Long orgid = Long.parseLong(orgnationid);
			if (orgid != null) {
				relationEntity = adminService.createRelationWithOrg(entity.getId(), orgid);
				super.update(entity.getId(), relationEntity);
			}						
			Organization org = organizationService.find(orgid);
			String orgName=org.getName();
			
			sendEmail(name, email, password,orgName);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		}
		return new UserMessage(status, entity);
	}

	/**
	 * Method to edit the user by admin
	 * 
	 * @param email
	 * @param password
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/edituser", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public UserMessage editUser(String id, String name, String email,
			String role) {
		LOGGER.info("Edit user request processing :: " + email);
		ResponseStatus status = new ResponseStatus(
				ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");
		User entity = null;
		if (id != null) {
			Long userId = Long.parseLong(id);
			entity = adminService.find(userId);
			if (name != null) {
				entity.setName(name);
			}
			if (email != null) {
				entity.setEmail(email);
			}

			List<Role> rollList = new ArrayList<Role>();

			for (String roleValue : role.split(",")) {
				Long roleId = Long.parseLong(roleValue);
				if (roleId != null) {
					Role roleObj = adminService.getRoleById(roleId);
					if (roleObj != null) {
						rollList.add(roleObj);
					}
				}
			}
			entity.setRoles(rollList);
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		}
		return new UserMessage(status, entity);
	}

	/**
	 * To get the user and role info for logged in user TODO
	 */
	@RequestMapping(value = "/getuserbylogin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUserByLogin(
			@RequestParam(value = "login") String login) {
		User userDetails;
		if (login != null) {
			userDetails = adminService.getUserDetail(login);
			if (userDetails != null) {
				return new ResponseEntity<User>(userDetails, HttpStatus.OK);
			} else {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * To get the user and role info for logged in user TODO
	 */
	@RequestMapping(value = "/googlelogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserMessage googleLogin(String googleid, String email) {
		ResponseStatus status = new ResponseStatus(
				ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");
		User userDetails = null;
		if (googleid != null && email != null) {
			userDetails = adminService.getUserByEmail(email);
			if (userDetails != null) {
				if (userDetails.getGoogleId() == null
						|| StringUtils.isEmpty(userDetails.getGoogleId())) {
					userDetails.setGoogleId(googleid);
					super.create(userDetails);
				}
				status = new ResponseStatus(ResponseStatusCode.STATUS_OK,
						"SUCCESS");
			} else {
				status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,
						"STATUS_NORECORD");
			}
		}
		return new UserMessage(status, userDetails);
	}

	/**
	 * To get all the user TODO
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UsersMessage listUser() {
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		entity = adminService.listUser();
		if (entity != null) {
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");

		} else {
			status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,
					"No Record found");
		}
		return new UsersMessage(status, entity);

	}

	/**
	 * Method to send the email
	 * 
	 * @param email
	 */
	public void sendEmail(String name, String email, String password, String org) {
		System.out.println("Check :: " + email);
		String text = generateMailContent(name, email, password, org);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setSubject("Invitation for nuclei");
		mailMessage.setText(text);
		javaMailSender.send(mailMessage);
	}

	public String generateMailContent(String name, String email, String password, String org) {
		String text = "Hi " + name + "\n";
		text = text + "You have been invited the nuclei. " + "\n";
		text = text + "Organization :" + org+ "\n"+"Username :" + email + "\n" + "Password :" + password+ "\n";
		text = text + "Click this link (http://184.106.189.143:8080/Nuclei) to login nuclei.";
		return text;
	}

	@RequestMapping(value = "/deleteuser", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public UserMessage delete(String id) {
		ResponseStatus status = null;
		Long roleId = Long.parseLong(id);
		User user = null;
		try {
			user = adminService.find(roleId);
			user.setDelete(true);
			super.create(user);
			super.delete(roleId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new UserMessage(status, user);
	}

	@RequestMapping(value = "/getUserById", method = RequestMethod.GET)
	public @ResponseBody UsersMessage find(@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long userId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		   User usr = null;	
		try {				
			   usr = adminService.find(userId);
			   usr.setNewUser(true);
			   super.create(usr);	
			   
			   entity = adminService.getUserById(userId);	
			
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new UsersMessage(status, entity);
	}

	@RequestMapping(value = "/getUserByEmailObj", method = RequestMethod.GET)
	public @ResponseBody UsersMessage findUsingEmail(@FormDataParam("email") String email,
			final HttpServletResponse response) {
		ResponseStatus status = null;		
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		User usr = null;	
		try {			
			 usr = adminService.getUserByEmail(email);
			 User user=adminService.find(usr.getId());
			 user.setNewUser(true);
			 super.create(user);
			   
			entity = adminService.getUserByEmailObj(email);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new UsersMessage(status, entity);
	}


	@Override
	public MainService<User> getService() {
		return adminService;
	}
}