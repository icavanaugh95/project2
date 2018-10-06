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
	
}
