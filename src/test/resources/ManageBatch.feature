
#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Managing a Batch


@OpenYearMenu
Scenario:
Given I am logged on the Manage Batch Page
When I click on the Year Menu
Then I should be given a selection of Batches for each year

@NotOpenOldYearBatch
Scenario:
Given I am logged on the Manage Batch Page
When I click on the Year Menu
Then I should not be able to make a batch for a prior year

@CreateNewBatch
Scenario:
Given I am logged on the Manage Batch Page
When I click on the Create Batch tab
Then I should be able to Create a new Batch

@ImportNewBatch
Scenario:

Given I am logged on the Manage Batch Page
When I click on the Import Batch tab
And I select a batch from the Menu
And I click on the Import Button
Then I should be able to Import a new Batch

@
Scenario:
Given I am logged on the Manage Batch Page
When I click on the View/Add Trainees Icon
Then I should be able to Import a new Batch

Scenario:
Given I am logged on the Manage Batch Page
When I click on any X Button for a record
Then I should be able to delete a record
