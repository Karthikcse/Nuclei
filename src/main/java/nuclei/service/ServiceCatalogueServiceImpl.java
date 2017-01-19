/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.ServiceCatalogue;
import nuclei.repository.ServiceCatalogueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("serviceCatalogueService")
public class ServiceCatalogueServiceImpl extends GenericService<ServiceCatalogue> implements
ServiceCatalogueService {

	@Autowired
	private ServiceCatalogueRepository serviceCatalogueRepository;

	@Override
	public GraphRepository<ServiceCatalogue> getRepository() {
		return serviceCatalogueRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getServiceCatalogues() {
		return serviceCatalogueRepository.getServiceCatalogues();
	}

	@Override
	public Iterable<Map<String, Object>> getServiceCatalogueById(Long id) {
		return serviceCatalogueRepository.getServiceCatalogueById(id);
	}

}
