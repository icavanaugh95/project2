package cucumberclasses;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = {"src/test/resources/HomePage.feature",											//Homepage
					"src/test/resources/TrainerPageOpen.feature",									//Add Trainer
					"src/test/resources/AddTrainerMenuOpen.feature",
					"src/test/resources/AddTrainerValidInput.feature",
					"src/test/resources/AddTrainerEmailLeadingTrailingWhitespaceValid.feature",
					"src/test/resources/AddTrainerNameBlank.feature",
					"src/test/resources/AddTrainerEmailBlank.feature",
					"src/test/resources/AddTrainerEmailNoAt.feature",
					"src/test/resources/AddTrainerEmailNoUserOrDomain.feature",
					"src/test/resources/AddTrainerTitleBlank.feature",
					"src/test/resources/AddTrainerTierBlank.feature",
					"src/test/resources/AddTrainerMenuClose.feature",
					"src/test/resources/AddTrainerMenuGrayX.feature",
					"src/test/resources/EditTrainerFillInFields.feature"							//Edit Trainer
					},				
		glue = {"cucumberclasses"}
		)

public class NGCucumberRunner extends AbstractTestNGCucumberTests {

}
