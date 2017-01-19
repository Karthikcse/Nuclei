/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.TaskFunction;

/**
 * @author Karthikeyan
 *
 */
public interface TaskFunctionService extends MainService<TaskFunction> {

	Iterable<Map<String, Object>> getTaskFunctions();

	Iterable<Map<String, Object>> getTaskFunctionById(Long id);
}
