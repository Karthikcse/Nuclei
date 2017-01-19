/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.TemplateFunction;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.neo4j.annotation.Query;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface TemplateFunctionRepository extends GraphRepository<TemplateFunction>{
	
	 @Query("MATCH (TemplateFunction:TemplateFunction{isDeleted:'0'}) RETURN id(TemplateFunction) as id,TemplateFunction")	
	 Iterable<Map<String,Object>> getTemplateFunctionsValues();	

	 @Query("MATCH (TemplateFunction)-->(TemplateAttributes{isDeleted:'0'}) where id(TemplateFunction)={0} RETURN id(TemplateAttributes) as id, TemplateAttributes as Attributes")
	 Iterable<Map<String, Object>> getFunctionById(Long id);	

}
