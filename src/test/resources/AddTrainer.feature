#Add Trainer
Feature: Adding a trainer works

@TrainerPageOpen
Scenario:
	Given I am logged in to the Caliber website
	When I click on "Settings" 
  	And I click on the Trainers tab
	Then the Trainers page should appear on the browser.

@AddTrainerMenuOpen
Scenario:
	Given I am logged in to the Caliber website
  	And I am on the Trainers Page
	When I click on “Create Trainer+”
	Then the “Add Trainer” menu should appear.

@AddTrainerValid
Scenario: 
	Given I am logged in to the Caliber website
  	And I am in the “Add Trainer” menu
	When I properly fill in Trainer Name, Title, Email, and Tier
 		And I click “Save”
	Then a new Trainer should appear on the list of Trainers with the proper fields I filled in.

@AddTrainerInvalidInput
Scenario:
	Given I am logged in to the Caliber website
  	And I am in the “Add Trainer” menu
	When the “Trainer Name” field is left blank
  	And I click “Save”
	Then an error message should appear below the “Name” textbox saying “Please fill out this field.”

Scenario: 
	Given I am logged in to the Caliber website
  	And I am in the “Add Trainer” menu
	When the “Email” field is left blank
  	And I click “Save”
	Then an error message should appear below the “Email” textbox saying “Please fill out this field.”

Scenario:
	Given I am logged in to the Caliber website
  	And I am in the “Add Trainer” menu
	When the “Email” field does not have an ‘@’
  	And I click “Save”
	Then an error message should appear below the “Email” textbox saying “Please enter an email address.”

Scenario:
	Given I am logged in to the Caliber website
  	And I am in the “Add Trainer” menu
	When the “Email” field does not have any text to the left or the right of the ‘@’
  	And I click “Save”
	Then an error message should appear below the “Email” textbox saying “Please enter an email address.”

Scenario:
	Given I am logged in to the Caliber website
  	And I am in the “Add Trainer” menu
	When the “Email” field has a valid email
  	But the email has whitespace to the left or right of it
  	And I click “Save”
	Then an error message should NOT appear below the “Email” textbox saying “Please enter an email address.”

Scenario:
	Given I am logged in to the Caliber website
  	And I am in the “Add Trainer” menu
	When the “Title” field is left blank
  	And I click “Save”
	Then an error message should appear below the “Enter Title” textbox saying “Please fill out this field.”

Scenario:
	Given I am logged in to the Caliber website
  	And I am in the “Add Trainer” menu
	When a Tier is not selected
  	And I click “Save”
	Then an error message should appear below the “Select Tier” dropdown saying “Please select an item in the list.”

Scenario:
	Given I am logged in to the Caliber website
  	And I am in the “Add Trainer” menu
	When I click on “Close”
	Then the “Add Trainer” menu should close without any errors.

Scenario:
	Given I am logged in to the Caliber website
  	And I am in the “Add Trainer” menu
	When I click on the gray x on the top right
	Then the “Add Trainer” menu should close without any errors.
