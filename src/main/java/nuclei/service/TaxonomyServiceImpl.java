/**
 * 
 */
package nuclei.service;

import nuclei.domain.Taxonomy;
import nuclei.repository.TaxonomyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("taxonomyService")
public class TaxonomyServiceImpl extends GenericService<Taxonomy> implements
		TaxonomyService {

	@Autowired
	private TaxonomyRepository taxonomyRepository;

	@Override
	public GraphRepository<Taxonomy> getRepository() {
		return taxonomyRepository;
	}
}
