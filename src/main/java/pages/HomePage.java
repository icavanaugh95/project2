package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	private static WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getUserGuideLink() {
		return driver.findElement(By.cssSelector("a[href='https://github.com/pjw6193/caliber/wiki#user-guide']"));
	}
	
	public WebElement getLastAuditStateDropdown() {
		return driver.findElement(By.cssSelector("select[ng-model='selectedBarState']"));
	}
	
	public List<WebElement> getLastAuditStates(){
		String[] states = getLastAuditStateDropdown()
				.getAttribute("innerHTML")
				.split("<option label=\"");
		List<WebElement> stateElement = new ArrayList<WebElement>();
		for(int i = 1; i < states.length; i++) {
			stateElement.add(driver.findElement(
					By.cssSelector("option[label='" + states[i].substring(0, 2) + "']")));
		}
		return stateElement;
	}
	
	public WebElement getLastAuditCityDropdown() {
		return driver.findElement(By.cssSelector("select[ng-model='selectedBarCity']"));
	}
	
	public List<WebElement> getLastAuditCities(){
		String[] cities = getLastAuditCityDropdown()
				.getAttribute("innerHTML")
				.split("<option label=\"");
		List<WebElement> cityElement = new ArrayList<WebElement>();
		for(int i = 1; i < cities.length; i++) {
			cityElement.add(driver.findElement(
					By.cssSelector("option[label='" + 
					cities[i].substring(0, cities[i].indexOf("\"", 0)) + "']")));
		}		
		return cityElement;
	}
	
	
	public WebElement getWeeklyAuditStateDropdown() {
		return driver.findElement(By.cssSelector("select[ng-model='selectedLineState']"));
	}
	
	public List<WebElement> getWeeklyAuditStates(){
		String[] states = getWeeklyAuditStateDropdown()
				.getAttribute("innerHTML")
				.split("value=\"object:");
		
		List<WebElement> stateElement = new ArrayList<WebElement>();
		for(int i = 1; i < states.length; i++) {
			stateElement.add(driver.findElement(
					By.cssSelector("option[value='object:" + states[i].substring(0, 3) + "']")));
		}
		return stateElement;
	}
	
	public WebElement getWeeklyAuditCityDropdown() {
		return driver.findElement(By.cssSelector("select[ng-model='selectedLineCity']"));
	}
	
	public List<WebElement> getWeeklyAuditCities(){
		String[] cities = getWeeklyAuditCityDropdown()
				.getAttribute("innerHTML")
				.split("value=\"object:");
		
		List<WebElement> cityElement = new ArrayList<WebElement>();
		for(int i = 1; i < cities.length; i++) {
			cityElement.add(driver.findElement(
					By.cssSelector("option[value='object:" + cities[i].substring(0, 3) + "']")));
		}		
		return cityElement;
	}
}
