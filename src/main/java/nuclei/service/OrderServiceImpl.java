/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.Order;
import nuclei.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("orderService")
public class OrderServiceImpl extends GenericService<Order> implements
OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public GraphRepository<Order> getRepository() {
		return orderRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getOrderValues() {
		return orderRepository.getOrderValues();
	}

	@Override
	public Iterable<Map<String, Object>> getOrderById(Long id) {
		return orderRepository.getOrderById(id);
	}

	@Override
	public Iterable<Map<String, Object>> getOrderByServiceCatalogue(Long id){
		return orderRepository.getOrderByServiceCatalogue(id);
	}

	@Override
	public Iterable<Map<String, Object>> createIaasConfigRelation(Long orderid,Long confgid){
		return orderRepository.createIaasConfigRelation(orderid, confgid);
	}

	@Override
	public Iterable<Map<String, Object>> createSCRelation(Long orderid,Long scid){
		return orderRepository.createSCRelation(orderid, scid);
	}

	@Override
	public Iterable<Map<String, Object>> generateTransactionList(Long id){
		return orderRepository.generateTransactionList(id);
	}
}
