package nuclei.domain;

public class UserRole extends Entity {
	
	private long role_id;
	
	/*@Relationship(type = "role")
	List<RoleDetails> roleDetails;
	*/
	
	User user;
	
	Role roleDetails;
	
	public Role getRoleDetails() {
		return roleDetails;
	}
	
	public void setRoleDetails(Role roleDetails) {
		this.roleDetails = roleDetails;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	/*	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}*/
	public long getRole_id() {
		return role_id;
	}
	public void setRole_id(long role_id) {
		this.role_id = role_id;
	}
	/*public List<RoleDetails> getRoleDetails() {
		return roleDetails;
	}
	public void setRoleDetails(List<RoleDetails> roleDetails) {
		this.roleDetails = roleDetails;
	}*/
}