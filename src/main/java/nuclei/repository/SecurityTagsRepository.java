/**
 * 
 */
package nuclei.repository;

import nuclei.domain.SecurityTags;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface SecurityTagsRepository extends GraphRepository<SecurityTags>{
	
}

