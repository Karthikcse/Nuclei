/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.CatalogueBlueprint;
import nuclei.domain.TaskIaasFunctionRelation;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.neo4j.annotation.Query;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface TaskIaasFunctionRelationRepository extends GraphRepository<TaskIaasFunctionRelation> {

	@Query("MATCH (TaskIaasFunctionRelation:TaskIaasFunctionRelation{isDeleted:'0'}) RETURN id(TaskIaasFunctionRelation) as id,TaskIaasFunctionRelation")
	Iterable<Map<String, Object>> getTaskIaasFunctionRelation();
	
	//@Query("match(ts:Tasks)-[r:Task_Function]-(tf:TemplateFunction)where id(r)={0} return id(r) as id,r as TaskIaasFunctionRelation")
	@Query("Match ()-[r:Task_Function]-() where id(r)={0} set r.taskId={1},r.functionId={2},r.value={3},r.step={4},r.x_axis={5},r.y_axis={6} return distinct(r) as TaskIaasFunctionRelation")
	Iterable<Map<String, Object>> updateValue(Long id,String taskId,String functionId,String value,String step,String x_axis,String y_axis);
	
	//Check this one
	//@Query("match(bp:Blueprint)-[r:Task_Function]-(sc:ScTaxonomy)where id(bp)={0} and r.catalogueBlueprintId={0} return id(r) as id,r as TaskIaasFunctionRelation")
	@Query("match(ts:Tasks)-[r:Task_Function]-(tf:TemplateFunction)where id(ts)={0} and id(tf)={1} return id(r) as id,r as TaskIaasFunctionRelation")
	Iterable<Map<String, Object>> getRelation(Long taskId,Long functId);

	@Query("MATCH (a)-[r]-(b) WHERE id(r)= {0} RETURN DISTINCT id (r) as id,r as TaskIaasFunctionRelation")
	Iterable<Map<String, Object>> getTaskIaasFunctionRelationById(Long id);

	// Update step
	@Query("MATCH a-[r:Task_Function]-b where r.functionId={0} and r.taskId={1} SET r.step={2} RETURN DISTINCT id (r) as id,r as TaskIaasFunctionRelation")
	Iterable<Map<String, Object>> updateStep(Long functionId, Long taskId, String step);

	//Update position
	@Query("MATCH (a)-[r:Task_Function{functionId:{0}}]-(b) SET r.x_axis={1},r.y_axis={2} with r RETURN DISTINCT id (r) as id,r as TaskIaasFunctionRelation")
	Iterable<Map<String, Object>> updatePosition(Long functionId, String x_axis, String y_axis);
	
	@Query("MATCH (n)-[rel:Task_Function{functionId:{0},taskId:{1}}]-(r) DELETE rel")
	Iterable<Map<String, Object>> DeleteRelationship(Long functionId, Long taskId);
	
	
}
