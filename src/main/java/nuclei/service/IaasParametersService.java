/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.IaasParameters;

/**
 * @author Karthikeyan
 *
 */
public interface IaasParametersService extends MainService<IaasParameters> {

	Iterable<Map<String, Object>> getIaasParameters();

	Iterable<Map<String, Object>> getIaasParameterById(Long id);
}
