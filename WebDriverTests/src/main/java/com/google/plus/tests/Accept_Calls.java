package com.google.plus.tests;

import java.util.ArrayList;
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
	wait = new WebDriverWait(driver, 10);
	
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

	while(true) {
	    try {
	    	List<WebElement> account = null; 
	    	List<WebElement> iframes = null; 
	    	List<String> divs_names = new ArrayList<String>();
	    	driver.switchTo().defaultContent();
	    	account = driver.findElements(By.cssSelector(".talk_chat_widget"));
	    	Iterator<WebElement> itr = account.iterator();
	    	
	    	while(itr.hasNext())
	    	{
	    		WebElement web= itr.next();
	    		WebElement ifm;
	    		//To avoid the preload functionality for the hangout widget. 
	    		if(!web.getAttribute("id").equals("preld_m"))
	    		{
	    			System.out.println(web.getAttribute("id"));
	    			divs_names.add(web.getAttribute("id"));
	    		}
	    		
	    		
	    			//wait.until(ExpectedConditions.presenceOfElementLocated(web));
	    			//Now get the elements ehre
	    		//iframes = web.findElements(By.tagName("iframe"));

	    		//web.findElement(arg0);
	    	}
	    	if(divs_names != null && divs_names.size() != 0)
	    	{
	    	Iterator<String> str = divs_names.iterator();
	    	System.out.printf("Size of the divs %d \n",divs_names.size());
	    	while(str.hasNext())
	    	{
	    		//Concurrency is needed here.
	    		/*
	    		 * Do some processing to get a perfect value for item.
	    		 * 
	    		 *  Currently the values are obtained from the name of div element it contains
	    		 *  it so. As it stands now in September 16, 2015 the iframes have the same name
	    		 *  up to _m part of the element name.  
	    		 */
	    		String frame_name = str.next();
	    		int value = frame_name.lastIndexOf("_");
	    		frame_name=frame_name.substring(0, value);
	    		//This is the problem. 
	    		
	    		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame_name));
	    		//WebElement iframe = driver.findElement(By.cssSelector(frame_name));
	    		
	    		driver.switchTo().frame(frame_name);
	    		
	    		//driver.switchTo().f
	    		//System.out.println(driver.getPageSource());
	    		System.out.printf("Context Switched %s \n",frame_name);
	    	}
	    	//Now the context have been switch indicating the above function has to make 
	    	//a wait for the system.
	    	
	    	
	    	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".Xv.yR.uq.Q")));
	    	WebElement button = driver.findElement(By.cssSelector(".Xv.yR.uq.Q"));
	    	//WebElement button = fluentWait(By.cssSelector("Xv.yR.uq.Q"));
	    	button.click();
	    	} else
				System.out.println("No Calls Currently");
	    } 
    

	    catch(TimeoutException e) {
	    	System.out.printf("Count now Reached %d \n",count);
	        if (++count == maxTries) 
	        	//throw e;
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
