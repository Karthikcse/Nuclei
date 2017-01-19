package nuclei.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.Relationship;

public class Order extends Entity {
	
	private String history;
	private String instance_type;
	private String version;
	private boolean isDeleted;
	
	@Relationship(type = "TO_DEPLOY")	
	List<Deployment> deployment = new ArrayList<Deployment>();
	
	public Order(){
		
	}

	public Order(String history, String instance_type, String version, boolean isDeleted){
		this.history=history;
		this.instance_type=instance_type;
		this.version=version;
		this.isDeleted=isDeleted;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getInstance_type() {
		return instance_type;
	}

	public void setInstance_type(String instance_type) {
		this.instance_type = instance_type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Deployment> getDeployment() {
		return deployment;
	}

	public void setDeployment(List<Deployment> deployment) {
		this.deployment = deployment;
	}
	
}

