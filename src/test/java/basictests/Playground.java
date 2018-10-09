package basictests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.HomePage;
import pages.ManageBatchPage;

public class Playground {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver = null;
		ManageBatchPage tester = new ManageBatchPage(driver);
		
		tester.getCreateBatch().click();
		
		WebDriver driver2 = null;
		HomePage tester2 = new HomePage(driver2);
		
		tester2.getLastAuditStates();
		
		
		
		
	}

}
