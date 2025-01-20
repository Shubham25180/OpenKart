package com.qa.openkart.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.openkart.base.BaseTest;
import com.qa.openkart.constants.AppConstant;
import com.qa.openkart.pages.SearchResultsPage;

public class AccountsPageTests extends BaseTest{
	
	@BeforeClass
	public void accSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		 String title = accountsPage.getAccountPageTitle();
		 Assert.assertEquals(title, AppConstant.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void accPageUrlTest() {
		String url = accountsPage.getAccountPageURL();
		Assert.assertTrue(url.contains(AppConstant.ACCOUNT_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accountsPage.logoutLinkExist());
	}
	
	@Test
	public void isMyAccountLinkExistTest() {
		Assert.assertTrue(accountsPage.myAccountLinkExist());
	}
	@Test
	public void accPageHeaderTest() {
		ArrayList<String> accountsPageHeaders = accountsPage.getAccountsPageHeaders();
		System.out.println(accountsPageHeaders.toString());
	}
	@Test
	public SearchResultsPage searchTest() {
		accountsPage.search("macbook");
		return new SearchResultsPage(driver);
	}
		
	

}
