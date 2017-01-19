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

import nuclei.domain.Deployment;
import nuclei.domain.Order;
import nuclei.domain.Tasks;

@XmlRootElement(name = "Order")
public class OrderMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;
	private Order entity = new Order();

	@XmlElement(name = "Order")
	public Order getEntity() {
		return entity;
	}

	public void setEntity(Order entity) {
		this.entity = entity;
	}

	public OrderMessage() {
		super();
	}

	public OrderMessage(Order entity) {
		super();
		setEntity(entity);
	}

	public OrderMessage(ResponseStatus status, Order entity) {
		super();
		this.status = status;
		this.entity = entity;
	}
}
