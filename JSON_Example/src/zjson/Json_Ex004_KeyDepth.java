package zjson;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json_Ex004_KeyDepth {

	public static void main(String[] args)  throws ParseException
	{								
		/** TOP Level JSON */							
		//...JSON DATA ������ ���� �Ʒ� level ���� ������.							
		//             Parsing �� ���� top level ���� ���� ������.							
		JSONObject data = new JSONObject();							
		data.put("name", "����");							
		data.put("age", "10");							
									
									
		/** TOP LEVEL*/							
									
		JSONObject response = new JSONObject();							
		response.put("response", data);							
									
		String strJson = response.toJSONString();							
		System.out.println("������� json ����Ÿ : "+strJson);							
									
		/** ��� : {"response":{"name":"����","age":"10"}} */							
									
		/** ���� ����							
		*		{					
		*			response:				
		*				{			
		*					name:"����","age":"10"		
		*				}			
		*		}					
		*/							
									
									
									
		/** �Ľ� �ϱ� */							
									
		// �Ľ� ��ü ����							
		JSONParser paser = new JSONParser();							
									
									
		// json object �� �Ľ��ؿ´�.							
		JSONObject json_obj = (JSONObject) paser.parse(strJson);							
									
		// top level ���� �Ľ��ϱ�							
									
		// ���� response ��ü�� �޾ƿ´�.							
		JSONObject parse_response = (JSONObject) json_obj.get("response");							
									
		//response �ȿ� ������ ���� ��� �ִ�.							
									
		String name = (String) parse_response.get("name");							
		String age = (String) parse_response.get("age");							
									
									
		System.out.println("�Ľ� �Ϸ� : name = "+name);							
		System.out.println("�Ľ� �Ϸ� : age = "+age);							
									
	}

}

