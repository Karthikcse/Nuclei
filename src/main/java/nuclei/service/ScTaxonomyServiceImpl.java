/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.Blueprint;
import nuclei.domain.ScTaxonomy;
import nuclei.repository.ScTaxonomyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("scTaxonomy")
public class ScTaxonomyServiceImpl extends GenericService<ScTaxonomy> implements
ScTaxonomyService {

	@Autowired
	private ScTaxonomyRepository scTaxonomyRepository;

	@Override
	public GraphRepository<ScTaxonomy> getRepository() {
		return scTaxonomyRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getScTaxonomyValues() {
		return scTaxonomyRepository.getScTaxonomyValues();
	}

	@Override
	public Iterable<Map<String, Object>> getScTaxonomyById(Long id) {
		return scTaxonomyRepository.getScTaxonomyById(id);
	}
	
	@Override
	public Blueprint getBlueprint(Long id){
		return scTaxonomyRepository.getBlueprint(id);
	}
	
/*	@Override
	public ScTaxonomy updateStep(long blueprintId,long taxonomyId,String step){		
		return scTaxonomyRepository.updateStep(blueprintId,taxonomyId,step);
	}*/
}
