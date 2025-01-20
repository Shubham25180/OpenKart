package com.qa.openkart.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.openkart.base.BaseTest;
import com.qa.openkart.constants.AppConstant;
import com.qa.openkart.utils.ExcelUtil;
import com.qa.openkart.utils.StringUtils;

import io.qameta.allure.Step;

public class RegistrationPageTests extends BaseTest{
	
	@BeforeTest
	private void registrationSetup() {
		registrationPage = loginPage.navigationToRegisterPage();
	}
	
//	@DataProvider
//	public Object[] getUserRequestTestData(){
//		return new Object[][] {
//			{"abc","xyz",StringUtils.getRandomEmailId(),"9876543456","sddsaccsa"},
//			{"dsa","gdsf",StringUtils.getRandomEmailId(),"9876543456","sddsaccsa"},
//			{"cxz","kyutyy",StringUtils.getRandomEmailId(),"9876543456","sddsaccsa"}
//		};
//	}
	@DataProvider
	public Object[] getUserRequestTestDataFromExcel(){
		return ExcelUtil.getTestData(AppConstant.REGISTER_SHEET_NAME);
		
	}
	@Step("Checking user registration")
	@Test(dataProvider="getUserRequestTestDataFromExcel")
	public void userRegTest(String a, String b, String c,String d ) {
		registrationPage.userRegistration(a,b,StringUtils.getRandomEmailId(),c,d);		
	}
	
}
