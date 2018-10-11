
Feature:  Checks that Trainers link works

@navTrainers
	Scenario: Checks that trainers button works
			Given I am logged in to the Caliber website nav
			Given Settings have been revealed
			When I click on Trainers
			Then I should see the Trainers page 