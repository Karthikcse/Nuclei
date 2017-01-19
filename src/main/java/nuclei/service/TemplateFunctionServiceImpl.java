/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.TemplateFunction;
import nuclei.repository.TemplateFunctionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("templateFunctionService")
public class TemplateFunctionServiceImpl extends
		GenericService<TemplateFunction> implements TemplateFunctionService {

	@Autowired
	private TemplateFunctionRepository templateFunctionRepository;

	@Override
	public GraphRepository<TemplateFunction> getRepository() {
		return templateFunctionRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getTemplateFunctionsValues() {
		return templateFunctionRepository.getTemplateFunctionsValues();
	}

	@Override
	public Iterable<Map<String, Object>> getFunctionById(Long id) {
		return templateFunctionRepository.getFunctionById(id);
	}
}
