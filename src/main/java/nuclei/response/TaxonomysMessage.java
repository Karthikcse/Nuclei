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

@XmlRootElement(name = "Taxonomy")
public class TaxonomysMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;

	private Iterable<Map<String, Object>> entities;

	public TaxonomysMessage() {
		super();
	}

	public TaxonomysMessage(ResponseStatus status,
			Iterable<Map<String, Object>> entities) {
		super();
		this.status = status;
		this.entities = entities;
	}

	@XmlElement(name = "Taxonomy")
	public Iterable<Map<String, Object>> getIaaSTemplate() {
		return entities;
	}

}
