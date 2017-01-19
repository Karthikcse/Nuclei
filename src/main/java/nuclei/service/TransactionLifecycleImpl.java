/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.IaaSTemplate;
import nuclei.domain.TransactionLifecycle;
import nuclei.domain.TransactionLog;
import nuclei.repository.IaaSTemplateRepository;
import nuclei.repository.TransactionLifecycleRepository;
import nuclei.repository.TransactionLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("transactionLifecycleService")
public class TransactionLifecycleImpl extends GenericService<TransactionLifecycle>
		implements TransactionLifecycleService {

	@Autowired
	private TransactionLifecycleRepository transactionLifecycleRepository;

	@Override
	public GraphRepository<TransactionLifecycle> getRepository() {
		return transactionLifecycleRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getTransactionLifecycleValues() {
		return transactionLifecycleRepository.getTransactionLifecycleValues();
	}
}
