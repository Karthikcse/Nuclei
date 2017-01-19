package nuclei.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.Relationship;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class User extends Entity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String email;
	private String password;
	private String googleId;
	private boolean isNewUser;
	private boolean isDelete;
	
	@Relationship(type = "HAS_ROLE")
	@JsonIgnore
	private List<Role> roles = new ArrayList<Role>();
	
	public User(){
	}
	
	public User(User user) {
		super();
		this.name = user.getName();
		this.password = user.getPassword();
		this.roles = user.getRoles();
		this.googleId = user.getGoogleId();
		this.isDelete = user.isDelete();
		this.isNewUser=user.isNewUser();
	}	

	
	public boolean isNewUser() {
		return isNewUser;
	}

	public void setNewUser(boolean isNewUser) {
		this.isNewUser = isNewUser;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}	
	
}