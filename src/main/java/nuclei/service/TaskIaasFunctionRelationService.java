/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.CatalogueBlueprint;
import nuclei.domain.TaskIaasFunctionRelation;

/**
 * @author Karthikeyan
 *
 */
public interface TaskIaasFunctionRelationService extends MainService<TaskIaasFunctionRelation> {

	Iterable<Map<String, Object>> getTaskIaasFunctionRelation();
	
	Iterable<Map<String, Object>> updateValue(Long id,String taskId,String functionId,String value,String step,String x_axis,String y_axis);
	
	Iterable<Map<String,Object>> getRelation(Long taskId,Long functId);
	
	Iterable<Map<String,Object>> getTaskIaasFunctionRelationById(Long id);
	
	Iterable<Map<String, Object>> updateStep(Long functionId, Long taskId,String step);
	
	Iterable<Map<String, Object>> updatePosition(Long blueprintId,String x_axis,String y_axis);
	
	Iterable<Map<String, Object>> DeleteRelationship(Long functionId, Long taskId);
}
