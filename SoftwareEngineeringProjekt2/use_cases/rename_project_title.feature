Feature: Rename the title of a project

Scenario: Rename the title of a project
Given there exists a project with a project leader
When the user renames the title to "someProject"
Then then the title is renamed to "someProject"

Scenario: Rename the title of a project to an existing title
Given there exists a project with title "brewCoffee" and with client "morgenPerson"
When the user renames the title to "brewCoffee"
Then I get the errorMessage: "Project title already used"