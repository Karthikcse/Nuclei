package nuclei.repository;

import nuclei.domain.User;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserRepository extends GraphRepository<User>	{
	
	@Query("MATCH (user:User) WHERE user.email = {0} RETURN user")
	User findByLogin(String email);
}
