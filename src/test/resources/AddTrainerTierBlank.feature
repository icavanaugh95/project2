Feature: Adding a Trainer without selecting a Tier will not work.

@AddTrainerTierBlank
Scenario:
	Given I am logged in to the Caliber website
  	And I am in the Add Trainer menu
	  And the Trainer name field is filled in
	  And the Email field is filled in
	  And the Title field is filled in
	When a Tier is not selected
  	And I click Save
	Then an error message should appear below the Select Tier dropdown saying Please select an item in the list.