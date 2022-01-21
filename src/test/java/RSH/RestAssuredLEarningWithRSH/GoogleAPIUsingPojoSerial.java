package RSH.RestAssuredLEarningWithRSH;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import POJO.googleAPISerialBodyMain;
import POJO.googleApiSerialSubLocation;

public class GoogleAPIUsingPojoSerial {
	public static void main(String[] args) {
		
		RestAssured.basePath = "https://rahulshettyacademy.com";
		
		googleAPISerialBodyMain p = new googleAPISerialBodyMain();
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress("29, side layout, cohen 09");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);

		googleApiSerialSubLocation l = new googleApiSerialSubLocation();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		System.out.println(p.toString());
		/*Response responseBook=given().log().all().
			queryParam("key", "qaclick123").
			
			//header("Content-Type","application/json").
			body(p).
		when().
			post("/maps/api/place/add/json").
		then().
			assertThat().statusCode(200).extract().response();
		System.out.println(responseBook.asString());*/
		Response res=given().log().all().queryParam("key", "qaclick123")
		.body(p)
		.when().post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).extract().response();

		String responseString=res.asString();
		System.out.println(responseString);
		
		
		
	}

}
