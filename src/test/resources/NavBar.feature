

Feature: All Links on the Navigation Bar work 

  @navHome
  Scenario: 
  		Given I am logged in to the Caliber website
			When I click on the Home button
			Then I should see the Home page information
			
	@navManageBatch
	Scenario:
			Given I am logged in to the Caliber website
			When I click on the Manage Batch
			Then I should see the Manage Batch page
			
	@navAssessBatch
	Scenario:
			Given I am logged in to the Caliber website
			When I click on the Assess Batch
			Then I should be in the Assess Batch page
			
	@navQualityAudit	
	Scenario:
			Given I am logged in to the Caliber website
			When I click on the Quality Audit
			Then i should be in the Quality Audit page
	
	@navPanel
	Scenario:
			Given I am logged in to the Caliber website
			When I click on the Panel 
			Then I should be in the Panel page

	@navReports
	Scenario:
			Given I am logged in to the Caliber website
			When I click on the Reports
			Then i should be in the Reports page
			
	@navSettings
	Scenario:
			Given I am logged in to the Caliber website
			When I click on the Setting
			Then I should see Trainers Locations and Category Options

	@navTrainers
	Scenario:
			Given I am logged in to the Caliber website
			Given Settings have been revealed
			When I click on Trainers
			Then I should see the Trainers page 
	
	@navLocation
	Scenario:
			Given I am logged in to the Caliber website
			Given Settings have been revealed
			When I click on Location
			Then I should see the Location page

	@navCategory
	Scenario:
			Given I am logged in to the Caliber website
			Given Settings have been revealed
			When I click on the Category
			Then I should see the Category page
  