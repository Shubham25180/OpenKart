package com.qa.openkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openkart.constants.AppConstant;
import com.qa.openkart.logger.Log;
import com.qa.openkart.utils.ElementUtil;
import com.qa.openkart.utils.TimeUtils;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	/**
	 * private by locators
	 */
	private By email = By.cssSelector("#input-email");
	private By password = By.cssSelector("#input-password");
	private By forgotPwdLink = By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/div[2]/a");
	private By loginButton = By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input");
	private By register = By.cssSelector("#column-right > div > a:nth-child(2)");
	
	/**
	 * public page class const..
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	/**
	 * public page actions/methods
	 * @return 
	 */
	
	@Step("getting login page title.... ")
	public String getLoginPageTitle() {		
		String title = eleUtil.waitForTitleIs(AppConstant.LOGIN_PAGE_TITLE, TimeUtils.DEFAULT_MEDIUM_TIME);
//		System.out.println("login page title : "+title);
		Log.info("login page title : "+title);
		
		return title;
	}

	@Step("getting login page url.... ")
	public String getLoginPageURL() {
		String url =eleUtil.waitForUrlContains(AppConstant.LOGIN_PAGE_URL_FRACTION, TimeUtils.DEFAULT_MEDIUM_TIME);
//		System.out.println("login page url : "+url);
		Log.info("login page url : "+url);
		return url;
	} 
	
	@Step("checking for fpwd exist....")
	public boolean forgotPwdExist() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}
	@Step("login with username :{0} || password : {1} ")
	public AccountsPage doLogin(String userName,String pwd) {
		eleUtil.doSendKeys(email, userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.waitThenClick(loginButton, 5);
		return new AccountsPage (driver);
	}
	@Step ("navigating to the registration page...")
	public RegistrationPage navigationToRegisterPage() {
		eleUtil.doClick(register, TimeUtils.DEFAULT_MEDIUM_TIME);
		return new RegistrationPage(driver);
	}
	
	

}
