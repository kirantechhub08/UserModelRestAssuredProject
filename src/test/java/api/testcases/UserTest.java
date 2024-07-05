package api.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.paylods.UserPayload;
import io.restassured.response.Response;

public class UserTest {
	
	Faker fk;
	UserPayload up;
	
	
	@BeforeClass
	public void generateTestData()
	{
		fk= new Faker();
		up= new UserPayload();
		up.setId(fk.idNumber().hashCode());
		up.setUsername(fk.name().username());
		up.setFirstname(fk.name().firstName());
		up.setLastname(fk.name().lastName());
		up.setEmail(fk.internet().safeEmailAddress());
		up.setPassword(fk.internet().password(5, 8));
		up.setPhone(fk.phoneNumber().cellPhone());
	}

	@Test(priority=1)
	public void validatePostRequest()
	{
		Response res=UserEndPoints.createUser(up);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
	}
	
	
	@Test(priority=2)
	public void validateGetRequest()
	{
		Response resp=UserEndPoints.readUser(this.up.getUsername());
		resp.then().log().all();
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void validatePutRequest()
	{
		//upddating firstname, lastname, and emailaddress
				up.setFirstname(fk.name().firstName());
				up.setLastname(fk.name().lastName());
				up.setEmail(fk.internet().safeEmailAddress());
				
		Response resp=UserEndPoints.updateUser(this.up.getUsername(), this.up);
		resp.then().log().all();
		Assert.assertEquals(resp.getStatusCode(), 200);
		
		
		//checking data after updating above testdata
		Response respcheckafterupdate=UserEndPoints.readUser(this.up.getUsername());
		Assert.assertEquals(respcheckafterupdate.getStatusCode(), 200);
		
	}
	
	@Test(priority=4)
	public void validateDeleteRequest()
	{
		
		Response resp=UserEndPoints.deleteUser(this.up.getUsername());
		resp.then().log().all();
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
}
