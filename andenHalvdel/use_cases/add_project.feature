Feature: Create project
	Description: A project is created with various details
	Actors: user

Scenario: Create a new project
 	When I create a new project
 	Then the project is created
 	And the project id is set
 	
Scenario: Create a new project
	When I create 10 new projects
	Then the projects are created
	And the project ids are set
	When I set some details
	Then The details are set
 	
Scenario: Add employees and details to project
	Given I create a new project
	Then I have a project
	When I add an employee, startDate, endDate, workLoad and a client
	Then All the details are added