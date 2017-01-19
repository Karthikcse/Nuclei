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

import nuclei.core.TransactionListGenerator;
import nuclei.core.YamlGenerator;
import nuclei.domain.IaaSTemplate;
import nuclei.domain.TemplateFunction;
import nuclei.response.BlueprintMessage;
import nuclei.response.BlueprintsMessage;
import nuclei.response.IaasTemplateMessage;
import nuclei.response.IaasTemplatesMessage;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.service.IaaSTemplateService;
import nuclei.service.MainService;

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
public class IaaSTemplateController extends MainController<IaaSTemplate> {

	@Autowired
	private IaaSTemplateService templateService;

	@Autowired
	private TransactionListGenerator sequenceCLIGenerator;

	@Autowired
	private YamlGenerator yaml;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody IaasTemplateMessage greetingForm(
			final HttpServletResponse response) {
		ResponseStatus status = null;
		status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		return new IaasTemplateMessage(status, null);
	}

	@RequestMapping(value = "/templates", method = RequestMethod.GET)
	public @ResponseBody IaasTemplatesMessage list(
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity = templateService.getIaaSTemplateValues();
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
		return new IaasTemplatesMessage(status, entity);
	}

	/*
	 * insert IaasTemplate values
	 */
	@RequestMapping(value = "/insertTemplate", method = RequestMethod.POST)
	public @ResponseBody IaasTemplateMessage create(
			@FormDataParam("IaasName") String IaasName,
			@FormDataParam("onIaas") String onIaas,
			@FormDataParam("script") String script,
			final HttpServletResponse response, Model model) {

		ResponseStatus status = null;
		IaaSTemplate entity = new IaaSTemplate();

		try {
			entity.setIaasName(IaasName);
			entity.setScript(script);
			entity.setOnIaas(onIaas);
			entity.setIsDeleted("0");
			response.setHeader("Cache-Control", "no-cache");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new IaasTemplateMessage(status, entity);
	}

	/*
	 * 
	 * Update
	 */
	@RequestMapping(value = "/updateTemplate", method = RequestMethod.POST)
	public @ResponseBody IaasTemplateMessage update(
			@FormDataParam("id") String id,
			@FormDataParam("IaasName") String IaasName,
			@FormDataParam("script") String script,
			@FormDataParam("onIaas") String onIaas,
			final HttpServletResponse response, Model model) {

		Long iaasId = Long.parseLong(id);
		ResponseStatus status = null;
		IaaSTemplate entity = null;
		try {
			entity = templateService.find(iaasId);
			entity.setIaasName(IaasName);
			entity.setOnIaas(onIaas);
			entity.setScript(script);
			entity.setIsDeleted("0");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new IaasTemplateMessage(status, entity);
	}

	/*
	 * 
	 * create functions
	 */

	@RequestMapping(value = "/insertFunctionById", method = RequestMethod.POST)
	public @ResponseBody IaasTemplateMessage addFunction(
			@FormDataParam("id") String id,
			@FormDataParam("functionName") String functionName,
			@FormDataParam("description") String description,
			final HttpServletResponse response, Model model) {

		Long iaasId = Long.parseLong(id);
		ResponseStatus status = null;

		IaaSTemplate entity = new IaaSTemplate();
		List<TemplateFunction> templateFunction = new ArrayList<TemplateFunction>();
		TemplateFunction functions = new TemplateFunction();
		IaaSTemplate iaas = null;
		try {
			iaas = templateService.find(iaasId);
			iaas.setIaasName(entity.getIaasName());
			iaas.setScript(entity.getScript());
			iaas.setOnIaas(entity.getOnIaas());
			iaas.setIsDeleted(entity.getIsDeleted());

			functions.setFunctionName(functionName);
			functions.setDescription(description);
			functions.setIsDeleted("0");

			templateFunction.add(functions);

			iaas.setTemplateFunction(templateFunction);

			setHeaders(response);
			super.update(iaasId, iaas);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new IaasTemplateMessage(status, iaas);
	}

	@RequestMapping(value = "/getTemplateById", method = RequestMethod.GET)
	public @ResponseBody IaasTemplatesMessage find(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		Long iaasId = Long.parseLong(id);
		ResponseStatus status = null;
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = templateService.getIaaSTemplateById(iaasId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new IaasTemplatesMessage(status, entity);
	}

	@RequestMapping(value = "/deleteTemplate", method = RequestMethod.POST)
	public @ResponseBody IaasTemplateMessage delete(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		Long templateId = Long.parseLong(id);
		ResponseStatus status = null;
		IaaSTemplate iaaSTemplate = null;
		try {
			iaaSTemplate = templateService.find(templateId);
			iaaSTemplate.setIsDeleted("1");
			setHeaders(response);
			super.create(iaaSTemplate);	
			super.delete(templateId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new IaasTemplateMessage(status, iaaSTemplate);
	}

	//Sequence generation
	@RequestMapping(value = "/generatecli", method = RequestMethod.GET)
	public @ResponseBody BlueprintsMessage generateSequenceCLI(@FormDataParam("id") String id,@FormDataParam("depid") String depid) {
		ResponseStatus status = null;
		Long templateId = Long.parseLong(id);
		//Long DeployId= Long.parseLong(depid);		
		Iterable<Map<String, Object>> entity = null;

		try {
			// entity= templateService.getIaaSTemplateSequence(templateId);		  
			sequenceCLIGenerator.generateCLI(templateId,depid);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return new BlueprintsMessage(status, entity);
	}

	//YAML to Json Conversion
	@RequestMapping(value="/yamlconversion", method= RequestMethod.GET)
	public @ResponseBody String yamlToJson(final HttpServletResponse response) throws IOException{	
		String yamlPath=yaml.FILEPATH;		
		BufferedReader br = new BufferedReader(new FileReader(yamlPath));
		String resultString="";
		String resultJson="";
		Yaml yaml= new Yaml();		
		try {			
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			resultString=sb.toString();			
			Map<String,Object> map= (Map<String, Object>) yaml.load(resultString);
			JSONObject jsonObject=new JSONObject(map);	
			resultJson=jsonObject.toString();
//System.out.println("Result  :: -->>");
			return resultJson;	
		} catch(Exception ex){
//System.out.println(":: Exception..!");
			ex.printStackTrace();			
		}finally {
			br.close();			
		}
		return "Error..!";
	}

	@Override
	public MainService<IaaSTemplate> getService() {
		return templateService;
	}

}
