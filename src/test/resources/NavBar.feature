

Feature: All Links on the Navigation Bar work 


  @navHome
  Scenario: Check that home button works
  		Given I am logged in to the Caliber website nav
			When I click on the Home button
			Then I should see the Home page information
			
	@navManageBatch
	Scenario: Check that manage batch button works
			Given I am logged in to the Caliber website nav
			When I click on the Manage Batch
			Then I should see the Manage Batch page
			
	@navAssessBatch
	Scenario: Check that assess batch button works
			Given I am logged in to the Caliber website nav
			When I click on the Assess Batch
			Then I should be in the Assess Batch page
			
	@navQualityAudit	
	Scenario: Check that quality audit button works
			Given I am logged in to the Caliber website nav
			When I click on the Quality Audit
			Then i should be in the Quality Audit page
	
	@navPanel
	Scenario: Check that Panel button works
			Given I am logged in to the Caliber website nav
			When I click on the Panel 
			Then I should be in the Panel page

	@navReports
	Scenario: Check that reports button works
			Given I am logged in to the Caliber website nav
			When I click on the Reports
			Then i should be in the Reports page
			
	@navSettings
	Scenario: Checks that Setting button displays options
			Given I am logged in to the Caliber website nav
			When I click on the Setting
			Then I should see Trainers Locations and Category Options

	@navTrainers
	Scenario: Checks that trainers button works
			Given I am logged in to the Caliber website nav
			Given Settings have been revealed
			When I click on Trainers
			Then I should see the Trainers page 
	
	@navLocation
	Scenario: Checks that location button works
			Given I am logged in to the Caliber website nav
			Given Settings have been revealed
			When I click on Location
			Then I should see the Location page

	@navCategory
	Scenario: Checks that category button works
			Given I am logged in to the Caliber website nav
			Given Settings have been revealed
			When I click on the Category
			Then I should see the Category page
  