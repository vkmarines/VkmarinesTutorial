package com.vkmarines.tutorial;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchTests {

	private WebDriver driver;
	
	By videoLinkLocator = By.className("VYkpsb");
	
	@Before
	public void setUp() {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://google.com/");
	}
	
	@Test
	public void testGooglePage() {
		
		WebElement Searchbox = driver.findElement(By.name("q"));
		Searchbox.clear();
		Searchbox.sendKeys("Introduccion a la automatizacion de software");
		Searchbox.submit();
		
		//Espera implicita
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//assertEquals("Introduccion a la automatizacion de software - Google search", driver.getTitle());
		
		//Explicit wait
		//WebDriverWait ewait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//ewait.until(ExpectedConditions.titleContains("Introduccion a la automatizacion de software"));
		
		//Fluent Wait
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
						.withTimeout(Duration.ofSeconds(10))
						.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class)
						;
				
		WebElement video = fwait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(videoLinkLocator);
			}
				
		});
				
		assertTrue(driver.findElement(videoLinkLocator).isDisplayed());		
		
	}

	@After
	public void tearDown() {
		
		driver.quit();
	}
	
	
}
