
Feature: Check that Home link works

@navHome
  Scenario: Check that home button works
  		Given I am logged in to the Caliber website nav
			When I click on the Home button
			Then I should see the Home page information

 