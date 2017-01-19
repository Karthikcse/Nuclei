package nuclei.service;

import java.util.Map;

import nuclei.domain.Organization;
import nuclei.domain.Transaction;

public interface TransactionService extends MainService<Transaction>{
	
	public Iterable<Map<String,Object>> getTransactions();
	
	public Iterable<Map<String,Object>> createRelationWithDeployment(Long transactionid,Long deploymentid);
	
}
