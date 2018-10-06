Feature: Adding a Trainer with valid input works.

@AddTrainerValidInput
Scenario: 
	Given I am logged in to the Caliber website
  	And I am in the Add Trainer menu
	When I properly fill in Trainer Name, Title, Email, and Tier
 		And I click Save
	Then a new Trainer should appear on the list of Trainers with the proper fields I filled in.