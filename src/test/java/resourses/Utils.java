package resourses;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Utils {
	static RequestSpecification rsq;
	public RequestSpecification addPlaceReqSpec() throws IOException {
		if(rsq == null) {
		PrintStream stream = new PrintStream(new FileOutputStream("logging.txt"));
		 rsq= new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.setBaseUri(getGlobleValues("baseURL"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.build();
		 return rsq;
		}
		return rsq;
	}
	
	public String getGlobleValues(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("D:\\eclips\\CucumberAPI\\src\\test\\java\\resourses\\globle.properties");
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
	}
	
	public String getJsonPath(String responce, String key ) {
		JsonPath js  = new JsonPath(responce);
		return js.getString(key);
	}
	
}
