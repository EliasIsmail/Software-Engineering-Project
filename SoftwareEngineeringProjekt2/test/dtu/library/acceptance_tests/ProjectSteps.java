package dtu.library.acceptance_tests;

import cucumber.api.java.en.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import dtu.library.app.Activity;
import dtu.library.app.App;
import dtu.library.app.Employee;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;

public class ProjectSteps {

	private App app;
	private Project project;
	private Employee employee;
	private ErrorMessageHolder errorMessage;
	
	
	/*
	 * Note that the constructor is apparently never called, but there are no null
	 * pointer exceptions regarding that libraryApp is not set. When creating the
	 * BookSteps object, the Cucumber libraries are using that constructor with an
	 * object of class LibraryApp as the default.
	 * 
	 * This also holds for all other step classes that have a similar constructor.
	 * In this case, the <b>same</b> object of class LibraryApp is used as an
	 * argument. This provides an easy way of sharing the same object, in this case
	 * the object of class LibraryApp, among all step classes.
	 * 
	 * This principle is called <em>dependency injection</em>. More information can
	 * be found in the "Cucumber for Java" book available online from the DTU Library.
	 */
	
	public ProjectSteps(App app, ErrorMessageHolder errorMessage) {
		this.app = app;
		this.errorMessage = errorMessage;
	}

	@When("the employee creates a project with title {string} and the client {string}")
	public void theEmployeeCreatesAProjectWithTitleAndTheClient(String title, String client) {
	    try {
			app.createProject(title, client);
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
			errorMessage.setErrorMessage(e.getMessage());
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
				errorMessage.setErrorMessage(e.getMessage());
			}
		}

	
	@Then("I get the errorMessage: {string}")
	public void iGetTheErrorMessage(String errorMessage) {
		assertEquals(errorMessage, this.errorMessage.getErrorMessage());
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
	
	@Given("there exist an employee")
	public void thereExistAnEmployee() {
	    app.createEmployee("Erik");
	    employee = app.employees.get(0);
	}
	
	@Given("the employee is logged in")
	public void theEmployeeIsLoggedIn() {
	   app.login(employee.name);
	}

	@When("the employee sets himself as leader")
	public void theEmployeeSetsHimselfAsLeader() throws OperationNotAllowedException {
	    app.createProject("project1", "client1");
	    app.projects.get(0).setLeader(employee);
	}

	@Then("he is the leader of the project")
	public void heIsTheLeaderOfTheProject() {
	    assertTrue(app.projects.get(0).getLeader().equals(employee));
	}
	
	@Given("there is a project with a leader")
	public void thereIsAProjectWithALeader() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("a user who isn't the leader is the user")
	public void aUserWhoIsnTTheLeaderIsTheUser() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the leader of is change")
	public void theLeaderOfIsChange() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("I get an error message: {string}")
	public void iGetAnErrorMessage(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}


}





