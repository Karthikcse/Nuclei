package nuclei.domain;


import java.util.*;

import org.neo4j.ogm.annotation.Relationship;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Role extends Entity implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private boolean isDelete;	
	
	@Relationship(type = "Security_Tags", direction = Relationship.OUTGOING)	
	@JsonIgnore
	List<SecurityTags> securityTags=new ArrayList<SecurityTags>();;	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public List<SecurityTags> getSecurityTags() {
		return securityTags;
	}

	public void setSecurityTags(List<SecurityTags> securityTags) {
		this.securityTags = securityTags;
	}	
}