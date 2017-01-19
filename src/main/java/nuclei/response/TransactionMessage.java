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

import nuclei.domain.Organization;
import nuclei.domain.Transaction;

@XmlRootElement(name = "Transaction")
public class TransactionMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private Transaction entity = new Transaction();

	@XmlElement(name = "Transaction")
	public Transaction getEntity() {
		return entity;
	}

	public void setEntity(Transaction entity) {
		this.entity = entity;
	}

	public TransactionMessage() {
		super();
	}

	public TransactionMessage(Transaction entity) {
		super();
		setEntity(entity);
	}

	public TransactionMessage(ResponseStatus status, Transaction entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
