package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utli.Constants;
import com.qa.hubspot.utli.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage
{
	WebDriver driver;
	ElementUtil elementUtil;
	
	//1.Page Locators :By locators
	By emaliId = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By SignUpLink = By.linkText("Sign up");
	
	//2.Create Constructor of the page Class:
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
		
	}
	//3.Page Actions/Page Methods
	@Step("getting login page title...")
	public String getLoginPageTittle()
	{
		return elementUtil.waitForPageTittlePresent(Constants.LOGIN_PAGE_TITTLE,10);
	}
	@Step("cheking sign up link on the login page...")
	public boolean isSignUpLinkExist()
	{
		return elementUtil.doIsDisplayed(SignUpLink);
	}
	@Step("login with : {0} and {1}")
	public HomePage doLogin(String username,String Pwd)
	{
		System.out.println("Login With :"+ username +" : " + Pwd);
		elementUtil.doSendKeys(emaliId, username);
		elementUtil.doSendKeys(password, Pwd);
		elementUtil.doclick(loginButton);
		return new HomePage(driver);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
