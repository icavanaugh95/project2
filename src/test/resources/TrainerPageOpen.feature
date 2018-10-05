Feature: Opening the Trainers page works

@TrainerPageOpen
Scenario:
	Given I am logged in to the Caliber website
	When I click on Settings 
  	And I click on the Trainers tab
	Then the Trainers page should appear on the browser.


