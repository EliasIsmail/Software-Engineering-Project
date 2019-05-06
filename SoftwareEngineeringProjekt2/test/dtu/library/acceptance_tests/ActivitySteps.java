package dtu.library.acceptance_tests;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.library.app.Activity;
import dtu.library.app.App;
import dtu.library.app.Employee;
import dtu.library.app.Project;

public class ActivitySteps {
	private App app;
	private int fromWeek;
	private Date date;
	private Project project;
	private Employee employee;
	private Activity activity;
	
	public ActivitySteps(App app) {
		this.app = app;
	}
	
	@Given("there exists an activity in a project")
	public void thereExistsAnActivityInAProject() {
		app.createProject("Snake", "Ubisoft");
		app.projects.get(0).createActivity("User Interface");
		assertTrue(app.projects.get(0).activities.contains(app.projects.get(0).activities.get(0)));
	}

	@Given("an employee is available")
	public void anEmployeeIsAvailable() {
		app.createEmployee("Erik");
		assertTrue(app.getVacantEmployees(date).contains(app.employees.get(0)));
	}

	@Given("the user is the leader of the project")
	public void theUserIsTheLeaderOfTheProject() {
		app.createEmployee("Leader");
		app.projects.get(0).setLeader(app.employees.get(1));
		assertTrue(app.projects.get(0).isLeader(app.employees.get(1)));
	}

	@When("the user adds the available employee to the activity")
	public void theUserAddsTheAvailableEmployeeToTheActivity() {
		app.projects.get(0).activities.get(0).addEmployee(app.employees.get(0));
		assertTrue(app.projects.get(0).activities.get(0).employees.contains(app.employees.get(0)));
	}

	@Then("the employee is added to the activity in the system")
	public void theEmployeeIsAddedToTheActivityInTheSystem() {
		app.projects.get(0).activities.get(0).addEmployee(app.employees.get(0));
		assertTrue(app.projects.get(0).activities.get(0).employees.contains(app.employees.get(0)));
	}

	@When("the user adds an unavailable employee to the activity")
	public void theUserAddsAnUnavailableEmployeeToTheActivity() {
		app.getOccupiedEmployees(date).add(app.employees.get(0));
	}

	@Then("the following message will be displayed: {string}")
	public void theFollowingMessageWillBeDisplayed(String string) {
	    throw new IllegalArgumentException ("The employee is currently unavailable");
	}

}