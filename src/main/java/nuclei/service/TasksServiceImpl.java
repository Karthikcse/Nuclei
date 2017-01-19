/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.Tasks;
import nuclei.repository.TasksRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("taskService")
public class TasksServiceImpl extends GenericService<Tasks> implements
		TasksService {

	@Autowired
	private TasksRepository tasksRepository;

	@Override
	public GraphRepository<Tasks> getRepository() {
		return tasksRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getTasksValues() {
		return tasksRepository.getTasksValues();
	}

	@Override
	public Iterable<Map<String, Object>> gettasksById(Long id,Long functid) {
		return tasksRepository.gettasksById(id,functid);
	}
}
