/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.IaaSTemplate;
import nuclei.repository.IaaSTemplateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("templateService")
public class IaaSTemplateServiceImpl extends GenericService<IaaSTemplate>
		implements IaaSTemplateService {

	@Autowired
	private IaaSTemplateRepository templateRepository;

	@Override
	public GraphRepository<IaaSTemplate> getRepository() {
		return templateRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getIaaSTemplateValues() {
		return templateRepository.getIaaSTemplateValues();
	}

	@Override
	public Iterable<Map<String, Object>> getIaaSTemplateById(Long id) {
		return templateRepository.getIaaSTemplateById(id);
	}

	@Override
	public Iterable<Map<String, Object>> getIaaSTemplateSequence(Long id){
		return templateRepository.getIaaSTemplateSequence(id);
	}
	
	@Override
	public Iterable<Map<String, Object>> getBoshTarget(Long id){
		return templateRepository.getBoshTarget(id);
	}
	
	@Override
	public Iterable<Map<String, Object>> getBoshLogin(Long id){
		return templateRepository.getBoshLogin(id);
	}
	
	@Override
	public Iterable<Map<String, Object>> getBoshUploadStemcell(Long id){
		return templateRepository.getBoshUploadStemcell(id);
	}	
		
	@Override
	public Iterable<Map<String, Object>> getBoshDeploy(Long id){
		return templateRepository.getBoshDeploy(id);
	}
	
	@Override
	public 	Iterable<Map<String, Object>> getBoshRelease(Long id){
		return templateRepository.getBoshRelease(id);
	}
	
	@Override
	public Iterable<Map<String, Object>> getBoshCreateRelease(Long id){
		return templateRepository.getBoshCreateRelease(id);
	}
	@Override
	public Iterable<Map<String, Object>> getBoshDeployment(Long id){
		return templateRepository.getBoshDeployment(id);
	}
}
