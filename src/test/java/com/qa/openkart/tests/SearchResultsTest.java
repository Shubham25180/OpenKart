package com.qa.openkart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.openkart.base.BaseTest;

public class SearchResultsTest extends BaseTest {

	@BeforeClass
	public void searchResultSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductCountData() {
		return new Object[][] { { "macbook", 3 },
								{ "imac", 1 },
								{ "samsung", 2 }

		};
	}

	@Test(dataProvider="getProductCountData")
	public void doSearchResultTest(String searchKey, int count) {
		searchResultsPage = accountsPage.search(searchKey);
		Assert.assertEquals(searchResultsPage.getSearchProductCount(), count);
	}

	public void selectProductTest(String name) {
		searchResultsPage.selectProductTest("MacBook Pro");
	}
}
