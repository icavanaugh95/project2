
Feature: Checks that Manage Batch link works

@navManageBatch
	Scenario: Check that manage batch button works
			Given I am logged in to the Caliber website nav
			When I click on the Manage Batch
			Then I should see the Manage Batch page
			