/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.Order;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.neo4j.annotation.Query;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface OrderRepository extends GraphRepository<Order>{

	@Query("MATCH (Order:Order{isDeleted:false}) RETURN id(Order) as id,Order")	
	Iterable<Map<String,Object>> getOrderValues();	

	@Query("MATCH (Order)-->(ServiceCatalogue{isDeleted:'0'}) where id(Order)={0} RETURN id(ServiceCatalogue) as id, ServiceCatalogue")
	Iterable<Map<String, Object>> getOrderById(Long id);	

	@Query("Match(ServiceCatalogue:ServiceCatalogue)-[]->(ScTaxonomy:ScTaxonomy)-[]->(Blueprint:Blueprint)-[]->(Tasks:Tasks)-[]->(TemplateFunction:TemplateFunction)-[]-(IaaSTemplate:IaaSTemplate) where id(ServiceCatalogue)={0} with ServiceCatalogue,ScTaxonomy,Blueprint,Tasks,TemplateFunction,IaaSTemplate return distinct{ServiceCatalogue:[{id:id(ServiceCatalogue),name:ServiceCatalogue.Template,Blueprint:[{id:id(Blueprint),name:Blueprint.blueprint,IaaSTemplate:[{id:id(IaaSTemplate),name:IaaSTemplate.IaasName}]}]}]} as ServiceCatalogue;")
	Iterable<Map<String, Object>> getOrderByServiceCatalogue(Long id);

	//Create Order to IaasConfig relation
	@Query("MATCH (Order:Order),(iaaSConfig:IaaSConfig) WHERE id(Order) ={0} and id(iaaSConfig) = {1} create (Order)-[r:ORDER_CONFIG]->(iaaSConfig) RETURN Order, iaaSConfig")
	Iterable<Map<String, Object>> createIaasConfigRelation(Long orderid,Long confgid);

	//Create Order to Servicecatalogue relation
	@Query("MATCH (Order:Order),(ServiceCatalogue:ServiceCatalogue) WHERE id(Order) ={0} and id(ServiceCatalogue) = {1} create (Order)-[r:INCLUDING]->(ServiceCatalogue) RETURN Order, ServiceCatalogue")
	Iterable<Map<String, Object>> createSCRelation(Long orderid,Long scid);
	
	@Query("MATCH (Transaction:Transaction{isDeleted:false})-->(Deployment:Deployment) with Transaction,Deployment optional match(Deployment)<--(Order:Order) with Transaction,Deployment,Order optional match(Transaction)-->(TransactionLifecycle:TransactionLifecycle) with distinct(Transaction),Deployment,TransactionLifecycle,Order where id(Order)={0} RETURN{Deployment:{id:id(Deployment),name:Deployment.name,TransactionLifecycle:[{id:id(TransactionLifecycle),status:TransactionLifecycle.status,Transaction:[{id:id(Transaction),sequence_num:Transaction.sequence_num,command:Transaction.command,command_type:Transaction.command_type,trax_uuid:Transaction.trax_uuid,parameters:Transaction.parameters,date_time_created:Transaction.date_time_created,isDeleted:Transaction.isDeleted}]}]}}as TransactionList ORDER BY Transaction.sequence_num ASC")
	Iterable<Map<String, Object>> generateTransactionList(Long id);

}
