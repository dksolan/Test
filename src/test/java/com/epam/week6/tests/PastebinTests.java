package com.epam.week6.tests;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.epam.week6.pages.PastebinHomePage;
import com.epam.week6.pages.PastebinPage;

public class PastebinTests extends BaseTest {
	
	private PastebinHomePage pastebinHomePage;
	private PastebinPage pastebinPage;
	
	@Test(groups="Pastebin Tests")
	public void creationOfNewPasteWithTextTest() {
		pastebinHomePage = new PastebinHomePage(driver);
		pastebinHomePage.openPage();
		pastebinHomePage.enterPasteText("Hello from WebDriver");
		pastebinHomePage.setPasteExpirationToTenMinutes();
		pastebinHomePage.enterPasteName("helloweb");
		pastebinHomePage.clickOnCreateNewPasteButton();
	}
	
	@Test(groups="Pastebin Tests")
	public void creationOfNewPasteWithCodeTest() {
		String code = "git config --global user.name  \"New Sheriff in Town\"\n"
				+ "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n"
				+ "git push origin master --force";
		String pasteName = "how to gain dominance among developers";
		
		pastebinPage = new PastebinPage(driver);
		pastebinHomePage = new PastebinHomePage(driver);
		pastebinHomePage.openPage();
		pastebinHomePage.enterPasteText(code);
		pastebinHomePage.setSyntaxHighlightingToBash();
		pastebinHomePage.setPasteExpirationToTenMinutes();
		pastebinHomePage.enterPasteName(pasteName);
		pastebinHomePage.clickOnCreateNewPasteButton();
		
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(
				ExpectedConditions.titleContains(pasteName));
		
		Assert.assertTrue(driver.getTitle().contains(pasteName), 
				"Title of the page is different than the title of the paste");
		Assert.assertEquals(pastebinPage.getTextOfThePaste(), code,
				"The actual code does not equal to the code which was entered into the paste");
		Assert.assertEquals(pastebinPage.getSyntaxHighlightName(), "Bash",
				"The actual syntax highlight does not correspond to what was chosen initially");
	}
	
}
