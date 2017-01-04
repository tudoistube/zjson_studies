package zjson;									
									
import org.json.simple.JSONArray;									
import org.json.simple.JSONObject;									
import org.json.simple.parser.JSONParser;									
import org.json.simple.parser.ParseException;									
									
public class Json_Ex005_ParsingJsonArray {									
									
	public static void main(String[] args) throws ParseException								
	{								
		/** JSON DATA 생성 부분*/							
		//...JSON DATA 생성은 가장 아래 level 에서 생성함.							
		//             Parsing 은 가장 top level 에서 부터 접근함.							
									
		//...JSONObject 는 유일한 키와 키값의 조합을 만듦.							
		JSONObject data1 = new JSONObject();							
		data1.put("name", "신사");							
		data1.put("age", "52");							
									
									
		JSONObject data2 = new JSONObject();							
		data2.put("name", "숙녀");							
		data2.put("age", "42");							
									
									
		//JSON Array							
		//...JSON 은 MAP 구조를 사용하므로 중복된 Key 값을 가질 수 없음.							
		//   JSONArray 는 이러한 MAP 구조를 사용한 JSON 의 특성을 해결해서 중복된 Key 값을 가진 데이터를 저장할 수 있음.							
		JSONArray jsonArray = new JSONArray();							
									
		//json data 추가							
		jsonArray.add(data1);							
		jsonArray.add(data2);							
									
		// item으로 묶기							
		//...itemlist 라는 key 의 조합으로 묶이므로 JSONObject 으로 담을 수 있음.							
		JSONObject item = new JSONObject();							
		item.put("itemlist", jsonArray);							
									
									
		// items 라는 이름으로 itemlist 넣기							
		//...items 라는 key 의 조합으로 묶이므로 JSONObject 으로 담을 수 있음.							
		JSONObject json = new JSONObject();							
		json.put("items", item);							
									
									
		String strJson = json.toJSONString();							
		System.out.println(strJson);							
									
		/*결과 :  같은 키를 가진 데이터들이 반복되고 있어서 JSONArray 를 사용해야 함.							
		* {"items":							
		*		{"itemlist":					
		*				[			
		*					{"name":"서울","age":"10"},		
		*					{"name":"대전","age":"20"}		
		*				]			
		*		}					
		* }							
		*/							
									
									
		/** 파싱 하기 */							
		JSONParser paser = new JSONParser();							
									
		// 파싱							
		JSONObject json_obj = (JSONObject) paser.parse(strJson);							
									
		//단계별 접근 items 가져오기							
		JSONObject json_obj_items = (JSONObject) json_obj.get("items");							
									
		// itemlist 가져오기 itemlist 의 경우 type이 list 이다.							
		// 이유는 itemlist : 뒤에 "[" 로 배열로 표시되어있다.							
		System.out.println("itemlist의 타입 : "+json_obj_items.get("itemlist").getClass());							
									
									
		JSONArray json_arr_itemlist = (JSONArray) json_obj_items.get("itemlist");							
									
		for(int i = 0 ; i < json_arr_itemlist.size(); i ++)							
		{							
			JSONObject imsi = (JSONObject) json_arr_itemlist.get(i);						
			String name = (String) imsi.get("name");						
			String age = (String) imsi.get("age");						
									
			System.out.println("배열의 "+i+"번째 요소");						
			System.out.println("name : "+name);						
			System.out.println("age : "+age);						
		}							
									
	}								
									
}									
									