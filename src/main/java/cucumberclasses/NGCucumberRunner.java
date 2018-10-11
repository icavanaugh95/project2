package cucumberclasses;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = {"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/HomePage.feature",											//Homepage
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/TrainerPageOpen.feature",									//Add Trainer
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/AddTrainerMenuOpen.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/AddTrainerValidInput.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/AddTrainerEmailLeadingTrailingWhitespaceValid.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/AddTrainerNameBlank.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/AddTrainerEmailBlank.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/AddTrainerEmailNoAt.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/AddTrainerEmailNoUserOrDomain.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/AddTrainerTitleBlank.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/AddTrainerTierBlank.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/AddTrainerMenuClose.feature",
					"C:/Users/Administrator/.jenkins/workspace/Project 2/src/test/resources/AddTrainerMenuGrayX.feature",
					/*"src/test/resources/EditTrainerFillInFields.feature"*/							//Edit Trainer
					},				
		glue = {"cucumberclasses"}
		)

public class NGCucumberRunner extends AbstractTestNGCucumberTests {

}
