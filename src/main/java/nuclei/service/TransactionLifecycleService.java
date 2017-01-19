/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.IaaSTemplate;
import nuclei.domain.TransactionLifecycle;
import nuclei.domain.TransactionLog;

/**
 * @author Karthikeyan
 *
 */
public interface TransactionLifecycleService extends MainService<TransactionLifecycle> {
	
	Iterable<Map<String, Object>> getTransactionLifecycleValues();
	
}
