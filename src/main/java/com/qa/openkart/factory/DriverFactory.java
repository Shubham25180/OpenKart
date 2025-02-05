package com.qa.openkart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.openkart.errors.AppError;
import com.qa.openkart.exceptions.BrowserException;
import com.qa.openkart.exceptions.FrameworkException;
import com.qa.openkart.logger.Log;


public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static String highlight;

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		highlight = prop.getProperty("highlight");
		
		Log.info("Browser name is : "+browserName);

		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
//			driver = new ChromeDriver(optionsManager.ChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.ChromeOptions()));
			break;
		case "firefox":
//			driver = new FirefoxDriver(optionsManager.FirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.FirefoxOptions()));
			break;
		case "edge":
//			driver = new EdgeDriver(optionsManager.EdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.EdgeOptions()));
			break;
		case "safari":
//			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
//			System.out.println("please pass the right browser :: " + browserName);
			Log.error("please pass the right browser :: " + browserName);
			throw new BrowserException("Browser Not Found" + browserName);
		}
		
//		driver.manage().window().maximize();
		getDriver().manage().window().maximize();
//		driver.manage().deleteAllCookies();
		getDriver().manage().deleteAllCookies();
//		driver.get(prop.getProperty("url"));
		getDriver().get(prop.getProperty("url"));

//		return driver;
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	

	public Properties initProp() {

		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
//		System.out.println("Running in : " + envName + "  environment");
		Log.info("Running in : " + envName + "  environment");
		try {

			if (envName == null) {
				System.out.println("No environment provided.. running in default settings");
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} else {

				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;
				case "production":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;

				default:
					Log.error("Environment name : " + envName + ": does not exist");
					throw new FrameworkException(AppError.ENVIRONMENT_MISSING + " : " + envName);
				}
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			throw new FrameworkException(AppError.FILE_NOT_FOUND);

		}

		try {

			prop.load(ip);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}
	
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
