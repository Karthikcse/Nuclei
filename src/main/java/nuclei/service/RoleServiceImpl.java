package nuclei.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import nuclei.domain.Role;
import nuclei.repository.RoleRepository;

@Service("roleService")
public class RoleServiceImpl extends GenericService<Role>  implements RoleService  {

	@Autowired
	RoleRepository roleRepository;
	

	@Override
	public Iterable<Map<String, Object>> listRole() {
		return roleRepository.listRole();
	}


	@Override
	public GraphRepository<Role> getRepository() {
		return roleRepository;
	}

	@Override
	public Iterable<Map<String, Object>> getRoleById(Long id){
		return roleRepository.getRoleById(id);
	}
}
