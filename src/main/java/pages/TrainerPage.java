package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrainerPage {
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	public TrainerPage (WebDriver driver) {
		this.driver = driver;
	}
	
	public void waitForTrainerPageOpen() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Create Trainer")));
	}
	
	public void waitForAddTrainerMenuOpen() {
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
	
	public WebElement getAddTrainerSaveButton() {
		waitForAddTrainerMenuOpen();
		return driver.findElement(By.cssSelector("#createTrainerModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > input:nth-child(1)"));
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
		waitForTrainerPageOpen();
		//waitForAddTrainerMenuClosed();
		return driver.findElement(By.tagName("tbody"));
	}
}
