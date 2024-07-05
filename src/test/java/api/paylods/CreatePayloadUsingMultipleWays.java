package api.paylods;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.http.ContentType;
import io.restassured.internal.support.FileReader;


public class CreatePayloadUsingMultipleWays {
	
	//Create Post Body using Map
	
	
	/*
	 * @Test public void testPostDataUsingHashMap() {
	 * 
	 * HashMap data= new HashMap(); 
	 * data.put("id",0);
	 * 
	 * HashMap categoryObject=new HashMap(); 
	 * categoryObject.put("id", 0);
	 * categoryObject.put("name", "kiran");
	 * 
	 * data.put("category", categoryObject); data.put("name", "doggie");
	 * 
	 * 
	 * String[] urls=
	 * {"https://stackoverflow.com/questions/68959296/using-hashmap-to-create-complex-json"
	 * }; data.put("photoUrls", urls);
	 * 
	 * HashMap<String, Object> tagsarr=new HashMap<>(); tagsarr.put("id", 0);
	 * tagsarr.put("name", "test1");
	 * 
	 * data.put("tags", tagsarr); data.put("status", "available");
	 * 
	 * 
	 * 
	 * given() .contentType(ContentType.JSON) .body(data) .accept(ContentType.JSON)
	 * .when() .post("https://petstore.swagger.io/v2/pet") .then() .statusCode(201)
	 * .body("name", Matchers.equalTo("doggie"));
	 * 
	 * 
	 * 
	 * }
	 */
	
	
	
	@Test
	public void createPostDataUsingJsonObject() throws JSONException
	{
		JSONObject object;
		
		object= new JSONObject();
		object.put("id", "0");
		object.put("username", "kiran");
		object.put("firstName", "kirantest");
		object.put("lastName", "xyz");
		
		object.put("email", "kirantest@test.com");
		object.put("password", "admin@1234");
		object.put("phone", "9878987898");
		object.put("userStatus", "0");
		
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.when()
		.body(object.toString())
		.then()
		.statusCode(201)
		.header("Content-Type","application/json; charset=utf-8")
		.body("email", Matchers.equalTo("kirantest@test.com"));
	}
	
	//4th pproach 
	public void app4() throws JSONException
	{
	    File f = new File("filepath");
	    java.io.FileReader reader = null;
		try {
			reader = new java.io.FileReader(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JSONTokener t=new JSONTokener(reader);
	    JSONObject o= new JSONObject(t);
	    
	}

}
