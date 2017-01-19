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

import nuclei.domain.IaaSTemplate;

@XmlRootElement(name = "IaaSTemplate")
public class IaasTemplateMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private IaaSTemplate entity = new IaaSTemplate();

	@XmlElement(name = "IaasTemplate")
	public IaaSTemplate getEntity() {
		return entity;
	}

	public void setEntity(IaaSTemplate entity) {
		this.entity = entity;
	}

	public IaasTemplateMessage() {
		super();
	}

	public IaasTemplateMessage(IaaSTemplate entity) {
		super();
		setEntity(entity);
	}

	public IaasTemplateMessage(ResponseStatus status, IaaSTemplate entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
