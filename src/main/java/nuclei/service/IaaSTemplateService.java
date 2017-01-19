/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.IaaSTemplate;

/**
 * @author Karthikeyan
 *
 */
public interface IaaSTemplateService extends MainService<IaaSTemplate> {

	Iterable<Map<String, Object>> getIaaSTemplateById(Long id);

	Iterable<Map<String, Object>> getIaaSTemplateValues();
	
	Iterable<Map<String, Object>> getIaaSTemplateSequence(Long id);	
	
	Iterable<Map<String, Object>> getBoshTarget(Long id);
	
	Iterable<Map<String, Object>> getBoshLogin(Long id);
	
	Iterable<Map<String, Object>> getBoshUploadStemcell(Long id);
	
	Iterable<Map<String, Object>> getBoshDeploy(Long id);
	
	Iterable<Map<String, Object>> getBoshRelease(Long id);
	
	Iterable<Map<String, Object>> getBoshCreateRelease(Long id);
	
	Iterable<Map<String, Object>> getBoshDeployment(Long id);
}
