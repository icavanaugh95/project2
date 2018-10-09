Feature: Adding a Trainer with the email field with and '@' but nothing to the left or right to it will not work.

@AddTrainerEmailNoUserOrDomain
Scenario:
	Given I am logged in to the Caliber website
  	And I am in the Add Trainer menu
	When the Email field does not have any text to the left or the right of the @
  	And I click Save
	Then an error message should appear below the Email textbox saying Please enter an email address.