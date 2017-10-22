package hack.api.userList;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hack.domain.model.Total;
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
  public Map<String, Object> getTodos() throws Exception{ 
	  
/*
    //List<Map<String, Object>> createUser = new ArrayList<Map<String, Object>>();
	Map<String, Object> map1 = new HashMap<>();
	map1.put("id", "user01");
	map1.put("userName", "クオリカ太郎");
	map1.put("location", "東京都新宿区西新宿８丁目１７−１ 住友不動産新宿グランドタワー23F");
	map1.put("photoUrl", "http://localhost:3000/samples/image");
	//createUser.add(map1);
	//System.out.println(createUser);
	
	User user1 = new User();
	
	//mapからbeanへの変換
	BeanUtils.populate(user1, map1);
	
	//ユーザ情報の登録
	userService.create(user1);
	
	Map<String, Object> map2 = new HashMap<>();
	map2.put("id", "user02");
	map2.put("userName", "クオリカ二郎");
	map2.put("location", "東京都新宿区西新宿８丁目１７−１ 住友不動産新宿グランドタワー23F");
	map2.put("photoUrl", "http://localhost:3000/samples/image");

	User user2 = new User();
	BeanUtils.populate(user2, map2);
	
	userService.create(user2);
	
	//ユーザ情報の取得
    Collection<User> userList = userService.findAll();
    List<UserListResource> userListResponse = new ArrayList<>(); 
    for(User userinfo : userList){
  	  userListResponse.add(beanMapper.map(userinfo, UserListResource.class));
    }
    return userListResponse;
    
*/
      
      Map<String, Object> valueMap = new HashMap<>();
      
      List<User> users = new ArrayList<User>();
      //1人目 ユーザ情報登録
      User user = new User();
      user.setId("user01");
      user.setUserName("クオリカ太郎");
      user.setLocation("東京都新宿区西新宿８丁目１７−１ 住友不動産新宿グランドタワー23F");
      user.setPhotoUrl("http://localhost:3000/samples/image");
      users.add(user);
      
      //2人目 ユーザ情報登録
      user = new User();
      user.setId("user02");
      user.setUserName("クオリカ二郎");
      user.setLocation("東京都新宿区西新宿８丁目１７−１ 住友不動産新宿グランドタワー23F");
      user.setPhotoUrl("http://localhost:3000/samples/image");
      users.add(user);
      
    //3人目 ユーザ情報登録
      user = new User();
      user.setId("user03");
      user.setUserName("クオリカ三朗");
      user.setLocation("東京都新宿区西新宿８丁目１７−１ 住友不動産新宿グランドタワー23F");
      user.setPhotoUrl("http://localhost:3000/samples/image");
      users.add(user);
      
      //「list」の内容を書きこむ
      valueMap.put("list", users);
      
      
      
      //ユーザ数を格納
      int total = 3;
      Total userTotal = new Total();
      userTotal.setTotal(total);
      
      //「resultInfo」に内容を書きこむ
      valueMap.put("resultInfo", userTotal);
      
      
      //JSON形式に変換するメソッドへ
//      String json = makeJSONString(valueMap);
//      System.out.println(json);
      return valueMap;
  }
  
  private static String makeJSONString(Map<String,Object> valueMap){
      
      ObjectMapper objectMapper = new ObjectMapper();
      String jsonString = null;
      
      try {
          jsonString = objectMapper.writeValueAsString(valueMap);
      } catch (JsonGenerationException e) {
          e.printStackTrace();
      } catch (JsonMappingException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
      
//      System.out.println(jsonString);
      return jsonString;
  }

}
