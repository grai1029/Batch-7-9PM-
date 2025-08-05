package DDT;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ToReadDataFromJson {

	public static void main(String[] args) throws IOException, ParseException {
		FileReader fir = new FileReader(".\\src\\test\\resources\\Commondata1.json");
		JSONParser parser = new JSONParser();
		//converts physical file to java object
		Object javaobj = parser.parse(fir);
		//type casting
		JSONObject jsonobj = (JSONObject)javaobj;
		//reading data
		String BROWSER = jsonobj.get("Browser").toString();
		String USERNAME = jsonobj.get("Username").toString();
		String PASSWORD = jsonobj.get("Password").toString();
		//String USERNAME1 = jsonobj.get("Username1").toString();
		System.out.println(BROWSER);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
		//System.out.println(USERNAME1);			
	}

}
