/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.TemplateFunction;

/**
 * @author Karthikeyan
 *
 */
public interface TemplateFunctionService extends MainService<TemplateFunction> {

	Iterable<Map<String, Object>> getTemplateFunctionsValues();

	Iterable<Map<String, Object>> getFunctionById(Long id);
}
