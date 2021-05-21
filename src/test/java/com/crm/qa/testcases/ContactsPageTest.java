package com.crm.qa.testcases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.RegisterPage;
import com.crm.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ContactsPageTest extends TestBase {

	LoginPage loginPage;
	RegisterPage registerPage;
	ContactsPage contactsPage;
	public ExtentReports extent;
	public ExtentTest extentTest;

	String sheetName = "contacts";

	public ContactsPageTest() {
		super();

	}

	@BeforeTest
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);     //true will replace existing extent.html file with new
		extent.addSystemInfo("Host Name", "Sankalpa Windows");        //normal parameters which will reflect in report and are optional
		extent.addSystemInfo("User Name", "Sankalpa Mehra Project");
		extent.addSystemInfo("Environment", "QA");
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {

		initialization();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		registerPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = registerPage.clickOnContactsLink();
	}

	@Test(priority = 1)
	public void verifyFlightsPageLabel() {
		extentTest = extent.startTest("verifyFlightsPageLabel");
		Assert.assertTrue(contactsPage.verifyFlightsLabel(), "Flight label is missing on the page");
	}

	@Test(priority = 2)
	public void selectSingleContactsTest() throws InterruptedException {
		extentTest = extent.startTest("selectSingleContactsTest");
		ContactsPage.selectContactsByName();
	}

	@Test(priority = 3)
	public void selectMultipleContactsTest() throws InterruptedException {
		extentTest = extent.startTest("selectMultipleContactsTest");
		ContactsPage.selectContactsByName();
		ContactsPage.selectContactsByName();

	}

	@DataProvider
	public Object[][] getCRMTestData() throws InvalidFormatException {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}

	@Test(priority = 4, dataProvider = "getCRMTestData")
	public void validateCreateNewContact(String firstName, String lastName) {
		registerPage.clickOnNewContactLink();
		// contactsPage.createNewContact("Tom", "Peter");
		contactsPage.createNewContact(firstName, lastName);

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = TestUtil.takeScreenshotAtEndOfTest(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add and attach screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}
		
		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
	}
	
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}

}
