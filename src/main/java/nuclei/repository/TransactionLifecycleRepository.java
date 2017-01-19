/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.IaaSTemplate;
import nuclei.domain.TransactionLifecycle;
import nuclei.domain.TransactionLog;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface TransactionLifecycleRepository extends GraphRepository<TransactionLifecycle>{

	@Query("MATCH (TransactionLifecycle:TransactionLifecycle{isDeleted:false}) RETURN id(TransactionLifecycle) as id,TransactionLifecycle")	
	Iterable<Map<String,Object>> getTransactionLifecycleValues();	
	
}