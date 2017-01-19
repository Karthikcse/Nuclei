package nuclei.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import nuclei.core.DeployAddRabbitMQList;
import nuclei.domain.Deployment;
import nuclei.domain.Order;
import nuclei.response.DeploymentsMessage;
import nuclei.response.OrderMessage;
import nuclei.response.OrdersMessage;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.response.TransactionsMessage;
import nuclei.service.MainService;
import nuclei.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class OrderController extends MainController<Order> {

	@Autowired
	private OrderService orderService;

	@Autowired
	private DeploymentController deploymentController;

	@Autowired
	private BlueprintController blueprintController;

	@Autowired
	private IaaSTemplateController iaaSTemplateController;
	
	@Autowired
	private DeployAddRabbitMQList transList; 

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public @ResponseBody OrdersMessage list(final HttpServletResponse response) {
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity = orderService.getOrderValues();
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
		return new OrdersMessage(status, entity);

	}

	@RequestMapping(value = "/insertorder", method = RequestMethod.POST)
	public @ResponseBody OrderMessage create(@FormDataParam("history") String history,
			@FormDataParam("instance_type") String instance_type,
			@FormDataParam("version") String version,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Order entity = new Order();
		try {	
			entity.setHistory(history);
			entity.setInstance_type(instance_type);
			entity.setVersion(version);
			entity.setDeleted(false);
			response.setHeader("Cache-Control", "no-cache");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new OrderMessage(status, entity);
	}

	@RequestMapping(value = "/updateorder", method = RequestMethod.POST)
	public @ResponseBody OrderMessage update(@FormDataParam("id") String id,
			@FormDataParam("history") String history,
			@FormDataParam("instance_type") String instance_type,
			@FormDataParam("version") String version,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long taskId = Long.parseLong(id);
		Order entity = null;
		try {
			entity = orderService.find(taskId);
			entity.setHistory(history);
			entity.setInstance_type(instance_type);
			entity.setVersion(version);
			entity.setDeleted(false);
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new OrderMessage(status, entity);
	}

	@RequestMapping(value = "/getorderbyid", method = RequestMethod.GET)
	public @ResponseBody OrdersMessage find(@FormDataParam("id") String id,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long taskId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = orderService.getOrderById(taskId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new OrdersMessage(status, entity);
	}
	
	//Create Order to iaasConfig relation
	@RequestMapping(value="/orderToIaasConfig",method= RequestMethod.POST, produces="application/json")		
	public @ResponseBody TransactionsMessage orderToIaasconfig(@FormDataParam("orderid") String orderid,@FormDataParam("iaasid") String iaasid)
	{
		ResponseStatus status = new ResponseStatus(ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");
		Long ordId=Long.parseLong(orderid);
		Long configId=Long.parseLong(iaasid);
		Iterable<Map<String, Object>> entity = null;
		try{
			entity=orderService.createIaasConfigRelation(ordId, configId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionsMessage(status,entity);
	}

	//Create Order to SC relation
	@RequestMapping(value="/orderToSC",method= RequestMethod.POST, produces="application/json")		
	public @ResponseBody TransactionsMessage orderToSC(@FormDataParam("orderid") String orderid,@FormDataParam("scid") String scid)
	{
		ResponseStatus status = new ResponseStatus(ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");
		Long ordId=Long.parseLong(orderid);
		Long serviceId=Long.parseLong(scid);
		Iterable<Map<String, Object>> entity = null;
		try{
			entity=orderService.createSCRelation(ordId, serviceId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionsMessage(status,entity);
	}

	@RequestMapping(value = "/deleteorder", method = RequestMethod.POST)
	public @ResponseBody OrderMessage delete(@FormDataParam("id") String id,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long taskId = Long.parseLong(id);
		Order tasks = null;
		try {
			tasks = orderService.find(taskId);
			tasks.setDeleted(true);
			setHeaders(response);
			super.create(tasks);
			super.delete(taskId);				
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new OrderMessage(status, tasks);
	}

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/createNewOrder", method = RequestMethod.POST)
	public @ResponseBody OrderMessage createNewOrder(
			@FormDataParam("history") String history,
			@FormDataParam("instance_type") String instance_type,
			@FormDataParam("version") String version,
			@FormDataParam("name") String name,
			@FormDataParam("scid") String scid,
			@FormDataParam("iaas") String iaas) {
		ResponseStatus status = null;
		Order entity = new Order();		
		List<Deployment> deptList = new ArrayList<Deployment>();
		Deployment deployment = new Deployment();
		try {	
			entity.setHistory(history);
			entity.setInstance_type(instance_type);
			entity.setVersion(version);
			entity.setDeleted(false);		
			super.create(entity);	// create new order

			orderToIaasconfig(entity.getId().toString(),iaas); // Create relationship with order to IaasConfig
			orderToSC(entity.getId().toString(),scid); // Create relationship with order to service catalog

			deployment.setName(name);
			deployment.setStatus("NOT-OK");
			deployment.setDeleted(false);

			deptList.add(deployment);
			entity.setDeployment(deptList);

			super.update(entity.getId(), entity);	// create deployment and related to order

			deploymentController.deploymentToSC(deployment.getId().toString(), scid);	 // Create relationship with deployment to service catalog
			deploymentController.deploymentToIaasconfig(deployment.getId().toString(), iaas);	// Create relationship with deployment to IaasConfig	
			Iterable<Map<String, Object>> newEntity=orderService.getOrderByServiceCatalogue(Long.valueOf(scid));
			HashMap hm=getBlueprintAndTemplateId(newEntity);

			blueprintController.generateyaml(hm.get("bpid").toString());	// Generate Yaml file

			iaaSTemplateController.generateSequenceCLI(hm.get("tempid").toString(),deployment.getId().toString());	// Generate transaction list

			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new OrderMessage(status, entity);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap getBlueprintAndTemplateId(Iterable<Map<String, Object>> entity){
		//System.out.println("newEntity :: "+ entity);
		ArrayList scArray = new ArrayList();	
		HashMap scList = new HashMap();

		ArrayList bpArray = new ArrayList();		
		HashMap bpValue = new HashMap();

		ArrayList tempArray = new ArrayList();		
		HashMap tempValue = new HashMap();	

		HashMap getTempId = new HashMap();

		HashMap collectIds=new HashMap();

		for (Map<String, Object> map : entity) {
			for (Entry<String, Object> entry : map.entrySet()) {
				scList = (HashMap) entry.getValue();
				scArray = (ArrayList) scList.get("ServiceCatalogue");

				bpValue = (HashMap) scArray.get(0);
				String id1 = bpValue.get("id").toString();
					
				collectIds.put("scid",id1);
				bpArray = (ArrayList) bpValue.get("Blueprint");
				
				tempValue=(HashMap) bpArray.get(0);
				String id2 = tempValue.get("id").toString();
					
				collectIds.put("bpid",id2);
				tempArray=(ArrayList) tempValue.get("IaaSTemplate");
						
				getTempId=(HashMap) tempArray.get(0);
				String id3 = getTempId.get("id").toString();
				
				collectIds.put("tempid",id3);
					
				return collectIds;
			}
		}
		return collectIds;
	}
	
	//Get order to deploy values
	@RequestMapping(value = "/generateTransactionList", method = RequestMethod.GET)
	public @ResponseBody OrdersMessage generateTransactionList(@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long deployId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			transList.getTransactionList(deployId);
		//	entity = deploymentService.getTransactionByDeployId(deployId);			
		//	System.out.println("entity : "+entity);			
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new OrdersMessage(status, entity);
	}

	@Override
	public MainService<Order> getService() {
		return orderService;
	}
}
