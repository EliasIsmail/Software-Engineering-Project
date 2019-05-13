Feature: Create activity

Scenario: Create activity
Given there exists a project
And the user is the leader of the project
When the user creates an activity with title "Boil water"
Then the activity is created in the system

Scenario: Create activity
Given there exists a project
And the user is the leader of the project
When the user creates an activity with title ""
Then the following message will be displayed: "Missing activity information"

Scenario: Set attributes of activity
Given there exists an activity in a project
And the user is the leader of the project
When the user sets the start week to 21
Then the start time is set