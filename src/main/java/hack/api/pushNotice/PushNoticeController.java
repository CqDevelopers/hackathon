package hack.api.pushNotice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("push")
public class PushNoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(PushNoticeController.class);
	
	//push通知呼び出し用ホストのURL
	private static final String TARGET_HOST = "https://dvrgahua54.execute-api.ap-northeast-1.amazonaws.com/demo/iot/cms/1/push/publish";
	CloseableHttpClient client;
	
	@RequestMapping(method = RequestMethod.POST) 
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public void postPushNotice(@RequestParam("type") String type) throws IOException {
		//TODO:引数としてデバイストークンを取得する
		
		callHttpRequest(type);
		
		//繰り返しの終了処理をどう呼び出すか
		if(type == "repeat"){
			callHttpRequest(type);
		}
		
    }
		
	//Push通知のHTTPRequestを処理する
	private void callHttpRequest(String type) {
		
		this.client = HttpClients.createDefault();
		
		HttpPost post = new HttpPost(TARGET_HOST);
		
		//json形式のデータをPOSTするためのリクエストヘッダー
		post.setHeader("Content-Type", "application/json; charset=UTF-8");
		
		Map<String,Object> valueMap = new HashMap<String,Object>();
		
		//TODO:引数としてデバイストークンを投げる
		setValueMap(valueMap, type);
		
		//Map→JSONへの変換処理呼び出し
		String json = makeJSONString(valueMap);
		StringEntity entity = new StringEntity(json, "UTF-8");
		post.setEntity(entity);
		
		try(CloseableHttpResponse response = this.client.execute(post)){
			int status = response.getStatusLine().getStatusCode();
			logger.info("responce status is " + status);
			
			if(status == 202){//push通知のPOSTリクエスト正常完了通知のレスポンスコードは202
				String responseData = EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8);
				logger.info("responce data is " + responseData);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//HTTPリクエストのリクエストボディーの情報をMap形式で作成する
	private static void setValueMap(Map<String, Object> valueMap, String type) {
		
		//TODO：引数として受け取ったデバイストークンの情報を変数に格納する
		String deviceToken = "80ed6e46cbf4029840d82e74460db4e4166fff6960dcf9af4cefcfe5b402c8f4";
		
		Map<String, Object> apns_sandbox = new HashMap<>();
		
		if(type == "share"){
			apns_sandbox.put("apns_sandbox", "{\"aps\":{\"alert\":\"共有リクエスト通知を取得しました\"}}");
		} else if(type == "release"){
			apns_sandbox.put("apns_sandbox", "{\"aps\":{\"alert\":\"公開要求通知を取得しました\"}}");
		} else {
			apns_sandbox.put("apns_sandbox", "{\"aps\":{\"alert\":\"不正なリクエストです\"}}");
		}
		
		valueMap.put("messages", apns_sandbox);
		
		Map<String, Object> mapTerminal = new HashMap<>();
		mapTerminal.put("token", deviceToken);
		mapTerminal.put("platform", "apns_sandbox");
		
		List<Map<String, Object>> familyFirstNames = new ArrayList<>();
		familyFirstNames.add(mapTerminal);
		
		valueMap.put("targets", familyFirstNames);
	}
	
	//Map→JSONへの変換を行う
	private String makeJSONString(Map<String, Object> valueMap) {
		
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
		
		return jsonString;
	}

}
