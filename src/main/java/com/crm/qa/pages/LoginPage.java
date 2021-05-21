package com.crm.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class LoginPage extends TestBase {

	// Page Factory - ObjectRepository:

	@FindBy(xpath="//input[@name='email']")
	WebElement uname;

	@FindBy(xpath = "//input[@name='password']")
	WebElement pswd;

	@FindBy(xpath="//div[@class='ui fluid large blue submit button']")
	WebElement loginBtn;

	// Initializing the Page Objects above here /Page factory:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public RegisterPage login(String un, String pwd) {

		uname.sendKeys(un);
		pswd.sendKeys(pwd);
		loginBtn.click();
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].click();", loginbtn);

		return new RegisterPage();
	}

}
