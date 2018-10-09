Feature: Adding a Trainer with no title will not work.

@AddTrainerTitleBlank
Scenario:
	Given I am logged in to the Caliber website
  	And I am in the Add Trainer menu
	When the Title field is left blank
  	And I click Save
	Then an error message should appear below the Enter Title textbox saying Please fill out this field.