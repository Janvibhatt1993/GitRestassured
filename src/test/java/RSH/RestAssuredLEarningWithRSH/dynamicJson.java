package RSH.RestAssuredLEarningWithRSH;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import Files.ReusableMethod;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class dynamicJson {
	
	@Test(dataProvider = "AddAddressAgainGoogle")
	public void addAddress(String newAddress,String newNumber)
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String addressPost = given().
			queryParam("key", "qaclick123").
			header("Content-Type","application/json").
			body(Payload.AddPlaceAgain(newAddress,newNumber)).
		when().
			post("/maps/api/place/add/json").
		then().
			assertThat().
			statusCode(200).
			log().all().
			extract().response().asString();
			System.out.println(addressPost);
			
		JsonPath js =ReusableMethod.rawToJson(addressPost);
		String newPlaceID=js.get("place_id");
		System.out.println(newPlaceID);
		
		given().
			header("Content-Type","application/json").
			queryParam("key", "qaclicks123").
			body("{\r\n"
				+ "\"place_id\":\""+newPlaceID+"\"\r\n"
				+ "}").
		when().
			delete("/maps/api/place/delete/json").
		then().
			statusCode(200).log().all();
	}
	
	@DataProvider(name="AddAddressAgainGoogle")
	public Object[][] getData()
	{
		return new Object[][] { {"11 ramivas dadabhai road","123456"}, {"Mahesh nagar ramwadi","12345678"}, {"bhanukunj vimal nagar banaglore","3456789"} };
		
	}

}
