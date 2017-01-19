/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.ServiceCatalogue;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface ServiceCatalogueRepository extends GraphRepository<ServiceCatalogue>{
	
	@Query("MATCH (ServiceCatalogue:ServiceCatalogue{isDeleted:'0'}) RETURN id(ServiceCatalogue) as id,ServiceCatalogue")	
	Iterable<Map<String,Object>> getServiceCatalogues();	
	
	//@Query("MATCH (serviceCatalogue:ServiceCatalogue)-[]->(scTaxonomy:ScTaxonomy{isDeleted:'0'}) where id(serviceCatalogue)={0} with serviceCatalogue,scTaxonomy optional match (scTaxonomy)-[]->(catalogueBlueprint:CatalogueBlueprint{isDeleted:'0'}) with serviceCatalogue,scTaxonomy,catalogueBlueprint RETURN {serviceCatalogues:[{id:id(serviceCatalogue),scName:serviceCatalogue.scName,shortDesc:serviceCatalogue.shortDesc,description:serviceCatalogue.description,isDeleted:serviceCatalogue.isDeleted}],scTaxonomy:[{id:id(scTaxonomy),taxName:scTaxonomy.taxName,description:scTaxonomy.description,isDeleted:scTaxonomy.isDeleted,x_axis:scTaxonomy.x_axis,y_axis:scTaxonomy.y_axis,serviceCatalogueStep:scTaxonomy.serviceCatalogueStep}],catalogueBlueprint:[{id:id(catalogueBlueprint),catalogueBlueprintObj:collect(catalogueBlueprint)}]} as serviceCatalogue")
	
	//@Query("MATCH (serviceCatalogue:ServiceCatalogue)-[]->(scTaxonomy:ScTaxonomy{isDeleted:'0'}) where id(serviceCatalogue)={0} with serviceCatalogue,scTaxonomy optional match (scTaxonomy)-[]-(blueprint:Blueprint{isDeleted:'0'}) with serviceCatalogue,scTaxonomy,blueprint RETURN {serviceCatalogues:[{id:id(serviceCatalogue),scName:serviceCatalogue.scName,shortDesc:serviceCatalogue.shortDesc,description:serviceCatalogue.description,isDeleted:serviceCatalogue.isDeleted}],scTaxonomy:[{id:id(scTaxonomy),taxName:scTaxonomy.taxName,description:scTaxonomy.description,isDeleted:scTaxonomy.isDeleted,x_axis:scTaxonomy.x_axis,y_axis:scTaxonomy.y_axis,step:scTaxonomy.step,serviceCatalogueStep:scTaxonomy.serviceCatalogueStep}],Blueprint:[{id:id(blueprint),BlueprintObj:collect(blueprint)}]} as serviceCatalogue")
	@Query("MATCH (serviceCatalogue:ServiceCatalogue)-[]->(scTaxonomy:ScTaxonomy{isDeleted:'0'}) where id(serviceCatalogue)={0} with serviceCatalogue,scTaxonomy optional match (scTaxonomy)-[r:RELATED_TO_ScTaxonomy]-(blueprint:Blueprint{isDeleted:'0'}) with serviceCatalogue,scTaxonomy,blueprint,r RETURN {serviceCatalogues:[{id:id(serviceCatalogue),scName:serviceCatalogue.scName,shortDesc:serviceCatalogue.shortDesc,description:serviceCatalogue.description,isDeleted:serviceCatalogue.isDeleted}], scTaxonomy:[{id:id(scTaxonomy),taxName:scTaxonomy.taxName,description:scTaxonomy.description,isDeleted:scTaxonomy.isDeleted,x_axis:scTaxonomy.x_axis,y_axis:scTaxonomy.y_axis,step:scTaxonomy.step,serviceCatalogueStep:scTaxonomy.serviceCatalogueStep}], relation:[{id:id(r),blueprintName:r.blueprintName,blueprintId:r.blueprintId,taxonomyId:r.taxonomyId,x_axis:r.x_axis,y_axis:r.y_axis,step:r.step}],Blueprint:[{id:id(blueprint),BlueprintObj:collect(blueprint)}]} as serviceCatalogue")
	Iterable<Map<String, Object>> getServiceCatalogueById(Long id);	 	
}
