package dtu.library.acceptance_tests;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.library.app.App;
import dtu.library.app.OperationNotAllowedException;

public class ActivitySteps {
	private App app;
	private ErrorMessageHolder errorMessageHolder;
	
	public ActivitySteps(App app, ErrorMessageHolder errorMessageHolder) {
		this.app = app;
		this.errorMessageHolder = errorMessageHolder;
	}
	Date date = new Date(System.currentTimeMillis());
	
	@Given("there exists an activity in a project")
	public void thereExistsAnActivityInAProject() {
		try {
		app.createProject("Snake", "Ubisoft");
		} catch (OperationNotAllowedException e) {
		errorMessageHolder.setErrorMessage(e.getMessage());
		}
		try {
		app.projects.get(0).createActivity("User Interface");
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
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
	@Given("there exists a project")
	public void thereExistsAProject() {
		try {
		app.createProject("Snake", "Ubisoft");
		} catch (OperationNotAllowedException e) {
		errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertTrue(app.projects!=null);
	}

	@When("the user creates an activity with title {string}")
	public void theUserCreatesAnActivityWithTitle(String string) {
	    try {
		app.projects.get(0).createActivity("Design GUI");
	    } catch (OperationNotAllowedException e) {
	    	errorMessageHolder.setErrorMessage(e.getMessage());
	    }
	}

	@Then("the activity is created in the system")
	public void theActivityIsCreatedInTheSystem() {
		assertTrue(app.projects.get(0).activities.contains(app.projects.get(0).activities.get(0)));
	}
	
	@When("the user sets the start time to {int} and client to {string}")
	public void theUserSetsTheStartTimeToAndClientTo(Integer int1, String string) {
		try {
	    app.projects.get(0).activities.get(0).setStartDate(date);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	    app.projects.get(0).activities.get(0).setClient("IT Minds");
	}

	@Then("both the start time and client of the activity has been set")
	public void bothTheStartTimeAndClientOfTheActivityHasBeenSet() {
		assertTrue(app.projects.get(0).activities.get(0).startDate.equals(date));
		assertTrue(app.projects.get(0).activities.get(0).client.equals("IT Minds"));
	}

//	@When("the user adds an unavailable employee to the activity")
//	public void theUserAddsAnUnavailableEmployeeToTheActivity() {
//		try {
//			add employee
//			unavailable employee
//		}
//		app.getOccupiedEmployees(date).add(app.employees.get(0));
//	}
//
//	@Then("the following message will be displayed: {string}")
//	public void theFollowingMessageWillBeDisplayed(String errorMessage) {
//		assertThat(errorMessageHolder.getErrorMessage(), is(equalTo(errorMessage)));
//	}

}