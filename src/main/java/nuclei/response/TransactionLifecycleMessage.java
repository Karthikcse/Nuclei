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
import nuclei.domain.TransactionLifecycle;
import nuclei.domain.TransactionLog;

@XmlRootElement(name = "TransactionLifecycle")
public class TransactionLifecycleMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private TransactionLifecycle entity = new TransactionLifecycle();

	@XmlElement(name = "TransactionLifecycle")
	public TransactionLifecycle getEntity() {
		return entity;
	}

	public void setEntity(TransactionLifecycle entity) {
		this.entity = entity;
	}

	public TransactionLifecycleMessage() {
		super();
	}

	public TransactionLifecycleMessage(TransactionLifecycle entity) {
		super();
		setEntity(entity);
	}

	public TransactionLifecycleMessage(ResponseStatus status, TransactionLifecycle entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
