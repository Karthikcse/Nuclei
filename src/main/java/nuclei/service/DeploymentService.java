/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.Deployment;
import nuclei.domain.Tasks;

/**
 * @author Karthikeyan
 *
 */
public interface DeploymentService extends MainService<Deployment> {

	Iterable<Map<String, Object>> getdeploymentValues();

	Iterable<Map<String, Object>> getdeploymentById(Long id);

	Iterable<Map<String, Object>> createSCRelation(Long deploymentid,Long scid);
	
	Iterable<Map<String, Object>> createIaasConfigRelation(Long deploymentid,Long confgid);

	Iterable<Map<String, Object>> getBlueprintByDeployId(Long id);	

//	Iterable<Map<String, Object>> getStatusById(Long id);

	/*Iterable<Map<String, Object>> generateTransactionList(Long id);*/
	
	Iterable<Map<String, Object>> countTransaction(Long id);
	
	Iterable<Map<String, Object>> countOKCount(Long id);
	
}


