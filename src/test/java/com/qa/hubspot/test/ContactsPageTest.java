package com.qa.hubspot.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.utli.Constants;
import com.qa.hubspot.utli.ExcelUtil;

public class ContactsPageTest extends BaseTest {
	@BeforeClass
	public void homePageSetup(){
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homepage.gotoContactsPage();
	}

	
	@Test(priority=2)
	public void verifyContactspageTitle()
	{
		String tittle =  contactsPage.grtContactsPageTitile();
		System.out.println("Contacts Page tittle is :"+tittle);
		Assert.assertEquals(tittle, Constants.CONTACTS_PAGE_TITTLE,Constants.CONTACTS_TITTLE_ERROR_MESSAGE);
	}
	@Test(priority=1)
	public void verifyContactspageHeader()
	{
		String header = contactsPage.getContactsPageHeaderValue();
		System.out.println("Contacts Page header is :"+header);
		Assert.assertEquals(header, Constants.CONTACTS_PAGE_HEADER);
		
	}
	
	@DataProvider
	public Object[][] getContactsTestdata()
	{
		Object data[] [] =  ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}
	
	
	
	@Test(priority=3,dataProvider = "getContactsTestdata")
	public void createContactTest( String emailId,String firstName,String LastName,String jobTitle)
	{
		contactsPage.createContact(emailId,firstName,LastName,jobTitle);
	}
	
	
	
	
	
	
	

}
