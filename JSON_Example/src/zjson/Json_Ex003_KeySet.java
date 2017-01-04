package zjson;

import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json_Ex003_KeySet {

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
		System.out.println("Ex001_Output : " + strJson);
		
		//...S.Ex002_Parsing.
		//...http://blog.naver.com/pajamasi/220554335879
		JSONParser parser = new JSONParser();
		
		try {
			JSONObject json_obj = (JSONObject) parser.parse(strJson);
			
			System.out.println("Number of Object : " + json_obj.size());
			
			String name = (String) json_obj.get("name");
			//...simple json 의 경우, long 으로 반환하므로 다시 int 로 변환함.
			int age = ((Long)json_obj.get("age")).intValue();
			boolean gender = (boolean) json_obj.get("gender");
			String nationality = (String) json_obj.get("nationality");
			
			User2 User2 = new User2(name, age, gender, nationality);
			
			System.out.println("Ex002_Parsing : " + User2);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//...E.Ex002_Parsing.	
		
		//...S.Ex003_KeySet.
		//...http://blog.naver.com/pajamasi/220554352594
		//JSONParser parser = new JSONParser();
		
		try {
			JSONObject json_obj = (JSONObject) parser.parse(strJson);
			
			System.out.println("Number of Object : " + json_obj.size());
			
			//...get KeySet.
			Set zkeySet = json_obj.keySet();
			
			//...Iterator 설정.
			Iterator<String> iter = zkeySet.iterator();
			int n = 0;
			while(iter.hasNext())
			{
				String strKeyName = iter.next();
				n++;
				System.out.println("Ex003_KeySet : key " + n + " : " + strKeyName + ", type : " + json_obj.get(strKeyName).getClass());
			}//...E.while(iter.hasNext())
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//...E.Ex003_KeySet.

	}

}

class User2
{
	String name;
	int age;
	boolean gender;
	String nationality;
	
	public User2(String name, int age, boolean gender, String nationality) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		return "User2 [name=" + name + ", age=" + age + ", gender=" + gender
				+ ", nationality=" + nationality + "]";
	}
	
	
	
}
