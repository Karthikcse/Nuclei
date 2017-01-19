/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nuclei.response;

/**
 *
 * @author Karthikeyan
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Map;

@XmlRootElement(name = "TaskIaasFunctionRelation")
public class TaskIaasFunctionRelationsMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;

	private Iterable<Map<String, Object>> entities;

	public TaskIaasFunctionRelationsMessage() {
		super();
	}

	public TaskIaasFunctionRelationsMessage(ResponseStatus status,
			Iterable<Map<String, Object>> entities) {
		super();
		this.status = status;
		this.entities = entities;
	}

	@XmlElement(name = "TaskIaasFunctionRelation")
	public Iterable<Map<String, Object>> getTaskIaasFunctionRelation() {
		return entities;
	}

}
