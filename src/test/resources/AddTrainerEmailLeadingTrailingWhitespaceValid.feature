Feature: Adding Trainer with Email with whitespace before or after it is valid

@AddTrainerEmailLeadingTrailingWhitespaceValid
Scenario:
	Given I am logged in to the Caliber website
  	And I am in the Add Trainer menu
  	And the Trainer name field is filled in
	When the Email field has a valid email
  	But the email has whitespace to the left or right of it
  	And I click Save
	Then an error message should NOT appear below the Email textbox saying “Please enter an email address.”