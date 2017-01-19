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

import nuclei.domain.Deployment;
import nuclei.domain.Tasks;

@XmlRootElement(name = "Deployment")
public class DeploymentMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private Deployment entity = new Deployment();

	@XmlElement(name = "Deployment")
	public Deployment getEntity() {
		return entity;
	}

	public void setEntity(Deployment entity) {
		this.entity = entity;
	}

	public DeploymentMessage() {
		super();
	}

	public DeploymentMessage(Deployment entity) {
		super();
		setEntity(entity);
	}

	public DeploymentMessage(ResponseStatus status, Deployment entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
