package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {

	@FindBy(xpath = "//div[@class='ui header item mb5 light-black']")
	WebElement ContactLabel;

	@FindBy(xpath = "//input[@name='first_name' and @type='text']")
	WebElement firstName;

	@FindBy(xpath = "//input[@name='last_name' and @type='text']")
	WebElement lastName;

	@FindBy(xpath = "//*[@name='company']")
	WebElement company;

	@FindBy(xpath = "//*[@class='ui linkedin button']")
	WebElement saveBtn;

	// Initializing the Page Objects:
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	
	public boolean verifyFlightsLabel() {
		return ContactLabel.isDisplayed();
	}

	public static void selectContactsByName() throws InterruptedException {
		WebElement filter = driver.findElement(By.xpath("//button[text()='Show Filters']"));
		WebElement text = driver.findElement(By.xpath("//a[text()='Sank Mehra']"));
		WebElement checkbox = driver.findElement(By.xpath("//a[text()='Sank Mehra']//parent::td//preceding-sibling::td/div/input[@class='hidden']"));
		Actions action = new Actions(driver);
		action.moveToElement(filter).build().perform();
		Thread.sleep(3000);
		action.moveToElement(text).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Sank Mehra']//parent::td//preceding-sibling::td/div/input[@class='hidden']")));
		checkbox.click();

	}
	 
	public void createNewContact(String ftName, String ltName) {
		Actions action = new Actions(driver);
		action.moveToElement(firstName).build().perform();
		firstName.sendKeys(ftName);
		lastName.sendKeys(ltName);

		saveBtn.click();

	}

}
