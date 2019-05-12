Feature: Overview of available employees

Scenario: Find available employees
Given there exists a project
When the user searches for available employees in week 1
Then a list of available employees in week 1 is returned to the user

Scenario: Find available employees when there is none
Given there exists a project
And there is currently no available employees in week 1
When the user searches for available employees in week 1
Then the following message will be displayed to the user: "Currently no available employees"

Scenario: Find available employees but an undefined week is given
When the user searches in week 87
Then the following message will be displayed to the user: "Undefined week number"