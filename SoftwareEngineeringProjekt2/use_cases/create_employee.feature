Feature: Assign available employee to activities and projects

Scenario: Create employee with name
When I create an employee with name "Jonas"
Then the emplyee with name "Jonas" is created

Scenario: Create employee with empty name
When I create an employee with name ""
Then I get the error message "Missing parameters"

Scenario: Create employee without name
When I create an employee without a name
Then I get the error message "Missing parameters"

Scenario: Search for nonexisting employee
When I get an nonexisting employee named "Pegasus"
Then I get the error message "Desired object does not exists"

