package api.testcases;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.paylods.PojoPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateRequestBodyMultipleWay {
	
	
	@Test(priority=0)
	public void createPayloadDataUsingMap()
	{
	Map mapdata=new HashMap();
	mapdata.put("name","morpheus");
	mapdata.put("job","leader");
	
    Response res=given()
    .contentType(ContentType.JSON)
    .body(mapdata)
    .when()
    .post("https://reqres.in/api/users");
    
    
    Assert.assertEquals(res.getStatusCode(), 201);
    Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
     res.getBody().prettyPrint(); 
     String n=res.jsonPath().get("name").toString();
     boolean found=false;
     if(n.equals("morpheus"))
     {
    	 found=true;
     }
     
     System.out.println(found);
	}
	
	
	
	@Test(priority=1)
	public void deleteMethodUsingJSONObjectPayload()
	{
		JSONObject o= new JSONObject();
		try {
		o.put("name", "morpheus");
		o.put("job", "zion resident");
		
		}catch(JSONException e)
		{
			e.printStackTrace();
		}
		given()
		.contentType(ContentType.JSON)
		.body(o.toString())
		.pathParam("uid", 2)
		.when()
		.put("https://reqres.in/api/users/{uid}")
		.then()
		.statusCode(200)
		.body("name", equalTo("morpheus"));	
	}
	
	@Test(priority=2)
	public void updateDetailsUsingPojoClass()
	{
	     PojoPayload p= new PojoPayload();
	     p.setEmail("eve.holt@reqres.in");
	     p.setPassword("cityslicka");
	     
	     
	     given()
	     .contentType(ContentType.JSON)
	     .body(p)
	     .when()
	     .post("https://reqres.in/api/login")
	     .then()
	     .statusCode(200)
	     .body("token", equalTo("QpwL5tke4Pnpja7X4"));
	     
	     
	}
	
	
	
	@Test(priority=3)
	public void externalFile() throws FileNotFoundException, JSONException
	{
		//To open a file 
		String fpath="C:\\Users\\admin\\Desktop\\SDET_RestAssured\\Final_RestAssuredAPI\\testjson.json";
	    java.io.File f= new java.io.File(fpath);
	    
	      java.io.FileReader fr=new java.io.FileReader(f);
	      JSONTokener jt= new JSONTokener(fr);
	      
	      JSONObject o= new JSONObject(jt);
	      
	      
	      given()
	      .contentType(ContentType.JSON)
	      .body(o.toString())
	      .when()
	      .post("https://reqres.in/api/login")
	      .then()
	      .statusCode(400)
	      .body("error", equalTo("Missing password"));
		
		
		
	}
	
	

}
