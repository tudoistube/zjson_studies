package zjson;

import org.json.simple.JSONObject;

public class Json_Ex001_Output {

	public static void main(String[] args) {
		
		//...JSON Data �غ���.
		JSONObject json_data = new JSONObject();
		
		//...������ �߰���.
		json_data.put("name", "JoyWins");
		json_data.put("age", new Integer(52));
		json_data.put("gender", new Boolean(true));
		json_data.put("nationality", "S.Korea");
		
		//...JSON ���ڿ��� ��ȯ��.
		String strJson = json_data.toJSONString();
		
		//...�����.
		System.out.println(strJson);

	}

}
