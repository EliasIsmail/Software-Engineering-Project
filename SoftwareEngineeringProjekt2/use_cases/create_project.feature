Feature: Create project

Scenario: Create project
Given the user is an employee
When the employee creates a project with title "Brew coffee" and the client "DTU: Compute"
Then the project is created with the title "Brew coffee" and client "DTU: Compute"

#Scenario: Create project with missing attributes
#Given the user is an employee
#When the employee creates a project without a title
#Then the following message will be displayed: "Missing project attributes"

#Scenario: Create project, but user isn't employee
#Given the user isn't an employee
#When the user creates a project with title "Brew coffee" and the client "DTU: Compute"
#Then the project is created with the correct attributes
