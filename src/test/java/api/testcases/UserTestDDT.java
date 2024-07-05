package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;



import api.endpoints.UserEndPoints;
import api.paylods.UserPayload;
import api.utilities.DataProvider;
import io.restassured.response.Response;

public class UserTestDDT {
	
	
	@Test(dataProvider = "getAllUserData", dataProviderClass = DataProvider.class )
	public void validatePostRequest(String UserID, String UserName,String FirstName, String LastName, String Email, String	Password,String Phone )
	{
		UserPayload up= new UserPayload();
		up.setId(Integer.parseInt(UserID));
		up.setUsername(UserName);
		up.setFirstname(FirstName);
		up.setLastname(LastName);
		up.setEmail(Email);
		up.setPassword(Password);
		up.setPhone(Phone);
		
	  Response resp=UserEndPoints.createUser(up);
	  int statuscode=resp.getStatusCode();
	  Assert.assertEquals(statuscode, 200);
		
	}

}
