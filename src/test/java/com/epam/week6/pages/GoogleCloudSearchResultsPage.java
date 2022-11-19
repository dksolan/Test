package com.epam.week6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleCloudSearchResultsPage extends BasePage {
	
	private final String searchResultsLocator = ".gsc-resultsbox-visible";
	
	public GoogleCloudSearchResultsPage(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getSearchResults() {
		return waitSecondsForPresenceOfAnElement(5, By.cssSelector(searchResultsLocator));
	}
	
	public void goToSpecificSearchResultWhichContainsSpecificText(String expectedLink) {
		getSearchResults().findElement(By.partialLinkText(expectedLink)).click();
	}
	
}
