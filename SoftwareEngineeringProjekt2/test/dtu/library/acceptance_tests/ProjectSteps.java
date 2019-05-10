package dtu.library.acceptance_tests;

import cucumber.api.java.en.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import dtu.library.app.App;
import dtu.library.app.Employee;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;

public class ProjectSteps {

	private App app;
	private Project project;
	private Employee employee;
	private ErrorMessageHolder errorMessageHolder;
	ArrayList<Employee> list;
	private boolean printed = false;
	
	public ProjectSteps(App app, ErrorMessageHolder errorMessageHolder) {
		this.app = app;
		this.errorMessageHolder = errorMessageHolder;
	}

	@When("the employee creates a project with title {string} and the client {string}")
	public void theEmployeeCreatesAProjectWithTitleAndTheClient(String title, String client) {
	    try {
			app.createProject(title, client);
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("the project is created with the title {string} and client {string}")
	public void theProjectIsCreatedWithTheTitleAndClient(String title, String client) {
		assertTrue(app.projects.get(0).getTitle().equals(title));
		assertTrue(app.projects.get(0).getClient().equals(client));
	}
	
	@When("the employee creates a project without a title or a client")
	public void theEmployeeCreatesAProjectWithoutATitleOrAClient() {
		try {
			    app.createProject(null, null);
			} catch (OperationNotAllowedException e) {
				errorMessageHolder.setErrorMessage(e.getMessage());
			}
		}

	
	@Then("I get the errorMessage: {string}")
	public void iGetTheErrorMessage(String errorMessage) {
		assertEquals(errorMessage, this.errorMessageHolder.getErrorMessage());
	}
	

	@When("I create two projects")
	public void iCreateTwoProjects() throws OperationNotAllowedException {
	    app.createProject("project1", "client1");
	    app.createProject("project2", "client2");
	}
	
	@Then("they have the correct project id's")
	public void theyHaveTheCorrectProjectIdS() {
	    assertTrue(app.projects.get(0).getProjectId().equals("191001"));
	    assertTrue(app.projects.get(1).getProjectId().equals("191002"));
	}
	
	@When("the user searches for available employees in week {int}")
	public void theUserSearchesForAvailableEmployeesInWeek(int week) throws Exception {
		try {
			app.getVacantEmployees(week);
		} catch (OperationNotAllowedException e) {
		errorMessageHolder.setErrorMessage(e.getMessage());
		}
		employee = app.createEmployee("Elias");
		
	}

	@Then("a list of available employees in week {int} is returned to the user")
	public void aListOfAvailableEmployeesIsReturnedToTheUser(int week) throws Exception {
		assertTrue(app.getVacantEmployees(week).contains(employee));
	}
	@Given("there is currently no available employees in week {int}")
	public void thereIsCurrentlyNoAvailableEmployeesInWeek(int week) throws Exception {
		app.projects.get(0).setStartWeek(1);
		app.projects.get(0).setEndWeek(5);
		for(Employee employee : app.getVacantEmployees(3)) {
			employee.addProject(app.projects.get(0));
		}
		try {
		assertTrue(app.getVacantEmployees(3).isEmpty());
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
	    app.createEmployee("Erik");
	    employee = app.employees.get(0);
	}
	
	@Given("the employee is logged in")
	public void theEmployeeIsLoggedIn() {
	   app.login(employee.name);
	}

	@When("the employee sets themselves as leader of the project")
	public void theEmployeeSetsThemselvesAsLeaderOfTheProject() throws OperationNotAllowedException {
	    app.createProject("project1", "client1");
	    app.projects.get(0).setLeader(employee);
	}

	@Then("the employee is set as the leader of the project in the system")
	public void theEmployeeIsSetAsTheLeaderOfTheProjectInTheSystem() {
	    assertTrue(app.projects.get(0).getLeader().equals(employee));
	}
	
	@Given("there exists a project with a project leader")
	public void thereExistsAProjectWithAProjectLeader() {
	    try {
			project = app.createProject("Test", "Intern");
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	    employee = app.createEmployee("emp1");
	    try {
			project.setLeader(employee);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Given("the user is not the leader of the project")
	public void theUserIsNotTheLeaderOfTheProject() {
	    employee = app.createEmployee("emp2");
	    app.login("emp2");
	}

	@When("the user attempts to change the leader of the project")
	public void theUserAttemptsToChangeTheLeaderOfTheProject() {
		try {
			project.setLeader(employee);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("I get an error message: {string}")
	public void iGetAnErrorMessage(String ErrorMessage) {
	    assertTrue(errorMessageHolder.getErrorMessage().equals(ErrorMessage));

	}
	
	@Given("the necessary info for a status is filled out")
	public void theNecessaryInfoForAStatusIsFilledOut() {
		try {
				project.setStartWeek(3);
				project.setEndWeek(6);
		} catch (Exception e) {
				errorMessageHolder.setErrorMessage(e.getMessage());
		}
	    for (int i = 0; i < 3; ++i) {
	    	employee = app.createEmployee("emp" + i);
	    	project.addEmployee(employee);
	    }
	}

	@When("the user checks the status of a project")
	public void theUserChecksTheStatusOfAProject() {
	    try {
			project.printStatus();
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
			return;
		}
	    printed = true;
	}

	@Then("the system returns a status of the project and the activities in the project")
	public void theSystemReturnsAStatusOfTheProjectAndTheActivitiesInTheProject() {
	    assertTrue(printed);
	}
	
	@Then("I get an errormessage saying: {string}")
	public void iGetAnErrormessageSaying(String errormessage) {
	    assertFalse(printed);
	    assertTrue(errorMessageHolder.getErrorMessage().equals(errormessage));
	}
}
	
//	@Given("there exists an employee")
//	public void thereExistAnEmployee() {
//	    app.createEmployee("Erik");
//	    employee = app.employees.get(1);
//	}
//	
//	@Given("the employee is logged in")
//	public void theEmployeeIsLoggedIn() {
//	   app.login(employee.name);
//	   assertTrue(app.user.name.equals(employee.name));
//	}
//
//	@When("the employee sets themselves as leader of the project")
//	public void theEmployeeSetsThemselvesAsLeaderOfTheProject() throws OperationNotAllowedException {
//	    app.createProject("project1", "client1");
//	    app.projects.get(0).setLeader(employee);
//	    System.out.println(employee.name);
//	}
//
//	@Then("the employee is the leader of the project")
//	public void theEmployeeIsTheLeaderOfTheProject() {
//		assertTrue(app.projects.get(0).getLeader().equals(employee));
//	}
//	
//	@Given("the user is not the leader of the project")
//	public void theUserIsNotTheLeaderOfTheProject() {
//		assertFalse(app.user.equals(app.projects.get(0).getLeader()));
//	}
//
//	@When("the user tries to change the project leader of the project")
//	public void theUserTriesToChangeTheProjectLeaderOfTheProject() {
//		try {
//			app.projects.get(0).setLeader(app.user);
//		}
//		catch (OperationNotAllowedException e) {
//			errorMessageHolder.setErrorMessage(e.getMessage());
//		}
//	}
//
//
//}
//
//



