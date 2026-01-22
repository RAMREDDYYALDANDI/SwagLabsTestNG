package testlogic;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import BaseTest.Base;
import LoginPOM.loginPOM;
import Utils.JsonUtils;

public class LoginLogicTest extends Base{
	
	
	
	@DataProvider(name = "loginData")
	public Object[][] logininvaalidacrediantials() throws IOException
	{
		JsonUtils Json = new JsonUtils();
		
		List<HashMap<String,String>> loginData = Json.Jsonreader("loginData.json");
		
		Object[][] data = new Object[loginData.size()][1];
		for(int i=0;i<loginData.size();i++)
		{
			data[i][0] = loginData.get(i);
		}
		return data;
		
		
		
	}
	@DataProvider(name= "validLogin")
	public Object[][] validcrediantials() throws IOException
	{
		JsonUtils Json1 = new JsonUtils();
		List<HashMap<String,String>> loginvaliddata = Json1.Jsonreader("correctvalitlogin.json");
		
		Object[][] data1 = new Object[loginvaliddata.size()][1];
		for(int j=0;j<loginvaliddata.size();j++)
		{
			data1[j][0] = loginvaliddata.get(j);
		}
		return data1;
		
		
	}
	
	@Test(dataProvider ="loginData")
	public void logintest(HashMap<String,String> user) {
		
		
		
		loginPOM lan = new loginPOM(driver);
		//for every test the user and the pass should be clear right so we are clearing the data 
		lan.clearuser();
		lan.clearpassword();
		
		//these are comming from the JSON parameters
		lan.enterusername(user.get("username"));
		lan.enterpassword(user.get("password"));
		
		lan.clicklogin();
		 Assert.assertEquals(lan.error(), "Epic sadface: Username and password do not match any user in this service"); 
		
	}
	
	@Test(dataProvider = "validLogin")
	public void validlogin(HashMap<String,String> validuser)
	{
		loginPOM lan1 = new loginPOM(driver);
		lan1.clearuser();
		lan1.clearpassword();
		
		lan1.enterusername(validuser.get("username"));
		lan1.enterpassword(validuser.get("password"));
		
		lan1.clicklogin();
		
		System.out.println("webhook test1");
		System.out.println("webhook test2");
		System.out.println("webhook test3");
		System.out.println("webhook test4");
		System.out.println("webhook test5");
		System.out.println("webhook test6");
		
		
	}

}
