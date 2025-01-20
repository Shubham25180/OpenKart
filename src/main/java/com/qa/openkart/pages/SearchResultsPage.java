package com.qa.openkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openkart.utils.ElementUtil;

public class SearchResultsPage {
	
	protected WebDriver driver;
	private ElementUtil eleUtil;
	/**
	 * private by locators
	 */
	private By searchProduct = By.cssSelector("div.product-thumb");
	
	private By header = By.cssSelector("div#content h2");
	private By search = By.xpath("//*[@id=\"search\"]/input");
	private By searchButton = By.xpath("//*[@id=\"search\"]/span/button/i");

	/**
	 * public page class const..
	 */
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	/**
	 * public page actions/methods
	 * 
	 * @return
	 */
	public int getSearchProductCount() {
		return eleUtil.waitForAllElementsVisibility(searchProduct, 10).size();
	}
	
	public ProductInfoPage selectProductTest(String name) {
		eleUtil.doClick(By.linkText(name), 10);
		return new ProductInfoPage(driver);
	}
	
	

}
