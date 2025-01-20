package com.qa.openkart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	private WebDriver driver;
	private JavascriptExecutor js;
	
	public JavaScriptUtil(WebDriver driver) {
		this.driver=driver;
		js = (JavascriptExecutor)driver;
		
	}
	
	public String getTitleByJS() {		
		return js.executeScript("return document.title;").toString();
	}	
	public String getUrlByJS() {		
		return js.executeScript("return document.URL;").toString();
	}
	public void goBackByJs() {		
		js.executeScript("history.go(-1);");
	}
	public void goForwardByJs() {		
		js.executeScript("history.go(+1);");
	}
	public void doRefreshByJs() {		
		js.executeScript("history.go(0);");
	}
	public void generateAlert(String message) {
		 js.executeScript("alert('" + message + "')");
	}
	public void generatePrompt(String message) {
		 js.executeScript("prompt('" + message + "')");
	}
	public void generateConfirmPopUp(String message) {
		 js.executeScript("confirm('" + message + "')");
	}
	public String getPageInnerTextByJS() {
	 return js.executeScript("document.documentElement.innerText").toString();
	}
	public void ScrollPageDown() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	public void ScrollPageDown(String value) {
		js.executeScript("window.scrollTo(0,'"+value+"')");
	}
	public void ScrollPageUp() {
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	public void ScrollPageMiddlepage() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
	}
	public void ScrollToElement(WebElement element) {
		 js.executeScript("argument[0].scrollIntoView(true);",element);
	}
	public void zoomChromeEdge(String zoomPercentage) {
		js.executeScript("document.body.style.zoom = '+zoomPercentage+%'");
	}
	public void zoomFirefox(String zoomPercentage) {
		js.executeScript("document.body.style.MozTransform = 'scale("+zoomPercentage+")'");
	}
	public void drawBorder(WebElement element) {
		js.executeScript("argument[0].style.border='3px solid red'",element);
	}
	public void changeColor(String color, WebElement element) {
		js.executeScript("arguments[0].style.backgroundColor='"+color+"'",element);
		
		try {
			Thread.sleep(20);
		} catch(InterruptedException e) {
			
		}
	}
	
	public void flash(WebElement element) {
        String originalColor = element.getCssValue("backgroundColor");
        for (int i = 0; i < 10; i++) {
        	
            changeColor("rgb(0,200,0)", element); // Flash green
            changeColor(originalColor, element); // Revert to original
        }
	}
	public void clickElementByJS(WebElement element) {
		js.executeScript("argument[0].click", element);
	}
	public void sendKeysUsingWithId(String id , String  value) {
		js.executeScript("document.getElementById('"+id+"').value='"+value+"'");
	}				    //document.getElementById('input-email).value='tom@gmail.com'");

}
