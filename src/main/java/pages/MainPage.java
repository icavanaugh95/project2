package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
	private static WebDriver driver;
	
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
}
