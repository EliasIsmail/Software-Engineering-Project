Feature: Set project leader

Scenario: Set employee to leader of a project
Given there exist an employee
When the employee sets himself as leader
Then he is the leader of the project
