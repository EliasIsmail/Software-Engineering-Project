Feature: Register work hours

Scenario: Register work hours
Given there exists an activity in a project
Given the user is an employee
When the user registers 7 work hours on a given date
Then the employees work hours are registered in the system

Scenario: Register work hours
Given there exists an activity in a project
Given the user is an employee
And the user registers 7 work hours on a given date
When the user registers 8 work hours on a given date
Then I get the error message "Activity already added to log"

Scenario: Register work hours
Given there is a project
And there are two activies in the project
When the user registers 4 hours on both activities on the same day
Then The this is registered in the log for the day

Scenario: Remove one activity from the day
Given there is a project
And there are two activies in the project
And the user registers 4 hours on both activities on the same day
When the user deletes on of them
Then there is only one left

Scenario: Remove work hours
Given there exists an activity in a project
Given the user is an employee
And the user registers 7 work hours on a given date
When the user removes that log element
Then the log element is removed

