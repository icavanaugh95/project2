Feature: Add a Trainer with a blank email field will not work.

@AddTrainerEmailBlank
Scenario: 
	Given I am logged in to the Caliber website
  	And I am in the Add Trainer menu
	When the Email field is left blank
  	And I click Save
	Then an error message should appear below the Email textbox saying Please fill out this field.
