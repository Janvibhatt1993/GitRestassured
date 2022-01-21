package RSH.RestAssuredLEarningWithRSH;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;

import org.testng.annotations.Test;


import Files.Payload;
import Files.ReusableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class staticJson {
	
	@Test
	public void postAddressFromSticJsonFile() throws IOException
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response=given().
			log().all().
			queryParam("key", "qaclick123").
			header("Content-Type","application/json").
			body(new String(Files.readAllBytes(Paths.get("C:\\Users\\10688824\\eclipse-workspace\\RestAssuredLEarningWithRSH\\target\\classes\\RSH\\RestAssuredLEarningWithRSH\\NewCity.json")))).
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
		
	}

}
