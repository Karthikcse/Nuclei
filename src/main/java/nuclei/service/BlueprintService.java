/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.Blueprint;
import nuclei.domain.IaaSTemplate;

/**
 * @author Karthikeyan
 *
 */
public interface BlueprintService extends MainService<Blueprint> {

	Iterable<Map<String, Object>> getTemplateBlueprints();

	Iterable<Map<String, Object>> getBlueprintById(Long id);

	IaaSTemplate getIaasTemplate(Long id);
	
	Iterable<Map<String, Object>> generateYamlFile(Long id);
}
