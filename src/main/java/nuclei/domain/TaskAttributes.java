/**
 * 
 */
package nuclei.domain;

/**
 * @author Karthikeyan
 *
 */

public class TaskAttributes extends Entity {

	private String name;
	private String value;
	private String isDeleted;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

}
