package nuclei.repository;

import java.util.Map;
import nuclei.domain.Role;
import nuclei.domain.User;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends GraphRepository<User>{
	
	/**
	 * Get the role for role name
	 * @param role
	 * @return
	 */
	@Query("MATCH (role:Role) WHERE role.name = {0} RETURN id(role) as id, role")
	Role getRole(String role);
	
	@Query("MATCH (role:Role) WHERE id(role) = {0} RETURN id(role) as id, role")
	Role getRoleById(Long role);
	
	/**
	 * Get all user
	 * @return user list
	 */
	@Query("MATCH (User:User{isDelete:false}) RETURN id(User) as id,User")
	Iterable<Map<String, Object>> listUser();
	
	/**
	 * Get user email to invite the user
	 * @param email
	 * @return user
	 */
	@Query("MATCH (user:User) WHERE user.email = {0} RETURN user")
	User getUserByEmail(String email);
	
	/**
	 * To get the user and role details for logged in user
	 */
	@Query("MATCH (user:User)-[r]->(role:Role) WHERE user.login = {0} RETURN user, role")
	User getUserDetail(String login);
	
	
	@Query("MATCH (user:User),(organization:Organization) WHERE id(user) ={0} and id(organization) = {1} create (user)-[r:Relation_To_Organization]->(organization) RETURN user, organization")
	User createRelationWithOrg(Long userid,Long orgid);
	
	@Query("MATCH (user:User)-[]->(role:Role{isDelete:false}) where id(user)={0} with user,role optional match (role)-[]->(securityTags:SecurityTags{isDelete:false}) with user,role,securityTags RETURN { users:[{id:id(user),name:user.name,email:user.email,password:user.password,googleId:user.googleId,isDelete:user.isDelete,isNewUser:user.isNewUser}],role:[{id:id(role),name:role.name,description:role.description,isDelete:role.isDelete}],securityTags:[{id:id(securityTags),securityTagsObj:collect(securityTags)}]} as user")
	Iterable<Map<String, Object>> getUserById(Long id);
	
	@Query("MATCH (user:User)-[]->(role:Role{isDelete:false}) where user.email = {0} with user,role optional match (role)-[]->(securityTags:SecurityTags{isDelete:false}) with user,role,securityTags RETURN { users:[{id:id(user),name:user.name,email:user.email,password:user.password,googleId:user.googleId,isDelete:user.isDelete,isNewUser:user.isNewUser}],role:[{id:id(role),name:role.name,description:role.description,isDelete:role.isDelete}],securityTags:[{id:id(securityTags),securityTagsObj:collect(securityTags)}]} as user")
	Iterable<Map<String, Object>> getUserByEmailObj(String email);	
	
}
