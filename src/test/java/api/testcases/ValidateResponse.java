package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONException;
import org.json.JSONObject;


public class ValidateResponse {
	
	@Test(priority = 1)
	void testJSONResponse()
	{
		//approch 1 to validate json response is chaining
		given()
        .contentType(ContentType.JSON) 		
		.when()
		.get("https://reqres.in/api/user/2")
		.then()
		.statusCode(200)
		.header("Content-Type","application/json; charset=utf-8")
	    .body("data.name", equalTo("fuchsia rose"))
		.body("support.url", equalTo("https://reqres.in/#support-heading"));
		
	}

	@Test(priority = 2)
	void testJSONResponseUsingResponseVariable()
	{   
		JSONObject jobject = null;
		
		try {
		jobject= new JSONObject();
		jobject.put("name", "morpheus");
		jobject.put("job", "leader");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Response res=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(jobject.toString())
		.when()
		.post("https://reqres.in/api/users");
		
		//assert using testng 
		Assert.assertEquals(res.getStatusCode(), 201);
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
	    String firstname=	res.jsonPath().get("name").toString();
	   
		//Assert.assertEquals(firstname,equalTo("morpheus"));
		
	}
	
	@Test(priority=3)
	public void validateDynamicArrayUsingJSONObject() throws JSONException
	{
		
		Response resp=
		given()
		.contentType(ContentType.JSON)
		.when()
		.get("https://reqres.in/api/unknown");
		JSONObject jo=null;
		try {
	jo=new JSONObject(resp.asString());
		}catch(JSONException e)
		{
			e.printStackTrace();
		}
		
		boolean found=false;
		for(int i=0; i<jo.getJSONArray("data").length(); i++)
		{
			String name=jo.getJSONArray("data").getJSONObject(i).get("name").toString();
			
			if(name.equals("fuchsia rose"))
			{
				found=true;
				break;
			}
			
		}
		
		
	}
}


