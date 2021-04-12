package com.qa.hubspot.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utli.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Epic("EPIC 100: Design LoginPage Feature for Hub Spot Application")
@Feature("US 101: Design LoginPage Module with title ,sign up and loginForm")
public class LoginPageTest extends BaseTest {

	@Description("verify LoginPage TittleTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void verifyLoginPageTittleTest()
	{
		String tittleLoginPage = loginpage.getLoginPageTittle();
		System.out.println("Login Page tittle is :"+tittleLoginPage);
		Assert.assertEquals(tittleLoginPage, Constants.LOGIN_PAGE_TITTLE,Constants.LOGIN_TITTLE_ERROR_MESSAGE);
	}
	@Description("verify SignUp LinkTest")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 1)
	public void verifysignUplinkTest()
	{
		Assert.assertTrue(loginpage.isSignUpLinkExist(),Constants.SIGNUPLINK_ERROR_MESSAGE);
		
	}
	@Description("HubSpot Login Form Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 3)
	public void loginTest()
	{
		HomePage homepage = loginpage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		String tittle = homepage.getHomePageTittle();
		System.out.println("HomePage Tittle is:" + tittle);
		Assert.assertEquals(tittle, Constants.HOME_PAGE_TITTLE);
	}
	
	
	
	

	
	
	
	
	

}
