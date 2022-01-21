package RSH.RestAssuredLEarningWithRSH;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class sumValidation {
	
	@Test
	public void sumOfValidation()
	{
		JsonPath js = new JsonPath(Payload.coursePrice());
		int Count=js.getInt("courses.size()");
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		int sumOfcopiesWithPrice = 0;
		for(int i=0; i<Count; i++)
		{
			int coursePrice = js.getInt("courses["+i+"].price");
			int courseCopies = js.getInt("courses["+i+"].copies");
			System.out.println(coursePrice);
			int amount = coursePrice*courseCopies;
			System.out.println(amount);
			sumOfcopiesWithPrice = sumOfcopiesWithPrice+amount;
			System.out.println(sumOfcopiesWithPrice);
		}
		assertEquals(purchaseAmount, sumOfcopiesWithPrice);
		System.out.println("matching amount go ahead!!");
		
	}
	
}
