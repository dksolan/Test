package com.epam.week6.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YopmailEmailInboxPage extends BasePage {
	
	private final String numberOfEmailsLocator = "//div[text()='1 mail']";
	private final String estimationResultsEmailLocator = "//tr/td[2]/h3";
	
	@FindBy(css="#refresh")
	private WebElement refreshEmailsButton;
	
	@FindBy(css=".bname")
	private WebElement currentEmailAddress;
	
	public YopmailEmailInboxPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickRefreshEmailsButton() {
		refreshEmailsButton.click();
	}
	
	public String getCurrentEmailAddressString() {
		return currentEmailAddress.getText();
	}
	
	public void switchToEmailIFrame() {
		driver.switchTo().frame("ifmail");
	}
	
	// Here the do-while loop is used, since we need to click the refresh button at 
	// least once to see if email has come, and if it is not here yet - 
	// we need to continue clicking
	public void waitForEmail() {
		do {
			clickRefreshEmailsButton();
		} while(driver.findElements(By.xpath(numberOfEmailsLocator)).size() == 0);
	}
	
	public List<WebElement> getEstimationResultsFromEmail() {
		return driver.findElements(By.xpath(estimationResultsEmailLocator));
	}
}
