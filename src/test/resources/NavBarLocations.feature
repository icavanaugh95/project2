
Feature: Check that location link works

  @navLocation
	Scenario: Checks that location button works
			Given I am logged in to the Caliber website nav
			Given Settings have been revealed
			When I click on Location
			Then I should see the Location page