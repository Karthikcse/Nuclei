/**
 * 
 */
package nuclei.controller;

import nuclei.domain.SecurityTags;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.response.SecurityTagMessage;
import nuclei.service.MainService;
import nuclei.service.SecurityTagsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Karthikeyan
 *
 */

// @RestController
@Controller
public class SecurityTagsController extends MainController<SecurityTags> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityTagsController.class);
	
	@Autowired
	private SecurityTagsService securityTagsService;	
	
	@RequestMapping(value = "/createsecuritytags", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public SecurityTagMessage create(String functionName,
			String create, String edit, String read, String delete) {
		
		boolean createAccess=Boolean.parseBoolean(create);
		boolean editAccess=Boolean.parseBoolean(edit);
		boolean readAccess=Boolean.parseBoolean(read);
		boolean deleteAccess=Boolean.parseBoolean(delete);
		
		LOGGER.info("Create role :: " + functionName );		
		ResponseStatus status = new ResponseStatus(ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");
		SecurityTags entity = new SecurityTags();
		
		entity.setFunctionName(functionName);
		entity.setCreateAccess(createAccess);
		entity.setEditAccess(editAccess);
		entity.setReadAccess(readAccess);
		entity.setDeleteAccess(deleteAccess);
		entity.setDelete(false);
		
		super.create(entity);
		status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		return new SecurityTagMessage(status, entity);
	}
	
	@RequestMapping(value = "/updatesecuritytags", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public SecurityTagMessage update(String id, String functionName,
			String create, String edit, String read, String delete) {
		
		boolean createAccess=Boolean.parseBoolean(create);
		boolean editAccess=Boolean.parseBoolean(edit);
		boolean readAccess=Boolean.parseBoolean(read);
		boolean deleteAccess=Boolean.parseBoolean(delete);		
			
		ResponseStatus status = null;
		Long securityTagsId = Long.parseLong(id);
		SecurityTags entity = null;
		try {
			entity = securityTagsService.find(securityTagsId);
			entity.setFunctionName(functionName);
			entity.setCreateAccess(createAccess);
			entity.setEditAccess(editAccess);
			entity.setReadAccess(readAccess);
			entity.setDeleteAccess(deleteAccess);
			entity.setDelete(false);
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SecurityTagMessage(status, entity);
	}

	@RequestMapping(value = "/getsecuritytagsbyid", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public SecurityTagMessage find(String id) {
		ResponseStatus status = null;
		Long securityTagsId = Long.parseLong(id);	
		SecurityTags entity  = null;
		try {
			entity = securityTagsService.find(securityTagsId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SecurityTagMessage(status, entity);
	}

	@RequestMapping(value = "/deletesecuritytags", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public SecurityTagMessage delete(String id) {
		ResponseStatus status = null;
		Long securityTagsId = Long.parseLong(id);
		SecurityTags entity = null;
		try {
			entity = securityTagsService.find(securityTagsId);
			entity.setDelete(true);		
			super.create(entity);
			super.delete(securityTagsId);				
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SecurityTagMessage(status, entity);
	}	
		
	@Override
	public MainService<SecurityTags> getService() {
		return securityTagsService;
	}
}
