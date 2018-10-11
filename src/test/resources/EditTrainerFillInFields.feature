Feature: Edit Trainer menu fills in data into appropriate fields.

@EditTrainerFillInFields
Scenario:
	Given I am logged in to the Caliber website
	When I click on Settings
		And I click on the Trainers tab
		And I click on any pencil icon
	Then the Edit Trainer menu should appear
		And the proper Trainer Name, Email, Title, and Tier should appear in their respective fields.