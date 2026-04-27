package dataDrivenTesting;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadingDataFromJson {

	public static void main(String[] args) throws IOException, ParseException {
		FileReader reader=new FileReader("./src\\test\\resources\\CommonData.Json");
		JSONParser parser=new JSONParser();
		Object javaobj = parser.parse(reader);
		JSONObject jsonobj=(JSONObject)javaobj;
		String value = jsonobj.get("url").toString();
		System.out.println(value);
				

	}

}
