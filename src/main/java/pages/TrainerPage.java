package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

	public void waitForAddTrainerMenuOpen() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#createTrainerModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > h4:nth-child(2)")));
		wait.until(ExpectedConditions.attributeContains(this.getAddTrainerMenu(), "innerHTML", "Add Trainer"));
	}

	public void waitForEditTrainerMenuOpen() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#editTrainerModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > h4:nth-child(2)")));
		wait.until(ExpectedConditions.attributeContains(this.getAddTrainerMenu(), "innerHTML", "Edit Trainer"));
	}

	//Used for Add Trainer, Edit Trainer, and Deactivate Trainer
	public void waitForPopupTrainerMenuClose() {
		wait = new WebDriverWait(driver, 20);
		if (this.getAddTrainerMenu().isDisplayed()) {
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("trainerModalLabel")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#createTrainerModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > h4:nth-child(2)")));
		} else if (this.getEditTrainerMenu().isDisplayed()) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#editTrainerModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > h4:nth-child(2)")));
		} else {
			throw new IllegalArgumentException("ERROR: Either Add Trainer or Edit Trainer menu is being currently displayed.");
		}
	}

	public WebElement getCreateTrainerButton() {
		waitForTrainerPageOpen();
		return driver.findElement(By.linkText("Create Trainer"));
	}

	//Presumes one Trainer record is already in the page
	public WebElement getFirstPencilIcon() {
		waitForTrainerPageOpen();
		return driver.findElement(By.cssSelector("tr.ng-scope:nth-child(1) > td:nth-child(5) > a:nth-child(1) > span:nth-child(1)"));
	}

	//Presumes one Trainer record is already in the page
	public WebElement getFirstRedX() {
		waitForTrainerPageOpen();
		return driver.findElement(By.cssSelector("tr.ng-scope:nth-child(1) > td:nth-child(6) > a:nth-child(1) > span:nth-child(1)"));
	}


	public WebElement getAddTrainerMenu(){
		waitForTrainerPageOpen();	
		//return driver.findElement(By.id("trainerModalLabel")); //Checks only label "Add Trainer"
		return driver.findElement(By.id("createTrainerModal"));
	}

	public WebElement getTrainerNameTextBox() {
		if (this.getAddTrainerMenu().isDisplayed()) {
			this.waitForAddTrainerMenuOpen();
		} else if (this.getEditTrainerMenu().isDisplayed()) {
			this.waitForEditTrainerMenuOpen();
		} else {
			throw new IllegalArgumentException("ERROR: Neither Add Trainer nor Edit Trainer menus are displayed.");
		}
		return driver.findElement(By.id("trainerName"));
	}

	public WebElement getEmailTextBox() {
		if (this.getAddTrainerMenu().isDisplayed()) {
			this.waitForAddTrainerMenuOpen();
		} else if (this.getEditTrainerMenu().isDisplayed()) {
			this.waitForEditTrainerMenuOpen();
		} else {
			throw new IllegalArgumentException("ERROR: Neither Add Trainer nor Edit Trainer menus are displayed.");
		}
		return driver.findElement(By.id("trainerEmail"));
	}

	public WebElement getTitleTextBox() {
		if (this.getAddTrainerMenu().isDisplayed()) {
			this.waitForAddTrainerMenuOpen();
		} else if (this.getEditTrainerMenu().isDisplayed()) {
			this.waitForEditTrainerMenuOpen();
		} else {
			throw new IllegalArgumentException("ERROR: Neither Add Trainer nor Edit Trainer menus are displayed.");
		}
		return driver.findElement(By.id("Title"));
		//return driver.findElement(By.id("trainerTitle"));
	}

	public WebElement getTierDropdown() {
		if (this.getAddTrainerMenu().isDisplayed()) {
			this.waitForAddTrainerMenuOpen();
		} else if (this.getEditTrainerMenu().isDisplayed()) {
			this.waitForEditTrainerMenuOpen();
		} else {
			throw new IllegalArgumentException("ERROR: Neither Add Trainer nor Edit Trainer menus are displayed.");
		}
		return driver.findElement(By.id("trainerTier"));
	}

	//Add Trainer only (Edit Trainer uses 'Update' button)
	public WebElement getSaveButton() {
		waitForAddTrainerMenuOpen();
		wait.until(ExpectedConditions.attributeContains(this.getAddTrainerMenu(), "innerHTML", "Add Trainer"));		//Look specifically for Add Trainer menu
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

	//Add and Edit Trainer only (Deactivate Trainer uses 'Cancel')
	public WebElement getCloseButton() {
		if (this.getAddTrainerMenu().isDisplayed()) {
			this.waitForAddTrainerMenuOpen();
			return driver.findElement(By.cssSelector("#createTrainerModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > button:nth-child(2)"));
		} else if (this.getEditTrainerMenu().isDisplayed()) {
			this.waitForEditTrainerMenuOpen();
			return driver.findElement(By.cssSelector("#editTrainerModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > button:nth-child(2)"));
		} else {
			throw new IllegalArgumentException("ERROR: Neither Add Trainer nor Edit Trainer menus are displayed.");
		}
	}

	public WebElement getGrayX() {
		if (this.getAddTrainerMenu().isDisplayed()) {
			this.waitForAddTrainerMenuOpen();
			return driver.findElement(By.cssSelector("#createTrainerModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > button:nth-child(1)"));		
		} else if (this.getEditTrainerMenu().isDisplayed()) {
			this.waitForEditTrainerMenuOpen();
			return driver.findElement(By.cssSelector("#editTrainerModal > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > button:nth-child(1)"));
		} else {
			throw new IllegalArgumentException("ERROR: Neither Add Trainer nor Edit Trainer menus are displayed.");
		}

	}

	//trainerModalLabel is in Create Trainer, Update Trainer, and Deactivate Trainer menus
	public WebElement getTrainerModalLabel() {
		if (this.getAddTrainerMenu().isDisplayed()) {
			this.waitForAddTrainerMenuOpen();
		} else if (this.getEditTrainerMenu().isDisplayed()) {
			this.waitForEditTrainerMenuOpen();
		} else {
			throw new IllegalArgumentException("ERROR: Neither Add Trainer nor Edit Trainer menus are displayed.");
		}
		return driver.findElement(By.id("trainerModalLabel"));
	}

	//Edit Trainer exclusive

	public WebElement getEditTrainerMenu() throws NoSuchElementException{
		waitForTrainerPageOpen();
		//return driver.findElement(By.id("trainerModalLabel"));	//Checks only label "Edit Trainer"
		return driver.findElement(By.id("editTrainerModal"));
	}
}
