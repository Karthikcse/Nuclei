/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.Blueprint;
import nuclei.domain.IaaSTemplate;
import nuclei.repository.BlueprintRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("blueprintService")
public class BlueprintServiceImpl extends GenericService<Blueprint> implements
		BlueprintService {

	@Autowired
	private BlueprintRepository blueprintRepository;

	@Override
	public GraphRepository<Blueprint> getRepository() {
		return blueprintRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getTemplateBlueprints() {
		return blueprintRepository.getTemplateBlueprints();
	}

	@Override
	public Iterable<Map<String, Object>> getBlueprintById(Long id) {
		return blueprintRepository.getBlueprintById(id);
	}

	@Override
	public IaaSTemplate getIaasTemplate(Long id) {
		return blueprintRepository.getIaasTemplate(id);
	}
	
	@Override
	public Iterable<Map<String, Object>> generateYamlFile(Long id) {
		return blueprintRepository.generateYamlFile(id);
	}
	
}
