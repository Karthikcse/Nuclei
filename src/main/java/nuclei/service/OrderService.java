/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.Order;

/**
 * @author Karthikeyan
 *
 */
public interface OrderService extends MainService<Order> {

	Iterable<Map<String, Object>> getOrderValues();

	Iterable<Map<String, Object>> getOrderById(Long id);
	
	Iterable<Map<String, Object>> getOrderByServiceCatalogue(Long id);
	
	Iterable<Map<String, Object>> createIaasConfigRelation(Long orderid,Long confgid);
	
	Iterable<Map<String, Object>> createSCRelation(Long orderid,Long scid);
	
	Iterable<Map<String, Object>> generateTransactionList(Long id);

}


