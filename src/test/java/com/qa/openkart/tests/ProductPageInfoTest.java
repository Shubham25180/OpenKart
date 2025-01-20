package com.qa.openkart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.openkart.base.BaseTest;
import com.qa.openkart.constants.AppConstant;
import com.qa.openkart.utils.ExcelUtil;

public class ProductPageInfoTest extends BaseTest {

	@BeforeClass
	public void infoSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider
	public Object[][] daataProvider() {
		return new Object[][]  { {"macbook","MacBook Pro"}, {"imac","iMac"},{"samsung","Samsung Galaxy Tab 10.1"},
		{"samsung","Samsung SyncMaster 941BW"}
		};
	}

	@Test(dataProvider="daataProvider")
	public void productHeaderTest(String searchKey, String clickOn) {
		searchResultsPage = accountsPage.search(searchKey);
		productInfoPage = searchResultsPage.selectProductTest(clickOn);
		softAssert.assertEquals(productInfoPage.getProductHeader(), clickOn);
		softAssert.assertAll();
	}

	@DataProvider
	public Object[][] dataProvider() {
		return new Object[][]  
				{ {"macbook","MacBook Pro",4},
				  {"imac","iMac",3},
				  {"samsung","Samsung Galaxy Tab 10.1",7},
				  {"samsung","Samsung SyncMaster 941BW",1}
		};
	}
	@DataProvider
	public Object[][] dataProviderFromExcel() {
		return ExcelUtil.getTestData(AppConstant.SHOPPING_SHEET_NAME);
	}
	
	
	@Test(dataProvider="dataProviderFromExcel")
	public void productImageCountTest(String a ,String b, String c) {
		searchResultsPage = accountsPage.search(a);
		productInfoPage = searchResultsPage.selectProductTest(b);
		Assert.assertEquals(productInfoPage.getProductImageCount(), Integer.parseInt(c));
	}

	@Test
	public void productInfoTest() {
		searchResultsPage = accountsPage.search("mac book");
		productInfoPage = searchResultsPage.selectProductTest("MacBook Pro");
		Map<String, String> productEct = productInfoPage.getProductDetailsMap();
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: In Stock
//		$2,000.00
//		Ex Tax: $2,000.00
		softAssert.assertEquals(productEct.get("Brand"), "Apple");
		softAssert.assertEquals(productEct.get("Product Code"), "Product 18");
		softAssert.assertEquals(productEct.get("Reward Points"), "800");
		softAssert.assertEquals(productEct.get("Availability"), "In Stock");
		softAssert.assertEquals(productEct.get("productPrice"), "$2,000.00");
		softAssert.assertEquals(productEct.get("extaxPrice"), "$2,000.00");
		softAssert.assertAll();
	}

}
