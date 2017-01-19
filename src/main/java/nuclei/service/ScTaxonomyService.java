/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.Blueprint;
import nuclei.domain.ScTaxonomy;

/**
 * @author Karthikeyan
 *
 */
public interface ScTaxonomyService extends MainService<ScTaxonomy> {

	Iterable<Map<String, Object>> getScTaxonomyValues();

	Iterable<Map<String, Object>> getScTaxonomyById(Long id);
	
	Blueprint getBlueprint(Long id);
	
/*	ScTaxonomy updateStep(long blueprintId,long taxonomyId,String step);*/
	
	}
