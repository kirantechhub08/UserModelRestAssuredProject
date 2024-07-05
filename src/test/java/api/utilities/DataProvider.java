package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

public class DataProvider {
	
	
	public XLUtility utility;
	
	@org.testng.annotations.DataProvider(name="getAllUserData")
	public String[][] getAllTestData() throws IOException
	{
		
		String path=System.getProperty("user.dir")+"//TestData//RestAssuredTestData.xlsx";
        utility= new XLUtility();
        int columncount=utility.getCellCount(path, "TestData");
        int rowcount=utility.getRowCount(path, "TestData");
        
        String[][] data= new String[columncount][rowcount];
        
        for(int i=1; i<=rowcount; i++)
        {
        	for(int j=0; j<columncount; j++)
        	{
        		data[i-1][j]=utility.getCellValue(path, "TestData", i, j);
        	}
        }
        
       return data; 
	}
	
	@org.testng.annotations.DataProvider(name="getOnlyUserName")
	public String[] getUserNameOnly() throws IOException 
	{
		String path=System.getProperty("user.dir")+"//TestData//RestAssuredTestData.xlsx";
        utility= new XLUtility();
        
        int rowcount=utility.getRowCount(path, "TestData");
        
        String[] usernames=new String[rowcount];
        
        for(int i=1; i<=rowcount; i++)
        {
        	usernames[i-1]=utility.getCellValue(path, "TestData", i, 1);
        }
        
        return usernames;
	}

}
