/**
 * 
 */
package nuclei.domain;

/**
 * @author Karthikeyan
 *
 */

public class IaasParameters extends Entity {

	private String iaasConfig;
	private String isDeleted;
	
	public String getIaasConfig() {
		return iaasConfig;
	}
	public void setIaasConfig(String iaasConfig) {
		this.iaasConfig = iaasConfig;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}	

}
