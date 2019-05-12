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

Scenario: Set endweek before activity start- and endweek
Given there exists a project with a project leader
And an activity lasts from week 15 through 18
When the user set the projects endweek to 10
Then the activity lasts from week 9 through 10

Scenario: Set startweek after activity start- and endweek
Given a project has been created
And an activity lasts from week 5 through 8
When the user set the projects startweek to 10
Then the activity lasts from week 10 through 11