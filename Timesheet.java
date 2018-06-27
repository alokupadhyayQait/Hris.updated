import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Timesheet 
{
WebDriver driver;
Timesheet(WebDriver driver)
{
	this.driver=driver;
}
 public Boolean getDisplay(){
	 return driver.findElement(By.className("ng-binding")).isDisplayed();
 }
// public Boolean isTimeSheetPage(){
//     return getDisplay().
// }
}
