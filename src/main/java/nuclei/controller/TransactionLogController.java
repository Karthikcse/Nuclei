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
import nuclei.domain.TransactionLog;
import nuclei.response.BlueprintMessage;
import nuclei.response.BlueprintsMessage;
import nuclei.response.IaasTemplateMessage;
import nuclei.response.IaasTemplatesMessage;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.response.TransactionLogMessage;
import nuclei.response.TransactionLogsMessage;
import nuclei.service.IaaSTemplateService;
import nuclei.service.MainService;
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
public class TransactionLogController extends MainController<TransactionLog> {

	@Autowired
	private TransactionLogService transactionLogService;	

	@RequestMapping(value = "/transactionLogs", method = RequestMethod.GET)
	public @ResponseBody TransactionLogsMessage list(
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity = transactionLogService.getTransactionLogValues();
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
		return new TransactionLogsMessage(status, entity);
	}

	/*
	 * insert IaasTemplate values
	 */	
	@RequestMapping(value = "/insertTransactionLog", method = RequestMethod.POST)
	public @ResponseBody TransactionLogMessage create(
			@FormDataParam("result") String result,
			@FormDataParam("response_payload") String response_payload,
			@FormDataParam("run_no") String run_no,
			@FormDataParam("date_time_executed") String date_time_executed,
			final HttpServletResponse response, Model model) {

		ResponseStatus status = null;
		TransactionLog entity = new TransactionLog();

		try {
			entity.setResult(result);
			entity.setResponse_payload(response_payload);
			entity.setRun_no(run_no);
			entity.setDate_time_executed(date_time_executed);
			entity.setDeleted(false);
			response.setHeader("Cache-Control", "no-cache");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionLogMessage(status, entity);
	}

	/*
	 * 
	 * Update
	 */
	@RequestMapping(value = "/updateTransactionLog", method = RequestMethod.POST)
	public @ResponseBody TransactionLogMessage update(
			@FormDataParam("id") String id,
			@FormDataParam("result") String result,
			@FormDataParam("response_payload") String response_payload,
			@FormDataParam("run_no") String run_no,
			@FormDataParam("date_time_executed") String date_time_executed,
			final HttpServletResponse response, Model model) {

		Long iaasId = Long.parseLong(id);
		ResponseStatus status = null;
		TransactionLog entity = null;
		try {
			entity = transactionLogService.find(iaasId);
			entity.setResult(result);
			entity.setResponse_payload(response_payload);
			entity.setRun_no(run_no);
			entity.setDate_time_executed(date_time_executed);
			entity.setDeleted(false);
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionLogMessage(status, entity);
	}
	
	@RequestMapping(value = "/getTransactionLogById", method = RequestMethod.GET)
	public @ResponseBody TransactionLogMessage find(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		Long iaasId = Long.parseLong(id);
		ResponseStatus status = null;
		response.setHeader("Cache-Control", "no-cache");
		TransactionLog entity = null;
		try {
			entity = transactionLogService.find(iaasId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionLogMessage(status, entity);
	}

	@RequestMapping(value = "/deleteTransactionLog", method = RequestMethod.POST)
	public @ResponseBody TransactionLogMessage delete(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		Long templateId = Long.parseLong(id);
		ResponseStatus status = null;
		TransactionLog iaaSTemplate = null;
		try {
			iaaSTemplate = transactionLogService.find(templateId);
			iaaSTemplate.setDeleted(true);
			setHeaders(response);
			super.create(iaaSTemplate);	
			super.delete(templateId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TransactionLogMessage(status, iaaSTemplate);
	}

	@Override
	public MainService<TransactionLog> getService() {
		return transactionLogService;
	}

}
