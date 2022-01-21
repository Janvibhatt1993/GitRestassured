package RSH.RestAssuredLEarningWithRSH;
	
	import io.restassured.RestAssured;
	import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import POJO.AddPlace;
	import POJO.Location;
	import static io.restassured.RestAssured.given;

	import java.util.ArrayList;
	import java.util.List;

	public class googleAPIReqResBuilder {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
	RestAssured.baseURI="https://rahulshettyacademy.com";

	AddPlace p =new AddPlace();
	p.setAccuracy(50);
	p.setName("Frontline house");
	p.setPhone_number("(+91) 983 893 3937");
	p.setAddress("29, side layout, cohen 09");
	p.setWebsite("https://rahulshettyacademy.com");
	p.setLanguage("French-IN");
	
	
	List<String> myList =new ArrayList<String>();
	myList.add("shoe park");
	myList.add("shop");

	p.setTypes(myList);
	Location l =new Location();
	l.setLat(-38.383494);
	l.setLng(33.427362);

	p.setLocation(l);
	
	RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
	addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
	
	ResponseSpecification resres = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
	RequestSpecification reqq=given().spec(req)
	.body(p).log().all();
	
	Response res=reqq.when().post("/maps/api/place/add/json").
	then().spec(resres).extract().response();

	String responseString=res.asString();
	System.out.println(responseString);

}
}