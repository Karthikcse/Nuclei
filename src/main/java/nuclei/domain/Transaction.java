package nuclei.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.Relationship;
/**
 * @author Karthikeyan
 *
 */
public class Transaction extends Entity implements Serializable{

	private static final long serialVersionUID = 1L;

	private String command;	
	private String trax_uuid;
	private String command_type;
	private String date_time_created;
	private String parameters;
	private String sequence_num;
	private boolean isDeleted;

	//Relationship to TemplateFunction
	@Relationship(type = "transaction", direction = Relationship.INCOMING)	
	List<TemplateFunction> templateFunction = new ArrayList<TemplateFunction>();

	//Relationship to Deployment
	/*@Relationship(type = "deployment", direction = Relationship.OUTGOING)	
	List<Deployment> deployment = new ArrayList<Deployment>();*/

	//Relationship to log
	@Relationship(type = "transaction_log")	
	List<TransactionLog> transactionLog = new ArrayList<TransactionLog>();

	//Relationship to Transaction Lifecycle
	@Relationship(type = "STATUS")	
	List<TransactionLifecycle> transactionLifecycle = new ArrayList<TransactionLifecycle>();

	public Transaction(){

	}

	public Transaction(String command,String trax_uuid,String command_type, String date_time_created,String parameters,String sequence_num,boolean isDeleted){
		this.command=command;
		this.trax_uuid=trax_uuid;
		this.command_type=command_type;
		this.date_time_created=date_time_created;
		this.parameters=parameters;
		this.sequence_num=sequence_num;
		this.isDeleted=isDeleted;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getTrax_uuid() {
		return trax_uuid;
	}

	public void setTrax_uuid(String trax_uuid) {
		this.trax_uuid = trax_uuid;
	}

	public String getCommand_type() {
		return command_type;
	}

	public void setCommand_type(String command_type) {
		this.command_type = command_type;
	}

	public String getDate_time_created() {
		return date_time_created;
	}

	public void setDate_time_created(String date_time_created) {
		this.date_time_created = date_time_created;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getSequence_num() {
		return sequence_num;
	}

	public void setSequence_num(String sequence_num) {
		this.sequence_num = sequence_num;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<TemplateFunction> getTemplateFunction() {
		return templateFunction;
	}

	public void setTemplateFunction(List<TemplateFunction> templateFunction) {
		this.templateFunction = templateFunction;
	}

	/*public List<Deployment> getDeployment() {
		return deployment;
	}

	public void setDeployment(List<Deployment> deployment) {
		this.deployment = deployment;
	}*/

	public List<TransactionLog> getTransactionLog() {
		return transactionLog;
	}

	public void setTransactionLog(List<TransactionLog> transactionLog) {
		this.transactionLog = transactionLog;
	}

	public List<TransactionLifecycle> getTransactionLifecycle() {
		return transactionLifecycle;
	}

	public void setTransactionLifecycle(List<TransactionLifecycle> transactionLifecycle) {
		this.transactionLifecycle = transactionLifecycle;
	}
}
