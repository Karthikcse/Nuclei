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

public class Tasks extends Entity {

	private String task;
	private String description;
	private String isDeleted;
	private String step;
	private String blueprintStep;	
	private String x_axis;
	private String y_axis;
	
	public static final String Task_Function = "Task_Function";
	 
	@Relationship(type = "Task_Function", direction = Relationship.INCOMING)
	@JsonIgnore
	private Set<TaskIaasFunctionRelation> relations = new HashSet<>();	

	public Tasks() {
		
	}
	
	public Tasks(String task, String description, String step, String isDeleted,String blueprintStep,String x_axis,String y_axis) {
		this.task = task;
		this.description = description;
		this.step = step;
		this.isDeleted = isDeleted;
		this.blueprintStep=blueprintStep;
		this.x_axis=x_axis;
		this.y_axis=y_axis;
	}
	//relations
	  public TaskIaasFunctionRelation rate(TemplateFunction function, String value,long functionId,long taskId,String isDeleted,String x_axis,String y_axis,String step) {
	        if (relations == null) {
	        	relations = new HashSet<>();
	        }
	      
	        TaskIaasFunctionRelation relation = new TaskIaasFunctionRelation(this, function,value,functionId,taskId,isDeleted,x_axis,y_axis,step);
	        relations.add(relation);
	        function.addRelations(relation);
	        return relation;
	    }
	  
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public String getBlueprintStep() {
		return blueprintStep;
	}

	public void setBlueprintStep(String blueprintStep) {
		this.blueprintStep = blueprintStep;
	}

	public String getX_axis() {
		return x_axis;
	}

	public void setX_axis(String x_axis) {
		this.x_axis = x_axis;
	}

	public String getY_axis() {
		return y_axis;
	}

	public void setY_axis(String y_axis) {
		this.y_axis = y_axis;
	}

	public Set<TaskIaasFunctionRelation> getRelations() {
		return relations;
	}

	public void setRelations(Set<TaskIaasFunctionRelation> relations) {
		this.relations = relations;
	}

	public static String getRelatedToFunction() {
		return Task_Function;
	}
}