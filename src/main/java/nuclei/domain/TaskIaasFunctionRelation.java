package nuclei.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@RelationshipEntity(type="TaskFunction")
public class TaskIaasFunctionRelation extends Entity {

	private long functionId;
	private long taskId;
	private String value;
	private String x_axis;
	private String y_axis;
	private String step;
	private String isDeleted;
	
	 @StartNode
	 @JsonIgnore
	 private Tasks tasks;
	    
	 @EndNode
	 @JsonIgnore
	 private TemplateFunction function;
	
	public TaskIaasFunctionRelation() {
	
	}

	public TaskIaasFunctionRelation(Tasks from,TemplateFunction to, String value,long functionId,long taskId,
			String isDeleted,String x_axis,String y_axis,String step) {
		
		this.tasks=from;
		this.function=to;		
		this.value = value;
		this.isDeleted = isDeleted;
		this.x_axis=x_axis;
		this.y_axis=y_axis;
		this.step=step;
		this.functionId=functionId;
		this.taskId=taskId;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public Tasks getTaskRelation() {
		return tasks;
	}

	public void setTaskRelation(Tasks taskRelation) {
		this.tasks = taskRelation;
	}

	public TemplateFunction getFunctionRelation() {
		return function;
	}

	public void setFunctionRelation(TemplateFunction functionRelation) {
		this.function = functionRelation;
	}

	public long getFunctionId() {
		return functionId;
	}

	public void setFunctionId(long functionId) {
		this.functionId = functionId;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

}
