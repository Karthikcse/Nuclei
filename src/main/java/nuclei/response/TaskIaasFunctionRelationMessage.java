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

import nuclei.domain.CatalogueBlueprint;
import nuclei.domain.TaskIaasFunctionRelation;

@XmlRootElement(name = "TaskIaasFunctionRelation")
public class TaskIaasFunctionRelationMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private TaskIaasFunctionRelation entity = new TaskIaasFunctionRelation();

	@XmlElement(name = "TaskIaasFunctionRelation")
	public TaskIaasFunctionRelation getEntity() {
		return entity;
	}

	public void setEntity(TaskIaasFunctionRelation entity) {
		this.entity = entity;
	}

	public TaskIaasFunctionRelationMessage() {
		super();
	}

	public TaskIaasFunctionRelationMessage(TaskIaasFunctionRelation entity) {
		super();
		setEntity(entity);
	}

	public TaskIaasFunctionRelationMessage(ResponseStatus status, TaskIaasFunctionRelation entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
