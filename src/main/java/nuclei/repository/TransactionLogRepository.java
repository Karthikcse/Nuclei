/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.IaaSTemplate;
import nuclei.domain.TransactionLog;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface TransactionLogRepository extends GraphRepository<TransactionLog>{

	@Query("MATCH (TransactionLog:TransactionLog{isDeleted:false}) RETURN id(TransactionLog) as id,TransactionLog")	
	Iterable<Map<String,Object>> getTransactionLogValues();	
	
}