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

import nuclei.domain.IaasParameters;

@XmlRootElement(name = "IaasParameters")
public class IaasParameterMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private IaasParameters entity = new IaasParameters();

	@XmlElement(name = "IaasParameters")
	public IaasParameters getEntity() {
		return entity;
	}

	public void setEntity(IaasParameters entity) {
		this.entity = entity;
	}

	public IaasParameterMessage() {
		super();
	}

	public IaasParameterMessage(IaasParameters entity) {
		super();
		setEntity(entity);
	}

	public IaasParameterMessage(ResponseStatus status, IaasParameters entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
