package RSH.RestAssuredLEarningWithRSH;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.File;

import Files.Payload;
import Files.ReusableMethod;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class workingWithJIRA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//------create issue--------------	
		RestAssured.baseURI = "https://janvirestassured.atlassian.net";
		
		SessionFilter session = new SessionFilter();
		String res = given().relaxedHTTPSValidation().
						auth().preemptive().basic(ReusableMethod.credUser(), ReusableMethod.credPass()).
						header("Content-Type","application/json").
						log().all().
						body(Payload.addIssuesJira()).filter(session).
					when().
						post("/rest/api/2/issue").
					then().
						statusCode(201).extract().asString();
				System.out.println(res.toString());
				JsonPath j3=ReusableMethod.rawToJson(res);
				String idJira=j3.get("id");
				System.out.println(idJira);
		
	//--------Add comments------------			
	String commentRes=given().relaxedHTTPSValidation().pathParam("key", idJira).
						auth().preemptive().basic(ReusableMethod.credUser(), ReusableMethod.credPass()).
			   			header("Content-Type","application/json").log().all().
			   			body("{\r\n"
			   					+ "    \"body\": \"Adding comment for bug id 10023\"\r\n"
			   					+ "}").
			   			filter(session).
			   		when().
			   			post("/rest/api/2/issue/{key}/comment").
			   		then().
			   			statusCode(201).
			   			log().all().extract().asString();
	
	//-------Add attachments-------------
	String resAttachment=given().relaxedHTTPSValidation().
						pathParam("key", idJira).
						multiPart("file", new File("JIRAattachment.txt")).
						auth().preemptive().basic(ReusableMethod.credUser(), ReusableMethod.credPass()).
						header("X-Atlassian-Token","no-check").
						header("Content-Type","multipart/form-data").
						log().all().
						filter(session).
					when().
						post("/rest/api/2/issue/{key}/attachments").
					then().
						statusCode(200).
						log().all().extract().asString();
	System.out.println(resAttachment.toString());
	
	//-----retrive data from created bug-------------
	
	String getISsueDetails=given().relaxedHTTPSValidation().
					header("Content-Type","application/json").
					queryParam("fields", "comment").
					pathParam("key", "10015").
					auth().preemptive().basic(ReusableMethod.credUser(), ReusableMethod.credPass()).
					log().all().
				when().
					get("/rest/api/2/issue/{key}").
				then().
					statusCode(200).
					log().all().extract().asString();
			JsonPath p1 = ReusableMethod.rawToJson(getISsueDetails)	;
			
			int countOfResponse = p1.getInt("fields.comment.comments.size()");
			System.out.println(countOfResponse);
			
			
			for (int i=0;i<countOfResponse;i++)
			{
				String expectedCommentId = "10016";
				String actualID;
				String expectedCommennt = "Adding comment for bug id 10020";
				actualID = p1.getString("fields.comment.comments["+i+"].id").toString();
				System.out.println("actual ID is::"+actualID);
				String actualComment = p1.getString("fields.comment.comments["+i+"].body").toString();
				System.out.println(actualComment);
				
				if (actualID.equalsIgnoreCase(expectedCommentId))
					
				{
					System.out.println("matching");
					System.out.println("actual ID is::"+actualID+" expected ID is::"+expectedCommentId);
					assertEquals(actualComment, expectedCommennt);
					System.out.println("actual Comment ::" +actualComment+" expected comment::"+expectedCommennt);
					break;
				}
			}
			
			
			
				
			
}
}