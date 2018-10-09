package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	public MainPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getUsername() {
		return driver.findElement(By.id("username"));
	}
	
	public WebElement getPassword() {
		return driver.findElement(By.id("pw"));
	}
	
	public WebElement getLoginButton() {
		return driver.findElement(By.cssSelector("input[value='Login']"));
	}
	
	
	public void waitForNavBar() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[ui-sref='vp.manage']")));
	}

	public WebElement getHomeLink() {
		waitForNavBar();
		return driver.findElement(By.linkText("Home"));
	}
	
	public WebElement getManageBatchLink() {
		waitForNavBar();
		return driver.findElement(By.cssSelector("a[ui-sref='vp.manage']"));
	}
	
	public WebElement getAccessBatchLink() {
		waitForNavBar();
		return driver.findElement(By.cssSelector("a[ui-sref='vp.assess']"));
	}
	
	public WebElement getQualityAuditLink() {
		waitForNavBar();
		return driver.findElement(By.cssSelector("a[ui-sref='vp.audit']"));
	}
	
	public WebElement getPanelLink() {
		waitForNavBar();
		return driver.findElement(By.cssSelector("a[ui-sref='vp.panel']"));
	}
	
	public WebElement getReportsLink() {
		waitForNavBar();
		return driver.findElement(By.cssSelector("a[ui-sref='vp.reports']"));
	}
	
	public WebElement getSettingsDropdown() {
		waitForNavBar();
		return driver.findElement(By.cssSelector("li[role='presentation']"));
	}
	
	public WebElement getTrainersLinkFromSettings() {
		waitForNavBar();
		return driver.findElement(By.cssSelector("a[ui-sref='vp.trainers']"));
	}
	
	public WebElement getLocationsLinkFromSettings() {
		waitForNavBar();
		return driver.findElement(By.cssSelector("a[ui-sref='vp.locations']"));
	}
	
	public WebElement getCategoryLinkFromSettings() {
		waitForNavBar();
		return driver.findElement(By.cssSelector("a[ui-sref='vp.category']"));
	}
	
	public WebElement getLogoLink() {
		waitForNavBar();
		return driver.findElement(By.cssSelector("img[src='/static/app/resources/images/rev-logo.jpg']"));
	}
	
	
}
