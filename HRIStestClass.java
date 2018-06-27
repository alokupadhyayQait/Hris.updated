import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HRIStestClass {
  
	 WebDriver driver;
	    
	    HrisJavaClass loginForm;
	    String usern1;
	    String userp1;
	    String usern2;
	    String userp2;
	    String usern3;
	    String userp3;
	    public HRIStestClass() throws FileNotFoundException,IOException
	    {
	    Properties prop= new Properties();
	    
	    prop.load(new FileInputStream("db.properties"));
	    usern1=prop.getProperty("username1");
	    userp1=prop.getProperty("password1");
	    usern2=prop.getProperty("username2");
	    userp2=prop.getProperty("password2");
	    usern3=prop.getProperty("username3");
	    userp3=prop.getProperty("password3");
	    		
	    	
	    }
	    
	 @Test
	    public void attempt_Login_With_Incorrect_Password_Should_Render_Error_Message(){
	        Assert.assertTrue(loginForm
	                .loginWithIncorrectCredentials(usern1, userp1).contains("Invalid Login"));
	    }
	    
	    @Test 
	    public void attempt_Login_With_correct_Password_Should_Render_Error_Message(){
	    	 if(!this.driver.findElement(By.xpath("//div[@id=\"login\"]")).isDisplayed())
		        {
		        	driver.findElement(By.className("icon-lock")).click();
		        }
	    	 //driver.get("https://hris.qainfotech.com/login.php");
		     //   driver.findElement(By.cssSelector("i.icon-lock")).click();
	    	Assert.assertTrue(loginForm
	                .loginWithCorrectCredentials(usern3, userp3).getDisplay());
	    }
	    @Test
	    public void attempt_Login_With_No_Password_Should_Annotate_Black_Password_Field(){
	    	 if(!this.driver.findElement(By.xpath("//div[@id=\"login\"]")).isDisplayed())
		        {
		        	driver.findElement(By.className("icon-lock")).click();
		        }
	        loginForm.login(usern2, userp2);
	        // red border in password entry
	        Assert.assertTrue(loginForm.isPasswordEntryAnnotated());  
	    }
	    
	    @BeforeClass
	    public void launchBrowser(){
	    	 System.setProperty("webdriver.chrome.driver", "C:\\My_java_prog\\chromedriver_win32\\chromedriver.exe");
	 		driver = new ChromeDriver();
	        driver.get("https://hris.qainfotech.com/login.php");
	        if(!driver.findElement(By.xpath("//div[@id=\"login\"]")).isDisplayed())
	        {
	        	driver.findElement(By.className("icon-lock")).click();
	        }
	        
	        loginForm = new HrisJavaClass(driver);
	        
	    }
	    
	    @AfterClass
	    public void closeBrowser(){
	        driver.quit();
	    }
}
