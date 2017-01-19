/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nuclei.response;

/**
 *
 * @author karthikeyan
 */
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import nuclei.domain.TaskFunction;

@XmlRootElement(name = "TaskFunction")
public class TaskFunctionMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private TaskFunction entity = new TaskFunction();

	@XmlElement(name = "TaskFunction")
	public TaskFunction getEntity() {
		return entity;
	}

	public void setEntity(TaskFunction entity) {
		this.entity = entity;
	}

	public TaskFunctionMessage() {
		super();
	}

	public TaskFunctionMessage(TaskFunction entity) {
		super();
		setEntity(entity);
	}

	public TaskFunctionMessage(ResponseStatus status, TaskFunction entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
