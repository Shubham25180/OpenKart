package com.qa.openkart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openkart.utils.ElementUtil;

public class ProductInfoPage {

	protected WebDriver driver;
	protected ElementUtil eleUtil;
	private Map<String,String> productMap = new HashMap<String,String>();

	/**
	 * private by locators
	 */
	private By productImages = By.cssSelector(".thumbnails li");
	private By productMetaData =By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By header = By.tagName("h1");
	

	/**
	 * public page class const..
	 */
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	/**
	 * public page actions/methods
	 * @return 
	 * 
	 * @return
	 */
	public String getProductHeader() {
		String productName =eleUtil.getElementText(header);
		System.out.println(productName);
		return productName;
	}
	
	public int getProductImageCount() {
		int totalImages = eleUtil.waitForAllElementsVisibility(productImages, 10).size();
		System.out.println("Total Images : "+totalImages);
		return totalImages;
	}
	private void getProductMetaData() {
		List<WebElement> metaData = eleUtil.getElements(productMetaData);
		for(WebElement e: metaData) {
			String text = e.getText();
			String metaKey = text.split(":")[0].trim();
			String metaValue = text.split(":")[1].trim();
			productMap.put(metaKey, metaValue);
		}
		
	}
	private void getProductPriceData() {
		List<WebElement> priceData = eleUtil.getElements(productPriceData);
			String price = priceData.get(0).getText();
			String exTax = priceData.get(1).getText().split(":")[1].trim();
			productMap.put("productPrice", price);
			productMap.put("extaxPrice", exTax);
		
		
	}
	public Map<String,String>  getProductDetailsMap() {
		productMap.put("Header", getProductHeader());
		productMap.put("no of images", String.valueOf(getProductImageCount()));
		getProductMetaData();
		getProductPriceData();
		return productMap;
	}



}
