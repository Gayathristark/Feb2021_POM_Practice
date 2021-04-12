package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utli.Constants;
import com.qa.hubspot.utli.ElementUtil;



public class ContactsPage extends BasePage
{
	
	ElementUtil elementUtil;
	private WebDriver driver;
	
	private By header = By.cssSelector("span.private-dropdown__item__label");
	private By createContactPrimary = By.xpath("//span[text()='Create contact']");
	private By emailId = By.xpath("//input[@data-field='email']");
	private By firstName = By.xpath("//input[@data-field='firstname']");
	private By lastName = By.xpath("//input[@data-field='lastname']");
	private By jobTitle = By.xpath("//textarea[@data-field='jobtitle']");
	private By createContactSecondary = By.xpath("(//span[text()='Create contact'])[position()=2]");
	private By contactsBackLink = By.xpath("(//*[text()='Contacts'])[position()=2]");
	
	
	public ContactsPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		this.driver = driver;
	}
	public String grtContactsPageTitile()
	{
		return elementUtil.waitForPageTittlePresent(Constants.CONTACTS_PAGE_TITTLE, 10);
	}
	public String getContactsPageHeaderValue()
	{
		return elementUtil.doGetText(header);
	}
	public void createContact(String emailId,String firstname,String lastname, String jobTitle)
	{
		elementUtil.waitForElementToBeLocated(createContactPrimary, 10);
		elementUtil.doclick(createContactPrimary);
		elementUtil.waitForElementToBeLocated(this.emailId, 5);
		elementUtil.doSendKeys(this.emailId,emailId);
		
		elementUtil.doSendKeys(this.firstName, firstname);
		elementUtil.doSendKeys(this.lastName,lastname);
		
		elementUtil.waitForElementToBevisible(this.jobTitle, 5);
		elementUtil.doSendKeys(this.jobTitle,jobTitle);
		
		elementUtil.ClickWhenReady(createContactSecondary, 5);
		elementUtil.ClickWhenReady(contactsBackLink, 10);
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}