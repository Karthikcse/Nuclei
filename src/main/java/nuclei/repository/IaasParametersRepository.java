/**
 * 
 */
package nuclei.repository;

import java.util.Map;

import nuclei.domain.IaasParameters;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karthikeyan
 *
 */
@Repository
public interface IaasParametersRepository extends GraphRepository<IaasParameters>{		
		 
		 @Query("MATCH (IaasParameters:IaasParameters{isDeleted:'0'}) RETURN id(IaasParameters) as id,IaasParameters")	
		 Iterable<Map<String,Object>> getIaasParameters();	
		 
		 @Query("MATCH (IaasParameters {isDeleted:'0'}) where id(IaasParameters)={0} RETURN id(IaasParameters) as id,IaasParameters")   
		 Iterable<Map<String, Object>> getIaasParameterById(Long id);
}
