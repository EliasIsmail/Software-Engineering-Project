package dtu.library.acceptance_tests;

import cucumber.api.java.en.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import cucumber.api.java.en.When;
import dtu.library.app.Activity;
import dtu.library.app.App;
import dtu.library.app.Employee;
import dtu.library.app.Project;

public class ProjectSteps {

	private App app;
	private Project project;
	
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
	
	public ProjectSteps(App app) {
		this.app = app;
	}
	
	@Given("the user is an employee")
	public void theUserIsAnEmployee() {
	    app.createEmployee("Erik");
	}

	@When("the employee creates a project with title {string} and the client {string}")
	public void theEmployeeCreatesAProjectWithTitleAndTheClient(String title, String client) {
	    app.createProject(title, client);
	}
	
	@Then("the project is created with the title {string} and client {string}")
	public void theProjectIsCreatedWithTheTitleAndClient(String title, String client) {
		assertTrue(app.projects.get(0).title.equals(title));
		assertTrue(app.projects.get(0).client.equals(client));
	}
}





