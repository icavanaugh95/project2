
Feature: Checks that link works

 @navAssessBatch
	Scenario: Check that assess batch button works
			Given I am logged in to the Caliber website nav
			When I click on the Assess Batch
			Then I should be in the Assess Batch page