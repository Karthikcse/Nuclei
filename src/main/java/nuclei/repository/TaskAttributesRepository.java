/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.TaskAttributes;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface TaskAttributesRepository extends GraphRepository<TaskAttributes>{
	
	 @Query("MATCH (TaskAttributes:TaskAttributes{isDeleted:'0'}) RETURN id(TaskAttributes) as id,TaskAttributes")	
	 Iterable<Map<String,Object>> getTaskAttributes();	
	 
	 @Query("MATCH (TaskAttributes{isDeleted:'0'}) where id(TaskAttributes)={0} RETURN id(TaskAttributes) as id, TaskAttributes")
		Iterable<Map<String, Object>> getTaskAttributesById(Long id);	
}
