package nuclei.service;

import java.util.Map;
import nuclei.domain.Role;
import nuclei.domain.User;

public interface AdminService extends MainService<User>{
	
	
	/**
	 * Fetch role 
	 * @param role
	 * @return
	 */
	public Role getRole(String role);
	
	public Role getRoleById(Long role);
	
	/**
	 * Get all User 
	 * @return User list
	 */
	public Iterable<Map<String, Object>> listUser();
	
	/**
	 * Get the user and role details for logged in user
	 * @param login
	 * @return
	 */
	public User getUserDetail(String login);
	
	/**
	 * Get user email to invite the user
	 * @param name
	 * @return
	 */
	public User getUserByEmail(String email); 
	
	public User createRelationWithOrg(Long userid,Long orgid);
	
	public Iterable<Map<String, Object>> getUserById(Long id);
	
	public Iterable<Map<String, Object>> getUserByEmailObj(String email);
	
}
