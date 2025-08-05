package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility {

	public String togetDataFromProperties(String key) throws IOException 
	
	
	{
		//create object of FIS
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\Commondata.properties");
		// object of properties file
		Properties prop = new Properties();
		//load all keys
		prop.load(fis);
		//get properties
		String value = prop.getProperty(key);
		return value;
	}

}
