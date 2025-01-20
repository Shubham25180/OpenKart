package com.qa.openkart.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openkart.constants.AppConstant;
import com.qa.openkart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	/**
	 * private by locators
	 */
	private By logout = By.cssSelector("#column-right > div > a:nth-child(13)");
	private By myAccountLink = By.cssSelector("#column-right > div > a:nth-child(1)");
	private By header = By.cssSelector("div#content h2");
	private By search = By.xpath("//*[@id=\"search\"]/input");
	private By searchButton = By.xpath("//*[@id=\"search\"]/span/button/i");

	/**
	 * public page class const..
	 */
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	/**
	 * public page actions/methods
	 * 
	 * @return
	 */

	public String getAccountPageTitle() {



		String title = eleUtil.waitForTitleIs(AppConstant.ACCOUNTS_PAGE_TITLE, 5);
		System.out.println("Account page title : " + title);

		return title;
	}

	public String getAccountPageURL() {
		String url = eleUtil.waitForUrlContains(AppConstant.ACCOUNT_PAGE_URL_FRACTION, 5);
		System.out.println("Account page url : " + url);
		return url;
	}

	public boolean logoutLinkExist() {
		return eleUtil.isElementDisplayed(logout);
	}

	public boolean myAccountLinkExist() {
		return eleUtil.isElementDisplayed(myAccountLink);
	}

	public ArrayList<String> getAccountsPageHeaders() {
		return eleUtil.getElementsTextList(header);
	}

	public SearchResultsPage search(String key) {
		System.out.println("searching  for :" + key);
		eleUtil.doSendKeys(search, key);
		eleUtil.doclick(searchButton);
		return new SearchResultsPage(driver);
	}

}
