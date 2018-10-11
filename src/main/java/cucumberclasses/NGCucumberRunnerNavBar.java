package cucumberclasses;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = {"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/NavBarAssessBatch.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/NavBarCategory.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/NavBarHome.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/NavBarLocations.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/NavBarManageBatch.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/NavBarPanel.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/NavBarQualityAudit.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/NavBarReports.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/NavBarSettings.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/NavBarTrainers.feature"},				
		glue = {"cucumberclasses"}
		)

public class NGCucumberRunnerNavBar extends AbstractTestNGCucumberTests{

}
