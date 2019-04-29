Feature: Create activity

Scenario: Create activity
Given there exists a project
And the user is the leader of the project
When the user creates an activity with title "Boil water"
Then the activity is created in the system

Scenario: Set attributes of activity
Given there exists an activity in a project
And the user is the leader of the project
When the user sets the start time to 21 and client to "DTU: Aqua"
Then both the start time and client of the activity has been set

Scenario: Set illegal attributes of activity
Given there exists an activity in a project
And the user is the leader of the project
When the user sets the startTime of the activity to "Next week"
Then the following message will be displayed: "Illegal attributes"