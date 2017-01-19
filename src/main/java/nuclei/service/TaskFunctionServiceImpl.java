/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.TaskFunction;
import nuclei.repository.TaskFunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("taskFunctionService")
public class TaskFunctionServiceImpl extends GenericService<TaskFunction>
		implements TaskFunctionService {

	@Autowired
	private TaskFunctionRepository taskFunctionRepository;

	@Override
	public GraphRepository<TaskFunction> getRepository() {
		return taskFunctionRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getTaskFunctions() {
		return taskFunctionRepository.getTaskFunctions();
	}

	@Override
	public Iterable<Map<String, Object>> getTaskFunctionById(Long id) {
		return taskFunctionRepository.getTaskFunctionById(id);
	}
}
