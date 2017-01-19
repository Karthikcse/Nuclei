package nuclei.domain;

public class SecurityTags extends Entity{
	
	private static final long serialVersionUID = 1L;

	private String functionName;
	private boolean createAccess;
	private boolean editAccess;
	private boolean readAccess;	
	private boolean deleteAccess;
	private boolean isDelete;
	
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public boolean isCreateAccess() {
		return createAccess;
	}
	public void setCreateAccess(boolean createAccess) {
		this.createAccess = createAccess;
	}
	public boolean isEditAccess() {
		return editAccess;
	}
	public void setEditAccess(boolean editAccess) {
		this.editAccess = editAccess;
	}
	public boolean isReadAccess() {
		return readAccess;
	}
	public void setReadAccess(boolean readAccess) {
		this.readAccess = readAccess;
	}
	public boolean isDeleteAccess() {
		return deleteAccess;
	}
	public void setDeleteAccess(boolean deleteAccess) {
		this.deleteAccess = deleteAccess;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}