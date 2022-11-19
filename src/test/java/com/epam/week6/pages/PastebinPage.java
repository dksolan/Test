package com.epam.week6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PastebinPage extends BasePage {
	
	@FindBy(css=".source")
	private WebElement pasteText;
	
	@FindBy(xpath="//div[@class='left']/a[1]")
	private WebElement syntaxHighlighting;
	
	public PastebinPage(WebDriver driver) {
		super(driver);
	}
	
	public String getTextOfThePaste() {
		return pasteText.getText();
	}
	
	public String getSyntaxHighlightName() {
		return syntaxHighlighting.getText();
	}
}
