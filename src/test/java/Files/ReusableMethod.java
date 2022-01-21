package Files;

import io.restassured.path.json.JsonPath;

public class ReusableMethod {
	
	public static JsonPath rawToJson(String ResponseName) {
		JsonPath js2 = new JsonPath(ResponseName);
		return js2;
	}
	
	public static String credUser()
	{
		return "janvibhatt1993@gmail.com";	
	}
	public static String credPass()
	{
		return "7XMu0s2qh8dSa6cJCXxeE687";	
	}

}
