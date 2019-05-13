package dtu.library.acceptance_tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.library.app.Activity;
import dtu.library.app.App;
import dtu.library.app.Employee;
import dtu.library.app.LogElement;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;

public class EmployeeSteps {
	private App app;
	private ErrorMessageHolder errorMessageHolder;
	private Employee employee;
	private Project project;
	private Activity activity;
	private Date date = new Date(System.currentTimeMillis());
	
	public EmployeeSteps (App app, ErrorMessageHolder errorMessageHolder) {
		this.app = app;
		this.errorMessageHolder = errorMessageHolder;
	}
	@Given("the user is an employee")
	public void theUserIsAnEmployee() {
		try {
			employee = app.createEmployee("Oliver");
			app.login(employee.name);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the user registers {int} work hours on a given date")

	public void theUserRegisters7WorkHoursOnAGivenDate(int workhours) {
		try {
			employee.addProject(app.projects.get(0));
			if (!employee.assignedActivities.contains(app.projects.get(0).activities.get(0))) {
				employee.addActivity(app.projects.get(0).activities.get(0));
			}
			employee.addActivityToLog(date,app.projects.get(0).activities.get(0),workhours);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employees work hours are registered in the system")
	public void theEmployeeSWorkHoursAreRegisteredInTheSystem() {
	    assertTrue(employee.assignedActivities.get(0).name.equals(app.projects.get(0).activities.get(0).name));
	}
	
	@Given("there exists an employee with name {string}")
	public void thereExistsAnEmployeeWithName(String name) {
	    try {
			employee = app.createEmployee(name);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("there exists a project with title {string} and with client {string}")
	public void thereExistsAProjectWithTitle(String title, String client) {
	    try {
			project = app.createProject(title, client);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("I add the employee to the project")
	public void iAddTheEmployeeToTheProject() {
	    try {
			project.addEmployee(employee);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());

		}
	}
	
	@Then("the employee is added to the project")
	public void theEmployeeIsAddedToTheProject() {
	    assertTrue(project.employees.contains(employee));
	}
	
	@When("the user removes that log element")
	public void theUserRemovesThatLogElement() {
		activity = app.projects.get(0).activities.get(0);
	    employee.removeLogElement(date, activity);
	}

	@Then("the log element is removed")
	public void theLogElementIsRemoved() {
		boolean notRemoved = false;
		if (employee.getLogElementFromDate(date) != null) {
			for (LogElement logElement: employee.getLogElementFromDate(date)) {
				if (logElement.activity.equals(activity)) {
					notRemoved = true;
					System.out.println("It has not been removed");
				}
			}
		}
		assertFalse(notRemoved);
	}
	
	@When("I create an employee with name {string}")
	public void iCreateAnEmployeeWithName(String name) {
	    try {
			employee = app.createEmployee(name);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the emplyee with name {string} is created")
	public void theEmplyeeIsCreated(String name) {
	    try {
			assertTrue(app.getEmployee(name) != null);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("I get the error message {string}")
	public void iGetTheErrorMessage(String string) {
	    assertTrue(errorMessageHolder.getErrorMessage().equals(string));
	}

	@When("I create an employee without a name")
	public void iCreateAnEmployeeWithoutAName() {
	    try {
			app.createEmployee(null);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@When("I get an nonexisting employee named {string}")
	public void iGetAnNonexistingEmployeeNamed(String name) {
	    try {
			app.getEmployee(name);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("there are two activies in the project")
	public void thereAreTwoActiviesInTheProject() {
	    try {
			project.createActivity("act1");
		    project.createActivity("act2");
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}

	}

	@When("the user registers {int} hours on both activities on the same day")
	public void theUserRegistersHoursOnBothActivitiesOnTheSameDay(Integer hours) {
	    try {
			app.user.addActivityToLog(app.getCurrentDate(), project.activities.get(0), hours);
		    app.user.addActivityToLog(app.getCurrentDate(), project.activities.get(1), hours);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("The this is registered in the log for the day")
	public void theThisIsRegisteredInTheLogForTheDay() {
		assertTrue(app.user.log.get(app.getCurrentDate()).get(0).hours==4);
		assertTrue(app.user.log.get(app.getCurrentDate()).get(1).hours==4);

	}
	
	@Given("there is a project")
	public void thereIsAProject() {
	    try {
			project = app.createProject("TestRegisterHours", "Intern");
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@When("the user deletes on of them")
	public void theUserDeletesOnOfThem() {
	    app.user.removeLogElement(app.getCurrentDate(), project.activities.get(0));
	}

	@Then("there is only one left")
	public void thereIsOnlyOneLeft() {
	    assertFalse(app.user.log.get(app.getCurrentDate()).contains(project.activities.get(0)));
	}
	
}
