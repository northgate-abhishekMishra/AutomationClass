package ddf.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseTest_Class {
	public WebDriver driver;
	
	
	public void openBrowser() {
		//System.out.println("Browsertype = " + browserType);
		Properties prop = null;
		if (prop == null) {
			InputStream input = null;
			try {
				input = new FileInputStream(
						(System.getProperty("user.dir") + "\\src\\test\\resources\\ProjectConfig.properties"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			prop = new Properties();

			// load a properties file
			try {
				prop.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
	//	System.out.println("Browsertype = " + prop.get("browsertype"));
		
		if(prop.get("browsertype").equals("Firefox")) {
			driver = new FirefoxDriver();
		}else if (prop.get("browsertype").equals("Chrome")) {
			driver = new ChromeDriver();
		}else if (prop.get("browsertype").equals("IE")) {
			driver = new InternetExplorerDriver();
		}
	}

	public void navigate() {
		Properties prop = null;
		if (prop == null) {
			InputStream input = null;
			try {
				input = new FileInputStream(
						(System.getProperty("user.dir") + "\\src\\test\\resources\\ProjectConfig.properties"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			prop = new Properties();

			// load a properties file
			try {
				prop.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("URL = " + prop.getProperty("app_url"));
		driver.navigate().to(prop.getProperty("app_url"));
	}

}
