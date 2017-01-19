/**
 * 
 */
package nuclei.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Karthikeyan
 *
 */

public class TaskFunction extends Entity {

	private String functionName;
	private String description;
	private String isDeleted;
	private String tempFunctId;
	private String step;
	private String x_axis;
	private String y_axis;

	@Relationship(type = "taskAttributes", direction = Relationship.OUTGOING)
	List<TaskAttributes> Attributes;

	public TaskFunction() {
		Attributes = new ArrayList<TaskAttributes>();
	}

	public TaskFunction(String functionName, String description,
			String isDeleted, String tempFunctId, String step,String x_axis,String y_axis) {
		this();
		this.functionName = functionName;
		this.description = description;
		this.isDeleted = isDeleted;
		this.tempFunctId = tempFunctId;
		this.step = step;
		this.x_axis=x_axis;
		this.y_axis=y_axis;
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

	public List<TaskAttributes> getAttributes() {
		return Attributes;
	}

	public void setAttributes(List<TaskAttributes> attributes) {
		Attributes = attributes;
	}

	public String getTempFunctId() {
		return tempFunctId;
	}

	public void setTempFunctId(String tempFunctId) {
		this.tempFunctId = tempFunctId;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
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

}