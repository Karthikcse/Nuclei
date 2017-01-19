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

import nuclei.domain.TemplateFunction;

@XmlRootElement(name = "TemplateFunction")
public class TemplateFunctionMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private TemplateFunction entity = new TemplateFunction();

	@XmlElement(name = "TemplateFunction")
	public TemplateFunction getEntity() {
		return entity;
	}

	public void setEntity(TemplateFunction entity) {
		this.entity = entity;
	}

	public TemplateFunctionMessage() {
		super();
	}

	public TemplateFunctionMessage(TemplateFunction entity) {
		super();
		setEntity(entity);
	}

	public TemplateFunctionMessage(ResponseStatus status,
			TemplateFunction entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
