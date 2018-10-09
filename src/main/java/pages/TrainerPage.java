package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//TODO: Check that all elements can be accessed fully at any time (i.e. no bugs below)
public class TrainerPage {
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	public TrainerPage (WebDriver driver) {
		this.driver = driver;
	}
	
	private void waitForTrainerPageOpen() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Create Trainer")));
	}
	
	private void waitForAddTrainerMenuOpen() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("trainerModalLabel")));
	}
	
	public void waitForAddTrainerMenuClosed() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("trainerModalLabel")));
	}
	
	public WebElement getCreateTrainerButton() {
		waitForTrainerPageOpen();
		return driver.findElement(By.linkText("Create Trainer"));
	}
	
	//Presumes one Trainer record is already in the page
	public WebElement getFirstPencilIcon() {
		waitForTrainerPageOpen();
		return driver.findElement(By.cssSelector("a[class=\"pull-right\" role=\"button\" ng-click=\"populateTrainer($index)\" data-toggle=\"modal\" data-target=\"#editTrainerModal\"]"));
	}
	
	//Presumes one Trainer record is already in the page
	public WebElement getFirstRedX() {
		waitForTrainerPageOpen();
		return driver.findElement(By.cssSelector("a[class=\"pull-right\" role=\"button\" ng-click=\"populateTrainer($index)\" data-toggle=\"modal\" data-target=\"#deleteTrainerModal\"]"));
	}
	
	//Checks only label "Add Trainer"
	public WebElement getAddTrainerMenu() {
		waitForTrainerPageOpen();
		return driver.findElement(By.id("trainerModalLabel"));
	}
	
	public WebElement getTrainerNameTextBox() {
		waitForAddTrainerMenuOpen();
		return driver.findElement(By.id("trainerName"));
	}
	
	public WebElement getEmailTextBox() {
		waitForAddTrainerMenuOpen();
		return driver.findElement(By.id("trainerEmail"));
	}
	
	public WebElement getTitleTextBox() {
		waitForAddTrainerMenuOpen();
		return driver.findElement(By.id("Title"));
		//return driver.findElement(By.id("trainerTitle"));
	}
	
	public WebElement getTierDropdown() {
		waitForAddTrainerMenuOpen();
		return driver.findElement(By.id("trainerTier"));
	}
	
	public WebElement getSaveButton() {
		waitForAddTrainerMenuOpen();
		return driver.findElement(By.cssSelector("#createTrainerModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > input:nth-child(1)"));
	}
	
	//Only works if there is one table (i.e. one <tbody>) on the page
	public WebElement getAllTrainersTable() {
		//waitForAddTrainerMenuClosed();	//can cause test to halt
		waitForTrainerPageOpen();
		return driver.findElement(By.tagName("tbody"));
	}
	
	//TODO: Get "id" of Email textbox error message of "Please fill out this field" or "Please enter an email addrees"
	public WebElement getEmailErrorMessage() {
		return null;
	}
	
	//TODO: Get "id" of Name textbox error message of "Please fill out this field"
	public WebElement getNameErrorMessage() {
		return null;
	}
	
	//TODO: Get "id" of Title textbox error message of "Please fill out this field"
		public WebElement getTitleErrorMessage() {
			return null;
		}
	
	public WebElement getCloseButton() {
		waitForAddTrainerMenuOpen();
		return driver.findElement(By.cssSelector("#createTrainerModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > button:nth-child(2)"));
	}
	
	public WebElement getGrayX() {
		waitForAddTrainerMenuOpen();
		return driver.findElement(By.cssSelector("#createTrainerModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > button:nth-child(1)"));
	}
}
