
Feature: Checks that the Category link works

@navCategory
	Scenario: Checks that category button works
			Given I am logged in to the Caliber website nav
			Given Settings have been revealed
			When I click on the Category
			Then I should see the Category page