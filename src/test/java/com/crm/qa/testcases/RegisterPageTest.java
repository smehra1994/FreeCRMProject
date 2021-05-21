package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.RegisterPage;
import com.crm.qa.util.TestUtil;


public class RegisterPageTest extends TestBase {

	LoginPage loginPage;
	RegisterPage registerPage;
	ContactsPage contactsPage;
	

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		registerPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyRegisterPageTitleTest() {
		String registerPageTitle = registerPage.verifyRegisterPageTitle();
		Assert.assertEquals(registerPageTitle, "Cogmento CRM", "Homepage title did not match");    //if title unmatch will return the other msg
	}

	@Test(priority = 2)
	public void verifyCorrectPageNameTest() {
		//testUtil.switchToFrame();     
		Assert.assertTrue(registerPage.verifyCorrectPageName()); 
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest(){
		//testUtil.switchToFrame();
		contactsPage = registerPage.clickOnContactsLink();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}


}
