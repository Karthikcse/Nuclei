package nuclei.service;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import nuclei.domain.Role;
import nuclei.domain.User;
import nuclei.repository.AdminRepository;

@Service("adminService")
public class AdminServiceImpl extends GenericService<User> implements AdminService {

	@Autowired
	AdminRepository adminRepository;
	
	@Override
	public GraphRepository<User> getRepository() {
		return adminRepository;
	}

	@Override
	public Role getRole(String role) {
		return adminRepository.getRole(role);
	}
	
	@Override
	public Role getRoleById(Long role){
		return adminRepository.getRoleById(role);
	}
	
	@Override
	public Iterable<Map<String, Object>> listUser() {
		return adminRepository.listUser();
	}
	
	@Override
	public User getUserDetail(String login){
		return adminRepository.getUserDetail(login);
	}

	@Override
	public User getUserByEmail(String email) {
		return adminRepository.getUserByEmail(email);
	}
	
	@Override
	public User createRelationWithOrg(Long userid,Long orgid) {
		return adminRepository.createRelationWithOrg(userid,orgid);
	}
	
	@Override
	public Iterable<Map<String, Object>> getUserById(Long id){
		return adminRepository.getUserById(id);
	}
	
	@Override
	public Iterable<Map<String, Object>> getUserByEmailObj(String email){
		return adminRepository.getUserByEmailObj(email);
	}	
}
