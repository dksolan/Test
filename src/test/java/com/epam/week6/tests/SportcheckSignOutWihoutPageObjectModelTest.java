package com.epam.week6.tests;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SportcheckSignOutWihoutPageObjectModelTest {
	
	@Test
	public void sportcheckLoginToastMessageTest() {
		String loginEmail = "testreg.12.4.2022_1@mailinator.com";
		String loginPassword = "Testtest&1";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		driver.get("https://qa3-www.sportchek.ca/en.html");
        ((WebStorage) driver).getLocalStorage().setItem("preferred-store-id", "371");
		driver.findElement(By.cssSelector(".nl-sm-block .nl-pencil-banner__login")).click();
		driver.findElement(By.cssSelector(".gigya-composite-control-loginID input"))
			.sendKeys(loginEmail);
		driver.findElement(By.cssSelector(".gigya-composite-control-password input"))
			.sendKeys(loginPassword);
		driver.findElement(By.cssSelector(".gigya-composite-control-submit input")).click();
		
		// At this moment we need to wait until the spinner screen is gone, because 
		// it would intercept the clicks
		new WebDriverWait(driver, Duration.ofSeconds(10))
		.until(ExpectedConditions.invisibilityOfElementLocated(
				By.cssSelector(".nl-spinner__content")));
		driver.findElement(By.cssSelector(".nl-sm-block #accountButton")).click();
		driver.findElement(By.xpath("//div[@class='nl-pencil-banner__sign-out']/button")).click();
		
		Assert.assertTrue(driver.findElement(
				By.cssSelector(".nl-sm-block .nl-pencil-banner__login")).isDisplayed());
	}
	
}
