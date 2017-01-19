/**
 * 
 */
package nuclei.service;

import nuclei.domain.SecurityTags;
import nuclei.repository.SecurityTagsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("securityTagsService")
public class SecurityTagsServiceImpl extends GenericService<SecurityTags> implements
SecurityTagsService {

	@Autowired
	private SecurityTagsRepository securityTagsRepository;

	@Override
	public GraphRepository<SecurityTags> getRepository() {
		return securityTagsRepository;
	}
	
}
