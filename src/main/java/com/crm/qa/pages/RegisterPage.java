package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class RegisterPage extends TestBase {

	@FindBy(xpath = "//div[@class='header item']")
	WebElement PageLabel;

	@FindBy(xpath = "//span[@class='item-text' and text()='Contacts']")
	WebElement contactsLink;

	@FindBy(xpath = "//a[@href='/contacts']/following-sibling::button")
	WebElement newcontactLink;

	// Initializing the Page Objects:
	public RegisterPage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyRegisterPageTitle() {
		return driver.getTitle();
	}

	public boolean verifyCorrectPageName() {
		return PageLabel.isDisplayed();
	}

	public ContactsPage clickOnContactsLink() {
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		contactsLink.click();
		return new ContactsPage();
	}

	public ContactsPage clickOnNewContactLink() {
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		newcontactLink.click();
		return new ContactsPage();

	}

}
