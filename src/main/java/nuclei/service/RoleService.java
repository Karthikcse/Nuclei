package nuclei.service;

import java.util.Map;

import nuclei.domain.Role;

public interface RoleService extends MainService<Role>{
	
	
	/**
	 * Get all roles 
	 * @return role list
	 */
	public Iterable<Map<String, Object>> listRole();
	
	Iterable<Map<String, Object>> getRoleById(Long id);	
	
}
