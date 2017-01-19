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

import nuclei.domain.Tasks;

@XmlRootElement(name = "Tasks")
public class TaskMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private Tasks entity = new Tasks();

	@XmlElement(name = "Tasks")
	public Tasks getEntity() {
		return entity;
	}

	public void setEntity(Tasks entity) {
		this.entity = entity;
	}

	public TaskMessage() {
		super();
	}

	public TaskMessage(Tasks entity) {
		super();
		setEntity(entity);
	}

	public TaskMessage(ResponseStatus status, Tasks entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
