Feature: Create an activity
	Description: An activity is created with various details
	Actors: user

Scenario: Create a new activity
 	When I create a new project
 	And I create a new activity
 	Then The activity is created
 	
Scenario: Assign employee to activity
	When I create a new project
 	And I create a new activity
 	And I create a new employee
 	When I add the employee to the activity
 	Then the employee is added to the activity

Scenario: Remove employee from activity
	When I create a new project
 	And I create a new activity
 	And I create a new employee
 	When I add the employee to the activity
 	And I remove the employee from the activity
 	Then The employee is removed