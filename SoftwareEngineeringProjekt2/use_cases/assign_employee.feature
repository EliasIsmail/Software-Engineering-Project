Feature: Assign available employee to activity

Scenario: Assign available employee to activity
Given there exists an activity in a project
And an employee is available
And the user is the leader of the project
When the user adds the available employee to the activity
Then the employee is added to the activity in the system

#Scenario: Assign unavailable employee to activity
#Given there exists an activity in a project
#And the user is the leader of the project
#When the user adds an unavailable employee to the activity
#Then the following message will be displayed: "Employee is currently unavailable"
