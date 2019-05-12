Feature: Time setting of project

Scenario: Set project start time
Given there exists a project with a project leader
When the user sets the start week of the project to 3
Then the start week of the project is updated to 3 in the system

Scenario: Set project start time but user isn't the project leader
Given there exists a project with a project leader
And the user is not the leader of the project
When the user sets the start week of the project to 3
Then the user gets an error message saying: "User must be leader to execute operation"

#Scenario: The activity start time is before earlier than project start week
#Given there exists a project with a project leader
#And an activity in the project starts in week 2
#When the user sets the project start week to 3
#Then the following message will be displayed: "Activity start date before project start week"

Scenario: Set project end time
Given there exists a project with a project leader
When the user sets the end week of the project to 3
Then the end week of the project is updated to 3 in the system

Scenario: Set project end time but user isn't the project leader
Given there exists a project with a project leader
And the user is not the leader of the project
When the user sets the end week of the project to 3
Then the following message will be displayed: "User must be leader to execute operation"

Scenario: Get projects estimated time
Given there exists a project with a project leader
And the project has an activity with estimated time set to 120.0
And the project has an activity with estimated time set to 20.0
Then the projects estimated time is set to 140.0

Scenario: Get projects estimated time
Given there exists a project with a project leader
When the user sets estimated time to -3
Then the following message will be displayed: "Please enter valid integer"

Scenario: Get projects current time
Given there exists a project with a project leader
And the project has an activity with estimated time set to 120.0
And an employee has logged 6.0 hours of work to the activity
Then the projects time is set to 6.0

Scenario: Set endweek to illegal number 
Given there exists a project with a project leader
And endweek is set to 100
Then the following message will be displayed: "Undefined week number"

Scenario: Set startweek to illegal number 
Given there exists a project with a project leader
And startweek is set to 100
Then the following message will be displayed: "Undefined week number"

Scenario: Set endweek before an activity's startweek 
Given there exists a project with a project leader
And the project has an activity with endweek set to 12
When I set the projects endweek to 10
Then the activitys endweek is set to 10

#Scenario: The activity end time is after the project endweek
#Given there exists a project with a project leader
#And an activity in the project ends in week 4
#When the user sets the project end week to 3
#Then the following message will be displayed to the user: "Activity end date after project end date"
