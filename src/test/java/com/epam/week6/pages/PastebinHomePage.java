package com.epam.week6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PastebinHomePage extends BasePage {
	
	private static final String PAGE_URL = "https://pastebin.com";
	
	@FindBy(xpath="//textarea[@id='postform-text']")
	private WebElement pasteText;
	
	@FindBy(xpath="//span[@data-select2-id='3']")
	private WebElement pasteExpirationDropdown;
	
	@FindBy(xpath="//li[text()='10 Minutes']")
	private WebElement tenMinutesExpirationOption;
	
	@FindBy(xpath="//span[@data-select2-id='1']")
	private WebElement syntaxHighlightingDropdown;
	
	@FindBy(xpath="//li[text()='Bash']")
	private WebElement bashSyntaxHighlightingOption;
	
	@FindBy(xpath="//input[@id='postform-name']")
	private WebElement pasteName;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement createNewPasteButton;
	
	public PastebinHomePage(WebDriver driver) {
		super(driver);
	}
	
	public void openPage() {
		driver.get(PAGE_URL);
	}
	
	public void enterPasteText(String textToPaste) {
		pasteText.sendKeys(textToPaste);
	}
	
	public void setPasteExpirationToTenMinutes() {
		pasteExpirationDropdown.click();
		tenMinutesExpirationOption.click();
	}
	
	public void enterPasteName(String pasteName) {
		this.pasteName.sendKeys(pasteName);
	}
	
	public void clickOnCreateNewPasteButton() {
		createNewPasteButton.click();
	}
	
	public void setSyntaxHighlightingToBash() {
		syntaxHighlightingDropdown.click();
		bashSyntaxHighlightingOption.click();
	}
	
}
