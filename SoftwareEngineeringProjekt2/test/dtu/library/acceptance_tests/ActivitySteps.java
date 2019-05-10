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
import dtu.library.app.Employee;
import dtu.library.app.OperationNotAllowedException;

public class ActivitySteps {
	private App app;
	private Employee leader;
	int week = 1;
	private ErrorMessageHolder errorMessageHolder;
	
	public ActivitySteps(App app, ErrorMessageHolder errorMessageHolder) {
		this.app = app;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	
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
		assertTrue(app.projects.get(0).activities.get(0).name.equals("User Interface"));
	}

	@Given("an employee is available")
	public void anEmployeeIsAvailable() throws Exception {
		app.createEmployee("Erik");
		assertTrue(app.getVacantEmployees(week).contains(app.employees.get(0)));
	}

	@Given("the user is the leader of the project")
	public void theUserIsTheLeaderOfTheProject() {
		leader = app.createEmployee("leader");
		app.login("leader");
		try {
			app.projects.get(0).setLeader(leader);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
//		for(Project p:app.projects) {
//			
//		}
		assertTrue(app.projects.get(0).isLeader());
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
	
	@When("the user sets the start week to {int}")
	public void theUserSetsTheStartWeekTo(Integer int1) {
		try {
		    app.projects.get(0).activities.get(0).setStartWeek(week);
			} catch (Exception e) {
				errorMessageHolder.setErrorMessage(e.getMessage());
			}
	}

	@Then("the start time is set")
	public void theStartTimeIsSet() {
		assertTrue(app.projects.get(0).activities.get(0).startWeek == week);
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