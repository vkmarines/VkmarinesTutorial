package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends Base {
	
	By userLocator = By.name("userName");
	By passLocator = By.name("password");
	By signBtnLocator = By.name("submit");
	By homePageLocator= By.xpath("//img[@src='/images/mast_flightfinder.gif']");

	public SignInPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void singIn() {
		
		if (isDisplayed(userLocator)) {
			
			type("AdminAgosto",userLocator);
			type("prueba123",passLocator);
			
			click(signBtnLocator);
		}else {
			
			System.out.println("La caja de texto no se encuentra presente");			
		}
	}
	
	public Boolean isHomePageDisplayed() {
		
		return isDisplayed(homePageLocator);
	}
	

}
