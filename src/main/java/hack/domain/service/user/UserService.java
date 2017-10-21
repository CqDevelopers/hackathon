package hack.domain.service.user;

import java.util.Collection;

import hack.domain.model.User;

public interface UserService {
	
	Collection<User> findAll();
	
	//作成したユーザ情報を返却する
	User create(User user);

	//更新したユーザ情報を返却する
	User update(User user);

    void delete(String userId);

}
