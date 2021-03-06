package Files;

public class Payload {
	
	public static String AddPlace()
	{
		return "{\r\n"
				+ "\"location\": {\r\n"
				+ "\"lat\": -38.383494,\r\n"
				+ "\"lng\": 33.427362\r\n"
				+ "},\r\n"
				+ "\"accuracy\": 50,\r\n"
				+ "\"name\": \"Frontline house\",\r\n"
				+ "\"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "\"address\": \"29, side layout, cohen 09\",\r\n"
				+ "\"types\": [\r\n"
				+ "\"shoe park\",\r\n"
				+ "\"shop\"\r\n"
				+ "],\r\n"
				+ "\"website\": \"http://google.com\",\r\n"
				+ "\"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
		
	}
	
	public static String coursePrice()
	{
		return "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\r\n"
				+ "\"price\": 50,\r\n"
				+ "\r\n"
				+ "\"copies\": 6\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\r\n"
				+ "\"price\": 40,\r\n"
				+ "\r\n"
				+ "\"copies\": 4\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\r\n"
				+ "\"price\": 45,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}";
		
	}

	public static String AddPlaceAgain(String addressUpdate, String phonNumber)
	{
		String payloadAddress = "{\r\n"
				+ "\"location\": {\r\n"
				+ "\"lat\": -38.383494,\r\n"
				+ "\"lng\": 33.427362\r\n"
				+ "},\r\n"
				+ "\"accuracy\": 50,\r\n"
				+ "\"name\": \"Frontline house\",\r\n"
				+ "\"phone_number\": \""+phonNumber+"\",\r\n"
				+ "\"address\": \""+addressUpdate+"\",\r\n"
				+ "\"types\": [\r\n"
				+ "\"shoe park\",\r\n"
				+ "\"shop\"\r\n"
				+ "],\r\n"
				+ "\"website\": \"http://google.com\",\r\n"
				+ "\"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
		return payloadAddress;
		
	}
	
	public static String addIssuesJira()
	{
		String jiraBody = "{\r\n"
				+ "\"fields\": {\r\n"
				+ "\"project\":\r\n"
				+ "{\r\n"
				+ "\"key\": \"PRJ\"\r\n"
				+ "},\r\n"
				+ "\"assignee\": {\r\n"
				+ "\"name\": \"janvi bhatt\"\r\n"
				+ "},\r\n"
				+ "\"summary\": \"RESTassured jiras\",\r\n"
				+ "\"description\": \"creating sample bug using rest assured API\",\r\n"
				+ "\"issuetype\": {\r\n"
				+ "\"name\": \"Bug\"\r\n"
				+ "}\r\n"
				+ "}\r\n"
				+ "}";
		return jiraBody;
		
	}
	
}
