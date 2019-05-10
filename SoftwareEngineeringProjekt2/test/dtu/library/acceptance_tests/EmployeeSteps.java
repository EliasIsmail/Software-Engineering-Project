package dtu.library.acceptance_tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.library.app.App;
import dtu.library.app.Employee;

public class EmployeeSteps {
	private App app;
	private ErrorMessageHolder errorMessageHolder;
	private Employee employee;
	
	public EmployeeSteps (App app, ErrorMessageHolder errorMessageHolder) {
		this.app = app;
		this.errorMessageHolder = errorMessageHolder;
	}
	@Given("the user is an employee")
	public void theUserIsAnEmployee() {
		employee = new Employee("Oliver");
		if(!app.employees.contains(employee)) {
			employee = app.createEmployee("Oliver");
		}
			app.login(employee.name);
		}

	@When("the user registers their work hours")
	public void theUserRegistersTheirWorkHours() {
		employee.addActivity(employee.assignedProjects.get(0).activities.get(0));
	}

	@When("the number of hours are correct")
	public void theNumberOfHoursAreCorrect() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the employee's work hours are registered in the system")
	public void theEmployeeSWorkHoursAreRegisteredInTheSystem() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
}
