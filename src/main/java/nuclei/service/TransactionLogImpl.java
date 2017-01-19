/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.IaaSTemplate;
import nuclei.domain.TransactionLog;
import nuclei.repository.IaaSTemplateRepository;
import nuclei.repository.TransactionLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("transactionLogService")
public class TransactionLogImpl extends GenericService<TransactionLog>
		implements TransactionLogService {

	@Autowired
	private TransactionLogRepository transactionLogRepository;

	@Override
	public GraphRepository<TransactionLog> getRepository() {
		return transactionLogRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getTransactionLogValues() {
		return transactionLogRepository.getTransactionLogValues();
	}
}
