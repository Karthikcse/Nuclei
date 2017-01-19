/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.Organization;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface OrganizationRepository extends GraphRepository<Organization>{
	
	@Query("MATCH (Organization:Organization{isDeleted:false}) RETURN id(Organization) as id,Organization")	
	Iterable<Map<String,Object>> getOrganizations();	

	/*@Query("MATCH (blueprint:Blueprint)-[]->(tasks:Tasks{isDeleted:'0'}) where id(blueprint)={0} with blueprint,tasks optional match (tasks)-[]->(function:TaskFunction{isDeleted:'0'}) with blueprint,tasks,function RETURN {blueprints:[{id:id(blueprint),blueprint:blueprint.blueprint,onIaas:blueprint.onIaas,script:blueprint.script,isDeleted:blueprint.isDeleted}],tasks:[{id:id(tasks),description:tasks.description,task:tasks.task,step:tasks.step,blueprintStep:tasks.blueprintStep,isDeleted:tasks.isDeleted,x_axis:tasks.x_axis,y_axis:tasks.y_axis}],functions:[{id:id(function),functionObj:collect(function)}]} as blueprint")
	Iterable<Map<String, Object>> getBlueprintById(Long id);
	 */
}

