package com.qa.openkart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.openkart.factory.DriverFactory;
import com.qa.openkart.pages.AccountsPage;
import com.qa.openkart.pages.LoginPage;
import com.qa.openkart.pages.ProductInfoPage;
import com.qa.openkart.pages.RegistrationPage;
import com.qa.openkart.pages.SearchResultsPage;

import io.qameta.allure.Step;

public class BaseTest {
	
	protected WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	
	
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	protected RegistrationPage registrationPage;
	
	@Step("Setup: launching {0} browser & init the properties")
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		if (browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@Step("Closing the browser")
	@AfterTest
	public void tearDown(){
		driver.quit();		
	}
	

}
