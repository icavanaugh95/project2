package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AssessBatchPage {

	private static WebDriver driver;
	private static WebDriverWait wait;

	public AssessBatchPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getYearSelector() {

		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div > ui-view > ui-view > div:nth-child(1) > div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li:nth-child(1) > a")));

		return driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div:nth-child(1) > div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li:nth-child(1) > a"));
	}


	public List<WebElement> getYears() {
		getYearSelector().click();

		StringBuilder elementStr = new StringBuilder();
		List<WebElement> webList = new ArrayList<WebElement>();
		int i = 1;
		
			while(true) {

				elementStr.append("body > div > ui-view > ui-view > div:nth-child(1) > div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li.dropdown.open > ul > li:nth-child(");
				elementStr.append(i);
				elementStr.append(") > a");
				i += 1;
				//System.out.println(elementStr.toString());
				
				if(driver.findElements(By.cssSelector(elementStr.toString())).size() == 0)
				{
					break;
				}
				
				
				webList.add(driver.findElement(By.cssSelector(elementStr.toString())));
				elementStr.setLength(0);

			}
		

		return webList;


	}


	public WebElement getBatchSelector() {

		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div > ui-view > ui-view > div:nth-child(1) > div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li:nth-child(2) > a")));
		return driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div:nth-child(1) > div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li:nth-child(2) > a"));
	}

	public List<WebElement> getBatchSelectorBatches(){
		
		getBatchSelector().click();
		
		StringBuilder elementStr = new StringBuilder();
		List<WebElement> webList = new ArrayList<WebElement>();
		int i = 1;
		
			while(true) {

				elementStr.append("body > div > ui-view > ui-view > div:nth-child(1) > div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li.dropdown.open > ul > li:nth-child(");
				elementStr.append(i);
				elementStr.append(")");
				i += 1;
				//System.out.println(elementStr.toString());
				
				if(driver.findElements(By.cssSelector(elementStr.toString())).size() == 0)
				{
					break;
				}
				
				webList.add(driver.findElement(By.cssSelector(elementStr.toString())));
				elementStr.setLength(0);
				
				

			}
		
		return webList;


	}


}
