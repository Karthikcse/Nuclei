/**
 * 
 */
package nuclei.service;

import java.util.Map;

import nuclei.domain.Deployment;
import nuclei.domain.Tasks;
import nuclei.repository.DeploymentRepository;
import nuclei.repository.TasksRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

/**
 * @author Karthikeyan
 *
 */
@Service("deploymentService")
public class DeploymentServiceImpl extends GenericService<Deployment> implements
DeploymentService {

	@Autowired
	private DeploymentRepository deploymentRepository;

	@Override
	public GraphRepository<Deployment> getRepository() {
		return deploymentRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getdeploymentValues() {
		return deploymentRepository.getdeploymentValues();
	}

	@Override
	public Iterable<Map<String, Object>> getdeploymentById(Long id) {
		return deploymentRepository.getdeploymentById(id);
	}
	
	@Override
	public Iterable<Map<String, Object>> createSCRelation(Long deploymentid,Long scid){
		return deploymentRepository.createSCRelation(deploymentid, scid);
	}
	
	@Override
	public Iterable<Map<String, Object>> createIaasConfigRelation(Long deploymentid,Long confgid){
		return deploymentRepository.createIaasConfigRelation(deploymentid, confgid);
	}
	
	@Override
	public Iterable<Map<String, Object>> getBlueprintByDeployId(Long id){
		return deploymentRepository.getBlueprintByDeployId(id);
	}
	
	/*@Override
	public Iterable<Map<String, Object>> getStatusById(Long id){
		return deploymentRepository.getStatusById(id);
	}*/
	
	@Override
	public Iterable<Map<String, Object>> countTransaction(Long id){
		return deploymentRepository.countTransaction(id);
	}
	
	@Override
	public Iterable<Map<String, Object>> countOKCount(Long id){
		return deploymentRepository.countOKCount(id);
	}
	
	/*
	@Override
	public Iterable<Map<String, Object>> generateTransactionList(Long id){
		return deploymentRepository.generateTransactionList(id);
	}*/	
	
}
