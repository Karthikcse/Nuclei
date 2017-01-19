/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.CatalogueBlueprint;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.neo4j.annotation.Query;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface CatalogueBlueprintRepository extends GraphRepository<CatalogueBlueprint> {

	@Query("MATCH (CatalogueBlueprint:CatalogueBlueprint{isDeleted:'0'}) RETURN id(CatalogueBlueprint) as id,CatalogueBlueprint")
	Iterable<Map<String, Object>> getCatalogueBlueprint();
	
	@Query("match(bp:Blueprint)-[r:RELATED_TO_ScTaxonomy]-(sc:ScTaxonomy)where id(bp)={0} and r.catalogueBlueprintId={0} return id(r) as id,r as catalogueBlueprint")
	Iterable<Map<String, Object>> getRelation(long id);

	@Query("MATCH (a)-[r]-(b) WHERE id(r)= {0} RETURN DISTINCT id (r) as id,r as catalogBlueprint")
	Iterable<Map<String, Object>> getCatalogueBlueprintById(long id);

	// Update step
	@Query("MATCH a-[r:RELATED_TO_ScTaxonomy]-b where r.blueprintId={0} and r.taxonomyId={1} SET r.step={2} RETURN DISTINCT id (r) as id,r as catalogBlueprint")
	Iterable<Map<String, Object>> updateStep(long blueprintId, long taxonomyId, String step);

	//Update position
	@Query("MATCH (a)-[r:RELATED_TO_ScTaxonomy{blueprintId:{0}}]-(b) SET r.x_axis={1},r.y_axis={2} with r RETURN DISTINCT id (r) as id,r as catalogBlueprint")
	Iterable<Map<String, Object>> updatePosition(long blueprintId, String x_axis, String y_axis);
	
	@Query("MATCH (n)-[rel:RELATED_TO_ScTaxonomy{blueprintId:{0},taxonomyId:{1}}]-(r) DELETE rel")
	Iterable<Map<String, Object>> DeleteRelationship(long blueprintId, long taxonomyId);
	
	
}
