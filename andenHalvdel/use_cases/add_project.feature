Feature: Create project
	Description: A project is created with various details
	Actors: user

Scenario: Create a new project
 	When I create a new project
 	Then the project is created
 	And the project id is set to be "2019123456"
 	
Scenario: Create a new project
	When I create 10 new projects
	Then the projects are created
	And the project ids are set
	When I set some details
	Then the details are set
 	
Scenario: Add employees and details to project
	Given I have a project with ID "2019123456"
	When I add an employee, startDate, endDate, workLoad and a client
	Then all the details are added
	
Scenario: Add projectleader to project
	Given I have a project with ID "2019123456"
	When I add a project leader to the project
	Then the project has a projectleader with ID "ABCD"
		