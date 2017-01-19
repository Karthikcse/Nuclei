/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nuclei.response;

/**
 *
 * @author Karthikeyan
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Map;

@XmlRootElement(name = "CatalogueBlueprint")
public class CatalogueBlueprintsMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;

	private Iterable<Map<String, Object>> entities;

	public CatalogueBlueprintsMessage() {
		super();
	}

	public CatalogueBlueprintsMessage(ResponseStatus status,
			Iterable<Map<String, Object>> entities) {
		super();
		this.status = status;
		this.entities = entities;
	}

	@XmlElement(name = "CatalogueBlueprint")
	public Iterable<Map<String, Object>> getCatalogueBlueprint() {
		return entities;
	}

}
