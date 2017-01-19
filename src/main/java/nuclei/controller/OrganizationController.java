package nuclei.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nuclei.domain.Organization;
import nuclei.response.OrganizationMessage;
import nuclei.response.OrganizationsMessage;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.service.MainService;
import nuclei.service.OrganizationService;

/**
 * @author ramprasath
 *
 */

@RestController
public class OrganizationController extends MainController<Organization>{

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);


	@Autowired
	private OrganizationService organizationService;

	/**
	 * To get all the Organizations 
	 * TODO
	 */
	@RequestMapping(value = "/organizations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OrganizationsMessage listRole(){
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		entity = organizationService.getOrganizations();
		if(entity != null){
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");

		}else{
			status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD, "No Record found");
		}
		return new OrganizationsMessage(status, entity);
	}

	/**
	 * Method to create the Organization
	 * @param name
	 * @param description
	 * @return
	 */
	@RequestMapping(value = "/createorganization", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public  OrganizationMessage createOrganization(String name, String description){
		LOGGER.info("Create role :: " + name );		
		ResponseStatus status = new ResponseStatus(ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");
		Organization entity = new Organization();
		entity.setName(name);
		entity.setDescription(description);
		entity.setDeleted(false);			
		super.create(entity);
		status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		return new OrganizationMessage(status, entity);
	}

	/**
	 * Method to edit the role by admin
	 * @param name
	 * @param description
	 * @return
	 */
	@RequestMapping(value = "/editorganization", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public  OrganizationMessage editOrganization(String id,String name, String description){
		LOGGER.info("Edit role :: " + name );
		
		ResponseStatus status = new ResponseStatus(ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");
		Organization entity = null;
		if(id != null){
			Long orgId = Long.parseLong(id);
			entity = organizationService.find(orgId);
			if(name!= null){
				entity.setName(name);
			}
			if(description!= null){
				entity.setDescription(description);
			}			
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
			super.create(entity);
		}
		return new OrganizationMessage(status, entity);
	}

	/**
	 * To get the Organization info 
	 * TODO
	 */
	@RequestMapping(value = "/getorganizationbyid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getOrganizationById(@RequestParam(value = "id") String id){
		Organization entity = null;
		if(id != null){
			Long roleId = Long.parseLong(id);
			entity = organizationService.find(roleId);
		}
		return new ResponseEntity<Organization>(entity, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteorganization", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public OrganizationMessage delete(String id) {
		ResponseStatus status = null;
		Long roleId = Long.parseLong(id);
		Organization organization = null;
		try {
			organization = organizationService.find(roleId);
			organization.setDeleted(true);			
			super.create(organization);
			super.delete(roleId);				
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new OrganizationMessage(status, organization);
	}

	@Override
	public MainService<Organization> getService() {
		return organizationService;
	}	
}
