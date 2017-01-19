/**
 * 
 */
package nuclei.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nuclei.domain.TaskAttributes;
import nuclei.domain.TaskFunction;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.response.TaskFunctionMessage;
import nuclei.response.TaskFunctionsMessage;
import nuclei.service.TaskFunctionService;
import nuclei.service.MainService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
public class TaskFunctionController extends MainController<TaskFunction> {

	@Autowired
	private TaskFunctionService taskFunctionService;

	@RequestMapping(value = "/taskFunctions", method = RequestMethod.GET)
	public @ResponseBody TaskFunctionsMessage list(
			final HttpServletResponse response) {

		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity = taskFunctionService.getTaskFunctions();
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
		return new TaskFunctionsMessage(status, entity);
	}

	@RequestMapping(value = "/insertTaskFunction", method = RequestMethod.POST)
	public @ResponseBody TaskFunctionMessage create(
			@FormDataParam("functionName") String functionName,
			@FormDataParam("description") String description,
			@FormDataParam("x_axis") String x_axis,
			@FormDataParam("y_axis") String y_axis,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		TaskFunction entity = new TaskFunction();
		try {
			entity.setFunctionName(functionName);
			entity.setDescription(description);
			entity.setX_axis(x_axis);
			entity.setY_axis(y_axis);
			entity.setIsDeleted("0");

			response.setHeader("Cache-Control", "no-cache");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskFunctionMessage(status, entity);
	}

	@RequestMapping(value = "/updateTaskFunction", method = RequestMethod.POST)
	public @ResponseBody TaskFunctionMessage update(
			@FormDataParam("id") String id,
			@FormDataParam("functionName") String functionName,
			@FormDataParam("description") String description,			
			final HttpServletResponse response, Model model) {
		ResponseStatus status = null;
		Long functionId = Long.parseLong(id);

		TaskFunction entity = null;
		try {
			entity = taskFunctionService.find(functionId);
			entity.setFunctionName(functionName);
			entity.setDescription(description);			
			entity.setIsDeleted("0");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskFunctionMessage(status, entity);
	}

	@RequestMapping(value = "/updateTaskFunctionStep", method = RequestMethod.POST)
	public @ResponseBody TaskFunctionMessage updateStep(
			@FormDataParam("id") String id, @FormDataParam("step") String step,
			final HttpServletResponse response, Model model) {
		ResponseStatus status = null;
		Long functionId = Long.parseLong(id);
		TaskFunction entity = null;
		TaskFunction newEntity = null;
		try {
			entity = taskFunctionService.find(functionId);
			newEntity = taskFunctionService.find(functionId);
			newEntity.setFunctionName(entity.getFunctionName());
			newEntity.setDescription(entity.getDescription());
			newEntity.setIsDeleted(entity.getIsDeleted());
			newEntity.setTempFunctId(entity.getTempFunctId());
			newEntity.setX_axis(entity.getX_axis());
			newEntity.setY_axis(entity.getY_axis());
			newEntity.setStep(step);
			super.create(newEntity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskFunctionMessage(status, newEntity);
	}

	@RequestMapping(value = "/addTaskAttributesById", method = RequestMethod.POST)
	public @ResponseBody TaskFunctionMessage addAttribute(
			HttpServletRequest request, Model model) {

		ResponseStatus status = null;
		List<TaskAttributes> taskAttribute = new ArrayList<TaskAttributes>();
		TaskAttributes attribute = null;
		TaskFunction function = null;
		try {
			System.out.println("request.getQueryString() :"
					+ request.getQueryString());
			String urlValues = URLDecoder.decode(request.getQueryString(),
					"UTF-8");
			System.out.println("urlValues-->" + urlValues);
			String[] responseValues = urlValues.split("=");
			JSONObject blueprintObj = (JSONObject) new JSONParser()
					.parse(responseValues[1]);
			Long functionId = (Long) blueprintObj.get("taskFunctionId");
			function = taskFunctionService.find(functionId);
			JSONArray paramValues = (JSONArray) blueprintObj
					.get("attributeList");

			for (int i = 0; i < paramValues.size(); i++) {
				attribute = new TaskAttributes();
				JSONObject paramObj = (JSONObject) paramValues.get(i);
				JSONObject paramObjSub = (JSONObject) paramObj
						.get("Attributes");
				String name = (String) paramObjSub.get("name");
				String value = (String) paramObjSub.get("value");
				System.out.println("name" + name + "value" + value);
				attribute.setName(name);
				attribute.setValue(value);
				attribute.setIsDeleted("0");
				taskAttribute.add(attribute);

				System.out.println("taskAttribute-->" + taskAttribute);

			}
			function.setAttributes(taskAttribute);

			System.out.println("function-->" + function.getAttributes());
			super.update(functionId, function);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskFunctionMessage(status, function);
	}

	@RequestMapping(value = "/getTaskFunctionById", method = RequestMethod.GET)
	public @ResponseBody TaskFunctionsMessage find(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long functionId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = taskFunctionService.getTaskFunctionById(functionId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskFunctionsMessage(status, entity);
	}

	//Update x-axis & y-axis
		@RequestMapping(value = "/updateFunctionPosition", method = RequestMethod.POST)
		public @ResponseBody TaskFunctionMessage updateFunctionPosition(@FormDataParam("id") String id,
				@FormDataParam("x_axis") String x_axis,
				@FormDataParam("y_axis") String y_axis,
				final HttpServletResponse response, Model model) {
			ResponseStatus status = null;
			Long functionId = Long.parseLong(id);
			TaskFunction entity = null;
			TaskFunction newEntity = null;
			try {
				entity = taskFunctionService.find(functionId);
				newEntity = taskFunctionService.find(functionId);			
				
				newEntity.setFunctionName(entity.getFunctionName());
				newEntity.setDescription(entity.getDescription());
				newEntity.setTempFunctId(entity.getTempFunctId());
				newEntity.setStep(entity.getStep());			
				newEntity.setIsDeleted(entity.getIsDeleted());				
				newEntity.setX_axis(x_axis);
				newEntity.setY_axis(y_axis);
						
				super.create(newEntity);
				status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new TaskFunctionMessage(status, newEntity);
		}
		
	@RequestMapping(value = "/deleteTaskFunctionById", method = RequestMethod.POST)
	public @ResponseBody TaskFunctionMessage delete(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long functionId = Long.parseLong(id);
		TaskFunction blueprintFunction = null;
		try {
			blueprintFunction = taskFunctionService.find(functionId);
			blueprintFunction.setIsDeleted("1");
			setHeaders(response);
			super.create(blueprintFunction);
			super.delete(functionId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskFunctionMessage(status, blueprintFunction);
	}

	@Override
	public MainService<TaskFunction> getService() {
		return taskFunctionService;
	}
}
