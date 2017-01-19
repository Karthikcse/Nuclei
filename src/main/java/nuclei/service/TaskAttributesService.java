/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.TaskAttributes;

/**
 * @author Karthikeyan
 *
 */
public interface TaskAttributesService extends MainService<TaskAttributes> {

	Iterable<Map<String, Object>> getTaskAttributes();

	Iterable<Map<String, Object>> getTaskAttributesById(Long id);
}
