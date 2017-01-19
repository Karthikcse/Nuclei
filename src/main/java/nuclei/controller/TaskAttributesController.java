/**
 * 
 */
package nuclei.controller;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nuclei.domain.TaskAttributes;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.response.TaskAttributeMessage;
import nuclei.response.TaskAttributesMessage;
import nuclei.service.TaskAttributesService;
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
public class TaskAttributesController extends MainController<TaskAttributes> {

	@Autowired
	private TaskAttributesService taskAttributesService;

	@RequestMapping(value = "/taskAttributes", method = RequestMethod.GET)
	public @ResponseBody TaskAttributesMessage list(
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity = taskAttributesService.getTaskAttributes();
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
		return new TaskAttributesMessage(status, entity);
	}

	@RequestMapping(value = "/createTaskAttribute", method = RequestMethod.POST)
	public @ResponseBody TaskAttributeMessage create(
			@FormDataParam("name") String name,
			@FormDataParam("value") String value,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		TaskAttributes entity = new TaskAttributes();
		try {
			entity.setName(name);
			entity.setValue(value);
			entity.setIsDeleted("0");

			response.setHeader("Cache-Control", "no-cache");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskAttributeMessage(status, entity);
	}

	@RequestMapping(value = "/updateTaskAttribute", method = RequestMethod.POST)
	public @ResponseBody TaskAttributeMessage updateAttribute(
			HttpServletRequest request, Model model) {

		ResponseStatus status = null;
		TaskAttributes attribute = null;
		try {
			System.out.println("request.getQueryString() :"
					+ request.getQueryString());
			String urlValues = URLDecoder.decode(request.getQueryString(),
					"UTF-8");
			System.out.println("urlValues-->" + urlValues);
			String[] responseValues = urlValues.split("=");
			JSONObject attributeObj = (JSONObject) new JSONParser()
					.parse(responseValues[1]);
			JSONArray paramValues = (JSONArray) attributeObj
					.get("attributeList");
			for (int i = 0; i < paramValues.size(); i++) {
				JSONObject paramObj = (JSONObject) paramValues.get(i);
				Long attributeId = (Long) paramObj.get("id");
				attribute = taskAttributesService.find(attributeId);

				JSONObject paramObjSub = (JSONObject) paramObj
						.get("Attributes");

				String name = (String) paramObjSub.get("name");
				String value = (String) paramObjSub.get("value");
				System.out.println("name" + name + "value" + value);
				attribute.setName(name);
				attribute.setValue(value);
				attribute.setIsDeleted("0");
				super.create(attribute);
			}

			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskAttributeMessage(status, attribute);
	}

	@RequestMapping(value = "/getTaskAttributeById", method = RequestMethod.GET)
	public @ResponseBody TaskAttributesMessage find(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long attributeId = Long.parseLong(id);
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = taskAttributesService.getTaskAttributesById(attributeId);
			response.setHeader("Cache-Control", "no-cache");
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskAttributesMessage(status, entity);
	}

	@RequestMapping(value = "/deleteTaskAttributeById", method = RequestMethod.POST)
	public @ResponseBody TaskAttributeMessage delete(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long attributesId = Long.parseLong(id);
		TaskAttributes blueprintAttributes = null;
		try {
			blueprintAttributes = taskAttributesService.find(attributesId);
			blueprintAttributes.setIsDeleted("1");
			setHeaders(response);
			super.create(blueprintAttributes);
			super.delete(attributesId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskAttributeMessage(status, blueprintAttributes);
	}

	@Override
	public MainService<TaskAttributes> getService() {
		return taskAttributesService;
	}

}
