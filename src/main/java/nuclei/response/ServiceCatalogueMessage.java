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

import nuclei.domain.ServiceCatalogue;

@XmlRootElement(name = "ServiceCatalogue")
public class ServiceCatalogueMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private ServiceCatalogue entity = new ServiceCatalogue();

	@XmlElement(name = "ServiceCatalogue")
	public ServiceCatalogue getEntity() {
		return entity;
	}

	public void setEntity(ServiceCatalogue entity) {
		this.entity = entity;
	}

	public ServiceCatalogueMessage() {
		super();
	}

	public ServiceCatalogueMessage(ServiceCatalogue entity) {
		super();
		setEntity(entity);
	}

	public ServiceCatalogueMessage(ResponseStatus status, ServiceCatalogue entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
