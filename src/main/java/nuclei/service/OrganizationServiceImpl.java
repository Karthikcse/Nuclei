package nuclei.service;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import nuclei.domain.Organization;
import nuclei.repository.OrganizationRepository;

@Service("organizationService")
public class OrganizationServiceImpl extends GenericService<Organization> implements OrganizationService {

	@Autowired
	OrganizationRepository organizationRepository;
	
	@Override
	public GraphRepository<Organization> getRepository() {
		return organizationRepository;
	}

	@Override
	public Iterable<Map<String,Object>> getOrganizations() {
		return organizationRepository.getOrganizations();
	}
	
}
