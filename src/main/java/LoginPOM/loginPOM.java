package LoginPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.AbstractClass;

public class loginPOM extends AbstractClass {
	
	public WebDriver driver;
	public loginPOM (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css ="#user-name")
	WebElement username;
	
	@FindBy(css = "#password")
	WebElement password;
	
	@FindBy(id = "login-button")
	WebElement login;
	
	@FindBy(css="h3[data-test='error']")
	WebElement errormessage;
	
	public void enterusername(String user)
	{
		username.sendKeys(user);
	
	
	}
	
	public void enterpassword(String pass)
	{
		password.sendKeys(pass);
	
	
	}
	
	public void clicklogin()
	{
		login.click();
	}
	public String error()
	{
		waituntilelementvissible(errormessage);
		return errormessage.getText();
		
	}
	
	public void clearuser() {
		
		waitforelement(username);
		username.clear();
	}
	public void clearpassword()
	{
		waitforelement(password);
		password.clear();
		
	}
	
	
	
	
	
	

}
