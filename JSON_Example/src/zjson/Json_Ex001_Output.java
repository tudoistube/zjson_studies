package zjson;

import org.json.simple.JSONObject;

public class Json_Ex001_Output {

	public static void main(String[] args) {
		
		//...JSON Data 준비함.
		JSONObject json_data = new JSONObject();
		
		//...데이터 추가함.
		json_data.put("name", "JoyWins");
		json_data.put("age", new Integer(52));
		json_data.put("gender", new Boolean(true));
		json_data.put("nationality", "S.Korea");
		
		//...JSON 문자열로 변환함.
		String strJson = json_data.toJSONString();
		
		//...출력함.
		System.out.println(strJson);

	}

}
