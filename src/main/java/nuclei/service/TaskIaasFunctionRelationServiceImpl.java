/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.CatalogueBlueprint;
import nuclei.domain.TaskIaasFunctionRelation;
import nuclei.repository.CatalogueBlueprintRepository;
import nuclei.repository.TaskIaasFunctionRelationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("taskIaasFunctionRelationService")
public class TaskIaasFunctionRelationServiceImpl extends GenericService<TaskIaasFunctionRelation>
		implements TaskIaasFunctionRelationService {

	@Autowired
	private TaskIaasFunctionRelationRepository taskIaasFunctionRelationRepository;

	@Override
	public GraphRepository<TaskIaasFunctionRelation> getRepository() {
		return taskIaasFunctionRelationRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getTaskIaasFunctionRelation() {
		return taskIaasFunctionRelationRepository.getTaskIaasFunctionRelation();
	}
		
	@Override
	public Iterable<Map<String, Object>> updateValue(Long id,String taskId,String functionId,String value,String step,String x_axis,String y_axis){
		return taskIaasFunctionRelationRepository.updateValue(id,taskId,functionId,value,step,x_axis,y_axis);
	}
	
	@Override
	public Iterable<Map<String,Object>> getRelation(Long taskId,Long functId){
		return taskIaasFunctionRelationRepository.getRelation(taskId,functId);
	}
	
	@Override
	public  Iterable<Map<String,Object>> getTaskIaasFunctionRelationById(Long id){
		return taskIaasFunctionRelationRepository.getTaskIaasFunctionRelationById(id);
	}
	
	@Override
	public Iterable<Map<String, Object>> updateStep(Long functionId, Long taskId,String step){		
		return taskIaasFunctionRelationRepository.updateStep(functionId,taskId,step);
	}
	
	@Override
	public Iterable<Map<String, Object>> updatePosition(Long functionId,String x_axis,String y_axis){		
		return taskIaasFunctionRelationRepository.updatePosition(functionId,x_axis,y_axis);
	}
	
	@Override
	public Iterable<Map<String, Object>> DeleteRelationship(Long functionId, Long taskId){
		return taskIaasFunctionRelationRepository.DeleteRelationship(functionId, taskId);
	}
	
}
