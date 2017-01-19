/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.CatalogueBlueprint;

/**
 * @author Karthikeyan
 *
 */
public interface CatalogueBlueprintService extends MainService<CatalogueBlueprint> {

	Iterable<Map<String, Object>> getCatalogueBlueprint();
	
	Iterable<Map<String,Object>> getRelation(Long id);
	
	Iterable<Map<String,Object>> getCatalogueBlueprintById(Long id);
	
	Iterable<Map<String, Object>> updateStep(Long blueprintId,Long taxonomyId,String step);
	
	Iterable<Map<String, Object>> updatePosition(Long blueprintId,String x_axis,String y_axis);
	
	Iterable<Map<String, Object>> DeleteRelationship(Long blueprintId,Long taxonomyId);
}
