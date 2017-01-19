/**
 * 
 */
package nuclei.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import nuclei.domain.TaskFunction;
import nuclei.domain.Tasks;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.response.TaskMessage;
import nuclei.response.TasksMessage;
import nuclei.service.TaskFunctionService;
import nuclei.service.TasksService;
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
public class TasksController extends MainController<Tasks> {

	@Autowired
	private TasksService tasksService;

	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public @ResponseBody TasksMessage list(final HttpServletResponse response) {
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity = tasksService.getTasksValues();
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
		return new TasksMessage(status, entity);

	}

	@RequestMapping(value = "/insertTask", method = RequestMethod.POST)
	public @ResponseBody TaskMessage create(@FormDataParam("task") String task,
			@FormDataParam("description") String description,
			@FormDataParam("step") String step,
			@FormDataParam("blueprintStep") String blueprintStep,
			@FormDataParam("x_axis") String x_axis,
			@FormDataParam("y_axis") String y_axis,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Tasks entity = new Tasks();
		try {
			entity.setTask(task);
			entity.setDescription(description);
			entity.setStep(step);
			entity.setBlueprintStep(blueprintStep);
			entity.setX_axis(x_axis);
			entity.setY_axis(y_axis);
			entity.setIsDeleted("0");
			response.setHeader("Cache-Control", "no-cache");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskMessage(status, entity);
	}

	@RequestMapping(value = "/updateTaskStep", method = RequestMethod.POST)
	public @ResponseBody TaskMessage updateStep(@FormDataParam("id") String id,
			@FormDataParam("step") String step,
			@FormDataParam("blueprintStep") String blueprintStep,
			final HttpServletResponse response, Model model) {
		ResponseStatus status = null;
		Long functionId = Long.parseLong(id);
		Tasks entity = null;
		Tasks newEntity = null;
		try {
			entity = tasksService.find(functionId);
			newEntity = tasksService.find(functionId);
			newEntity.setTask(entity.getTask());
			newEntity.setDescription(entity.getDescription());
			newEntity.setX_axis(entity.getX_axis());
			newEntity.setY_axis(entity.getY_axis());
			newEntity.setIsDeleted(entity.getIsDeleted());
			newEntity.setStep(step);
			newEntity.setBlueprintStep(blueprintStep);
			
			super.create(newEntity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskMessage(status, newEntity);
	}
	@RequestMapping(value = "/updateBlueprintStep", method = RequestMethod.POST)
	public @ResponseBody TaskMessage updateBlueprintStep(@FormDataParam("id") String id,
			@FormDataParam("blueprintStep") String blueprintStep,
			final HttpServletResponse response, Model model) {
		ResponseStatus status = null;
		Long functionId = Long.parseLong(id);
		Tasks entity = null;
		Tasks newEntity = null;
		try {
			entity = tasksService.find(functionId);
			newEntity = tasksService.find(functionId);
			newEntity.setTask(entity.getTask());
			newEntity.setDescription(entity.getDescription());
			newEntity.setIsDeleted(entity.getIsDeleted());
			newEntity.setStep(entity.getStep());
			newEntity.setX_axis(entity.getX_axis());
			newEntity.setY_axis(entity.getY_axis());
			newEntity.setBlueprintStep(blueprintStep);			
			super.create(newEntity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskMessage(status, newEntity);
	}

	//Update x-axis & y-axis
	@RequestMapping(value = "/updatePosition", method = RequestMethod.POST)
	public @ResponseBody TaskMessage updatePosition(@FormDataParam("id") String id,
			@FormDataParam("x_axis") String x_axis,
			@FormDataParam("y_axis") String y_axis,
			final HttpServletResponse response, Model model) {
		ResponseStatus status = null;
		Long functionId = Long.parseLong(id);
		Tasks entity = null;
		Tasks newEntity = null;
		try {
			entity = tasksService.find(functionId);
			newEntity = tasksService.find(functionId);
			newEntity.setTask(entity.getTask());
			newEntity.setDescription(entity.getDescription());
			newEntity.setIsDeleted(entity.getIsDeleted());
			newEntity.setStep(entity.getStep());
			newEntity.setX_axis(x_axis);
			newEntity.setY_axis(y_axis);
			newEntity.setBlueprintStep(entity.getBlueprintStep());			
			super.create(newEntity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskMessage(status, newEntity);
	}
	
	@RequestMapping(value = "/updateTask", method = RequestMethod.POST)
	public @ResponseBody TaskMessage update(@FormDataParam("id") String id,
			@FormDataParam("task") String task,
			@FormDataParam("description") String description,
			final HttpServletResponse response, Model model) {
		ResponseStatus status = null;
		Long taskId = Long.parseLong(id);
		Tasks entity = null;
		try {
			entity = tasksService.find(taskId);
			entity.setTask(task);
			entity.setDescription(description);
			entity.setIsDeleted("0");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskMessage(status, entity);
	}

	@RequestMapping(value = "/getTasksById", method = RequestMethod.GET)
	public @ResponseBody TasksMessage find(@FormDataParam("id") String id,@FormDataParam("functionid") String functionid,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long taskId = Long.parseLong(id);
		Long functid= Long.parseLong(functionid);
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = tasksService.gettasksById(taskId,functid);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TasksMessage(status, entity);
	}

	@RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
	public @ResponseBody TaskMessage delete(@FormDataParam("id") String id,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long taskId = Long.parseLong(id);
		Tasks tasks = null;
		try {
			tasks = tasksService.find(taskId);
			tasks.setIsDeleted("1");
			setHeaders(response);
			super.create(tasks);
			super.delete(taskId);				
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskMessage(status, tasks);
	}

	@Override
	public MainService<Tasks> getService() {
		return tasksService;
	}
}
