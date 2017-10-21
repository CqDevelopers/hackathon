package hack.api.pushNotice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("push")
public class PushNoticeController {
	
	private static final String TARGET_HOST = "https://dvrgahua54.execute-api.ap-northeast-1.amazonaws.com/demo/iot/cms/1/push/publish";
	CloseableHttpClient client;
	
	@RequestMapping(method = RequestMethod.POST) 
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public String postPushNotice() throws IOException {
		
		this.client = HttpClients.createDefault();
		
		HttpPost post = new HttpPost(TARGET_HOST);
		
		//json形式のデータをPOSTする
		post.setHeader("Content-Type", "application/json; charset=UTF-8");
		
		//何とかする
		String json = "{\"messages\": {\"apns_sandbox\": \"{\"aps\":{\"alert\":\"PUSH通知テスト\"}}\"	},\"targets\": [{\"token\": \"80ed6e46cbf4029840d82e74460db4e4166fff6960dcf9af4cefcfe5b402c8f4\",\"platform\": \"apns_sandbox\"}]}";
		System.out.println(json);
		StringEntity entity = new StringEntity(json, "UTF-8");
		post.setEntity(entity);
		System.out.println(post);
		
		try(CloseableHttpResponse response = this.client.execute(post)){
			int status = response.getStatusLine().getStatusCode();
			System.out.println(status);
			
			//if(status == HttpStatus.SC_OK){
			if(status == 201){//post(create)完了時のレスポンスコード
				String responseData = EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8);
				System.out.println(responseData);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Hello"; 
    }
	
//	private static final String TARGET_HOST = "http://localhost:8080/hack/api/todos";
//	CloseableHttpClient client;
	
//	@RequestMapping(method = RequestMethod.POST) 
//    @ResponseStatus(org.springframework.http.HttpStatus.OK)
//    public String postPushNotice() throws IOException {
//		
//		this.client = HttpClients.createDefault();
//		
//		HttpPost post = new HttpPost(TARGET_HOST);
//		
//		//ArrayList<NameValuePair> params = new ArrayList<>();
//		//params.add(new BasicNameValuePair("todotitle", "Hello"));
//		//post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//		//post.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
//		
//		//json形式のデータをPOSTする
//		post.setHeader("Content-Type", "application/json; charset=UTF-8");
//		
//		String json = "{\"todoTitle\":\"xjapan\"}";
//		StringEntity entity = new StringEntity(json, "UTF-8");
//		post.setEntity(entity);
//		
//		try(CloseableHttpResponse response = this.client.execute(post)){
//			int status = response.getStatusLine().getStatusCode();
//			System.out.println(status);
//			
//			//if(status == HttpStatus.SC_OK){
//			if(status == 201){//post(create)完了時のレスポンスコード
//				String responseData = EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8);
//				System.out.println(responseData);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return "Hello"; 
//    }

}
