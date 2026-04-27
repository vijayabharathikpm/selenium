package dataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadingDataFromProeperties {

	public static void main(String[] args) throws IOException {
	FileInputStream fis=new FileInputStream("./src\\test\\resources\\CommonData.Properties");
	Properties prop=new Properties();
	prop.load(fis);
	String value = prop.getProperty("browser");
	System.out.println(value);
	String url = prop.getProperty("url");
	System.out.println(url);
	String username = prop.getProperty("username");
	System.out.println(username);
	String pwd = prop.getProperty("password");
	System.out.println(pwd);
	

	}

}
