package com.qa.openkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openkart.constants.AppConstant;
import com.qa.openkart.utils.ElementUtil;

import io.qameta.allure.Step;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtil ele;
	
	
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	
	private By email = By.id("input-email");
	private By phoneNumber = By.id("input-telephone");
	
	private By pwd = By.id("input-password");
	private By cnfPwd = By.id("input-confirm");
	
	private By no = By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[2]/input");
	private By agree = By.name("agree");
	private By finish = By.cssSelector("#content > form > div > div > input.btn.btn-primary");
	
	private By sucessMessage = By.tagName("h1");
	private By logOut = By.xpath("//*[@id=\"column-right\"]/div/a[13]");
	private By register = By.xpath("//*[@id=\"column-right\"]/div/a[2]");
	
	public RegistrationPage(WebDriver driver) {
		this.driver= driver;
		ele = new ElementUtil(driver);
		
	}
	
	@Step("User reg with: {0}, {1}, {2}, {3}, {4}")
	public boolean userRegistration (String fn,String ln,String em,String tele,String pwd) {

			ele.doSendKeys(firstName, fn, 10);
			
			ele.doSendKeys(lastName, ln, 10);
			ele.doSendKeys(email, em, 10);
			ele.doSendKeys(phoneNumber, tele, 10);
			ele.doSendKeys(this.pwd, pwd, 10);
			ele.doSendKeys(cnfPwd, pwd, 10);
			ele.doclick(agree);
			ele.doclick(finish);
			
			
			String s = ele.waitForElementVisible(sucessMessage, 10).getText();
			
			
			if (s.equals("Your Account Has Been Created!")) {
				System.out.println(AppConstant.USER_REG_SUCCESS_METHOD);
				ele.doClick(logOut , 5);
				ele.doClick(register, 5);
				return true;
				
				
			} else return false;
			
			
	}
	
	

}
