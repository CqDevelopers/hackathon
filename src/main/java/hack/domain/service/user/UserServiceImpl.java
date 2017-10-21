package hack.domain.service.user;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hack.domain.model.User;
import hack.domain.repository.user.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Inject
	UserRepository userRepository;
	
	@Override
    @Transactional(readOnly = true)
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

	@Override
	public User create(User user) {
		
		String userId = UUID.randomUUID().toString();
        Date createdAt = new Date();

        user.setUserId(userId);
        user.setCreatedAt(createdAt);
       
        userRepository.create(user);
        
		return user;
	}

	@Override
	public User update(User user) {
		
		Date createdAt = new Date();
		
		user.setCreatedAt(createdAt);
		
		userRepository.update(user);
		
		return user;
	}

	@Override
	public void delete(String userId) {
		
		userRepository.delete(userId);

	}

}
