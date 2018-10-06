Feature: Add Trainer menu can open

@AddTrainerMenuOpen
Scenario:
	Given I am logged in to the Caliber website
  	And I am on the Trainers Page
	When I click on “Create Trainer+”
	Then the Add Trainer menu should appear.