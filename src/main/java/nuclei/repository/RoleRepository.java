package nuclei.repository;

import java.util.Map;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import nuclei.domain.Role;

@Repository
public interface RoleRepository extends GraphRepository<Role>{
	
	/**
	 * Get all Role
	 * @return Role list
	 */
	@Query("MATCH (Role:Role{isDelete:false}) RETURN id(Role) as id,Role")
	Iterable<Map<String, Object>> listRole();
	
	@Query("MATCH (role:Role)-[]->(securityTags:SecurityTags{isDelete:false}) where id(role)={0} with securityTags,role RETURN { roles:[{id:id(role),name:role.name,description:role.description,isDelete:role.isDelete}],securityTags:[{id:id(securityTags),securityTagsObj:collect(securityTags)}]} as role")
	Iterable<Map<String, Object>> getRoleById(Long id);

}
