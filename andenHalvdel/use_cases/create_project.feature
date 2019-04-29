Feature: Create project

Scenario: Create project
Given the user is an employee
When the employee creates a project with title "Brew coffee"
And the employee adds "DTU: Compute" as client
Then the project is created with the correct attributes

Scenario: Create project with missing attributes
Given the user is an employee
When the employee creates a project without a title
Then the following message will be displayed: "Missing project attributes"
