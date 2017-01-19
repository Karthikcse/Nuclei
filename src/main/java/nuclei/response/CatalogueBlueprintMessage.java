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

import nuclei.domain.CatalogueBlueprint;

@XmlRootElement(name = "CatalogueBlueprint")
public class CatalogueBlueprintMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private CatalogueBlueprint entity = new CatalogueBlueprint();

	@XmlElement(name = "CatalogueBlueprint")
	public CatalogueBlueprint getEntity() {
		return entity;
	}

	public void setEntity(CatalogueBlueprint entity) {
		this.entity = entity;
	}

	public CatalogueBlueprintMessage() {
		super();
	}

	public CatalogueBlueprintMessage(CatalogueBlueprint entity) {
		super();
		setEntity(entity);
	}

	public CatalogueBlueprintMessage(ResponseStatus status, CatalogueBlueprint entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
