package com.qa.openkart.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.openkart.utils.CsvUtils;

public class CSVdataProviderTest {
	
	@DataProvider
	public Object[][] dataProvider1() {
		return CsvUtils.csvData("data");
	}
	
	@DataProvider(name="abc")
	public Object[][] dataProvider2() {
		return CsvUtils.csvData("register");
	}
	@Test(dataProvider="dataProvider1")
	public void print1 (String a, String b, String c) {
		System.out.println(a+"  :  "+b+"  :  "+c);
	}
	@Test(dataProvider="abc")
	public void print2 (String a, String b, String c,String d) {
		System.out.println(a+"  :  "+b+"  :  "+c+"  :  "+d);
	}

	
}
