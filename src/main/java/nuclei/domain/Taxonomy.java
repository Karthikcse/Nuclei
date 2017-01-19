/**
 * 
 */
package nuclei.domain;

/**
 * @author Karthikeyan
 *
 */

public class Taxonomy extends Entity {

	private String name;
	private String classType;
	private String version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
