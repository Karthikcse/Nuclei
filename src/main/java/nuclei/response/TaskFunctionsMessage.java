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

@XmlRootElement(name = "TaskFunction")
public class TaskFunctionsMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;

	private Iterable<Map<String, Object>> entities;

	public TaskFunctionsMessage() {
		super();
	}

	public TaskFunctionsMessage(ResponseStatus status,
			Iterable<Map<String, Object>> entities) {
		super();
		this.status = status;
		this.entities = entities;
	}

	@XmlElement(name = "TaskFunction")
	public Iterable<Map<String, Object>> getIaaSTemplate() {
		return entities;
	}

}
