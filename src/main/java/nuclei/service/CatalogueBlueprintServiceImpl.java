/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.CatalogueBlueprint;
import nuclei.repository.CatalogueBlueprintRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("catalogueBlueprintService")
public class CatalogueBlueprintServiceImpl extends GenericService<CatalogueBlueprint>
		implements CatalogueBlueprintService {

	@Autowired
	private CatalogueBlueprintRepository catalogueBlueprintRepository;

	@Override
	public GraphRepository<CatalogueBlueprint> getRepository() {
		return catalogueBlueprintRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getCatalogueBlueprint() {
		return catalogueBlueprintRepository.getCatalogueBlueprint();
	}
		
	@Override
	public Iterable<Map<String,Object>> getRelation(Long id){
		return catalogueBlueprintRepository.getRelation(id);
	}
	
	@Override
	public  Iterable<Map<String,Object>> getCatalogueBlueprintById(Long id){
		return catalogueBlueprintRepository.getCatalogueBlueprintById(id);
	}
	
	@Override
	public Iterable<Map<String, Object>> updateStep(Long blueprintId,Long taxonomyId,String step){		
		return catalogueBlueprintRepository.updateStep(blueprintId,taxonomyId,step);
	}
	
	@Override
	public Iterable<Map<String, Object>> updatePosition(Long blueprintId,String x_axis,String y_axis){		
		return catalogueBlueprintRepository.updatePosition(blueprintId,x_axis,y_axis);
	}
	
	@Override
	public Iterable<Map<String, Object>> DeleteRelationship(Long blueprintId,Long taxonomyId){
		return catalogueBlueprintRepository.DeleteRelationship(blueprintId, taxonomyId);
	}
	
}
