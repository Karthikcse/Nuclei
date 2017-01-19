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
import nuclei.domain.TransactionLog;

@XmlRootElement(name = "TransactionLog")
public class TransactionLogMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private TransactionLog entity = new TransactionLog();

	@XmlElement(name = "TransactionLog")
	public TransactionLog getEntity() {
		return entity;
	}

	public void setEntity(TransactionLog entity) {
		this.entity = entity;
	}

	public TransactionLogMessage() {
		super();
	}

	public TransactionLogMessage(TransactionLog entity) {
		super();
		setEntity(entity);
	}

	public TransactionLogMessage(ResponseStatus status, TransactionLog entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
