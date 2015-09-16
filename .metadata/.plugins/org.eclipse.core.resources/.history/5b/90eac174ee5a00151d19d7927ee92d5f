package com.google.plus.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class FluentWaitForWebElement {

    private static WebDriver driver;
    WebElement countdown;

    @BeforeClass
    public static void setup(){
        driver = new FirefoxDriver();
        //driver.get("http://stuntsnippets.com/javascript-countdown/");
        driver.get("https://plus.google.com");
    }

    @Test
    public void waitForWebElementFluently(){

        new FluentWait<WebElement>(countdown).
                withTimeout(10, TimeUnit.SECONDS).
                pollingEvery(100,TimeUnit.MILLISECONDS).
                until(new Function<WebElement, Boolean>() {
                     public Boolean apply(WebElement element) {
                        return element.getText().endsWith("04");
                    }
                }
                );
    }

    @Test
    public void waitForWebElementFluentlyPredicate(){

        new FluentWait<WebElement>(countdown).
                withTimeout(10, TimeUnit.SECONDS).
                pollingEvery(100,TimeUnit.MILLISECONDS).
                until(new Predicate<WebElement>() {
                    public boolean apply(WebElement element) {
                        return element.getText().endsWith("04");
                    }
                }
                );
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}