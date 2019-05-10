Feature: Check activity status

Scenario: Check activity status
Given there exists an activity in a project with a project leader
And the necessary info for an activity status is filled out
When the user checks the status of an activity
Then the system returns a status of the activity

Scenario: Check activity status when missing info
Given there exists an activity in a project with a project leader
When the user checks the status of an activity
Then the user gets an error message saying: "Missing information for status report"