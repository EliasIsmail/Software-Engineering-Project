Feature: Register work hours

Scenario: Register work hours
Given there exists an activity in a project
Given the user is an employee
When the user registers 7 work hours on a given date
Then the employees work hours are registered in the system

Scenario: Remove work hours
Given there exists an activity in a project
Given the user is an employee
And the user registers 7 work hours on a given date
When the user removes that log element
Then the log element is removed