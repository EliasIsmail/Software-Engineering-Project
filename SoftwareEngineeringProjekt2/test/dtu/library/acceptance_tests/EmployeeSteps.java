package dtu.library.acceptance_tests;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.library.app.Activity;
import dtu.library.app.App;
import dtu.library.app.Employee;
import dtu.library.app.OperationNotAllowedException;

public class EmployeeSteps {
	private App app;
	private ErrorMessageHolder errorMessageHolder;
	private Employee employee;
	private Date date = new Date(System.currentTimeMillis());
	
	public EmployeeSteps (App app, ErrorMessageHolder errorMessageHolder) {
		this.app = app;
		this.errorMessageHolder = errorMessageHolder;
	}
	@Given("the user is an employee")
	public void theUserIsAnEmployee() {
		employee = new Employee("Oliver");
		if(!app.employees.contains(employee)) {
			try {
				employee = app.createEmployee("Oliver");
				app.login(employee.name);
			} catch (OperationNotAllowedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}

	@When("the user registers {int} work hours on a given date")
	public void theUserRegisters7WorkHoursOnAGivenDate(int workhours) {
		try {
			employee.addProject(app.projects.get(0));
			employee.addActivity(app.projects.get(0).activities.get(0));
			employee.addActivityToLog(date,app.projects.get(0).activities.get(0),workhours);
		} catch (OperationNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("the employees work hours are registered in the system")
	public void theEmployeeSWorkHoursAreRegisteredInTheSystem() {
	    assertTrue(employee.assignedActivities.get(0).name.equals(app.projects.get(0).activities.get(0).name));
	}
	
}
