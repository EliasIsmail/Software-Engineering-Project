package dtu.library.acceptance_tests;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
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
	int week = 1;
	
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
		assertTrue(app.getVacantEmployees(week).contains(app.employees.get(0)));
	}

	@Given("the user is the leader of the project")
	public void theUserIsTheLeaderOfTheProject() {
		app.projects.get(0).setLeader(app.user);
		assertTrue(app.projects.get(0).isLeader(app.user));
	}

	@When("the user adds the available employee to the activity")
	public void theUserAddsTheAvailableEmployeeToTheActivity() {
		app.projects.get(0).activities.get(0).addEmployee(app.employees.get(0));
	}

	@Then("the employee is added to the activity in the system")
	public void theEmployeeIsAddedToTheActivityInTheSystem() {
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
	
	@When("the user sets the start week to {int} and client to {string}")
	public void theUserSetsTheStartWeekToAndClientTo(Integer int1, String string) {
		try {
	    app.projects.get(0).activities.get(0).setStartWeek(week);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	    app.projects.get(0).activities.get(0).setClient("IT Minds");
	}

	@Then("both the start time and client of the activity has been set")
	public void bothTheStartTimeAndClientOfTheActivityHasBeenSet() {
		assertTrue(app.projects.get(0).activities.get(0).startWeek == week);
		assertTrue(app.projects.get(0).activities.get(0).client.equals("IT Minds"));
	}

	@When("the user sets the start week of the activity to {int}")
	public void theUserSetsTheStartTimeOfTheActivityTo(int number) throws Exception {
		try {
			 app.projects.get(0).activities.get(0).setStartWeek(number);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the following message will be displayed: {string}")
	public void theFollowingMessageWillBeDisplayed(String errorMessage) {
		assertThat(errorMessageHolder.getErrorMessage(), is(equalTo(errorMessage)));
	}

	@When("the user sets the estimated time for an activity to {int} weeks")
		public void theUserSetsTheEstimatedTimeForAnActivity(int number) {
			try{
				app.projects.get(0).activities.get(0).setEstimatedTime(number);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	@Given("the start week of the project has been set")
	public void theStartWeekOfTheProjectHasBeenSet() {
		try {
			app.projects.get(0).activities.get(0).setStartWeek(week);
			} catch (Exception e) {
				errorMessageHolder.setErrorMessage(e.getMessage());
			}
	}

	@When("the user sets the estimated time for an activity to a negative number of weeks")
		public void theUserSetsTheEstimatedTimeForAnActivityToANegativeNumberOfWeeks() {
			try{
				app.projects.get(0).activities.get(0).setEstimatedTime(-4);
			} catch (Exception e) {
				errorMessageHolder.setErrorMessage(e.getMessage());
			}
		}
	
	@Then("the estimated time is updated to {int} weeks for the activity in the system")
	public void theEstimatedTimeIsUpdatedToWeeksForTheActivityInTheSystem(int number) {
	    assertTrue(app.projects.get(0).activities.get(0).estimatedTime == number);
	}
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