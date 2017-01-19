/**
 * 
 */
package nuclei.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Karthikeyan
 *
 */

public class TemplateFunction extends Entity {

	private String functionName;
	private String description;
	private String isDeleted;	

	
	@Relationship(type = "Task_Function", direction = Relationship.INCOMING)
	@JsonIgnore
	private Set<TaskIaasFunctionRelation> relations = new HashSet<>();	
	
	public TemplateFunction() {
	
	}

	public TemplateFunction(String functionName, String description,
			String isDeleted) {
		this();
		this.functionName = functionName;
		this.description = description;
		this.isDeleted = isDeleted;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public Set<TaskIaasFunctionRelation> getRelations() {
		return relations;
	}

	public void setRelations(Set<TaskIaasFunctionRelation> relations) {
		this.relations = relations;
	}

	public void addRelations(TaskIaasFunctionRelation relation) {
		relations.add(relation);
	}

}