package nuclei.response;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import nuclei.domain.Role;

@XmlRootElement(name = "Role")
public class RoleMessage {
	
	@XmlElement(name = "status")
	public ResponseStatus status;

	private Iterable<Map<String, Object>> entities;
	
	private Role entity;

	public RoleMessage() {
		super();
	}

	public RoleMessage(ResponseStatus status,
			Iterable<Map<String, Object>> entities) {
		super();
		this.status = status;
		this.entities = entities;
	}
	
	public RoleMessage(ResponseStatus status,
			Role entity) {
		super();
		this.status = status;
		this.entity = entity;
	}

	@XmlElement(name = "Roles")
	public Iterable<Map<String, Object>> getRoles() {
		return entities;
	}
	
	@XmlElement(name = "Role")
	public Role getRole() {
		return entity;
	}

}
