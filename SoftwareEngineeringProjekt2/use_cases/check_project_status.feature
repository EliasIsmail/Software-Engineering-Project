Feature: Check project status

Scenario: Check project status
Given there exists a project with a project leader
And the necessary info for a status is filled out
When the user checks the status of a project
Then the system returns a status of the project and the activities in the project

Scenario: Check project status when missing info
Given there exists a project with a project leader
When the user checks the status of a project
Then I get an errormessage saying: "Missing information for status report"