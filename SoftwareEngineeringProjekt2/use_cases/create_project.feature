Feature: Create employee

Scenario: Create project
When I create a project with title "Brew tea" and client "Myself"
And I search for the project
Then I find it

Scenario: Create project
When I search for a nonexisting project
Then I get the error message "Desired object does not exists"