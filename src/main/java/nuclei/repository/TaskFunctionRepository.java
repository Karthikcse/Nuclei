/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.TaskFunction;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.neo4j.annotation.Query;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface TaskFunctionRepository extends GraphRepository<TaskFunction>{

	@Query("MATCH (TaskFunction)-->(TaskAttributes{isDeleted:'0'}) where id(TaskFunction)={0} RETURN id(TaskAttributes) as id, TaskAttributes as Attributes")
	Iterable<Map<String, Object>> getTaskFunctionById(Long id);	

	 @Query("MATCH (TaskFunction:TaskFunction{isDeleted:'0'}) RETURN id(TaskFunction) as id,TaskFunction")	
	 Iterable<Map<String,Object>> getTaskFunctions();	
}
