package BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	protected Properties prop;
	public String url;
	protected static WebDriver driver;
	public String browserName;
	public void logconfig() throws IOException {
		
		
		prop = new Properties();
		prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
		url = prop.getProperty("url");
	}
	public void initilizer() throws IOException {
		
		logconfig();
		ChromeOptions chromeoptions = new ChromeOptions();
		chromeoptions.setAcceptInsecureCerts(true);
		chromeoptions.addArguments("--incognito");
		chromeoptions.addArguments("--disable-notifications");
		chromeoptions.addArguments("--start-maximized");
		
		EdgeOptions edgeoption = new EdgeOptions();
		edgeoption.setAcceptInsecureCerts(true);
		edgeoption.addArguments("--incognito");
		edgeoption.addArguments("--disable-notifications");
		edgeoption.addArguments("--start-maximized");
		
		browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser").toLowerCase();
		System.out.println("Browser configration opened : " + prop.getProperty("browser"));
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeoptions);
		}else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(edgeoption);
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));	
		
	}
	@BeforeMethod
	public void defaultbrowser() throws IOException {
		initilizer();
		driver.get(url);
	}
	public static String getscreenshot(String testcaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot TS = (TakesScreenshot)driver;
		File source = TS.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"/reports/"+testcaseName+".png");
		file.getParentFile().mkdirs();
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"/reports/"+testcaseName+".png";
		
	}

}
