Feature: Time setting of project

Scenario: Set project start time
Given there exists a project with a project leader
When the user sets the start week of the project to 3
Then the start week of the project is updated to 3 in the system

Scenario: Set project start time but user isn't the project leader
Given there exists a project with a project leader
And the user is not the leader of the project
When the user sets the start week of the project to 3
Then the following message will be displayed: "The user isn't leader of the project"

Scenario: The activity start time is before earlier than project start week
Given there exists a project with a project leader
And an activity in the project starts in week 2
When the user sets the project start week to 3
Then the following message will be displayed: "Activity start date before project start week"
