package com.qa.openkart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.openkart.base.BaseTest;
import com.qa.openkart.constants.AppConstant;
import com.qa.openkart.errors.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Desion openKart login page")
@Story("Us 101: Design login page features for open kart application")
@Feature("Feature 201: Adding login faetures")
public class LoginPageTests extends BaseTest {
	
	@Description("Checking login page title")
	@Severity (SeverityLevel.MINOR)
	@Test(priority = 1)
	public void lobinPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		
		Assert.assertEquals(actTitle, AppConstant.LOGIN_PAGE_TITLE, AppError.TITLE_MISSING);
	}
	
	@Description("Checking login page url")
	@Severity (SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageURL();
		Assert.assertTrue(actUrl.contains(AppConstant.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("Checking forgot password link ")
	@Severity (SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.forgotPwdExist());
	}
	
	@Description("Checking user is able to ligin")
	@Severity (SeverityLevel.BLOCKER)
	@Test (priority = 4)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	
		Assert.assertEquals(accountsPage.getAccountPageTitle(), "My Account");
	}
	

}
