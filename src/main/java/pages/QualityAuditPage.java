package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QualityAuditPage {
	private static WebDriver driver;
	
	public QualityAuditPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getDropdownYear() {
		return driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div.container.ng-scope > div:nth-child(1) > "
				+ "div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li:nth-child(1) > a"));
	}
	
	public List<WebElement> getYearsFromDropdown() {
			String[] years = driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div.container.ng-scope > "
					+ "div:nth-child(1) > div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li.dropdown.open > ul"))
					.getAttribute("innerHTML")
					.split("class=\"ng-binding\">");
			List<WebElement> yearElements = new ArrayList<WebElement>();
			
			for(int i = 1; i < years.length; i++) 
				yearElements.add(driver.findElement(By.linkText(years[i].substring(0, years[i].indexOf('<', 0)))));
			return yearElements;
	}
}
