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

import nuclei.domain.User;

@XmlRootElement(name = "User")
public class UserMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private User entity = new User();

	@XmlElement(name = "User")
	public User getEntity() {
		return entity;
	}

	public void setEntity(User entity) {
		this.entity = entity;
	}

	public UserMessage() {
		super();
	}

	public UserMessage(User entity) {
		super();
		setEntity(entity);
	}

	public UserMessage(ResponseStatus status, User entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
