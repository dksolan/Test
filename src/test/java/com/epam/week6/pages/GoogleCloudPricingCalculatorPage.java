package com.epam.week6.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPricingCalculatorPage extends BasePage {
	
	private String numberOfInstancesLocator = "#input_90";
	private WebElement numberOfInstances;
	
	private String addGPUCheckboxLocator = 
			"//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']";
	private WebElement addGPUCheckbox;
	
	private String addToEstimateButtonLocator = 
			"//button[contains(@ng-click, 'addComputeServer')]";
	private WebElement addToEstimateButton;
	
	private String emailEstimationResultsButtonLocator = "#email_quote";
	private WebElement emailEstimationResultsButton;
	
	private String emailEstimateEmailTextFieldLocator = "//input[@type='email']";
	private WebElement emailEstimateEmailTextField;
	
	private String sendEmailButtonOnEmailYourEstimateFormLocator = 
			"//button[@aria-label='Send Email']";
	private WebElement sendEmailButtonOnEmailYourEstimateForm;
	
	private String operatingSystemDropDownLocator = "#select_103";
	private WebElement operatingSystemDropDown;
	
	private String vMClassDropDownLocator = "#select_107";
	private WebElement vMClassDropDown;	
	
	private String machineTypeSeriesDropDownLocator = "#select_115";
	private WebElement machineTypeSeriesDropDown;
	
	private String machineTypeDropDownLocator = "#select_117";
	private WebElement machineTypeDropDown;
	
	private String gGPUTypeDropDownLocator = "//md-select[@placeholder='GPU type']";
	private WebElement gGPUTypeDropDown;
	
	private String numberOfGPUDropDownLocator = "//md-select[@placeholder='Number of GPUs']";
	private WebElement numberOfGPUDropDown;
	
	private String localSSDDropDownLocator = "//md-select[@placeholder='Local SSD']";
	private WebElement localSSDDropDown;
	
	private String datacenterLocationDropDownLocator = "#select_123";
	private WebElement datacenterLocationDropDown;
	
	private String commitedUsageDropDownLocator = "#select_130";
	private WebElement commitedUsageDropDown;
	
	private String estimationResultsLocator = "#resultBlock md-list-item";
	private String optionsOfAnActiveDropdownLocator = 
			".md-active.md-select-menu-container md-option";
	
	public GoogleCloudPricingCalculatorPage(WebDriver driver) {
		super(driver);
	}
	
	// This method switches to the IFrame of a calculator. Without it all elements would 
	// be unreachable
	public void switchFocusToCalculatorIFrame() {
		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0)
				.andThen(ExpectedConditions.frameToBeAvailableAndSwitchToIt("myFrame")));
	}
	
	// This method initializes all the elements which are present by default once the focus 
	// switches to the calculator Iframe. It does not initialize dynamic elements which 
	// are not present at this point
	public void initializeCalculatorIFrameElements() {
		numberOfInstances = driver.findElement(By.cssSelector(numberOfInstancesLocator));
		addGPUCheckbox = driver.findElement(By.xpath(addGPUCheckboxLocator));
		addToEstimateButton = driver.findElement(By.xpath(addToEstimateButtonLocator));
		operatingSystemDropDown = driver.findElement(
				By.cssSelector(operatingSystemDropDownLocator));
		vMClassDropDown = driver.findElement(By.cssSelector(vMClassDropDownLocator));
		machineTypeSeriesDropDown = driver.findElement(
				By.cssSelector(machineTypeSeriesDropDownLocator));
		machineTypeDropDown = driver.findElement(By.cssSelector(machineTypeDropDownLocator));
		datacenterLocationDropDown = driver.findElement(
				By.cssSelector(datacenterLocationDropDownLocator));
		commitedUsageDropDown = driver.findElement(By.cssSelector(commitedUsageDropDownLocator));
	}
	
	public void setNumberOfInstances(String numberOfInstances) {
		this.numberOfInstances.sendKeys(numberOfInstances);
	}
	
	public List<WebElement> getOperatingSystemOptions() {
		operatingSystemDropDown.click();
		return getAllOptionsOfAnActiveDropDown();
	}
	
	public void selectOperatingSystem(String operatingSystem) {
		selectSpecificOptionFromDropdown(getOperatingSystemOptions(), operatingSystem);
	}
	
	public List<WebElement> getVMClassOptions() {
		vMClassDropDown.click();
		return getAllOptionsOfAnActiveDropDown();
	}
	
	public void selectVMClass(String vMClass) {
		selectSpecificOptionFromDropdown(getVMClassOptions(), vMClass);
	}
	
	public List<WebElement> getMachineTypeSeriesOptions() {
		machineTypeSeriesDropDown.click();
		return getAllOptionsOfAnActiveDropDown();
	}
	
	public void selectMachineTypeSeries(String machineTypeSeries) {
		selectSpecificOptionFromDropdown(getMachineTypeSeriesOptions(), machineTypeSeries);
	}
	
	public List<WebElement> getMachineTypeOptions() {
		machineTypeDropDown.click();
		return getAllOptionsOfAnActiveDropDown();
	}
	
	public void selectMachineType(String machineType) {
		selectSpecificOptionFromDropdown(getMachineTypeOptions(), machineType);
	}
	
	// This method will check if add GPU checkbox is already selected, and if it 
	// is not - it would select it and initialize drop downs which only appear when the
	// checkbox is selected. At the same time, it will not initialize or do anything if 
	// the checkbox is either not enabled at all or is already selected
	public void selectAddGPUCheckbox() {
		if(addGPUCheckbox.isEnabled() && !addGPUCheckbox.isSelected()) {
			addGPUCheckbox.click();
			gGPUTypeDropDown = waitSecondsForVisibilityOfAnElement(5, By
					.xpath(gGPUTypeDropDownLocator));
			numberOfGPUDropDown = waitSecondsForVisibilityOfAnElement(5, By
					.xpath(numberOfGPUDropDownLocator));
		}
	}
	
	public List<WebElement> getGPUTypeOptions() {
		gGPUTypeDropDown.click();
		return getAllOptionsOfAnActiveDropDown();
	}
	
	public List<WebElement> getNumberOfGPUSOptions() {
		numberOfGPUDropDown.click();
		return getAllOptionsOfAnActiveDropDown();
	}
	
	public void selectGPUType(String gPUType, String numberOfGPUS) {
		selectSpecificOptionFromDropdown(getGPUTypeOptions(), gPUType);
		selectSpecificOptionFromDropdown(getNumberOfGPUSOptions(), numberOfGPUS);
	}
	
	// This method first of all ensures that with options already selected, 
	// Local SSD dropdown is available at all, since not every combination of
	// a virtual machine has this option
	public List<WebElement> getLocalSSDOptions() {
			localSSDDropDown = waitSecondsForVisibilityOfAnElement(
					5, By.xpath(localSSDDropDownLocator));
			localSSDDropDown.click();
			return getAllOptionsOfAnActiveDropDown();
	}
	
	public void selectLocalSSD(String localSSD) {
		selectSpecificOptionFromDropdown(getLocalSSDOptions(), localSSD);
	}
	
	public List<WebElement> getDatacenterLocationOptions() {
		datacenterLocationDropDown.click();
		return getAllOptionsOfAnActiveDropDown();
	}
	
	public void selectDatacenterLocation(String datacenterLocation) {
		selectSpecificOptionFromDropdown(getDatacenterLocationOptions(), datacenterLocation);
	}
	
	public List<WebElement> getCommitedUsageOptions() {
		commitedUsageDropDown.click();
		return getAllOptionsOfAnActiveDropDown();
	}
	
	public void selectCommitedUsage(String commitedUsage) {
		selectSpecificOptionFromDropdown(getCommitedUsageOptions(), commitedUsage);
	}
	
	public void clickAddToEstimateButton() {
		addToEstimateButton.click();
	}
	
	public void clickEmailEstimationResultsButton() {
		emailEstimationResultsButton = waitSecondsForVisibilityOfAnElement(
				5, By.cssSelector(emailEstimationResultsButtonLocator));
		emailEstimationResultsButton.click();
	}
	
	public void enterEmailIntoEmailYourEstimateForm(String email) {
		emailEstimateEmailTextField = waitSecondsForVisibilityOfAnElement(
				5, By.xpath(emailEstimateEmailTextFieldLocator));
		emailEstimateEmailTextField.sendKeys(email);
	}
	
	public void clickSendEmailButtonOnEmailYourEstimateForm() {
		sendEmailButtonOnEmailYourEstimateForm = waitSecondsForVisibilityOfAnElement(
				5, By.xpath(sendEmailButtonOnEmailYourEstimateFormLocator));
		sendEmailButtonOnEmailYourEstimateForm.click();
	}
	
	public WebElement getSpecificEstimationResult(String nameOfTheOption) {
		for(WebElement esimationResult : getEstimationResults()) {
			if(esimationResult.getText().toLowerCase().trim().contains(
					nameOfTheOption.toLowerCase().trim())) {
				return esimationResult;
			}
		}
		return null;
	}
	
	private List<WebElement> getEstimationResults() {
		return waitSecondsForVisibilityOfAllElements(5, By.cssSelector(estimationResultsLocator));
	}
	
	private List<WebElement> getAllOptionsOfAnActiveDropDown() {
		return waitSecondsForVisibilityOfAllElements(5, 
				By.cssSelector(optionsOfAnActiveDropdownLocator));
	}
	
	// This method provides the actual implementation of how a specific value 
	// in the dropdown is found and selected. It is private, since final users will have specific,
	// properly named methods, which would interact with the dropdown they actually need
	private void selectSpecificOptionFromDropdown(
			List<WebElement> dropdownOptions, String optionToSelect) {
		for (WebElement dropdownOption : dropdownOptions) {
			if (dropdownOption.getText().toLowerCase().contains(optionToSelect.toLowerCase())) {
				dropdownOption.click();
				waitSecondsForInvisibilityOfAllElements(5, dropdownOptions);
				break;
			}
		}
	}
}
