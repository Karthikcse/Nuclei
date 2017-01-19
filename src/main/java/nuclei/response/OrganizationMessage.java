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

import nuclei.domain.Organization;

@XmlRootElement(name = "Organization")
public class OrganizationMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private Organization entity = new Organization();

	@XmlElement(name = "Organization")
	public Organization getEntity() {
		return entity;
	}

	public void setEntity(Organization entity) {
		this.entity = entity;
	}

	public OrganizationMessage() {
		super();
	}

	public OrganizationMessage(Organization entity) {
		super();
		setEntity(entity);
	}

	public OrganizationMessage(ResponseStatus status, Organization entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
