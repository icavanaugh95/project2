package cucumberclasses;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = {"src/test/resources/HomePage.feature",										//Homepage
					"src/test/resources/TrainerPageOpen.feature",								//Add Trainer
					"src/test/resources/AddTrainerMenuOpen.feature",
					"src/test/resources/AddTrainerValidInput.feature",
					"src/test/resources/AddTrainerEmailLeadingTrailingWhitespaceValid.feature"},				
		glue = {"cucumberclasses"}
		)

public class NGCucumberRunner extends AbstractTestNGCucumberTests {

}
