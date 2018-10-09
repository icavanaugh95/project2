Feature: Adding a Trainer with no '@' will not work.

@AddTrainerEmailNoAt
Scenario:
	Given I am logged in to the Caliber website
  	And I am in the Add Trainer menu
	When the Email field does not have an @
  	And I click Save
	Then an error message should appear below the Email textbox saying Please enter an email address.