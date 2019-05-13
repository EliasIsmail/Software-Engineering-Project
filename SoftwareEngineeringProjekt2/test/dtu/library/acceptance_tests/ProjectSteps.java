package dtu.library.acceptance_tests;

import cucumber.api.cli.Main;
import cucumber.api.java.en.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;

import dtu.library.app.Activity;
import dtu.library.app.App;
import dtu.library.app.Employee;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;

public class ProjectSteps {

	private App app;
	private Project project;
	private Activity activity;
	private Employee employee;
	private ErrorMessageHolder errorMessageHolder;
	ArrayList<Employee> list;
	private boolean printed = false;
	
	public ProjectSteps(App app, ErrorMessageHolder errorMessageHolder) {
		this.app = app;
		this.errorMessageHolder = errorMessageHolder;
		try {
			project = app.createProject("TestUnique", "Intern1");
		} catch (OperationNotAllowedException e) {
			System.out.println("ERROR1");
		}
	}
	
	@Then("I get the errorMessage: {string}")
	public void iGetTheErrorMessage(String errorMessage) {
		assertEquals(errorMessage, this.errorMessageHolder.getErrorMessage());
	}
	
	@When("the user searches for available employees in week {int}")
	public void theUserSearchesForAvailableEmployeesInWeek(int week) {
		try {
			app.getVacantEmployees(week,week+1);
			employee = app.createEmployee("Elias");
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("a list of available employees in week {int} is returned to the user")
	public void aListOfAvailableEmployeesIsReturnedToTheUser(int week) {
		try {
			assertTrue(app.getVacantEmployees(week,week+1).contains(employee));
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	@Given("there is currently no available employees in week {int}")
	public void thereIsCurrentlyNoAvailableEmployeesInWeek(int week) {
		try {
			app.projects.get(0).setStartWeek(1);
			app.projects.get(0).setEndWeek(5);
			for(Employee employee : app.getVacantEmployees(3,4)) {
				employee.addProject(app.projects.get(0));
			}
			assertTrue(app.getVacantEmployees(3,4).isEmpty());
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	@Then("the following message will be displayed to the user: {string}")
	public void theFollowingMessageWillBeDisplayedToTheUser(String errorMessage) {
		assertThat(errorMessageHolder.getErrorMessage(), is(equalTo(errorMessage)));
	}

	@Given("there exist an employee")
	public void thereExistAnEmployee() {
		try {
			app.createEmployee("Erik");
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	    employee = app.employees.get(1);
	}
	
	@Given("the employee is logged in")
	public void theEmployeeIsLoggedIn() {
	   app.login(employee.name);
	}

	@When("the employee sets themselves as leader of the project")
	public void theEmployeeSetsThemselvesAsLeaderOfTheProject() {
	    try {
			app.createProject("project1", "client1");
			app.projects.get(0).setLeader(employee);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	    
	}

	@Then("the employee is set as the leader of the project in the system")
	public void theEmployeeIsSetAsTheLeaderOfTheProjectInTheSystem() {
	    assertTrue(app.projects.get(0).getLeader().equals(employee));
	}
	
	@Given("there exists a project with a project leader")
	public void thereExistsAProjectWithAProjectLeader() {
		try {
			project = app.createProject("TestUnique", "Intern1");
		} catch (OperationNotAllowedException e) {
			System.out.println("ERROR");
		}
		try {
			employee = app.createEmployee("justSomeLeader");
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		app.login(employee.name);
		try {
			project.setLeader(employee);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Given("the user is not the leader of the project")
	public void theUserIsNotTheLeaderOfTheProject() { 
		try {
			employee = app.createEmployee("employeeUnique9");
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	    for (Employee employee: app.employees) {
	    	if (!employee.equals(project.getLeader())) {
	    		app.login(employee.name);
	    		break;
	    	}
	    }
	}

	@When("the user attempts to change the leader of the project")
	public void theUserAttemptsToChangeTheLeaderOfTheProject() {
		try {
			project.setLeader(employee);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the user gets an error message saying: {string}")
	public void TheUserGetAnErrorMessageSaying(String ErrorMessage) {
		assertTrue(errorMessageHolder.getErrorMessage().equals(ErrorMessage));
	}
	
	@When("the user sets the start week of the project to {int}")
	public void theUserSetsTheStartWeekOfTheProjectTo(Integer week) {
	    try {
	    	project.setStartWeek(week);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the start week of the project is updated to {int} in the system")
	public void theStartWeekOfTheProjectIsUpdatedToInTheSystem(Integer week) {
	    assertTrue(project.startWeek == week);
	}
	
	@When("the user sets the end week of the project to {int}")
	public void theUserSetsTheEndWeekOfTheProjectTo(Integer week) {
		try {
			project.setEndWeek(week);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the end week of the project is updated to {int} in the system")
	public void theEndWeekOfTheProjectIsUpdatedToInTheSystem(Integer week) {
	    assertTrue(project.endWeek == week);
	}
	
	@When("the user renames the title to {string}")
	public void theUserRenamesTheTitle(String title) {
	    try {
			project.setTitle(title);
		} catch (OperationNotAllowedException e) { //authenticity exception
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	} 

	@Then("then the title is renamed to {string}")
	public void thenTheTitleIsRenamd(String title) {
	    assertTrue(project.getTitle().equals(title));
	}
	
	@Given("the project has an activity with estimated time set to {float}")
	public void theProjectHasAnActivityWithEstimatedTimeSetTo(Float flo1) throws Exception {
	    project.createActivity("someActivity"+flo1).setEstimatedTime(flo1);
	}

	@Then("the projects estimated time is set to {float}")
	public void theProjectsEstimatedTimeIsSetTo(Float flo1) {
		assertTrue(project.getEstimatedTime() == flo1);
	}
	
	@Given("an employee has logged {float} hours of work to the activity")
	public void anEmployeeHasLoggedHoursOfWorkToTheActivity(Float flo1) throws OperationNotAllowedException, ParseException {
	    project.getLeader().addActivityToLog(app.getSpecificDate("2019-05-21"), project.activities.get(0), flo1);
	}

	@Then("the projects time is set to {float}")
	public void theProjectsTimeIsSetTo(Float flo1) {
	    assertTrue(project.getTime() == flo1);
	}
	
	@Given("endweek is set to {int}")
	public void endweekIsSetTo(Integer int1) {
	    try {
			project.setEndWeek(int1);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("startweek is set to {int}")
	public void startweekIsSetTo(Integer int1) {
		try {
			project.setStartWeek(int1);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@When("I create a project with title {string} and client {string}")
	public void iCreateAProjectWithTitleAndClient(String title, String client) {
	    try {
			project = app.createProject(title, client);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("I search for the project")
	public void iSearchForTheProject() {
	    try {
			project = app.getProject(project.getTitle(), project.getClient());
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("I find a project with title {string} and client {string}")
	public void iFindAProjectWithTitleAndClient(String title, String client) {
		assertTrue(project.getTitle().equals(title));
		assertTrue(project.getClient().equals(client));
	}

	@When("I search for a nonexisting project")
	public void iSearchForANonexistingProject() {
		try {
			app.getProject("ExceedSpeedOfLight", "MadScientist");
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
		
	@When("the user sets estimated time to {int}")
	public void theUserSetsEstimatedTimeTo(Integer estimatedTime) {
		try {
			activity = project.createActivity("EstimatedTimeTest");
			activity.setEstimatedTime(estimatedTime);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}	
	
	@Given("an activity lasts from week {int} through {int}")
	public void anActivityLastsFromWeekThrough(Integer startWeek, Integer endWeek) {
	    try {
			activity = project.createActivity("Test1");
			activity.setStartWeek(startWeek);
			activity.setEndWeek(endWeek);

		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	    
	}

	@When("the user set the projects endweek to {int}")
	public void theUserSetTheProjectsEndweekTo(Integer endWeek) {
		try {
			project.setEndWeek(endWeek);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity lasts from week {int} through {int}")
	public void theActivityLastsFromWeekThrough(Integer startWeek, Integer endWeek) {
	    assertTrue(activity.startWeek == startWeek && activity.endWeek == endWeek);
	}

	@When("the user set the projects startweek to {int}")
	public void theUserSetTheProjectsStartweekTo(Integer startWeek) {
		try {
			project.setStartWeek(startWeek);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("a project has been created")
	public void aProjectHasBeenCreated() {
	    try {
			project = app.createProject("TestSetWeek", "Intern");
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
}
	




