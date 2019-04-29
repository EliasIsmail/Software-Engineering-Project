package dtu.library.acceptance_tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	private Project project;
	private Employee employee;
	private Activity activity;
	
	public ActivitySteps(App app) {
		this.app = app;
	}
	
	@Given("there exists an activity in a project")
	public void thereExistsAnActivityInAProject() {
		Project project = new Project("Snake 4", 1);
		Activity activity = new Activity("User Interface", project);
		app.projects.add(project);
		app.projects.get(0).activities.add(activity);
	   if(project.activities == null) {
		   throw new IllegalArgumentException ("No activities exist");
	   }
	}

	@Given("an employee is available")
	public void anEmployeeIsAvailable() {
		employee = new Employee("Erik");
	    if(app.getVacantEmployees(fromWeek).contains(employee)) {
	    	throw new IllegalArgumentException ("No employees available");
	    }
	}

	@Given("the user is the leader of the project")
	public void theUserIsTheLeaderOfTheProject() {
		Project project = new Project("Snake 4", 1);
		Employee leader = new Employee("Elias");
		project.setLeader(leader);
	}

	@When("the user adds the available employee to the activity")
	public void theUserAddsTheAvailableEmployeeToTheActivity() {
		app.addEmployeeToActivity(employee, activity);
		activity.employees.add(employee);
	}

	@Then("the employee is added to the activity in the system")
	public void theEmployeeIsAddedToTheActivityInTheSystem() {
		project.activities.get(0).employees.add(employee);
		assertEquals(project.activities.get(0).employees.get(0), employee);
	}

	@When("the user adds an unavailable employee to the activity")
	public void theUserAddsAnUnavailableEmployeeToTheActivity() {
	    throw new cucumber.api.PendingException();
	}

	@Then("the following message will be displayed: {string}")
	public void theFollowingMessageWillBeDisplayed(String string) {
	    throw new IllegalArgumentException ("The employee is currently unavailable");
	}

}