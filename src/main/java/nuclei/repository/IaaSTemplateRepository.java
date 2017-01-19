/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.IaaSTemplate;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface IaaSTemplateRepository extends GraphRepository<IaaSTemplate>{

	@Query("MATCH (IaaSTemplate:IaaSTemplate{isDeleted:'0'}) RETURN id(IaaSTemplate) as id,IaaSTemplate")	
	Iterable<Map<String,Object>> getIaaSTemplateValues();	

	@Query("MATCH (IaaSTemplate)-->(TemplateFunction{isDeleted:'0'}) where id(IaaSTemplate)={0} and not TemplateFunction.functionName='' RETURN id(TemplateFunction) as id,TemplateFunction")
	Iterable<Map<String, Object>> getIaaSTemplateById(Long id);	

	@Query("match(iaasTemplate:IaaSTemplate)-[relation:IaaS_Function]->(function:TemplateFunction) where id(iaasTemplate)={0} with iaasTemplate,relation,function RETURN {iaasTemplate:[{id:id(iaasTemplate),IaasName:iaasTemplate.IaasName,function:[{id:id(function),functionName:function.functionName,relation:[{id:id(relation),sequence_num:relation.sequence_num}]}]}]}as sequenceCLI ORDER BY relation.sequence_num ASC")
	Iterable<Map<String, Object>> getIaaSTemplateSequence(Long id);

	@Query("match(iaasTemplate:IaaSTemplate)-[relation:IaaS_Function]->(function:TemplateFunction) with iaasTemplate,relation,function optional match (function)-[r]->(iaasConfig:IaaSConfig) with iaasTemplate,relation,function,iaasConfig,r optional match (tasks:Tasks)-[taskRelation:Task_Function]->(iaasConfig) with iaasTemplate,relation,function,iaasConfig,r,taskRelation,tasks where id(iaasTemplate)={0}  and id(iaasConfig) IS NOT NULL and function.functionName='bosh target' RETURN {iaasTemplate:[{id:id(iaasTemplate),IaasName:iaasTemplate.IaasName,function:[{id:id(function),functionName:function.functionName,relation:[{id:id(relation),sequence_num:relation.sequence_num,iaasConfig:[{id:id(iaasConfig),name:iaasConfig.name,tasks:[{id:id(tasks),task:tasks.task,taskRelation:[{id:id(taskRelation),value:taskRelation.value}]}]}]}]}]}]}as sequenceCLI ORDER BY relation.sequence_num ASC")
	Iterable<Map<String, Object>> getBoshTarget(Long id);

	@Query("match(iaasTemplate:IaaSTemplate)-[relation:IaaS_Function]->(function:TemplateFunction) with iaasTemplate,relation,function optional match (function)-[r]->(iaasConfig:IaaSConfig) with iaasTemplate,relation,function,iaasConfig,r optional match (tasks:Tasks)-[taskRelation:Task_Function]->(iaasConfig) with iaasTemplate,relation,function,iaasConfig,r,taskRelation,tasks where id(iaasTemplate)={0}  and id(iaasConfig) IS NOT NULL and function.functionName='bosh login' RETURN {iaasTemplate:[{id:id(iaasTemplate),IaasName:iaasTemplate.IaasName,function:[{id:id(function),functionName:function.functionName,relation:[{id:id(relation),sequence_num:relation.sequence_num,iaasConfig:[{id:id(iaasConfig),name:iaasConfig.name,tasks:[{id:id(tasks),task:tasks.task,taskRelation:[{id:id(taskRelation),value:taskRelation.value}]}]}]}]}]}]}as sequenceCLI ORDER BY relation.sequence_num ASC")
	Iterable<Map<String, Object>> getBoshLogin(Long id);

	@Query("match(iaasTemplate:IaaSTemplate)-[relation]->(function1:TemplateFunction)-[r2]-(tasks:Tasks)-[taskRelation]->(function:TemplateFunction) with iaasTemplate,relation,function,function1,tasks,taskRelation where id(iaasTemplate)={0} and id(tasks) IS NOT NULL and taskRelation.value IS NOT NULL and function1.functionName='bosh upload-stemcell' RETURN {iaasTemplate:[{id:id(iaasTemplate),IaasName:iaasTemplate.IaasName,function:[{id:id(function1),functionName:function1.functionName,relation:[{id:id(relation),sequence_num:relation.sequence_num,tasks:[{id:id(tasks),task:tasks.task,taskRelation:[{id:id(taskRelation),value:taskRelation.value}]}]}]}]}]}as sequenceCLI ORDER BY relation.sequence_num ASC")
	Iterable<Map<String, Object>> getBoshUploadStemcell(Long id);

	@Query("match(iaasTemplate:IaaSTemplate)-[relation:IaaS_Function]->(function:TemplateFunction) where id(iaasTemplate)={0} and function.functionName='bosh deploy' with iaasTemplate,relation,function RETURN {iaasTemplate:[{id:id(iaasTemplate),IaasName:iaasTemplate.IaasName,function:[{id:id(function),functionName:function.functionName,relation:[{id:id(relation),sequence_num:relation.sequence_num}]}]}]}as sequenceCLI ORDER BY relation.sequence_num ASC")
	Iterable<Map<String, Object>> getBoshDeploy(Long id);

	@Query("match(iaasTemplate:IaaSTemplate)-[relation:IaaS_Function]->(function:TemplateFunction) where id(iaasTemplate)={0} and function.functionName='bosh upload-release' with iaasTemplate,relation,function RETURN {iaasTemplate:[{id:id(iaasTemplate),IaasName:iaasTemplate.IaasName,function:[{id:id(function),functionName:function.functionName,relation:[{id:id(relation),sequence_num:relation.sequence_num}]}]}]}as sequenceCLI ORDER BY relation.sequence_num ASC")
	Iterable<Map<String, Object>> getBoshRelease(Long id);

	@Query("match(iaasTemplate:IaaSTemplate)-[relation]->(function:TemplateFunction) with iaasTemplate,relation,function optional match (tasks:Tasks)-[taskRelation]->(function{functionName:'bosh upload-release'}) with iaasTemplate,relation,function,tasks,taskRelation where id(iaasTemplate)={0} and id(tasks) IS NOT NULL and taskRelation.value IS NOT NULL RETURN {iaasTemplate:[{id:id(iaasTemplate),IaasName:iaasTemplate.IaasName,function:[{id:id(function),functionName:function.functionName,relation:[{id:id(relation),sequence_num:relation.sequence_num,tasks:[{id:id(tasks),task:tasks.task,taskRelation:[{id:id(taskRelation),value:taskRelation.value}]}]}]}]}]}as sequenceCLI ORDER BY relation.sequence_num ASC")
	Iterable<Map<String, Object>> getBoshCreateRelease(Long id);

	@Query("match(iaasTemplate:IaaSTemplate)-[relation:IaaS_Function]->(function:TemplateFunction) where id(iaasTemplate)={0} and function.functionName='bosh deployment' with iaasTemplate,relation,function RETURN {iaasTemplate:[{id:id(iaasTemplate),IaasName:iaasTemplate.IaasName,function:[{id:id(function),functionName:function.functionName,relation:[{id:id(relation),sequence_num:relation.sequence_num}]}]}]}as sequenceCLI ORDER BY relation.sequence_num ASC")
	Iterable<Map<String, Object>> getBoshDeployment(Long id);
	
	@Query("match(iaasTemplate:IaaSTemplate)-[relation:IaaS_Function]->(function:TemplateFunction) where id(iaasTemplate)={0} with iaasTemplate,relation,function optional match(function)-[transactionRelation:transaction]->(transaction:Transaction) with iaasTemplate,relation,function,transactionRETURN {iaasTemplate:[{id:id(iaasTemplate),IaasName:iaasTemplate.IaasName,function:[{id:id(function),functionName:function.functionName,relation:[{id:id(relation),sequence_num:relation.sequence_num,transaction:[{id:id(transaction),transactionObj:transaction}]}]}]}]}as sequenceCLI ORDER BY relation.sequence_num ASC")
	Iterable<Map<String, Object>> getTransactionList(Long id);

}