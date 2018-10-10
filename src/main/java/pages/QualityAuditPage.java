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
		String linkText = "";
		String[] trainers = driver
				.findElement(By.cssSelector("body > div > ui-view > ui-view > div.container.ng-scope > div:nth-child(1) > div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li.dropdown.ng-scope.open > ul"))
				.getAttribute("innerHTML").split("class=\"ng-binding\">");
		List<WebElement> trainerElements = new ArrayList<WebElement>();
		// all the links are sort of ugly in the page so need partialLinkText
		for (int i = 1; i < trainers.length; i++) {
			linkText = trainers[i].substring(1, trainers[i].indexOf('-', 0)).trim();
			linkText += trainers[i].substring((trainers[i].indexOf('-', 0) - 1), trainers[i].indexOf('<',0));
			linkText = linkText.trim();
			trainerElements.add(driver.findElement(By.linkText(linkText)));
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#confirmingweeks > div > div > div.modal-footer")));
		return driver.findElement(By.cssSelector("#yesBtn"));
	}
	
	public WebElement getNoAddWeekButton() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#confirmingweeks > div > div > div.modal-footer > button.btn.btn-default")));
		return driver.findElement(By.cssSelector("#confirmingweeks > div > div > div.modal-footer > button.btn.btn-default"));
	}
	
	public WebElement getAddWeekButton() {
		waitForDiv();
		return driver.findElement(By.cssSelector("span[class='glyphicon glyphicon-plus']"));
	}
	
	
	public List<WebElement> getTextBoxes(){
		return driver.findElements(By.tagName("textarea"));
	}
	
	public void waitForDiv() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("body > div > ui-view > ui-view > div.container.ng-scope")));
	}
	
	public WebElement getOverallSmiley() {
		return driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div.container.ng-scope > div.container.ng-scope "
				+ "> div.row.centered > button:nth-child(2)"));
	}
	
	public WebElement getOverallMeh() {
		return driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div.container.ng-scope > div.container.ng-scope "
				+ "> div.row.centered > button:nth-child(3)"));
	}
	
	public WebElement getOverallFrown() {
		return driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div.container.ng-scope > div.container.ng-scope "
				+ "> div.row.centered > button:nth-child(4)"));
	}
}
