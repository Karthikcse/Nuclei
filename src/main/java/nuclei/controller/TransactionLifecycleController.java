/**
 * 
 */
package nuclei.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import nuclei.domain.IaaSTemplate;
import nuclei.domain.TemplateFunction;
import nuclei.domain.TransactionLifecycle;
import nuclei.domain.TransactionLog;
import nuclei.response.BlueprintMessage;
import nuclei.response.BlueprintsMessage;
import nuclei.response.IaasTemplateMessage;
import nuclei.response.IaasTemplatesMessage;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.response.TransactionLifecycleMessage;
import nuclei.response.TransactionLifecyclesMessage;
import nuclei.response.TransactionLogMessage;
import nuclei.response.TransactionLogsMessage;
import nuclei.service.IaaSTemplateService;
import nuclei.service.MainService;
import nuclei.service.TransactionLifecycleService;
import nuclei.service.TransactionLogService;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yaml.snakeyaml.Yaml;

import com.sun.jersey.multipart.FormDataParam;

/**
 * @author Karthikeyan
 *
 */

// @RestController
@Controller
public class TransactionLifecycleController extends MainController<TransactionLifecycle> {

	@Autowired
	private TransactionLifecycleService transactionLifecycleService;	

	@RequestMapping(value = "/transactionLifecycles", method = RequestMethod.GET)
	public @ResponseBody TransactionLifecyclesMessage list(
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity = transactionLifecycleService.getTransactionLifecycleValues();
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
		return new TransactionLifecyclesMessage(status, entity);
	}

	/*
	 * insert TransactionLifecycle values
	 */	
	@RequestMapping(value = "/insertTransactionLifecycle", method = RequestMethod.POST)
	public @ResponseBody TransactionLifecycleMessage create(
			@FormDataParam("status") String transactionStatus,
			final HttpServletResponse response, Model model) {

		ResponseStatus status = null;
		TransactionLifecycle entity = new TransactionLifecycle();

		try {
			entity.setStatus(transactionStatus);
			entity.setDeleted(false);
			response.setHeader("Cache-Control", "no-cache");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionLifecycleMessage(status, entity);
	}
	/*
	 * 
	 * Update
	 */
	@RequestMapping(value = "/updateTransactionLifecycle", method = RequestMethod.POST)
	public @ResponseBody TransactionLifecycleMessage update(
			@FormDataParam("id") String id,
			@FormDataParam("status") String transactionStatus) {

		Long iaasId = Long.parseLong(id);
		ResponseStatus status = null;
		TransactionLifecycle entity = null;
		try {
			entity = transactionLifecycleService.find(iaasId);
			entity.setStatus(transactionStatus);
			entity.setDeleted(false);
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionLifecycleMessage(status, entity);
	}
	
	@RequestMapping(value = "/getTransactionLifecycleById", method = RequestMethod.GET)
	public @ResponseBody TransactionLifecycleMessage find(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		Long iaasId = Long.parseLong(id);
		ResponseStatus status = null;
		response.setHeader("Cache-Control", "no-cache");
		TransactionLifecycle entity = null;
		try {
			entity = transactionLifecycleService.find(iaasId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionLifecycleMessage(status, entity);
	}

	@RequestMapping(value = "/deleteTransactionLifecycle", method = RequestMethod.POST)
	public @ResponseBody TransactionLifecycleMessage delete(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		Long templateId = Long.parseLong(id);
		ResponseStatus status = null;
		TransactionLifecycle iaaSTemplate = null;
		try {
			iaaSTemplate = transactionLifecycleService.find(templateId);
			iaaSTemplate.setDeleted(true);
			setHeaders(response);
			super.create(iaaSTemplate);	
			super.delete(templateId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionLifecycleMessage(status, iaaSTemplate);
	}

	@Override
	public MainService<TransactionLifecycle> getService() {
		return transactionLifecycleService;
	}

}
