package RSH.RestAssuredLEarningWithRSH;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import Files.ReusableMethod;
import POJO.apiCourses;
import POJO.getCourse;
import POJO.webAutomationCourses;
import io.restassured.path.json.JsonPath;
import io.restassured.parsing.Parser;
public class oAuth2_validation_Using_POJO {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
		/*
		//System.setProperty("webdriver.chrome.driver", "‪C:\\Users\\10688824\\eclipse-workspace\\RestAssuredLEarningWithRSH\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//driver.get("http://www.gooogle.com");
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		//driver.findElement(By.linkText("Use another account")).click();
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("janvibhatt1993@gmail.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("janvibhatt1993@gmail.com");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String url = driver.getCurrentUrl();*/
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWhlwPSsn-1cGazHImzv8m1tOoZBCVn5lP1CmMCMwaCYxDBPzoeAbiJdWPS-ucpgdg&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#";
		System.out.println(url);
		String partialCode=url.split("code=")[1];
		String code=partialCode.split("&scope")[0];
		System.out.println(code);
		
		 
		String accessTokenResponse= given().
			urlEncodingEnabled(false).
			queryParams("code",code).
			queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
			queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").
			queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php").
			queryParams("grant_type","authorization_code").
		when().
			log().all().
			post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath path = ReusableMethod.rawToJson(accessTokenResponse);
		String accessToken = path.getString("access_token");
		System.out.println("access token ::"+accessToken);
		
		
		getCourse gc=given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON).
		when().
			get("https://rahulshettyacademy.com/getCourse.php").as(getCourse.class);
		System.out.println(gc.getInstructor());
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		List<apiCourses> api=gc.getCourses().getApi();
		for (int i=0;i<api.size();i++)
		{
			if(api.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println(api.get(i).getPrice());
			}
		}
		
		System.out.println(gc.getCourses().getWebAutomation().get(0).getCourseTitle());
		
		List <webAutomationCourses> webAutomation=gc.getCourses().getWebAutomation();
		ArrayList <String> actualResponse = new ArrayList<String>();
		for (int j=0;j<webAutomation.size();j++)
		{
			System.out.println(webAutomation.get(j).getCourseTitle());
			System.out.println(webAutomation.get(j).getPrice());
			actualResponse.add(webAutomation.get(j).getCourseTitle());
		}
		
		List<String> exctedTitle = Arrays.asList(courseTitles);
		Assert.assertTrue(actualResponse.equals(exctedTitle));
		
		
		
	}
	}


