Feature: Set project leader

Scenario: Set employee to leader of a project
Given there exist an employee
And the employee is logged in
When the employee sets themselves as leader of the project
Then the employee is set as the leader of the project in the system

Scenario: A non-project leader tries to change the projectleader of a project
Given there exists a project with a project leader
And the user is not the leader of the project
When the user attempts to change the leader of the project
Then the following message will be displayed: "User must be leader to execute operation"

