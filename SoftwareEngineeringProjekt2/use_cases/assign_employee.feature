Feature: Assign available employee to activities and projects

Scenario: Assign available employee to activity
Given there exists an activity in a project
And an employee is available
And the user is the leader of the project
When the user adds the available employee to the activity
Then the employee is added to the activity in the system

Scenario: assign employee to project
Given there exists a project with title "weNeedEmployeesHere" and with client "Starbucks"
And there exists an employee with name "Oliver"
When I add the employee to the project
Then the employee is added to the project

Scenario: assign employee to project in which the employee is already assigned
Given there exists a project with title "weNeedEmployeesHere" and with client "Starbucks"
And there exists an employee with name "Oliver"
When I add the employee to the project
When I add the employee to the project
Then the employee is added to the project

#Scenario: Assign unavailable employee to activity
#Given there exists an activity in a project
#And the user is the leader of the project
#When the user adds an unavailable employee to the activity
#Then the following message will be displayed: "Employee is currently unavailable"
