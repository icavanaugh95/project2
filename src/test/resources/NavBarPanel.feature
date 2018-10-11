
Feature: Checks that the Panel link works

@navPanel
	Scenario: Check that Panel button works
			Given I am logged in to the Caliber website nav
			When I click on the Panel 
			Then I should be in the Panel page