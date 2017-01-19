/**
 * 
 */
package nuclei.controller;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import nuclei.domain.CatalogueBlueprint;
import nuclei.domain.TaskIaasFunctionRelation;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.response.CatalogueBlueprintMessage;
import nuclei.response.CatalogueBlueprintsMessage;
import nuclei.response.TaskIaasFunctionRelationMessage;
import nuclei.response.TaskIaasFunctionRelationsMessage;
import nuclei.service.CatalogueBlueprintService;
import nuclei.service.MainService;
import nuclei.service.TaskIaasFunctionRelationService;

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
public class TaskIaasFunctionRelationController extends	MainController<TaskIaasFunctionRelation> {

	@Autowired
	private TaskIaasFunctionRelationService taskIaasFunctionRelationService;

	@RequestMapping(value = "/taskIaasFunctionRelations", method = RequestMethod.GET)
	public @ResponseBody TaskIaasFunctionRelationsMessage list(
			final HttpServletResponse response) {

		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity = taskIaasFunctionRelationService.getTaskIaasFunctionRelation();
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
		return new TaskIaasFunctionRelationsMessage(status, entity);
	}

	@RequestMapping(value = "/insertTaskIaasFunctionRelation", method = RequestMethod.POST)
	public @ResponseBody TaskIaasFunctionRelationMessage create(
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
		TaskIaasFunctionRelation entity = new TaskIaasFunctionRelation();
		try {
			entity.setFunctionId(functId);
			entity.setTaskId(tasksId);
			entity.setValue(value);
			entity.setStep(step);			
			entity.setX_axis(x_axis);
			entity.setY_axis(y_axis);
			entity.setIsDeleted("0");	

			response.setHeader("Cache-Control", "no-cache");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskIaasFunctionRelationMessage(status, entity);
	}

	@RequestMapping(value = "/updateTaskIaasFunctionRelation", method = RequestMethod.POST)
	public @ResponseBody TaskIaasFunctionRelationsMessage update(
			@FormDataParam("id") String id,
			@FormDataParam("taskId") String taskId,
			@FormDataParam("functionId") String functionId,			
			@FormDataParam("value") String value,	
			@FormDataParam("step") String step,			
			@FormDataParam("x_axis") String x_axis,
			@FormDataParam("y_axis") String y_axis,
			final HttpServletResponse response, Model model) {
		System.out.println("id "+id);
		System.out.println("taskId "+taskId);
		System.out.println("functionId "+functionId);
		
		ResponseStatus status = null;
		Long catalogueBlueprintId = Long.parseLong(id);		
		Iterable<Map<String, Object>> entity = null;
		try {
			//entity = taskIaasFunctionRelationService.find(catalogueBlueprintId);
			entity = taskIaasFunctionRelationService.updateValue(catalogueBlueprintId,taskId,functionId,value,step,x_axis,y_axis);
						
			System.out.println("Entity : "+entity);
			/*Iterator it= entity.iterator();
			
			TaskIaasFunctionRelation entity1 =null;*/
			/*entity.setFunctionId(functId);
			entity.setTaskId(tasksId);
			entity.setValue(value);
			entity.setStep(step);			
			entity.setX_axis(x_axis);
			entity.setY_axis(y_axis);
			entity.setIsDeleted("0");	
*/
		//	super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskIaasFunctionRelationsMessage(status, entity);
	}

	@RequestMapping(value = "/getTaskFunctionRelationById", method = RequestMethod.GET)
	public @ResponseBody TaskIaasFunctionRelationsMessage find(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long TaskIaasFunctionId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = taskIaasFunctionRelationService.getTaskIaasFunctionRelationById(TaskIaasFunctionId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskIaasFunctionRelationsMessage(status, entity);
	}

	@RequestMapping(value = "/deleteTaskIaasFunctionRelationById", method = RequestMethod.POST)
	public @ResponseBody TaskIaasFunctionRelationsMessage deleteTaskIaasFunctionRelation(
			@FormDataParam("functionId") String functionId, @FormDataParam("taskId") String taskId,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long functId = Long.parseLong(functionId);
		Long taxId = Long.parseLong(taskId);
		Iterable<Map<String, Object>> taskFunction = null;
		try {
			taskFunction = taskIaasFunctionRelationService.DeleteRelationship(functId,taxId);
			response.setHeader("Cache-Control", "no-cache");	
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskIaasFunctionRelationsMessage(status, taskFunction);
	}

	@RequestMapping(value = "/getTaskIaasFunctionRelationById", method = RequestMethod.GET)
	public @ResponseBody TaskIaasFunctionRelationsMessage findRelationById(
			@FormDataParam("id") Long id,@FormDataParam("functionid") Long functionid, final HttpServletResponse response) {
		ResponseStatus status = null;		
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = taskIaasFunctionRelationService.getRelation( id,functionid);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskIaasFunctionRelationsMessage(status, entity);
	}

	// Update step value
	@RequestMapping(value = "/updateTaskIaasFunctionRelationStep", method = RequestMethod.POST)
	public @ResponseBody TaskIaasFunctionRelationsMessage deleteTest(
			@FormDataParam("functionId") long functionId, @FormDataParam("taskId") long taskId,
			@FormDataParam("step") String step,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = taskIaasFunctionRelationService.updateStep(functionId,taskId, step);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskIaasFunctionRelationsMessage(status, entity);
	}

	// Update x-axis & y-axis
	@RequestMapping(value = "/updateTaskIaasFunctionRelationPosition", method = RequestMethod.POST)
	public @ResponseBody TaskIaasFunctionRelationsMessage updateFunctionPosition(
			@FormDataParam("functionId") long functionId,
			@FormDataParam("x_axis") String x_axis,
			@FormDataParam("y_axis") String y_axis,
			final HttpServletResponse response, Model model) {

		ResponseStatus status = null;
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = taskIaasFunctionRelationService.updatePosition(functionId,x_axis, y_axis);
			System.out.println("Entity : -->"+entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskIaasFunctionRelationsMessage(status, entity);
	}
	
	@Override
	public MainService<TaskIaasFunctionRelation> getService() {
		return taskIaasFunctionRelationService;
	}
}
