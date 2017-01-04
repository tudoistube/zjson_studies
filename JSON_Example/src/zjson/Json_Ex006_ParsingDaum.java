package zjson;

import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import zjon.util.HttpClientGet;

public class Json_Ex006_ParsingDaum {

	public static void main(String[] args) throws ParseException 
	{
		
		// 요청할 주소를 넣으세요
		String url = "https://apis.daum.net/search/book?apikey=3250431f017bec03f26cc7781dfca95b&q=다음카카오&output=json";
		
		// 다음 서버로 부터 json 받아오기 
		String json = HttpClientGet.get_JSONDATA(url);
		
		JSONParser paser = new JSONParser();
		
		JSONObject obj = (JSONObject) paser.parse(json);
		
		// channel 가져오기 
		JSONObject channel = (JSONObject) obj.get("channel");
		
		
		//channel 로 부터 item 받아오기 jsonarray 이다 : [ 로 시작하기 때문에 
		JSONArray item = (JSONArray) channel.get("item");
		
		
		// 하나씩 꺼내보기 
		
		for(int i = 0 ; i < item.size(); i ++)
		{
			JSONObject imsi = (JSONObject) item.get(i);
			
			// 모든 키값 찾아서 출력
			Set key = imsi.keySet();
			
			Iterator<String> iter = key.iterator();
			
			System.out.println("************"+i+"번째 항목 내용 ************");
			while(iter.hasNext())
			{
				String keyName = iter.next();
				
				// 항목 출력 하기
				System.out.println(keyName+" : "+imsi.get(keyName));
			}
			System.out.println("----------------------------------\n");
				
		}
		

	}

}
