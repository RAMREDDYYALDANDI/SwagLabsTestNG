package Utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractClass {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	public AbstractClass(WebDriver driver) 
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
	}
	
	public void waituntilelementvissible(WebElement element) 
	{
		wait.until(ExpectedConditions.visibilityOf(element));
		
		
	}
	public void waitforelement(WebElement locator)
	{
		
		wait.until(ExpectedConditions.visibilityOf( locator));
		
	}

}
