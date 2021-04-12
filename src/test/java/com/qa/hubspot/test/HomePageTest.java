package com.qa.hubspot.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.utli.Constants;

public class HomePageTest extends BaseTest{
	
	@BeforeClass
	public void homePageSetup(){
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void homepageheaderTest()
	{
		String Homepageheader=  homepage.getHomePageHeaderValue();
		System.out.println("Home Page Header is :"+Homepageheader);
		Assert.assertEquals(Homepageheader, Constants.HOME_PAGE_HEADER);
	}
	@Test(priority = 2)
	public void homepagetittleTest()
	{
		String Homepagetittle = homepage.getHomePageTittle();
		System.out.println("Home Page tittle is :"+Homepagetittle);
		Assert.assertEquals(Homepagetittle,Constants.HOME_PAGE_TITTLE ,Constants.HOME_TITTLE_ERROR_MESSAGE);
	}
	@Test(priority = 3)
	public void useraccountNameTest()
	{
		String accountName = homepage.getuseraccountname();
		System.out.println("Logged in user account Name  is :"+accountName);
		Assert.assertEquals(accountName,prop.getProperty(accountName).trim());
	}
	@Test(priority = 4)
	public void settingsiconExixtTest(){
		Assert.assertTrue(homepage.isExistsettingsicon());
	}
	
	
	
	
	
	
	
	
}
