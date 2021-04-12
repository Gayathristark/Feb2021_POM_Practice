package com.qa.hubspot.utli;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil extends BasePage{
	 
	WebDriver driver;
	JavaScriptUtil jsUtil;
	
	//Creating driver using constructor:
	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
		jsUtil = new JavaScriptUtil(driver);
	}
	public  WebElement getElement(By locator)
	{
		WebElement element = driver.findElement(locator);
		if(highlight.equals("true"))
		{
			jsUtil.flash(element);
		}
		
		return driver.findElement(locator);
	}
	public  void doActionsSendKeys(By locator,String value)
	{
		WebElement element = getElement(locator);
		Actions action = new Actions(driver);
		action.sendKeys(element,value).perform();
	}
	public  void DoActionsClick(By locator)
	{
		WebElement element = getElement(locator);
		Actions action = new Actions(driver);
		action.click(element).perform();
	}
	public  void doSendKeys(By locator , String value)
	{
		getElement(locator).sendKeys(value);
	}
	public  void doclick(By locator)
	{
		getElement(locator).click();
	}
	public String doGetText(By locator)
	{
		return getElement(locator).getText();
		
	}
	public boolean doIsDisplayed(By locator){
		return getElement(locator).isDisplayed();
	}
	public boolean checkWebElementPresent(By locator)
	{
	int elementcount = driver.findElements(locator).size();	
	System.out.println("Total element count :" + elementcount+"for"+locator);
	   if(elementcount>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//******DropDownUtils*******
	public void doDropdownselectByVisibletext(By locator, String value)
	{
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}

	public void doDropdownselectByValue(By locator, String value)
	{
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}
	public void doDropdownselectByIndex(By locator, String value)
	{
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}
	//************* DropDown Based on select util class*****************
	public  List<String> getAllDropDownOptionsList(By locator)
	{
		WebElement element = getElement(locator);
		List<String> optionsValuelist = new ArrayList<String>();
		
		Select select = new Select(element);
		List<WebElement> options_list = select.getOptions();
		
		for(int i=0;i<options_list.size();i++)
		{
			String text= options_list.get(i).getText();
			optionsValuelist.add(text);
		}
		return optionsValuelist;
	}
	
	public  void selectValueFromDropDown(By locator,String value)
	{
		WebElement element = getElement(locator);
		Select select = new Select(element);
		List<WebElement> options_list = select.getOptions();
		
		for(int i=0;i<options_list.size();i++)
		{
			String text= options_list.get(i).getText();
			if(text.equals(value))
			{
				options_list.get(i).click();
				break;
			}
		}
		
	}
	//***********Drop Down with out Select class***************
	public void selectDropDownValueWithoutSelectClass(By locator,String value)
	{
		List <WebElement> industry_options_list = driver.findElements(locator);
		for (int i=0;i<industry_options_list.size();i++)
			{
				String text = industry_options_list.get(i).getText();
				if(text.equals(value))
				{
					industry_options_list.get(i).click();
					break;
				}
			}
		
	}
	//****************WaitUtils*******************//
	public  List<WebElement> VisibilityOfAllElements(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public  void getPageLinksText(By locator, int timeout)
	{
		VisibilityOfAllElements(locator,timeout).stream().forEach(ele ->System.out.println(ele.getText()));
	}
	
	public WebElement waitForElementToBeLocated(By locator,int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	public WebElement waitForElementToBevisible(By locator,int timeout)
	{
		WebElement element  = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		return wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	public String waitForPageTittlePresent(String tittlevalue,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.titleContains(tittlevalue));
		return driver.getTitle();
	}
	
	public  Alert WaitForAlertToBePresent (int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
		
	}
	public boolean waitForUrl(String url, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.urlContains(url));
		
	}
	public void ClickWhenReady(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
		
	}
	
	public  WebElement waitForElementWithFluentWait(By locator,int timeout,int pollingtime)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
 				.withTimeout(Duration.ofSeconds(timeout))//Timeout
 				.pollingEvery(Duration.ofSeconds(pollingtime))//polling period
 				.ignoring(NoSuchElementException.class)//ignoring exception
 				.ignoring(StaleElementReferenceException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public  WebElement retryingElemnet(By locator) throws InterruptedException
	{
		WebElement element = null;
		int attempts = 0;
		while(attempts<30)
		{
			try
			{
			element = driver.findElement(locator);
			break;
			}
			catch(StaleElementReferenceException e)
			{
				try
				{
				Thread.sleep(500);
				}
				catch (InterruptedException e1)
				{
					
				}
			}
			catch(NoSuchElementException e)
			{ 
				try
				{
				Thread.sleep(500);
				}
				catch (InterruptedException e1)
				{
					
				}
			
				System.out.println("Element not found in attempt : "+(attempts+1));
				
			}
			attempts++;
		}
		return element;
		
	}
	
	
}
