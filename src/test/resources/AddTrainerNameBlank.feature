Feature: Adding a Trainer with no name is invalid.

@AddTrainerNameBlank
Scenario:
	Given I am logged in to the Caliber website
  	And I am in the Add Trainer menu
	When the Trainer Name field is left blank
  	And I click Save
	Then an error message should appear below the Name textbox saying Please fill out this field.