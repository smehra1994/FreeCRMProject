package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;



public class TestBase {
	public static WebDriver driver;
	public static Properties prop; // global variables that can be used everywhere be it child or base class
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static Logger log=Logger.getLogger(TestBase.class);
	
	
	
	public TestBase() // constructor of class to read properties
	{
		try {
			prop = new Properties();
			FileInputStream in = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/crm"
					+ "/qa/config/config.properties");;
			prop.load(in); // initializing my prop variable
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {

		
		String browserName = prop.getProperty("browser");

		if (browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C://Users/sanka/Documents/ChromeDriverEXE/new/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C://Users/sanka/Documents/FireFoxEXE/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		log.info("Launching borwser");
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
		
		log.info("Launching URL");
		
	}
}