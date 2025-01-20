package com.qa.openkart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.openkart.logger.Log;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions ChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			co.addArguments("--headless");
//			System.out.println("Running Chrome in --headless mode");
			Log.info("Running Chrome in --headless mode");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			co.addArguments("--incognito");
//			System.out.println("Running Chrome in --incognito mode");
			Log.info("Running Chrome in --incognito mode");
		}
		return co;
	}

	public EdgeOptions EdgeOptions() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			eo.addArguments("--headless");
//			System.out.println("Running Edge in --headless mode");
			Log.info("Running Edge in --headless mode");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			eo.addArguments("--incognito");
//			System.out.println("Running Edge in --incognito mode");
			Log.info("Running Edge in --incognito mode");
		}
		return eo;
	}

	public FirefoxOptions FirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			fo.addArguments("--headless");
//			System.out.println("Running Firefox in --headless mode");
			Log.info("Running Firefox in --headless mode");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			fo.addArguments("--incognito");
//			System.out.println("Running Firefox in --incognito mode");
			Log.info("Running Firefox in --incognito mode");
		}
		return fo;
	}

}
