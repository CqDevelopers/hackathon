package hack.api.userList;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hack.domain.model.User;
import hack.domain.service.user.UserService;

@RestController
@RequestMapping("userlist")
public class UserListController {
	
  @Inject
  UserService userService;
  @Inject
  Mapper beanMapper;
	
  @RequestMapping(method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public List<UserListResource> getTodos() throws Exception{ 
	  
	//List<Map<String, Object>> createUser = new ArrayList<Map<String, Object>>();
	Map<String, Object> map1 = new HashMap<>();
	map1.put("id", "user01");
	map1.put("userName", "クオリカ太郎");
	map1.put("location", "東京都新宿区西新宿８丁目１７−１ 住友不動産新宿グランドタワー23F");
	map1.put("photoUrl", "http://localhost:3000/samples/image");
	//createUser.add(map1);
	//System.out.println(createUser);
	
	//ユーザ情報の登録
	User user = new User();
	BeanUtils.populate(user, map1);
	
	userService.create(user);
	
	//ユーザ情報の取得
    Collection<User> userList = userService.findAll();
    List<UserListResource> userListResponse = new ArrayList<>(); 
    for(User userinfo : userList){
  	  userListResponse.add(beanMapper.map(userinfo, UserListResource.class));
    }
    return userListResponse;
  }

}
