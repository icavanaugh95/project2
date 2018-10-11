
Feature: Check that reports link works

@navReports
	Scenario: Check that reports button works
			Given I am logged in to the Caliber website nav
			When I click on the Reports
			Then i should be in the Reports page