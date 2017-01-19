package nuclei.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import nuclei.domain.Organization;
import nuclei.domain.Transaction;
import nuclei.repository.OrganizationRepository;
import nuclei.repository.TransactionRepository;

@Service("transactionService")
public class TransactionServiceImpl extends GenericService<Transaction> implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public GraphRepository<Transaction> getRepository() {
		return transactionRepository;
	}

	@Override
	public Iterable<Map<String,Object>> getTransactions() {
		return transactionRepository.getTransactions();
	}
	
	@Override
	public Iterable<Map<String,Object>> createRelationWithDeployment(Long transactionid,Long deploymentid){
		return transactionRepository.createRelationWithDeployment(transactionid, deploymentid);
	}
}
