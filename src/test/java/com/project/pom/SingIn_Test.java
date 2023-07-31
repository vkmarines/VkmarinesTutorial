package com.project.pom;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class SingIn_Test {
	
	private WebDriver driver;
	SignInPage signInPage;

	@Before
	public void setUp() throws Exception {
		
		signInPage = new SignInPage(driver);
		driver = signInPage.chromeDriverConnection();
		signInPage.visit("https://demo.guru99.com/test/newtours/login.php");
	}

	@After
	public void tearDown() throws Exception {
		//driver.quit();
	}

	@Test
	public void test()throws InterruptedException {
		signInPage.singIn();
		Thread.sleep(2000);
		assertTrue(signInPage.isHomePageDisplayed());
		
	}

}
