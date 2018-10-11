package cucumberclasses;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = {"src/test/resources/NavBarAssessBatch.feature",
					"src/test/resources/NavBarCategory.feature",
					"src/test/resources/NavBarHome.feature",
					"src/test/resources/NavBarLocations.feature",
					"src/test/resources/NavBarManageBatch.feature",
					"src/test/resources/NavBarPanel.feature",
					"src/test/resources/NavBarQualityAudit.feature",
					"src/test/resources/NavBarReports.feature",
					"src/test/resources/NavBarSettings.feature",
					"src/test/resources/NavBarTrainers.feature"},				
		glue = {"cucumberclasses"}
		)

public class NGCucumberRunnerNavBar extends AbstractTestNGCucumberTests{

}
