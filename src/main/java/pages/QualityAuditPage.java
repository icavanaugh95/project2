package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QualityAuditPage {
	private static WebDriver driver;
	private static WebDriverWait wait;

	public QualityAuditPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getDropdownYear() {
		waitForDiv();
		return driver.findElement(
				By.cssSelector("body > div > ui-view > ui-view > div.container.ng-scope > div:nth-child(1) > "
						+ "div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li:nth-child(1) > a"));
	}

	public List<WebElement> getYearsFromDropdown() {
		waitForDiv();
		String[] years = driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div.container.ng-scope > "
				+ "div:nth-child(1) > div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li.dropdown.open > ul"))
				.getAttribute("innerHTML").split("class=\"ng-binding\">");
		List<WebElement> yearElements = new ArrayList<WebElement>();

		for (int i = 1; i < years.length; i++)
			yearElements.add(driver.findElement(By.linkText(years[i].substring(0, years[i].indexOf('<', 0)))));
		return yearElements;
	}

	public WebElement getDropdownTrainer() {
		waitForDiv();
		return driver.findElement(
				By.cssSelector("body > div > ui-view > ui-view > div.container.ng-scope > div:nth-child(1) > "
						+ "div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li.dropdown.ng-scope > a"));
	}

	public List<WebElement> getTrainersFromDropdown() {
		waitForDiv();
		String[] trainers = driver
				.findElement(By.cssSelector("body > div > ui-view > ui-view > div.container.ng-scope > "
						+ "div:nth-child(1) > div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li.dropdown.ng-scope.open > ul"))
				.getAttribute("innerHTML").split("class=\"ng-binding\">");
		List<WebElement> trainerElements = new ArrayList<WebElement>();
		// all the links are sort of ugly in the page so need partialLinkText
		for (int i = 1; i < trainers.length; i++) {
			trainerElements.add(driver
					.findElement(By.partialLinkText(trainers[i].substring(1, trainers[i].indexOf('-', 0)).trim())));
		}

		return trainerElements;
	}

	public List<WebElement> getWeeks() {
		waitForDiv();
		String[] weeks = driver
				.findElement(
						By.cssSelector("body > div > ui-view > ui-view > div.container.ng-scope > div:nth-child(1) >"
								+ " div > div.col-sm-12.col-md-12.col-lg-12.top5.ng-scope > ul"))
				.getAttribute("innerHTML").split("class=\"ng-binding\">Week");
		List<WebElement> weekElements = new ArrayList<WebElement>();

		for (int i = 1; i < weeks.length; i++) {
			weekElements.add(driver
					.findElement(By.partialLinkText("Week " + weeks[i].substring(0, weeks[i].indexOf('<')).trim())));
		}

		return weekElements;
	}

	public WebElement getYesAddWeekButton() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#confirmingweeks > div > div > div.modal-footer")));
		return driver.findElement(By.cssSelector("#yesBtn"));
	}
	
	public WebElement getAddWeekButton() {
		waitForDiv();
		return driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div.container.ng-scope > "
				+ "div:nth-child(1) > div > div.col-sm-12.col-md-12.col-lg-12.top5.ng-scope > ul > li:nth-child(18) > a"));
	}
	
	public List<WebElement> getTextBoxes(){
		return driver.findElements(By.tagName("textarea"));
	}
	
	public void waitForDiv() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("body > div > ui-view > ui-view > div.container.ng-scope")));
	}
}
