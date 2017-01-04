package zjson;									
									
import org.json.simple.JSONArray;									
import org.json.simple.JSONObject;									
import org.json.simple.parser.JSONParser;									
import org.json.simple.parser.ParseException;									
									
public class Json_Ex005_ParsingJsonArray {									
									
	public static void main(String[] args) throws ParseException								
	{								
		/** JSON DATA ���� �κ�*/							
		//...JSON DATA ������ ���� �Ʒ� level ���� ������.							
		//             Parsing �� ���� top level ���� ���� ������.							
									
		//...JSONObject �� ������ Ű�� Ű���� ������ ����.							
		JSONObject data1 = new JSONObject();							
		data1.put("name", "�Ż�");							
		data1.put("age", "52");							
									
									
		JSONObject data2 = new JSONObject();							
		data2.put("name", "����");							
		data2.put("age", "42");							
									
									
		//JSON Array							
		//...JSON �� MAP ������ ����ϹǷ� �ߺ��� Key ���� ���� �� ����.							
		//   JSONArray �� �̷��� MAP ������ ����� JSON �� Ư���� �ذ��ؼ� �ߺ��� Key ���� ���� �����͸� ������ �� ����.							
		JSONArray jsonArray = new JSONArray();							
									
		//json data �߰�							
		jsonArray.add(data1);							
		jsonArray.add(data2);							
									
		// item���� ����							
		//...itemlist ��� key �� �������� ���̹Ƿ� JSONObject ���� ���� �� ����.							
		JSONObject item = new JSONObject();							
		item.put("itemlist", jsonArray);							
									
									
		// items ��� �̸����� itemlist �ֱ�							
		//...items ��� key �� �������� ���̹Ƿ� JSONObject ���� ���� �� ����.							
		JSONObject json = new JSONObject();							
		json.put("items", item);							
									
									
		String strJson = json.toJSONString();							
		System.out.println(strJson);							
									
		/*��� :  ���� Ű�� ���� �����͵��� �ݺ��ǰ� �־ JSONArray �� ����ؾ� ��.							
		* {"items":							
		*		{"itemlist":					
		*				[			
		*					{"name":"����","age":"10"},		
		*					{"name":"����","age":"20"}		
		*				]			
		*		}					
		* }							
		*/							
									
									
		/** �Ľ� �ϱ� */							
		JSONParser paser = new JSONParser();							
									
		// �Ľ�							
		JSONObject json_obj = (JSONObject) paser.parse(strJson);							
									
		//�ܰ躰 ���� items ��������							
		JSONObject json_obj_items = (JSONObject) json_obj.get("items");							
									
		// itemlist �������� itemlist �� ��� type�� list �̴�.							
		// ������ itemlist : �ڿ� "[" �� �迭�� ǥ�õǾ��ִ�.							
		System.out.println("itemlist�� Ÿ�� : "+json_obj_items.get("itemlist").getClass());							
									
									
		JSONArray json_arr_itemlist = (JSONArray) json_obj_items.get("itemlist");							
									
		for(int i = 0 ; i < json_arr_itemlist.size(); i ++)							
		{							
			JSONObject imsi = (JSONObject) json_arr_itemlist.get(i);						
			String name = (String) imsi.get("name");						
			String age = (String) imsi.get("age");						
									
			System.out.println("�迭�� "+i+"��° ���");						
			System.out.println("name : "+name);						
			System.out.println("age : "+age);						
		}							
									
	}								
									
}									
									