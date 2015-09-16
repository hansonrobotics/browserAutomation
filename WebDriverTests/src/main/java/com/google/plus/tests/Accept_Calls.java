package com.google.plus.tests;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Accept_Calls {

	private WebDriver driver;
	private WebDriverWait wait; 
//@BeforeSuite

//@BeforeTest	
	
@BeforeClass
public void setup(){
	driver = new FirefoxDriver(); 
	wait = new WebDriverWait(driver, 15);
	
}
@AfterClass
public void teardown() {
//	driver.close(); 
}
@BeforeMethod
public void checkROS(){
//initallize the ROS structure. 
}
@AfterMethod
public void deactiveDocker(){
//deinitallize the Docker	
}

@Test
public void signInloadPage()
{
	String emailId= "sophia.v1.api@gmail.com"; 
	String passwd= "v12345678";
	driver.get("https://plus.google.com");
	//Insert code for sign in Sophia. 
	
	WebElement account= driver.findElement(By.cssSelector("#Email"));
	wait.until(ExpectedConditions.elementToBeClickable(account));
	account.sendKeys(emailId);
	
	Assert.assertEquals(account.getAttribute("value"), emailId);
	System.out.println("Email Entered");
	
	account= driver.findElement(By.cssSelector("#next"));
	wait.until(ExpectedConditions.elementToBeClickable(account));
	account.click();
	
	
	account = driver.findElement(By.cssSelector("#email-display"));
	wait.until(ExpectedConditions.elementToBeClickable(account));
	Assert.assertEquals(account.getText(), emailId);
	
	
	//Now check whether the element is signed in. 
	
	account = driver.findElement(By.cssSelector("#Passwd"));
	wait.until(ExpectedConditions.elementToBeClickable(account));
	

	account.sendKeys(passwd);
	
	//Now Check the follow to this. 
	account = driver.findElement(By.cssSelector("#signIn"));
	wait.until(ExpectedConditions.elementToBeClickable(account));
	account.click();
	
	//Now clicked the button. 
	
	
	Assert.assertEquals(driver.getTitle(), "Google+");
	
}
@Test(dependsOnMethods="signInloadPage")
public void checkUserSophia()
{
	String userName= "Sophia";
	WebElement userID= driver.findElement(By.cssSelector(".gb_P.gb_R"));
	Assert.assertEquals(userID.getText(), userName);
	System.out.println("Sophia is Logged In");
}
@SuppressWarnings("unchecked")
@Test(dependsOnMethods="checkUserSophia")
public void awaitChatMessage()
{
	//Now Wait For the Elements to Continue.

	int count = 0;
	int maxTries = 1000;
	List<WebElement> account = null; 
	List<WebElement> iframes = null; 
	while(true) {
	    try {
	    	/*
	    	 * Check all the available divs with talk_chat_widget 
	    	 * 
	    	 * then check iframes available
	    	 * 
	    	 * then store this
	    	 * 
	    	 * if the change is name or size re-update. But continue the process.
	    	 * 
	    	 *  
	    	 */
	    	
	    	account = driver.findElements(By.cssSelector(".talk_chat_widget"));
	    	Iterator<WebElement> itr = account.iterator();
	    	
	    	while(itr.hasNext())
	    	{
	    		WebElement web= itr.next();
	    		WebElement ifm; 
	    		//to do some overkill
	    		if(web.getTagName().equals("div"))
	    		{
	    			//wait.until(ExpectedConditions.presenceOfElementLocated(web));
	    			//Now get the elements ehre
	    			iframes = web.findElements(By.tagName("iframe"));
	    			
	    			
	    		}
	    		//web.findElement(arg0);
	    	}
	    	System.out.printf("Number of items %d \n", iframes.size());
//	    	Iterator<WebElement> iftr= iframes.iterator();
//	    	while(iftr.hasNext())
//	    	{
//	    		System.out.printf("iframe with %s \n",iftr.next().getClass().getName());
//	    	}
	    } catch(TimeoutException e) {
	    	System.out.printf("Count now Reached %d \n",count);
	        if (++count == maxTries) 
	        	throw e;
	        	{}
	    }
	}

}

public WebElement fluentWait(final By locator){
    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        .withTimeout(10, TimeUnit.SECONDS)
        .pollingEvery(1, TimeUnit.SECONDS)
        .ignoring(NoSuchElementException.class);

    WebElement foo = wait.until(
        new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        }
    );
    return foo;
};

}
