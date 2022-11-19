package com.epam.week6.tests;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.epam.week6.pages.GoogleCloudHomePage;
import com.epam.week6.pages.GoogleCloudPricingCalculatorPage;
import com.epam.week6.pages.GoogleCloudSearchResultsPage;
import com.epam.week6.pages.YopmailEmailInboxPage;
import com.epam.week6.pages.YopmailRandomEmailGeneratorPage;

public class GoogleCloudCalculatorTests extends BaseTest {
	
	private GoogleCloudHomePage googleCloudHomePage;
	private GoogleCloudSearchResultsPage googleCloudSearchResultsPage;
	private GoogleCloudPricingCalculatorPage googleCloudPricingCalculatorPage;
	private YopmailRandomEmailGeneratorPage yopmailRandomEmailGeneratorPage;
	private YopmailEmailInboxPage yopmailEmailInboxPage;
	
	@Test(groups="Google Cloud Calculator Tests")
	public void cloudPlatformPricingCalculatorTest() {
		String searchQuery = "Google Cloud Platform Pricing Calculator";
		String numberOfInstances = "4";
		String operatingSystems = "Free: Debian";
		String vMClass = "Regular";
		String machineTypeSeries = "N1";
		String machineType = "n1-standard-8";
		String numberOfGPUs = "1";
		String gPUType = "NVIDIA Tesla V100";
		String localSSD = "2x375";
		String datacenterLocation = "Frankfurt";
		String committedUsage = "1 Year";
		
		String expectedMonthlyRent = "1,081.20";
		
		googleCloudHomePage = new GoogleCloudHomePage(driver);
		googleCloudSearchResultsPage = new GoogleCloudSearchResultsPage(driver);
		googleCloudPricingCalculatorPage = new GoogleCloudPricingCalculatorPage(driver);
		
		googleCloudHomePage.openPage();
		googleCloudHomePage.searchFor(searchQuery);
		googleCloudSearchResultsPage.goToSpecificSearchResultWhichContainsSpecificText(searchQuery);
		googleCloudPricingCalculatorPage.switchFocusToCalculatorIFrame();
		googleCloudPricingCalculatorPage.initializeCalculatorIFrameElements();
		googleCloudPricingCalculatorPage.setNumberOfInstances(numberOfInstances);
		googleCloudPricingCalculatorPage.selectOperatingSystem(operatingSystems);
		googleCloudPricingCalculatorPage.selectVMClass(vMClass);
		googleCloudPricingCalculatorPage.selectMachineTypeSeries(machineTypeSeries);
		googleCloudPricingCalculatorPage.selectMachineType(machineType);
		googleCloudPricingCalculatorPage.selectAddGPUCheckbox();
		googleCloudPricingCalculatorPage.selectGPUType(gPUType, numberOfGPUs);
		googleCloudPricingCalculatorPage.selectLocalSSD(localSSD);
		googleCloudPricingCalculatorPage.selectDatacenterLocation(datacenterLocation);
		googleCloudPricingCalculatorPage.selectCommitedUsage(committedUsage);
		googleCloudPricingCalculatorPage.clickAddToEstimateButton();
		
		Assert.assertTrue(googleCloudPricingCalculatorPage.
				getSpecificEstimationResult("Provisioning")
				.getText().contains(vMClass)
				, "The actual machine type differs from the expected");
		Assert.assertTrue(googleCloudPricingCalculatorPage.
				getSpecificEstimationResult("Instance type")
				.getText().contains(machineType)
				, "The actual instance type differs from the expected");
		Assert.assertTrue(googleCloudPricingCalculatorPage.
				getSpecificEstimationResult("Region")
				.getText().contains(datacenterLocation)
				, "The actual datacenter location differs from the expected");
		Assert.assertTrue(googleCloudPricingCalculatorPage.
				getSpecificEstimationResult("Local SSD")
				.getText().contains(localSSD)
				, "The actual local SSD differs from the expected");
		Assert.assertTrue(googleCloudPricingCalculatorPage.
				getSpecificEstimationResult("Estimated Component Cost")
				.getText().contains(expectedMonthlyRent)
				, "The actual component cost differs from the expected");
	}
	
	@Test(groups="Google Cloud Calculator Tests")
	public void cloudPlatformPricingCalculatorWithEmailTest() {
		String searchQuery = "Google Cloud Platform Pricing Calculator";
		String numberOfInstances = "4";
		String operatingSystems = "Free: Debian";
		String vMClass = "Regular";
		String machineTypeSeries = "N1";
		String machineType = "n1-standard-8";
		String numberOfGPUs = "1";
		String gPUType = "NVIDIA Tesla V100";
		String localSSD = "2x375";
		String datacenterLocation = "Frankfurt";
		String committedUsage = "1 Year";
		String emailAddress;
		String expectedMonthlyRent;
		List<WebElement> emailEstimationResults;
		
		googleCloudHomePage = new GoogleCloudHomePage(driver);
		googleCloudSearchResultsPage = new GoogleCloudSearchResultsPage(driver);
		googleCloudPricingCalculatorPage = new GoogleCloudPricingCalculatorPage(driver);
		yopmailRandomEmailGeneratorPage = new YopmailRandomEmailGeneratorPage(driver);
		yopmailEmailInboxPage = new YopmailEmailInboxPage(driver);
		
		googleCloudHomePage.openPage();
		googleCloudHomePage.searchFor(searchQuery);
		googleCloudSearchResultsPage.goToSpecificSearchResultWhichContainsSpecificText(searchQuery);
		googleCloudPricingCalculatorPage.switchFocusToCalculatorIFrame();
		googleCloudPricingCalculatorPage.initializeCalculatorIFrameElements();
		googleCloudPricingCalculatorPage.setNumberOfInstances(numberOfInstances);
		googleCloudPricingCalculatorPage.selectOperatingSystem(operatingSystems);
		googleCloudPricingCalculatorPage.selectVMClass(vMClass);
		googleCloudPricingCalculatorPage.selectMachineTypeSeries(machineTypeSeries);
		googleCloudPricingCalculatorPage.selectMachineType(machineType);
		googleCloudPricingCalculatorPage.selectAddGPUCheckbox();
		googleCloudPricingCalculatorPage.selectGPUType(gPUType, numberOfGPUs);
		googleCloudPricingCalculatorPage.selectLocalSSD(localSSD);
		googleCloudPricingCalculatorPage.selectDatacenterLocation(datacenterLocation);
		googleCloudPricingCalculatorPage.selectCommitedUsage(committedUsage);
		googleCloudPricingCalculatorPage.clickAddToEstimateButton();
		expectedMonthlyRent = googleCloudPricingCalculatorPage.
				getSpecificEstimationResult("Estimated Component Cost").getText();
		
		driver.switchTo().newWindow(WindowType.TAB);
		
		yopmailRandomEmailGeneratorPage.openPage();
		yopmailRandomEmailGeneratorPage.clickCheckInboxOfRandomEmailButton();
		emailAddress = yopmailEmailInboxPage.getCurrentEmailAddressString();
		
		ArrayList<String> windowHandles = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(0));
		
		googleCloudPricingCalculatorPage.switchFocusToCalculatorIFrame();
		googleCloudPricingCalculatorPage.clickEmailEstimationResultsButton();
		googleCloudPricingCalculatorPage.enterEmailIntoEmailYourEstimateForm(emailAddress);
		googleCloudPricingCalculatorPage.clickSendEmailButtonOnEmailYourEstimateForm();
		
		driver.switchTo().window(windowHandles.get(1));
		
		yopmailEmailInboxPage.waitForEmail();
		yopmailEmailInboxPage.switchToEmailIFrame();
		emailEstimationResults = yopmailEmailInboxPage.getEstimationResultsFromEmail();
		
		Assert.assertTrue(emailEstimationResults.size() != 0 
				&& expectedMonthlyRent.contains(emailEstimationResults.get(0).getText())
				, "Monthly rent cost differs between email and calculator");
	}
}
