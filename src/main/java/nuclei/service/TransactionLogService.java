/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.IaaSTemplate;
import nuclei.domain.TransactionLog;

/**
 * @author Karthikeyan
 *
 */
public interface TransactionLogService extends MainService<TransactionLog> {
	
	Iterable<Map<String, Object>> getTransactionLogValues();
	
}
