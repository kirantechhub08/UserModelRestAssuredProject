package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import api.paylods.UserPayload;
import io.restassured.http.ContentType;

public class UserEndPoints {
	
	
	public static Response createUser(UserPayload payload)
	{
		Response resp=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(Routes.postURL);
		
		return resp;
	}
	
	
	public static Response readUser(String uname)
	{
		Response resp=given()
				.pathParam("username", uname)
				.when()
				.get(Routes.getURL);
		return resp;
	}

	public static Response updateUser(String uname, UserPayload payload)
	{
		Response resp=given()
				.pathParam("username", uname)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload).when()
				.put(Routes.putURL);
		
		return resp;
				
	}
	
	public static Response deleteUser(String uname)
	{
		Response resp=given()
				.pathParam("username", uname)
				.when()
				.delete(Routes.deleteURL);
		
		return resp;
	}
	
}
