package RSH.RestAssuredLEarningWithRSH;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import Files.Payload;
import Files.ReusableMethod;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response=given().
			log().all().
			queryParam("key", "qaclick123").
			header("Content-Type","application/json").
			body(Payload.AddPlace()).
			when().
				post("/maps/api/place/add/json").
			then().
				assertThat().statusCode(200).
				body("scope", equalTo("APP")).
				header("Server","Apache/2.4.18 (Ubuntu)").
				//log().all().
				extract().response().asString();
				System.out.println(response);
				JsonPath js1=ReusableMethod.rawToJson(response);
				String placeID=js1.getString("place_id");
				System.out.println("placeID::"+placeID);
		
			String newAddress = "Holly land, Goa, India";
			//update
			given().
				contentType(ContentType.JSON).
				queryParam("key", "qaclickk123").
				header("Content-Type","application/json").
				body("{\r\n"
					+ "\"place_id\":\""+placeID+"\",\r\n"
					+ "\"address\":\""+newAddress+"\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}").
			when().
				put("/maps/api/place/update/json").
			then().
				assertThat().statusCode(200).
				log().all().
				body("msg", equalTo("Address successfully updated"));
		
		//get
		
		String getResponse=given().
			log().all().
			contentType(ContentType.JSON).
			queryParam("key", "qaclick123").
			queryParam("place_id", placeID).
		when().
			get("/maps/api/place/get/json").
		then().
			statusCode(200).
			//log().all().
			body("address",equalTo(newAddress)).
			extract().response().asString();
			System.out.println(getResponse);
			
			JsonPath js2=ReusableMethod.rawToJson(getResponse);
			String extractedAddress = js2.getString("address");
			System.out.println(extractedAddress);
			assertEquals(extractedAddress, newAddress);
			//assert.assertEquals(extractedAddress,"extractedAddress");
			
			
	}

}
