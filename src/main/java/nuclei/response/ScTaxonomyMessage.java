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

import nuclei.domain.ScTaxonomy;

@XmlRootElement(name = "ScTaxonomy")
public class ScTaxonomyMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private ScTaxonomy entity = new ScTaxonomy();

	@XmlElement(name = "ScTaxonomy")
	public ScTaxonomy getEntity() {
		return entity;
	}

	public void setEntity(ScTaxonomy entity) {
		this.entity = entity;
	}

	public ScTaxonomyMessage() {
		super();
	}

	public ScTaxonomyMessage(ScTaxonomy entity) {
		super();
		setEntity(entity);
	}

	public ScTaxonomyMessage(ResponseStatus status, ScTaxonomy entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
