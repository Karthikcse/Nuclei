package nuclei.service;

import java.util.Map;
import nuclei.domain.Organization;

public interface OrganizationService extends MainService<Organization>{
	
	public Iterable<Map<String,Object>> getOrganizations();
	
}
