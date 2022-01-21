package RSH.RestAssuredLEarningWithRSH;

import static org.testng.Assert.assertEquals;

import Files.Payload;
import io.restassured.path.json.JsonPath;
public class complexJsonParse {
	public static void main(String[] args) {
	JsonPath js = new JsonPath(Payload.coursePrice());
	//---size only apply on arrary
	int Count=js.getInt("courses.size()");
	System.out.println(Count);
	
	//---print purchase amount
	int amount=js.getInt("dashboard.purchaseAmount");
	System.out.println(amount);
	
	String title = js.get("courses[0].title");
	System.out.println(title);
	
	//---print all title and prices
	for(int i=0; i<Count; i++)
	{
		String courseTitle = js.get("courses["+i+"].title");
		String coursesPrice = js.get("courses["+i+"].price").toString();
		System.out.println("Title : "+courseTitle+" Price: "+coursesPrice);	
	}
	
	//---print RPA title and copies sold---
	
	for(int i=0; i<Count; i++)
	{
		String courseTitle = js.get("courses["+i+"].title");
		if (courseTitle.equalsIgnoreCase("RPA"))
		{
			String courseCopies = js.get("courses["+i+"].copies").toString();
			System.out.println("Title : "+courseTitle+"copies: "+courseCopies);
			break;
		}
	}
}

}