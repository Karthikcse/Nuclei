/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.Blueprint;
import nuclei.domain.ScTaxonomy;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.neo4j.annotation.Query;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface ScTaxonomyRepository extends GraphRepository<ScTaxonomy>{
	
	 @Query("MATCH (ScTaxonomy:ScTaxonomy{isDeleted:'0'}) RETURN id(ScTaxonomy) as id,ScTaxonomy")	
	 Iterable<Map<String,Object>> getScTaxonomyValues();	

	@Query("MATCH (ScTaxonomy)-->(CatalogueBlueprint{isDeleted:'0'}) where id(ScTaxonomy)={0} RETURN id(CatalogueBlueprint) as id, CatalogueBlueprint")
	Iterable<Map<String, Object>> getScTaxonomyById(Long id);	

	@Query("MATCH (Blueprint) where id(Blueprint)={0} RETURN id(Blueprint) as id,Blueprint")   
	Blueprint getBlueprint(Long id);
	
}
