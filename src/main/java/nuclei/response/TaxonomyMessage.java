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

import nuclei.domain.Taxonomy;

@XmlRootElement(name = "Taxonomy")
public class TaxonomyMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private Taxonomy entity = new Taxonomy();

	@XmlElement(name = "Taxonomy")
	public Taxonomy getEntity() {
		return entity;
	}

	public void setEntity(Taxonomy entity) {
		this.entity = entity;
	}

	public TaxonomyMessage() {
		super();
	}

	public TaxonomyMessage(Taxonomy entity) {
		super();
		setEntity(entity);
	}

	public TaxonomyMessage(ResponseStatus status, Taxonomy entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
