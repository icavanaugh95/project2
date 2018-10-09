Feature: The Add Trainer menu can close by clicking the Clise button.

@AddTrainerMenuClose
Scenario:
	Given I am logged in to the Caliber website
  	And I am in the Add Trainer menu
	When I click on Close
	Then the Add Trainer menu should close without any errors.