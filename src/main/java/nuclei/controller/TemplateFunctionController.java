/**
 * 
 */
package nuclei.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import nuclei.domain.Blueprint;
import nuclei.domain.CatalogueBlueprint;
import nuclei.domain.ScTaxonomy;
import nuclei.domain.TaskIaasFunctionRelation;
import nuclei.domain.Tasks;
import nuclei.domain.TemplateFunction;
import nuclei.response.BlueprintMessage;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.response.TemplateFunctionMessage;
import nuclei.response.TemplateFunctionsMessage;
import nuclei.service.TasksService;
import nuclei.service.TemplateFunctionService;
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
public class TemplateFunctionController extends
		MainController<TemplateFunction> {

	@Autowired
	private TemplateFunctionService templateFunctionService;

	@Autowired
	private TasksService tasksService;
	
	@RequestMapping(value = "/functions", method = RequestMethod.GET)
	public @ResponseBody TemplateFunctionsMessage list(
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity = templateFunctionService.getTemplateFunctionsValues();
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
		return new TemplateFunctionsMessage(status, entity);
	}

	@RequestMapping(value = "/insertFunction", method = RequestMethod.POST)
	public @ResponseBody TemplateFunctionMessage create(
			@FormDataParam("function") String functionName,
			@FormDataParam("description") String description,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		TemplateFunction entity = new TemplateFunction();
		try {
			entity.setFunctionName(functionName);
			entity.setDescription(description);
			entity.setIsDeleted("0");

			response.setHeader("Cache-Control", "no-cache");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TemplateFunctionMessage(status, entity);
	}

	@RequestMapping(value = "/updateFunction", method = RequestMethod.POST)
	public @ResponseBody TemplateFunctionMessage update(
			@FormDataParam("id") String id,
			@FormDataParam("functionName") String functionName,
			@FormDataParam("description") String description,
			final HttpServletResponse response, Model model) {
		ResponseStatus status = null;
		Long functionId = Long.parseLong(id);
		TemplateFunction entity = null;
		try {
			entity = templateFunctionService.find(functionId);
			entity.setFunctionName(functionName);
			entity.setDescription(description);
			entity.setIsDeleted("0");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TemplateFunctionMessage(status, entity);
	}
	
	@RequestMapping(value = "/getFunctionById", method = RequestMethod.GET)
	public @ResponseBody TemplateFunctionsMessage find(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long functionId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = templateFunctionService.getFunctionById(functionId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TemplateFunctionsMessage(status, entity);
	}

	@RequestMapping(value = "/deleteFunction", method = RequestMethod.POST)
	public @ResponseBody TemplateFunctionMessage delete(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long functionId = Long.parseLong(id);
		TemplateFunction templateFunction = null;
		try {
			templateFunction = templateFunctionService.find(functionId);
			templateFunction.setIsDeleted("1");
			setHeaders(response);
			super.create(templateFunction);
			super.delete(functionId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TemplateFunctionMessage(status, templateFunction);
	}
	
	//Create relation b/w Task to Function 
	@RequestMapping(value = "/createtaskfunctionrelation", method = RequestMethod.POST)
	public @ResponseBody TemplateFunctionMessage taskFunctionRelation(final Model model,
			@FormDataParam("taskId") String taskId,
			@FormDataParam("functionId") String functionId,			
			@FormDataParam("value") String value,	
			@FormDataParam("step") String step,			
			@FormDataParam("x_axis") String x_axis,
			@FormDataParam("y_axis") String y_axis,
			final HttpServletResponse response) {

		ResponseStatus status = null;
		Long functId = Long.parseLong(functionId);
		Long tasksId = Long.parseLong(taskId);	
		Tasks tasks = tasksService.find(tasksId);
		TemplateFunction function = templateFunctionService.find(functId);

		try{ 			
			TaskIaasFunctionRelation taskFunction =new TaskIaasFunctionRelation();		
		 	 
        	  	taskFunction.setFunctionRelation(function);
        	  	taskFunction.setTaskRelation(tasks);
        	  	
        	  	taskFunction.setFunctionId(functId);
        	  	taskFunction.setTaskId(tasksId);
        	  	taskFunction.setValue(value);
  				taskFunction.setStep(step);			
  				taskFunction.setX_axis(x_axis);
  				taskFunction.setY_axis(y_axis);
  				taskFunction.setIsDeleted("0");	
  				
  				function.addRelations(taskFunction);	      
          	
		super.create(function);
		
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TemplateFunctionMessage(status, function);
	}
	
	@Override
	public MainService<TemplateFunction> getService() {
		return templateFunctionService;
	}
}
