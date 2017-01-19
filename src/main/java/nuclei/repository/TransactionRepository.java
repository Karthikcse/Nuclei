/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.Organization;
import nuclei.domain.Transaction;
import nuclei.domain.User;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface TransactionRepository extends GraphRepository<Transaction>{
	
	@Query("MATCH (Transaction:Transaction{isDeleted:false}) RETURN id(Transaction) as id,Transaction")	
	Iterable<Map<String,Object>> getTransactions();	

	@Query("MATCH (transaction:Transaction),(deployment:Deployment) WHERE id(transaction) ={0} and id(deployment) = {1} create (transaction)-[r:Relation_To_Deployment]->(deployment) RETURN transaction, deployment")
	Iterable<Map<String,Object>> createRelationWithDeployment(Long transactionid,Long deploymentid);
}

