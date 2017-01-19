/**
 * 
 */
package nuclei.domain;

/**
 * @author Karthikeyan
 *
 */

public class TransactionLifecycle extends Entity {

	private String status;
	private boolean isDeleted;

	public TransactionLifecycle() {

	}

	public TransactionLifecycle(String status, boolean isDeleted) {		
		this.status=status;
		this.isDeleted = isDeleted;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}	
}