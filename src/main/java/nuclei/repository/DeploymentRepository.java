/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.Deployment;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.neo4j.annotation.Query;
/**
 * @author Karthikeyan
 *
 */
@Repository
public interface DeploymentRepository extends GraphRepository<Deployment>{

	@Query("MATCH (Deployment:Deployment{isDeleted:false}) RETURN id(Deployment) as id,Deployment")	
	Iterable<Map<String,Object>> getdeploymentValues();

	@Query("MATCH (Deployment)-->(ServiceCatalogue{isDeleted:'0'}) where id(Deployment)={0} RETURN id(ServiceCatalogue) as id, ServiceCatalogue")
	Iterable<Map<String, Object>> getdeploymentById(Long id);	

	//Create deployment to Secvicecatalogue relation
	@Query("MATCH (deployment:Deployment),(serviceCatalogue:ServiceCatalogue) WHERE id(deployment) ={0} and id(serviceCatalogue) = {1} create (deployment)-[r:DEPLOYING]->(serviceCatalogue) RETURN deployment, serviceCatalogue")
	Iterable<Map<String, Object>> createSCRelation(Long deploymentid,Long scid);

	//Create deployment to IaasConfig relation
	@Query("MATCH (deployment:Deployment),(iaaSConfig:IaaSConfig) WHERE id(deployment) ={0} and id(iaaSConfig) = {1} create (deployment)-[r:DEPLOYING]->(iaaSConfig) RETURN deployment, iaaSConfig")
	Iterable<Map<String, Object>> createIaasConfigRelation(Long deploymentid,Long confgid);

	//Get blueprint id using Deployment id
	@Query("Match (deployment:Deployment)-[:DEPLOYING]->(serviceCatalogue:ServiceCatalogue)-[]->(scTaxonomy:ScTaxonomy)-[:sc_Taxonomy_Blueprint]->(blueprint:Blueprint)-[:Blueprint_Task]->(tasks:Tasks) where id(deployment)={0} with distinct(blueprint) return {blueprint:[{id:id(blueprint)}]} as result;")
	Iterable<Map<String, Object>> getBlueprintByDeployId(Long id);	

	// @Query("MATCH (Transaction{isDeleted:false})-->(Deployment) where id(Deployment)={0} with Transaction,Deployment RETURN{Deployment:{id:id(Deployment),name:Deployment.name,Transaction:[{id:id(Transaction),sequence_num:Transaction.sequence_num,command:Transaction.command,command_type:Transaction.command_type,trax_uuid:Transaction.trax_uuid,parameters:Transaction.parameters,date_time_created:Transaction.date_time_created,isDeleted:Transaction.isDeleted}]}}as TransactionList ORDER BY Transaction.sequence_num ASC")
	// @Query("MATCH (Transaction:Transaction{isDeleted:false})-->(Deployment:Deployment)where id(Deployment)={0} with Transaction,Deployment optional match(Transaction)-->(TransactionLifecycle:TransactionLifecycle)  with distinct(Transaction),Deployment,TransactionLifecycle RETURN{ Deployment:{id:id(Deployment),name:Deployment.name,Transaction:[{id:id(Transaction),sequence_num:Transaction.sequence_num,command:Transaction.command,command_type:Transaction.command_type,trax_uuid:Transaction.trax_uuid,parameters:Transaction.parameters,date_time_created:Transaction.date_time_created,isDeleted:Transaction.isDeleted}],TransactionLifecycle:[{id:id(TransactionLifecycle),status:TransactionLifecycle.status}]}}as TransactionList ORDER BY Transaction.sequence_num ASC")
	/*@Query("MATCH (Transaction:Transaction{isDeleted:false})-->(Deployment:Deployment)where id(Deployment)={0} with Transaction,Deployment optional match(Transaction)-->(TransactionLifecycle:TransactionLifecycle)  with distinct(Transaction),Deployment,TransactionLifecycle RETURN{ Deployment:{id:id(Deployment),name:Deployment.name,TransactionLifecycle:[{id:id(TransactionLifecycle),status:TransactionLifecycle.status,Transaction:[{id:id(Transaction),sequence_num:Transaction.sequence_num,command:Transaction.command,command_type:Transaction.command_type,trax_uuid:Transaction.trax_uuid,parameters:Transaction.parameters,date_time_created:Transaction.date_time_created,isDeleted:Transaction.isDeleted}]}]}}as TransactionList ORDER BY Transaction.sequence_num ASC")
	Iterable<Map<String, Object>> generateTransactionList(Long id);*/
	
	@Query("MATCH (Transaction:Transaction)-->(Deployment:Deployment) where id(Deployment)={0} RETURN count(Transaction) as count")
	Iterable<Map<String, Object>> countTransaction(Long id);
	
	@Query("MATCH (TransactionLog:TransactionLog)-[]-(Transaction:Transaction)-->(Deployment:Deployment) where id(Deployment)={0} and TransactionLog.result='OK'  RETURN count(Transaction) as count")
	Iterable<Map<String, Object>> countOKCount(Long id);
	
	//Test
	/*@Query("MATCH (TransactionLog:TransactionLog)-[]-(Transaction:Transaction)-->(Deployment:Deployment) where id(Deployment)={0} and not TransactionLog.result='OK'  RETURN Transaction;")
	Iterable<Map<String, Object>> getStatusById(Long id);*/
	
	
}
