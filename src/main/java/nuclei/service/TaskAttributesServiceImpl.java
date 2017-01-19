/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.TaskAttributes;
import nuclei.repository.TaskAttributesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("taskAttributesService")
public class TaskAttributesServiceImpl extends GenericService<TaskAttributes>
		implements TaskAttributesService {

	@Autowired
	private TaskAttributesRepository taskAttributesRepository;

	@Override
	public GraphRepository<TaskAttributes> getRepository() {
		return taskAttributesRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getTaskAttributes() {
		return taskAttributesRepository.getTaskAttributes();
	}

	@Override
	public Iterable<Map<String, Object>> getTaskAttributesById(Long id) {
		return taskAttributesRepository.getTaskAttributesById(id);
	}
}
