Feature: Time setting of activity

Scenario: Edit activity times
Given the user is a project leader
And the user is working on the project of the activity
When the user edits the estimated time for an activity
Then the estimated times are updated for the activity in the system

Scenario: Wrongfully edit activity times
Given the user is a project leader
And the user is working on the project of the activity
When the user edits the estimated time for an activity
Then the following message will be displayed: "Times do not match"