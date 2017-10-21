package hack.domain.repository.user;

import java.util.Collection;

import hack.domain.model.User;

public interface UserRepository {
	
	//User findOne(String userId);
	
	Collection<User> findAll();
	
	void create(User user);

    void update(User user);

    void delete(String userId);

}
