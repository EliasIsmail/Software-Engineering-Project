Feature: Time setting of activity

Scenario: Edit activity times
Given there exists an activity in a project
And the user is the leader of the project
And the start week of the project has been set
When the user sets the estimated time for an activity to 7 weeks
Then the estimated time is updated to 7 weeks for the activity in the system


#outcommented bc. of changes in weeknumber regulations
#Scenario: Wrongfully edit activity times
#Given there exists an activity in a project
#And the user is the leader of the project
#And the start week of the project has been set
#When the user sets the estimated time for an activity to a negative number of weeks
#Then the following message will be displayed: "Impossible time frame for activity"