package com.vkmarines.tutorial;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConectSeleniumGrid {

    private WebDriver driver;   
    private By videoLinkLocator = By.className("VYkpsb");

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://google.com/");
    }

    @Test
    public void testGooglePage() throws MalformedURLException {
        
        // Configura las opciones de Chrome
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserName", "chrome");

        // Conecta al nodo de Selenium Grid
        WebDriver remoteDriver = new RemoteWebDriver(new URL("http://192.168.1.5:4444/wd/hub"), chromeOptions);
        
        WebElement Searchbox = remoteDriver.findElement(By.name("q"));
        Searchbox.clear();
        Searchbox.sendKeys("Introduccion a la automatizacion de software");
        Searchbox.submit();

        // Esperar a que la página se cargue completamente
        WebDriverWait wait = new WebDriverWait(remoteDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Introduccion a la automatizacion de software"));

        // Fluent Wait
        Wait<WebDriver> fwait = new FluentWait<WebDriver>(remoteDriver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        
        WebElement video = fwait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver remoteDriver) {
                return remoteDriver.findElement(videoLinkLocator);
            }
        });
        
        assertTrue(remoteDriver.findElement(videoLinkLocator).isDisplayed());
        remoteDriver.quit();        
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
