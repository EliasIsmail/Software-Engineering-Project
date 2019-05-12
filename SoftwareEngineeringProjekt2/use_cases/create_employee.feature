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

Scenario: Create employee with name
When I create an employee with name "Jonas"
Then the emplyee with name "Jonas" is created

Scenario: Create employee with empty name
When I create an employee with name ""
Then I get the error message "Missing parameters"

Scenario: Create employee without name
When I create an employee without a name
Then I get the error message "Missing parameters"

Scenario: Search for nonexisting employee
When I get an nonexisting employee named "Pegasus"
Then I get the error message "Desired object does not exists"


#Scenario: Assign unavailable employee to activity
#Given there exists an activity in a project
#And the user is the leader of the project
#When the user adds an unavailable employee to the activity
#Then the following message will be displayed: "Employee is currently unavailable"
