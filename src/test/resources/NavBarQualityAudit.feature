
Feature:  Checks that quality audit link works

@navQualityAudit	
	Scenario: Check that quality audit button works
			Given I am logged in to the Caliber website nav
			When I click on the Quality Audit
			Then i should be in the Quality Audit page