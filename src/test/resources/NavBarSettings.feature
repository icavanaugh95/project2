
Feature: Checks that setting drop down works correctly

@navSettings
	Scenario: Checks that Setting button displays options
			Given I am logged in to the Caliber website nav
			When I click on the Setting
			Then I should see Trainers Locations and Category Options