package com.vkmarines.tutorial;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MercuryTour_AutomaticTest {
	
	private WebDriver driver;
	
	// Localizadores para registrar usuario
	
	By registerLinkLocator = By.linkText("REGISTER");
	By registerPageLocator = By.xpath("//img[@src='images/mast_register.gif']");
	By userNameLocator = By.id("email");
	By passwordLocator = By.name("password");
	By confirmPasswordLocator = By.cssSelector("input[name='confirmPassword']");
	By registerBtnLocator = By.name("submit");
	
	//localizadores para iniciar sesion
	
	By userLocator = By.name("userName");
	By passLocator = By.name("password");
	By signBtnLocator = By.name("submit");

	@Before
	public void setUp() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/test/newtours/index.php");
	}

	@After
	public void tearDown() throws Exception {
		
		//driver.quit();
	}

	@Test
	public void registerUser() throws InterruptedException {
		
		driver.findElement(registerLinkLocator).click();
		Thread.sleep(2000);
		if(driver.findElement(registerPageLocator).isDisplayed()) {
			driver.findElement(userNameLocator).sendKeys("Adminquincejulio");
			driver.findElement(passwordLocator).sendKeys("prueba123456");
			driver.findElement(confirmPasswordLocator).sendKeys("prueba123456");
			
			driver.findElement(registerBtnLocator).click();			
		}
		else {
			System.out.print("Pagina de regsitro no fue encontrada");
		}
		
		List<WebElement> fonts = driver.findElements(By.tagName("font"));
		assertEquals("Note: Your user name is Adminquincejulio.",fonts.get(5).getText());
	}

	@Test
	public void signUser() throws InterruptedException {
		
		if (driver.findElement(userLocator).isDisplayed()) {
			
			driver.findElement(userLocator).sendKeys("Adminquincejulio");
			driver.findElement(passLocator).sendKeys("prueba123456");
			driver.findElement(signBtnLocator).click();
			Thread.sleep(2000);
			
			List<WebElement> td = driver.findElements(By.tagName("td"));
			assertEquals("Login Successfully",td.get(32).getText());
		}
		else
			System.out.print("pagina de login no esta presente");		
	}
	
}
