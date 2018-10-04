package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TrainerPage {
	private static WebDriver driver;
	
	public TrainerPage (WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getCreateTrainerButton() {
		return driver.findElement(By.cssSelector("li.pull-right > a:nth-child(1)"));
	}
	
	//Presumes one Trainer record is already in the page
	public WebElement getFirstPencilIcon() {
		return driver.findElement(By.cssSelector("tr.ng-scope:nth-child(1) > td:nth-child(5) > a:nth-child(1) > span:nth-child(1)"));
	}
	
	//Presumes one Trainer record is already in the page
	public WebElement getFirstRedX() {
		return driver.findElement(By.cssSelector("tr.ng-scope:nth-child(1) > td:nth-child(6) > a:nth-child(1) > span:nth-child(1)"));
	}
	
	
	
	
}
