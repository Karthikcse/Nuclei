package nuclei.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.jersey.multipart.FormDataParam;

import nuclei.domain.Blueprint;
import nuclei.domain.IaaSTemplate;
import nuclei.domain.IaasParameters;
import nuclei.domain.Organization;
import nuclei.domain.TemplateFunction;
import nuclei.domain.Transaction;
import nuclei.domain.TransactionLifecycle;
import nuclei.domain.TransactionLog;
import nuclei.response.BlueprintMessage;
import nuclei.response.IaasTemplateMessage;
import nuclei.response.OrganizationMessage;
import nuclei.response.OrganizationsMessage;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.response.TransactionMessage;
import nuclei.response.TransactionsMessage;
import nuclei.service.MainService;
import nuclei.service.OrganizationService;
import nuclei.service.TemplateFunctionService;
import nuclei.service.TransactionService;

/**
 * @author ramprasath
 *
 */

@RestController
public class TransactionController extends MainController<Transaction>{

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private TemplateFunctionService templateFunctionService;

	/**
	 * To get all the Transaction 
	 * TODO
	 */
	@RequestMapping(value = "/transactions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TransactionsMessage listRole(){
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		entity = transactionService.getTransactions();
		if(entity != null){
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");

		}else{
			status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD, "No Record found");
		}
		return new TransactionsMessage(status, entity);
	}

	/**
	 * Method to create the Organization
	 * @param name
	 * @param description
	 * @return
	 */
	@RequestMapping(value = "/createtransaction", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public  TransactionMessage createtransaction(String command,String trax_uuid,String command_type, 
			String parameters,String sequence_num){
		LOGGER.info("Create Transaction :: " + command );		
		ResponseStatus status = new ResponseStatus(ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Transaction entity = new Transaction();
		try{
		entity.setCommand(command);
		entity.setTrax_uuid(trax_uuid);
		entity.setCommand_type(command_type);
		entity.setDate_time_created(dateFormat.format(date));
		entity.setParameters(parameters);
		entity.setSequence_num(sequence_num);
		entity.setDeleted(false);			
		super.create(entity);
		status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return new TransactionMessage(status, entity);
	}

	
	/**
	 * Method to edit the role by admin
	 * @param name
	 * @param description
	 * @return
	 */
	@RequestMapping(value = "/edittransaction", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public  TransactionMessage edittransaction(String id,String command,String trax_uuid,String command_type, 
			String parameters,String sequence_num){
		LOGGER.info("Edit role :: " + command );

		ResponseStatus status = new ResponseStatus(ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");
		Transaction entity = null;
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		if(id != null){
			Long orgId = Long.parseLong(id);
			try{
				entity = transactionService.find(orgId);
				entity.setCommand(command);
				entity.setTrax_uuid(trax_uuid);
				entity.setCommand_type(command_type);
				entity.setDate_time_created(dateFormat.format(date));
				entity.setParameters(parameters);
				entity.setSequence_num(sequence_num);
				entity.setDeleted(false);	

				status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
				super.create(entity);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new TransactionMessage(status, entity);
	}

	/**
	 * To get the Transaction info 
	 * TODO
	 */	
	@RequestMapping(value = "/gettransactionbyid", method = RequestMethod.GET)
	public @ResponseBody TransactionMessage getTransactionById(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long transId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Transaction transaction = null;
		try {
			transaction = transactionService.find(transId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionMessage(status, transaction);
	}
		
	/*
	 * 
	 * create Transaction Log
	 */

	@RequestMapping(value = "/insertTransactionLogById", method = RequestMethod.POST)
	public @ResponseBody TransactionMessage addTransactionLog(
			@FormDataParam("id") String id,
			@FormDataParam("result") String result,
			@FormDataParam("response_payload") String response_payload,
			@FormDataParam("run_no") String run_no) {

		Long transId = Long.parseLong(id);
		ResponseStatus status = null;

		Transaction entity = new Transaction();
		List<TransactionLog> transLog = new ArrayList<TransactionLog>();
		TransactionLog log = new TransactionLog();
		Transaction trans = null;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		try {
			trans = transactionService.find(transId);
			
			trans.setCommand(entity.getCommand());
			trans.setTrax_uuid(entity.getTrax_uuid());
			trans.setCommand_type(entity.getCommand_type());
			trans.setDate_time_created(entity.getDate_time_created());
			trans.setParameters(entity.getParameters());
			trans.setSequence_num(entity.getSequence_num());
			trans.setDeleted(entity.isDeleted());	

			log.setResult(result);
			log.setResponse_payload(response_payload);
			log.setRun_no(run_no);
			log.setDate_time_executed(dateFormat.format(date));
			log.setDeleted(false);

			transLog.add(log);

			trans.setTransactionLog(transLog);

			super.update(transId, trans);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionMessage(status, trans);
	}
	
	/*
	 * 
	 * create Transaction Log
	 */

	@RequestMapping(value = "/insertTransactionLifecycleById", method = RequestMethod.POST)
	public @ResponseBody TransactionMessage addTransactionLifecycle(
			@FormDataParam("id") String id,
			@FormDataParam("status") String transactionStatus
			) {		
		Long transId = Long.parseLong(id);
		ResponseStatus status = null;

		Transaction entity = new Transaction();
		List<TransactionLifecycle> transLifecycle = new ArrayList<TransactionLifecycle>();
		TransactionLifecycle lifecycle = new TransactionLifecycle();
		Transaction trans = null;
		try {
			trans = transactionService.find(transId);
			
			trans.setCommand(entity.getCommand());
			trans.setTrax_uuid(entity.getTrax_uuid());
			trans.setCommand_type(entity.getCommand_type());
			trans.setDate_time_created(entity.getDate_time_created());
			trans.setParameters(entity.getParameters());
			trans.setSequence_num(entity.getSequence_num());
			trans.setDeleted(entity.isDeleted());	

			lifecycle.setStatus(transactionStatus);
			lifecycle.setDeleted(false);

			transLifecycle.add(lifecycle);

			trans.setTransactionLifecycle(transLifecycle);
			
			super.update(transId, trans);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionMessage(status, trans);
	}

	//Create function to transaction relation
		@RequestMapping(value = "/functionToTransaction", method = RequestMethod.POST, produces = "application/json")
		@ResponseBody
		public  TransactionMessage functionToTransaction(String id,String command,String trax_uuid,String command_type, 
				String parameters,String sequence_num,String depid){
			//LOGGER.info("Create role :: " + function );		
			ResponseStatus status = new ResponseStatus(ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");
			Transaction entity = new Transaction();
			TemplateFunction templateFunction=null;
			Long functId = Long.parseLong(id);

			List<TemplateFunction> functList = new ArrayList<TemplateFunction>();

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();

			try{
				templateFunction=templateFunctionService.find(functId);
				
				functList.add(templateFunction);

				entity.setTemplateFunction(functList);
				entity.setCommand(command);
				entity.setTrax_uuid(trax_uuid);
				entity.setCommand_type(command_type);
				entity.setDate_time_created(dateFormat.format(date));
				entity.setParameters(parameters);
				entity.setSequence_num(sequence_num);
				entity.setDeleted(false);				
				super.create(entity);					
				
				try{
					Long transId=entity.getId();				
					transactionToDeployment(transId.toString(),depid);
				}catch(Exception ex){
					ex.printStackTrace();
				}
				try{
					Long transId=entity.getId();
					addTransactionLifecycle(transId.toString(),"created");
				}catch(Exception ex){
					ex.printStackTrace();
				}				
				status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
			}catch (Exception e) {
				e.printStackTrace();
			}
			return new TransactionMessage(status, entity);
		}
		
		//Create transaction to deployment relation
		@RequestMapping(value="/transactionToDeployment",method= RequestMethod.POST, produces="application/json")
		@ResponseBody
		public TransactionsMessage transactionToDeployment(String transactionid,String deploymentid)
		{
			ResponseStatus status = new ResponseStatus(ResponseStatusCode.BAD_REQUEST, "BAD_REQUEST");
			Long transId=Long.parseLong(transactionid);
			Long deployId=Long.parseLong(deploymentid);
			Iterable<Map<String, Object>> entity = null;
			try{
				entity=transactionService.createRelationWithDeployment(transId, deployId);
				status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
			//	super.update(transId, entity);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return new TransactionsMessage(status,entity);
		}
		
	@RequestMapping(value = "/deletetransaction", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public TransactionMessage delete(String id) {
		ResponseStatus status = null;
		Long transactionId = Long.parseLong(id);
		Transaction transaction = null;
		try {
			transaction = transactionService.find(transactionId);
			transaction.setDeleted(true);			
			super.create(transaction);
			super.delete(transactionId);				
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionMessage(status, transaction);
	}

	@Override
	public MainService<Transaction> getService() {
		return transactionService;
	}	
}
