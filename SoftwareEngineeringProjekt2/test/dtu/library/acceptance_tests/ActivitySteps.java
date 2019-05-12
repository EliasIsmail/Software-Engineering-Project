package dtu.library.acceptance_tests;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.library.app.Activity;
import dtu.library.app.App;
import dtu.library.app.Employee;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;

public class ActivitySteps {
	private App app;
	private Employee leader;
	int week = 1;
	int workhours = 5;
	private ErrorMessageHolder errorMessageHolder;
	private Project project;
	private Employee employee;
	private Activity activity;
	private Date date = new Date(System.currentTimeMillis());
	private boolean printed = false;
	
	public ActivitySteps(App app, ErrorMessageHolder errorMessageHolder) {
		this.app = app;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	
	@Given("there exists an activity in a project")
	public void thereExistsAnActivityInAProject() {
		try {
			project = app.createProject("Snake", "Ubisoft");
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		try {
			activity = project.createActivity("User Interface");
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertTrue(activity.name.equals("User Interface"));
			
	}

	@Given("an employee is available")
	public void anEmployeeIsAvailable() throws Exception { 
		app.createEmployee("Erik");
		assertTrue(app.getVacantEmployees(week,week+1).contains(app.employees.get(0)));
	}

	@Given("the user is the leader of the project")
	public void theUserIsTheLeaderOfTheProject() {
		try {
			leader = app.createEmployee("Mao Zedong");
			app.login(leader.name);
			project.setLeader(leader);
			assertTrue(project.getLeader().equals(leader));
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the user adds the available employee to the activity")
	public void theUserAddsTheAvailableEmployeeToTheActivity() throws OperationNotAllowedException {
		app.projects.get(0).activities.get(0).addEmployee(app.employees.get(0));
	}

	@Then("the employee is added to the activity in the system")
	public void theEmployeeIsAddedToTheActivityInTheSystem() {
		assertTrue(app.projects.get(0).activities.get(0).employees.contains(app.employees.get(0)));
	}
	
	@Given("there exists a project")
	public void thereExistsAProject() {
		try {
			project = app.createProject("Snake", "Ubisoft");
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertTrue(app.projects.get(app.projects.size()-1) == project); //last added project
	}

	@When("the user creates an activity with title {string}")
	public void theUserCreatesAnActivityWithTitle(String string) {
	    try {
	    	app.projects.get(0).createActivity(string);
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

//	@When("the user sets the start week of the activity to {int}")
//	public void theUserSetsTheStartTimeOfTheActivityTo(int number) throws Exception {
//		try {
//			 app.projects.get(1).setStartWeek(1);
//			 app.projects.get(1).setEndWeek(6);
//			 app.projects.get(1).activities.get(0).setStartWeek(100);
//		} catch (Exception e) {
//			errorMessageHolder.setErrorMessage(e.getMessage());
//		}
//	}
	
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

//	@When("the user sets the estimated time for an activity to a negative number of weeks")
//		public void theUserSetsTheEstimatedTimeForAnActivityToANegativeNumberOfWeeks() {
//			try{
//				app.projects.get(0).activities.get(0).setEstimatedTime(-4);
//			} catch (Exception e) {
//				errorMessageHolder.setErrorMessage(e.getMessage());
//			}
//		}
	
	@Then("the estimated time is updated to {int} weeks for the activity in the system")
	public void theEstimatedTimeIsUpdatedToWeeksForTheActivityInTheSystem(int number) {
	    assertTrue(app.projects.get(0).activities.get(0).estimatedTime == number);
	}
//	@Given("there exists an activity in a project with a project leader")
//	public void thereExistsAnActivityInAProjectWithAProjectLeader() throws MissingAuthenticity {
//			try {
//				project = app.createProject("Test", "Intern");
//				employee = app.createEmployee("emp1");
//			    app.login("emp1");
//			    project.setLeader(employee);
//			    activity = project.createActivity("Game Mechanics");
//			} catch (OperationNotAllowedException e) {
//				errorMessageHolder.setErrorMessage(e.getMessage());
//			}
//		}
	
//	@Given("the necessary info for an activity status is filled out")
//	public void theNecessaryInfoForAnActivityStatusIsFilledOut() throws MissingAuthenticity, OperationNotAllowedException {
//		try {
//				project.setStartWeek(1);
//				project.setEndWeek(6);
//				activity.setStartWeek(2);
//				activity.setEndWeek(3);
//		} catch (Exception e) {
//				errorMessageHolder.setErrorMessage(e.getMessage());
//		}
//	    for (int i = 0; i < 3; ++i) {
//	    	employee = app.createEmployee("emp" + i);
//	    	project.addEmployee(employee);
//	    	employee.addActivity(activity);
//	    	employee.addActivityToLog(date, activity, workhours);
//	    }
//	}
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