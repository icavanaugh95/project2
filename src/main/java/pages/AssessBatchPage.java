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

		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div > ui-view > ui-view > div:nth-child(1) > div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li.dropdown.open > ul > li:nth-child(1)")));

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


	public List<WebElement> getBatchWeekAssessments() {

		StringBuilder elementStr = new StringBuilder();
		List<WebElement> webList = new ArrayList<WebElement>();
		int i = 1;

		while(true) {

			elementStr.append("body > div > ui-view > ui-view > div:nth-child(1) > div > div.col-sm-12.col-md-12.col-lg-12.top5 > ul > li:nth-child(");
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

	public WebElement createAssesmentButton() {
		return driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div:nth-child(1) > div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li:nth-child(4) > a"));
	}

	public WebElement importGradesButton() {
		return driver.findElement(By.cssSelector("body > div > ui-view > ui-view > div:nth-child(1) > div > div.col-md-12.col-lg-12.top10 > ul:nth-child(1) > li:nth-child(3) > a"));
	}

	public WebElement saveChangesButton() {
		return driver.findElement(By.cssSelector("#trainer-assess-table > div > div > ul > ul > div.form-group.col-lg-12.col-md-12.col-sm-12.overall-feedback > div > a > span"));
	}

	public List<WebElement> getAssociateLinks(){


		StringBuilder elementStr = new StringBuilder();
		List<WebElement> webList = new ArrayList<WebElement>();
		int i = 1;

		while(true) {

			elementStr.append("#trainer-assess-table > div > div > ul > ul > table > tbody > tr:nth-child(");
			elementStr.append(i);
			elementStr.append(") > td.col-sm-2.col-md-2.col-lg-2.ng-binding");
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


	public List<WebElement> getAssociateNotesBox(){


		StringBuilder elementStr = new StringBuilder();
		List<WebElement> webList = new ArrayList<WebElement>();
		int i = 1;

		while(true) {

			elementStr.append("#trainer-assess-table > div > div > ul > ul > table > tbody > tr:nth-child(");
			elementStr.append(i);
			elementStr.append(") > td.col-sm-8.col-md-8.col-lg-8 > textarea");
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

	public List<WebElement> getAssociateRows(){


		StringBuilder elementStr = new StringBuilder();
		List<WebElement> webList = new ArrayList<WebElement>();
		int i = 1;

		while(true) {

			elementStr.append("#trainer-assess-table > div > div > ul > ul > table > tbody > tr:nth-child(");
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


	public List<WebElement> getTraineeRowBoxes(WebElement row){

		return row.findElements(By.tagName("td"));

	}

	public WebElement getShowCommentForm(int rowNum) {
		List<WebElement> commentForm = driver.findElements(By.cssSelector("#trainer-assess-table > div > div > ul > ul > table > tbody > tr:nth-child("
				+ (rowNum + 1) + ") > td:nth-child(2) > form"));

		if(commentForm.size() > 0) {
			return commentForm.get(0);
		}

		return null;
	}
	
	
	
	public List<WebElement> getTableHeaders(){
		return driver.findElements(By.cssSelector("#trainer-assess-table > div > div > ul > ul > table > thead > tr"));
		
	}
	
	public WebElement getEditAssessmentCategory(){
		return driver.findElement(By.cssSelector("#category"));
	}
	
	public WebElement getEditAssessmentMaxPoints() {
		return driver.findElement(By.cssSelector("#editAssessmentModal_500000003 > div > div > div.modal-body > div > div:nth-child(2) > input"));
		
	}
	
	public WebElement getEditAssessmentType() {
		return driver.findElement(By.cssSelector("#assessmentType"));
	}
	
	public WebElement getEditAssessmentDelete() {
		return driver.findElement(By.cssSelector("#editAssessmentModal_500000003 > div > div > div:nth-child(3) > div:nth-child(1) > button"));
	}
	
	public WebElement getEditAssessmentUpdate() {
		return driver.findElement(By.cssSelector("#editAssessmentModal_500000003 > div > div > div:nth-child(3) > div:nth-child(2) > input"));
	}
	
	public WebElement getEditAssessmentClose() {
		return driver.findElement(By.cssSelector("#editAssessmentModal_500000003 > div > div > div:nth-child(3) > div:nth-child(2) > button"));
	}

	public List<WebElement> getEditAssessmentCatagoryOptions(WebElement dropDown){
		return dropDown.findElements(By.tagName("option"));
	}
	
	public List<WebElement> getEditAssessmentTypeOptions(WebElement dropDown){
		return dropDown.findElements(By.tagName("option"));
	}



}
