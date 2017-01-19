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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.jersey.multipart.FormDataParam;

import nuclei.domain.Role;
import nuclei.domain.SecurityTags;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.response.RoleMessage;
import nuclei.service.MainService;
import nuclei.service.RoleService;

/**
 * @author ramprasath
 *
 */

@RestController
public class RoleController extends MainController<Role>{

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);


	@Autowired
	private RoleService roleService;

	/**
	 * To get all the roles 
	 * TODO
	 */
	@RequestMapping(value = "/roles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody RoleMessage listRole(){
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		entity = roleService.listRole();
		if(entity != null){
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");

		}else{
			status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD, "No Record found");
		}
		return new RoleMessage(status, entity);
	}

	/**
	 * Method to create the role by admin
	 * @param name
	 * @param description
	 * @return
	 */
	@RequestMapping(value = "/createrole", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public  RoleMessage createRole(String name, String description){
		LOGGER.info("Create role :: " + name );		
		ResponseStatus status = new ResponseStatus(ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");
		Role entity = new Role();
		entity.setName(name);
		entity.setDescription(description);
		entity.setDelete(false);			
		super.create(entity);
		status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		return new RoleMessage(status, entity);
	}

	/**
	 * Method to edit the role by admin
	 * @param name
	 * @param description
	 * @return
	 */
	@RequestMapping(value = "/editrole", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public  RoleMessage editRole(String id, String name, String description,String functionName,
			String create, String edit, String read, String delete){
		LOGGER.info("Edit role :: " + name );

		boolean createAccess=Boolean.parseBoolean(create);
		boolean editAccess=Boolean.parseBoolean(edit);
		boolean readAccess=Boolean.parseBoolean(read);
		boolean deleteAccess=Boolean.parseBoolean(delete);


		ResponseStatus status = new ResponseStatus(ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");
		Role entity = null;
		if(id != null){
			Long roleId = Long.parseLong(id);
			entity = roleService.find(roleId);
			if(name!= null){
				entity.setName(name);
			}
			if(description!= null){
				entity.setDescription(description);
			}		

			if(functionName!=null && !StringUtils.isEmpty(functionName)){
				List<SecurityTags> securityTagsList=new ArrayList<SecurityTags>();
				if(entity.getSecurityTags()!=null && entity.getSecurityTags().size()>0){
					securityTagsList = entity.getSecurityTags();
					for (int i = 0; i < securityTagsList.size(); i++) {
						if(functionName.equals(securityTagsList.get(i).getFunctionName())){
							securityTagsList.remove(i);
						}
					}
				}

				SecurityTags securityTagsValue=new SecurityTags();
				securityTagsValue.setFunctionName(functionName);
				securityTagsValue.setCreateAccess(createAccess);
				securityTagsValue.setEditAccess(editAccess);
				securityTagsValue.setReadAccess(readAccess);
				securityTagsValue.setDeleteAccess(deleteAccess);
				securityTagsValue.setDelete(false);

				securityTagsList.add(securityTagsValue);		

				entity.setSecurityTags(securityTagsList);			
			}

			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
			super.create(entity);
		}
		return new RoleMessage(status, entity);
	}

	/**
	 * To get the role info 
	 * TODO
	 */
	@RequestMapping(value = "/getRole", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUserByLogin(@RequestParam(value = "id") String id){
		Role entity = null;
		if(id != null){
			Long roleId = Long.parseLong(id);
			entity = roleService.find(roleId);
		}
		return new ResponseEntity<Role>(entity, HttpStatus.OK);
	}

	@RequestMapping(value = "/getrolebyid", method = RequestMethod.GET)
	public @ResponseBody RoleMessage find(@FormDataParam("id") String id,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long userId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = roleService.getRoleById(userId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new RoleMessage(status, entity);
	}


	@RequestMapping(value = "/deleterole", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RoleMessage delete(String id) {
		ResponseStatus status = null;
		Long roleId = Long.parseLong(id);
		Role role = null;
		try {
			role = roleService.find(roleId);
			role.setDelete(true);			
			super.create(role);
			super.delete(roleId);				
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new RoleMessage(status, role);
	}

	@Override
	public MainService<Role> getService() {
		return roleService;
	}	
}
