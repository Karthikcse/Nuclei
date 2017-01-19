/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.IaasParameters;
import nuclei.repository.IaasParametersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("iaasParametersService")
public class IaasParametersServiceImpl extends GenericService<IaasParameters>
		implements IaasParametersService {

	@Autowired
	private IaasParametersRepository iaasParametersRepository;

	@Override
	public GraphRepository<IaasParameters> getRepository() {
		return iaasParametersRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getIaasParameters() {
		return iaasParametersRepository.getIaasParameters();
	}

	@Override
	public Iterable<Map<String, Object>> getIaasParameterById(Long id) {
		return iaasParametersRepository.getIaasParameterById(id);
	}
}
