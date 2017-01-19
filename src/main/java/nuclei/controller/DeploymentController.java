/**
 * 
 */
package nuclei.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import nuclei.core.DeployAddRabbitMQList;
import nuclei.domain.Deployment;
import nuclei.response.DeploymentMessage;
import nuclei.response.DeploymentsMessage;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.response.TransactionsMessage;
import nuclei.service.DeploymentService;
import nuclei.service.MainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.jersey.multipart.FormDataParam;

/**
 * @author Karthikeyan
 *
 */

// @RestController
@Controller
public class DeploymentController extends MainController<Deployment> {

	@Autowired
	private DeploymentService deploymentService;	

	@RequestMapping(value = "/deployments", method = RequestMethod.GET)
	public @ResponseBody DeploymentsMessage list(final HttpServletResponse response) {
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity = deploymentService.getdeploymentValues();
			if (entity != null) {
				status = new ResponseStatus(ResponseStatusCode.STATUS_OK,
						"SUCCESS");
			} else {
				status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,
						"No Record found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new DeploymentsMessage(status, entity);

	}

	@RequestMapping(value = "/insertdeployment", method = RequestMethod.POST)
	public @ResponseBody DeploymentMessage create(@FormDataParam("name") String name) {
		ResponseStatus status = null;
		Deployment entity = new Deployment();
		try {			
			entity.setName(name);
			entity.setStatus("Created");
			entity.setDeleted(false);

			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new DeploymentMessage(status, entity);
	}

	@RequestMapping(value = "/updatedeployment", method = RequestMethod.POST)
	public @ResponseBody DeploymentMessage update(@FormDataParam("id") String id,
			@FormDataParam("name") String name,
			final HttpServletResponse response, Model model) {
		ResponseStatus status = null;
		Long taskId = Long.parseLong(id);
		Deployment entity = null;
		try {
			entity = deploymentService.find(taskId);
			entity.setName(name);
			entity.setStatus("Created");
			entity.setDeleted(false);
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new DeploymentMessage(status, entity);
	}

	@RequestMapping(value = "/checkdeploystatus", method = RequestMethod.GET)
	public @ResponseBody String checkStatus(@FormDataParam("id") String id){
		Long deployId = Long.parseLong(id);
		String status="";
		Iterable<Map<String, Object>> transCount = null;
		Iterable<Map<String, Object>> statusCount = null;

		String transactionCount="";	
		String stasCount="";

		try {
			transCount = deploymentService.countTransaction(deployId);	
			statusCount=deploymentService.countOKCount(deployId);

			for (Map<String, Object> map : transCount) {
				for (Entry<String, Object> entry : map.entrySet()) {										
					transactionCount=entry.getValue().toString();
				}
			}
			for (Map<String, Object> map : statusCount) {
				for (Entry<String, Object> entry : map.entrySet()) {					
					stasCount=entry.getValue().toString();
				}
			}
			if(transactionCount.equals(stasCount)){
				status="OK";
			}
			else{
				status="NOT-OK";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	//Status update
	@RequestMapping(value = "/updatedeploymentstatus", method = RequestMethod.POST)
	public @ResponseBody DeploymentMessage updateStatus(@FormDataParam("id") String id,
			@FormDataParam("status") String status) {
		ResponseStatus responseStatus = null;
		Long taskId = Long.parseLong(id);
		Deployment entity = null;
		try {
			entity = deploymentService.find(taskId);
			entity.setName(entity.getName());
			entity.setStatus(status);
			entity.setDeleted(false);
			super.create(entity);
			responseStatus = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new DeploymentMessage(responseStatus, entity);
	}

	@RequestMapping(value = "/getdeploymentbyid", method = RequestMethod.GET)
	public @ResponseBody DeploymentsMessage find(@FormDataParam("id") String id,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long taskId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = deploymentService.getdeploymentById(taskId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new DeploymentsMessage(status, entity);
	}

	//Create deployment to Secvicecatalogue relation
	@RequestMapping(value="/deploymentToSC",method= RequestMethod.POST, produces="application/json")
	public @ResponseBody TransactionsMessage deploymentToSC(@FormDataParam("deptid") String deptid,@FormDataParam("scid") String scid)
	{
		ResponseStatus status = new ResponseStatus(ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");
		Long deloyId=Long.parseLong(deptid);
		Long serviceId=Long.parseLong(scid);
		Iterable<Map<String, Object>> entity = null;
		try{
			entity=deploymentService.createSCRelation(deloyId, serviceId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionsMessage(status,entity);
	}

	//Create deployment to iaasConfig relation
	@RequestMapping(value="/deploymentToIaasConfig",method= RequestMethod.POST, produces="application/json")		
	public @ResponseBody TransactionsMessage deploymentToIaasconfig(@FormDataParam("deptid") String deptid,@FormDataParam("iaasid") String iaasid)
	{
		ResponseStatus status = new ResponseStatus(ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");
		Long deloyId=Long.parseLong(deptid);
		Long serviceId=Long.parseLong(iaasid);
		Iterable<Map<String, Object>> entity = null;
		try{
			entity=deploymentService.createIaasConfigRelation(deloyId, serviceId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionsMessage(status,entity);
	}	

	@RequestMapping(value = "/deletedeployment", method = RequestMethod.POST)
	public @ResponseBody DeploymentMessage delete(@FormDataParam("id") String id,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long taskId = Long.parseLong(id);
		Deployment tasks = null;
		try {
			tasks = deploymentService.find(taskId);
			tasks.setDeleted(true);
			setHeaders(response);
			super.create(tasks);
			super.delete(taskId);				
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new DeploymentMessage(status, tasks);
	}

	@Override
	public MainService<Deployment> getService() {
		return deploymentService;
	}
}
