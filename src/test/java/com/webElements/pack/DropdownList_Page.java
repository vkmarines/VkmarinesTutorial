package com.webElements.pack;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.project.pom.Base;

public class DropdownList_Page extends Base{
	
	By dropdownList_passengers = By.name("passCount");
	By dropdownList_departingfrom = By.name("fromPort");
	/*By userLocator = By.name("userName");
	By passLocator = By.name("password");
	By signBtnLocator = By.name("submit");*/
	

	public DropdownList_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
 /*public void singIn() {
		
		if (isDisplayed(userLocator)) {
			
			type("AdminAgosto",userLocator);
			type("prueba123",passLocator);
			
			click(signBtnLocator);
		}else {
			
			System.out.println("La caja de texto no se encuentra presente");			
		}
	}*/

public String selectDropdownList_pasengers() {
	
	WebElement dropdownList = findElement(dropdownList_passengers);
	List<WebElement> options =  dropdownList.findElements(By.tagName("option"));
	for (int i = 0; i < options.size(); i++) {
		if(getText(options.get(i)).equals("4 ")) {
			click(options.get(i));
		}
		
	}
	
	String selectOption = "";
	for (int i = 0; i < options.size(); i++) {
		if (options.get(i).isSelected())
			selectOption = getText(options.get(i));
		
	}
	return selectOption;
}

public String selectDropdownList_departingfrom() {
	
	Select selectList = new Select(findElement(dropdownList_departingfrom));
	selectList.deselectByVisibleText("Paris");
	return getText(selectList.getFirstSelectedOption());
}

}
