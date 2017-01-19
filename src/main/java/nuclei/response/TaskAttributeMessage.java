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

import nuclei.domain.TaskAttributes;

@XmlRootElement(name = "TaskAttributes")
public class TaskAttributeMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private TaskAttributes entity = new TaskAttributes();

	@XmlElement(name = "TaskAttributes")
	public TaskAttributes getEntity() {
		return entity;
	}

	public void setEntity(TaskAttributes entity) {
		this.entity = entity;
	}

	public TaskAttributeMessage() {
		super();
	}

	public TaskAttributeMessage(TaskAttributes entity) {
		super();
		setEntity(entity);
	}

	public TaskAttributeMessage(ResponseStatus status, TaskAttributes entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
