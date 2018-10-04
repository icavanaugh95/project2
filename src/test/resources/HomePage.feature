

Feature:  Checks all links on the Home Page

  
  Scenario: Click User Guide
    Given I am logged on to the Home Page 
		When I click on the user guide link
		Then I will see the User Guide on github
		
#	Scenario:
#		Given I am logged on to the Home Page 
#		When I click on the All States drop down menu and select a state on the Last Quality Audit 
#		Then The graph updates to only include batches from selected state
	
#	Scenario:
#		Given I am logged on to the Home Page 
#		When I click on the All States drop down menu and select a state on the Weekly Progress 
#		Then The graph updates to only include batches from selected state
	
		


  
  