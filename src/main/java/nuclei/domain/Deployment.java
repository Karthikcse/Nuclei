package nuclei.domain;

public class Deployment extends Entity {
	
	private String name;
	private String status;
	private boolean isDeleted;
	
	public Deployment(){
		
	}

	public Deployment(String name,String status,boolean isDeleted){
		this.name=name;
		this.status=status;
		this.isDeleted=isDeleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

