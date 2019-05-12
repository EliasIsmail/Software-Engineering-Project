Feature: Create project

Scenario: Create project
When the employee creates a project with title "Design GUI" and the client "Microsoft"
Then the project is created with the title "Design GUI" and client "Microsoft"

Scenario: Create project with missing attributes
When the employee creates a project without a title or a client
Then I get the errorMessage: "Missing project information"

Scenario: Project gets correct project id when created
When I create two projects
Then they have the correct project id's

Scenario: Get existing project
Given the employee creates a project with title "Design GUI" and the client "Microsoft"
Then we can find the project using title and client