package selenium.practice.testcases;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;



public class Flipkart {
	
	
	
	@Test
	public void addPhoneToCart()
	{
	     //WebDriverManager.chromedriver().setup();
	     System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
	     WebDriver driver= new ChromeDriver();
	     driver.get("https://www.flipkart.com/");
	     driver.manage().window().maximize();
	     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	     WebElement flipkartSearchTextBox=driver.findElement(By.xpath("//input[@class='Pke_EE']"));  
	     flipkartSearchTextBox.sendKeys("iphone 15 pro max");
	     flipkartSearchTextBox.submit();
	     
	     List<WebElement> list= driver.findElements(By.xpath("//div[@class='KzDlHZ']"));
	     
	     for(WebElement l: list)
	     {
	    	 String title=l.getText();
	    	 if(title.equalsIgnoreCase("Apple iPhone 15 Pro Max (Black Titanium, 256 GB)"))
	    	 {
	    		l.click();
	    		break;
	    	 }
	     }
	     
	     try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     
	   Set<String> allWindows=driver.getWindowHandles();
	   String currentWindow=driver.getWindowHandle();
	   
	   for(String window:allWindows)
	   {
		   if(! currentWindow.equals(window))
		   {
			   driver.switchTo().window(window);
			   break;
		   }
	   }
	     
	   System.out.println(driver.getCurrentUrl());
	   
	   //Add to cart 
	   
	     driver.findElement(By.xpath("//button[@class='QqFHMw vslbG+ In9uk2']")).click();
	     
	     try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	    driver.findElement(By.xpath("//span[text()='Add Item']")).click();
	    
	    String amount=driver.findElement(By.xpath("//div[@class='_1Y9Lgu']")).getText();
	    
	    System.out.println("actual price"+ amount);
	    if(amount.equals("₹1,39,990"))
	    {
	    	System.out.println("Price is correct");
	    }else
	    {
	    	System.out.println("Price is not correct ");
	    }
	     
	     
	     
	     
	   
	   
	   
	   
	}

}
