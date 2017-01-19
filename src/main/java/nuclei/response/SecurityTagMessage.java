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

import nuclei.domain.SecurityTags;

@XmlRootElement(name = "SecurityTags")
public class SecurityTagMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private SecurityTags entity = new SecurityTags();

	@XmlElement(name = "SecurityTags")
	public SecurityTags getEntity() {
		return entity;
	}

	public void setEntity(SecurityTags entity) {
		this.entity = entity;
	}

	public SecurityTagMessage() {
		super();
	}

	public SecurityTagMessage(SecurityTags entity) {
		super();
		setEntity(entity);
	}

	public SecurityTagMessage(ResponseStatus status, SecurityTags entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
