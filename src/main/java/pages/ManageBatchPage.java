package pages;

import java.util.ArrayList;
import java.util.List;

//POM file

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageBatchPage {
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	public ManageBatchPage (WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getCreateBatch() {
		return driver.findElement(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(3) > a"));
	}
	
	public WebElement getCreateBatchBox5() {
		return driver.findElement(By.cssSelector("#goodGrade"));
	}
	public WebElement getImportBatch() {
		return driver.findElement(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(2) > a"));
	}
	
	public WebElement getYearDropdown() {
		return driver.findElement(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li.dropdown"));
	}

	public List<WebElement> getLastYear(){
			String[] years = driver.findElement(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li.dropdown > a"))
					.getAttribute("innerHTML")
					.split("class=\"ng-binding\">");
			List<WebElement> yearElement = new ArrayList<WebElement>();
			
			for(int i = 1; i < years.length; i++) 
				yearElement.add(driver.findElement(By.linkText(years[i].substring(0, years[i].indexOf('<',0)))));
				return yearElement;
			}
	
	public WebElement getYearSelector() {

		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div > ui-view > ui-view > div:nth-child(1) > div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li.dropdown.open > ul > li:nth-child(1) > a")));

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

	public WebElement getCloseCreateBatch() {
		return driver.findElement(By.cssSelector("#createBatchModal > div > div > div.modal-footer > button"));
	}
	
	public WebElement getTrainingName() {
        WebDriverWait wait1 = new WebDriverWait(driver, 20);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
		return driver.findElement(By.cssSelector("#trainingName"));
	}
	
	public WebElement getSkillType() {
		return driver.findElement(By.cssSelector("#skillType"));
	}
	
	public WebElement getTrainer() {
		return driver.findElement(By.cssSelector("#trainer"));
	}
	
	public WebElement getStartDate() {
		return driver.findElement(By.cssSelector("#start-date > input"));
	}
	
	public WebElement getGoodGrade() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#createBatchModal > div > div > div.modal-body > div:nth-child(5) > div:nth-child(1)")));
		return driver.findElement(By.cssSelector("#goodGrade"));
	}

	public WebElement getTrainingType() {
		return driver.findElement(By.cssSelector("#trainingType"));
	}

	public WebElement getLocation() {
		return driver.findElement(By.cssSelector("#location"));
	}
	
	public WebElement getCoTrainer() {
		return driver.findElement(By.cssSelector("#co-trainer"));
	}
	
	public WebElement getEndDate() {
		return driver.findElement(By.cssSelector("#end-date > input"));
	}
	public WebElement getPassingGrade() {
		return driver.findElement(By.cssSelector("#borderlineGrade"));
	}
	

		

}//end of class ManageBatchPage