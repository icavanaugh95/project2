Feature: The Add Trainer menu can close by clicking the grax x on the top right.

@AddTrainerMenuGrayX
Scenario:
	Given I am logged in to the Caliber website
  	And I am in the Add Trainer menu
	When I click on the gray x on the top right
	Then the Add Trainer menu should close without any errors.