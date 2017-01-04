package Level1;

//...S.Ex004_Parsing.
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class xJson_Ex004_Parsing {

	public static void main(String[] args) throws ParseException 
	{
		/** TOP Level JSON */
		
		//...JSON DATA 생성은 가장 아래 level 에서 생성함.
		//             Parsing 은 가장 top level 에서 부터 접근함.
		JSONObject data = new JSONObject();
		data.put("name", "서울");
		data.put("age", "10");

		
		/** TOP LEVEL*/
		
		JSONObject response = new JSONObject();
		response.put("response", data);

		String json = response.toJSONString();
		System.out.println("만들어진 json 데이타 : "+json);
		
		/** 결과 : {"response":{"name":"서울","age":"10"}} */
		
		/** 쉽게 보기 
		 * 		{
		 * 			"response":
		 * 				{ 
		 * 					"name":"서울","age":"10"
		 * 				}
		 * 		}
		 */
		
		
		
		/** 파싱 하기 */
		
		// 파싱 객체 생성
		JSONParser paser = new JSONParser();
		
		
		// json object 로 파싱해온다. 
		JSONObject object = (JSONObject) paser.parse(json);
		
		// top level 에서 파싱하기
		
		// 먼저 response 객체를 받아온다.
		JSONObject parse_response = (JSONObject) object.get("response");
		
		//response 안에 데이터 들이 들어 있다.
		
		String name = (String) parse_response.get("name");
		String age = (String) parse_response.get("age");
		
		
		System.out.println("파싱 완료 : name = "+name);
		System.out.println("파싱 완료 : age = "+age);
		
	}

}
//...E.Ex004_Parsing.
