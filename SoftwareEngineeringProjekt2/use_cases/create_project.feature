Feature: Create employee

Scenario: Create project
When I create a project with title "Design GUI" and client "Microsoft"
And I search for the project
Then I find a project with title "Design GUI" and client "Microsoft"


Scenario: Create project
When I search for a nonexisting project
Then I get the error message "Desired object does not exists"

Scenario: Create project
When I create a project with title "" and client ""
Then I get the error message "Missing project information"