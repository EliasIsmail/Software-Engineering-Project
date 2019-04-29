$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("use_cases\\add_activity.feature");
formatter.feature({
  "name": "Create an activity",
  "description": "\tDescription: An activity is created with various details\n\tActors: user",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Create a new activity",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I create a new project",
  "keyword": "When "
});
formatter.match({
  "location": "ProjectSteps.iCreateANewProject()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I create a new activity",
  "keyword": "And "
});
formatter.match({
  "location": "ProjectSteps.iCreateANewActivity()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The activity is created",
  "keyword": "Then "
});
formatter.match({
  "location": "ProjectSteps.theActivityIsCreated()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Assign employee to activity",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I create a new project",
  "keyword": "When "
});
formatter.match({
  "location": "ProjectSteps.iCreateANewProject()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I create a new activity",
  "keyword": "And "
});
formatter.match({
  "location": "ProjectSteps.iCreateANewActivity()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I create a new employee",
  "keyword": "And "
});
formatter.match({
  "location": "ProjectSteps.iCreateANewEmployee()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I add the employee to the activity",
  "keyword": "When "
});
formatter.match({
  "location": "ProjectSteps.iAddTheEmployeeToTheActivity()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the employee is added to the activity",
  "keyword": "Then "
});
formatter.match({
  "location": "ProjectSteps.theEmployeeIsAddedToTheActivity()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Remove employee from activity",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I create a new project",
  "keyword": "When "
});
formatter.match({
  "location": "ProjectSteps.iCreateANewProject()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I create a new activity",
  "keyword": "And "
});
formatter.match({
  "location": "ProjectSteps.iCreateANewActivity()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I create a new employee",
  "keyword": "And "
});
formatter.match({
  "location": "ProjectSteps.iCreateANewEmployee()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I add the employee to the activity",
  "keyword": "When "
});
formatter.match({
  "location": "ProjectSteps.iAddTheEmployeeToTheActivity()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I remove the employee from the activity",
  "keyword": "And "
});
formatter.match({
  "location": "ProjectSteps.iRemoveTheEmployeeFromTheActivity()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The employee is removed",
  "keyword": "Then "
});
formatter.match({
  "location": "ProjectSteps.theEmployeeIsRemoved()"
});
formatter.result({
  "status": "passed"
});
formatter.uri("use_cases\\add_project.feature");
formatter.feature({
  "name": "Create project",
  "description": "\tDescription: A project is created with various details\n\tActors: user",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Create a new project",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I create a new project",
  "keyword": "When "
});
formatter.match({
  "location": "ProjectSteps.iCreateANewProject()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the project is created",
  "keyword": "Then "
});
formatter.match({
  "location": "ProjectSteps.theProjectIsCreated()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the project id is set to be \"2019123456\"",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "Create a new project",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I create 10 new projects",
  "keyword": "When "
});
formatter.match({
  "location": "ProjectSteps.iCreateNewProjects(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the projects are created",
  "keyword": "Then "
});
formatter.match({
  "location": "ProjectSteps.theProjectsAreCreated()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the project ids are set",
  "keyword": "And "
});
formatter.match({
  "location": "ProjectSteps.theProjectIdsAreSet()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I set some details",
  "keyword": "When "
});
formatter.match({
  "location": "ProjectSteps.iSetSomeDetails()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the details are set",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "Add employees and details to project",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I have a project with ID \"2019123456\"",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I add an employee, startDate, endDate, workLoad and a client",
  "keyword": "When "
});
formatter.match({
  "location": "ProjectSteps.iAddAnEmployeeStartTimeEndTimeAndAClient()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "all the details are added",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "name": "Add projectleader to project",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I have a project with ID \"2019123456\"",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I add a project leader to the project",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "the project has a projectleader with ID \"1234\"",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.uri("use_cases\\check_employees.feature");
formatter.feature({
  "name": "Check employeees status",
  "description": "\tDescription: checks employees statis\n\tActors: user",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Checks active employees",
  "description": "\t//TODO",
  "keyword": "Scenario"
});
formatter.step({
  "name": "That something",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "Something related",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});