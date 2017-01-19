/**
 * 
 */
package nuclei.repository;

import nuclei.domain.Taxonomy;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface TaxonomyRepository extends GraphRepository<Taxonomy>{

	
}
