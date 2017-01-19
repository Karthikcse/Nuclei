/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.Blueprint;
import nuclei.domain.IaaSTemplate;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface BlueprintRepository extends GraphRepository<Blueprint>{
	
	@Query("MATCH (Blueprint:Blueprint{isDeleted:'0'}) RETURN id(Blueprint) as id,Blueprint")	
	Iterable<Map<String,Object>> getTemplateBlueprints();	

	//@Query("MATCH (blueprint:Blueprint)-[]->(tasks:Tasks{isDeleted:'0'}) where id(blueprint)={0} with blueprint,tasks optional match (tasks)-[]->(function:TaskFunction{isDeleted:'0'}) with blueprint,tasks,function RETURN {blueprints:[{id:id(blueprint),blueprint:blueprint.blueprint,onIaas:blueprint.onIaas,script:blueprint.script,isDeleted:blueprint.isDeleted}],tasks:[{id:id(tasks),description:tasks.description,task:tasks.task,step:tasks.step,blueprintStep:tasks.blueprintStep,isDeleted:tasks.isDeleted,x_axis:tasks.x_axis,y_axis:tasks.y_axis}],functions:[{id:id(function),functionObj:collect(function)}]} as blueprint")
	//@Query("MATCH (blueprint:Blueprint)-[]->(tasks:Tasks) where id(blueprint)={0} with blueprint,tasks optional match (tasks)-[r:Task_Function]->(function:TemplateFunction) with blueprint,tasks,function,r optional match (tasks)-[r1:Task_Function]->(iaaSConfig:IaaSConfig) with blueprint,tasks,function,iaaSConfig,r,r1 RETURN {blueprint:[{id:id(blueprint),blueprint:blueprint.blueprint,version:blueprint.version,task:[{id:id(tasks),task:tasks.task,relation:[{id:id(r),relationParam:collect(r)}],ConfigRelation:[{id:id(r1),relationParam:collect(r1)}]}]}]}as blueprint")
	//@Query("MATCH (blueprint:Blueprint)-[]->(tasks:Tasks) where id(blueprint)={0} with blueprint,tasks optional match (tasks)-[r:Task_Function]->(function:TemplateFunction) with blueprint,tasks,function,r optional match (tasks)-[r1:Task_Function]->(iaaSConfig:IaaSConfig) with blueprint,tasks,function,iaaSConfig,r,r1 RETURN {blueprint:[{id:id(blueprint),blueprint:blueprint.blueprint,version:blueprint.version}],task:[{id:id(tasks),task:tasks.task}],function:[{id:id(function),functionName:function.functionName}],relation:[{id:id(r),relationParam:collect(r)}],ConfigRelation:[{id:id(r1),relationParam:collect(r1)}]}as blueprint")
	//@Query("MATCH (blueprint:Blueprint)-[]->(tasks:Tasks) where id(blueprint)={0} with blueprint,tasks optional match (tasks)-[r:Task_Function]->(function:TemplateFunction) with blueprint,tasks,function,r optional match (tasks)-[r1:Task_Function]->(iaaSConfig:IaaSConfig) with blueprint,tasks,function,iaaSConfig,r,r1 optional match (iaaSConfig)-[]->(iaaSTemplate:IaaSTemplate) with blueprint,tasks,function,iaaSConfig,r,r1,iaaSTemplate RETURN {blueprint:[{id:id(blueprint),blueprint:blueprint.blueprint,version:blueprint.version}],task:[{id:id(tasks),task:tasks.task}],function:[{id:id(function),functionName:function.functionName}],relation:[{id:id(r),relationParam:collect(r)}],ConfigRelation:[{id:id(r1),relationParam:collect(r1)}],iaaSTemplate:[{id:id(iaaSTemplate),iaasName:iaaSTemplate.IaasName}]}as blueprint")
	@Query("MATCH (blueprint:Blueprint)-[]->(tasks:Tasks) where id(blueprint)={0} with blueprint,tasks optional match (tasks)-[r:Task_Function]->(function:TemplateFunction) with blueprint,tasks,function,r optional match (tasks)-[r1:Task_Function]->(iaaSConfig:IaaSConfig) with blueprint,tasks,function,iaaSConfig,r,r1 optional match (iaaSConfig)-[]->(iaaSTemplate:IaaSTemplate) with blueprint,tasks,function,iaaSConfig,r,r1,iaaSTemplate RETURN {blueprint:[{id:id(blueprint),blueprint:blueprint.blueprint,version:blueprint.version}],task:[{id:id(tasks),task:tasks.task}],function:[{id:id(function),functionName:function.functionName}],relation:[{id:id(r),relationParam:collect(r)}],ConfigRelation:[{id:id(r1),relationParam:collect(r1)}],iaaSTemplate:[{id:id(iaaSTemplate),iaasName:iaaSTemplate.IaasName}],iaaSConfig:[{id:id(iaaSConfig),name:iaaSConfig.name}]}as blueprint")
	Iterable<Map<String, Object>> getBlueprintById(Long id);
	
	//Get yaml file values
	@Query("MATCH (blueprint:Blueprint)-[]->(tasks:Tasks) where id(blueprint)={0} with blueprint,tasks optional match (tasks)-[r:Task_Function]->(function:TemplateFunction) with blueprint,tasks,function,r optional match (tasks)-[r1:Task_Function]->(iaaSConfig:IaaSConfig) with blueprint,tasks,function,iaaSConfig,r,r1 RETURN {blueprint:[{id:id(blueprint),blueprint:blueprint.blueprint,version:blueprint.version,task:[{id:id(tasks),task:tasks.task,relation:[{id:id(r),relationParam:collect(r)}],ConfigRelation:[{id:id(r1),relationParam:collect(r1)}]}]}]}as blueprint")
	Iterable<Map<String, Object>> generateYamlFile(Long id);
	 
	@Query("MATCH (IaaSTemplate) where id(IaaSTemplate)={0} RETURN id(IaaSTemplate) as id,IaaSTemplate")   
	IaaSTemplate getIaasTemplate(Long id);
}

