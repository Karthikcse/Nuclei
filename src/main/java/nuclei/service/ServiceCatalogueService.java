/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.ServiceCatalogue;

/**
 * @author Karthikeyan
 *
 */
public interface ServiceCatalogueService extends MainService<ServiceCatalogue> {

	Iterable<Map<String, Object>> getServiceCatalogues();

	Iterable<Map<String, Object>> getServiceCatalogueById(Long id);

}
