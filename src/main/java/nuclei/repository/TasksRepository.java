/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.Tasks;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.neo4j.annotation.Query;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface TasksRepository extends GraphRepository<Tasks>{
	
	 @Query("MATCH (Tasks:Tasks{isDeleted:'0'}) RETURN id(Tasks) as id,Tasks")	
	 Iterable<Map<String,Object>> getTasksValues();	

	//@Query("MATCH (Tasks)-->(TaskFunctions{isDeleted:'0'}) where id(Tasks)={0} RETURN id(TaskFunctions) as id, TaskFunctions")
	 @Query("MATCH (Tasks)-[r]->(TaskFunctions{isDeleted:'0'}) where id(Tasks)={0} and id(TaskFunctions)={1} RETURN id(TaskFunctions) as id, TaskFunctions,r as relation;")
	 Iterable<Map<String, Object>> gettasksById(Long id,Long functid);	

}
