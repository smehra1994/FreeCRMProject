package com.crm.qa.testcases;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.RegisterPage;



public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	RegisterPage registerPage;
	public static Logger log=Logger.getLogger(LoginPageTest.class);


	public LoginPageTest() {
		super(); // this will run first in this class and will directly call parent class i.e
					// TestBase
	}

	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Cogmento CRM"); // get this value of title by right click on main page and view source code
		log.info("Title verified");																		
	}

	@Test(priority = 2)
	public void loginTest() {

		registerPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
