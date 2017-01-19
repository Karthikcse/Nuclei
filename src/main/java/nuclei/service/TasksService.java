/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.Tasks;

/**
 * @author Karthikeyan
 *
 */
public interface TasksService extends MainService<Tasks> {

	Iterable<Map<String, Object>> getTasksValues();

	Iterable<Map<String, Object>> gettasksById(Long id,Long functid);
}
