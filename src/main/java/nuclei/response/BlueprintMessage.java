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

import nuclei.domain.Blueprint;

@XmlRootElement(name = "Blueprint")
public class BlueprintMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private Blueprint entity = new Blueprint();

	@XmlElement(name = "Blueprint")
	public Blueprint getEntity() {
		return entity;
	}

	public void setEntity(Blueprint entity) {
		this.entity = entity;
	}

	public BlueprintMessage() {
		super();
	}

	public BlueprintMessage(Blueprint entity) {
		super();
		setEntity(entity);
	}

	public BlueprintMessage(ResponseStatus status, Blueprint entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
