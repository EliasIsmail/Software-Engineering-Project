package dtu.library.acceptance_tests;

import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import cucumber.api.java.en.When;
import dtu.library.app.Activity;
import dtu.library.app.App;
import dtu.library.app.Employee;
import dtu.library.app.Project;

public class ProjectSteps {

	private App app;

	private Employee employee;
	private Project project;
	private Activity activity;
	
	int numberOfProjects;
	
	String[] clients = new String[] {"Compute", "Space", "Aqua", "TicTacToeTechnologies", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};

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
	
	@When("I create a new project")
	public void iCreateANewProject() {
	    project = app.createProject("TestProject1");
	}

	@Then("the project is created")
	public void theProjectIsCreated() {
	    assertTrue(app.projects.get(0).equals(project));
	}	
	
	@Then("the project id is set")
	public void theProjectIdIsSet() {
	    assertTrue(app.projects.get(0).projectId == 0);
	}

	@Then("I have a project")
	public void iHaveAProject() {
	    project = app.projects.get(0);
	}

	@When("I add an employee, startDate, endDate, workLoad and a client")
	public void iAddAnEmployeeStartTimeEndTimeAndAClient() {
	    app.createEmployee("Oliver");
	    employee = app.employees.get(0);
	    project.addEmployee(employee);
	    project.startDate = 19;
	    project.endDate = 31;
	    project.workLoad = 120;
	    project.client = "DTU Compute";
	}

	@Then("All the details are added")
	public void allTheDetailsAreAdded() {
	    assertTrue(project.employees.get(0).equals(employee));
	    assertTrue(project.startDate == 19);
	    assertTrue(project.endDate == 31);
	    assertTrue(project.workLoad == 120);
	    assertTrue(project.client.equals("DTU Compute"));
	}

	@When("I create {int} new projects")
	public void iCreateNewProjects(int numberOfProjects) {
		this.numberOfProjects = numberOfProjects;
	    for (int i=0; i<numberOfProjects; i++) {
	    	app.createProject("Project "+i);
	    }
	}

	@Then("the projects are created")
	public void theProjectsAreCreated() {
	    assertTrue(app.projects.size() == numberOfProjects);
	}

	@Then("the project ids are set")
	public void theProjectIdsAreSet() {
	    ArrayList<Project> projects = app.projects;
	    
	    for (int i=0; i<numberOfProjects; i++) {
	    	assertTrue(projects.get(i).name.equals("Project "+i));
	    }
	}

	@When("I set some details")
	public void iSetSomeDetails() {
		ArrayList<Project> projects = app.projects;

		for (int i=0; i<numberOfProjects; i++) {
			project = projects.get(i);
			project.client = "DTU "+clients[i];
			project.startDate = i+10;
			project.endDate = i+20;
			project.workLoad = i*20;
		}
	}

	@Then("The details are set")
	public void theDetailsAreSet() {
		ArrayList<Project> projects = app.projects;

		for (int i=0; i<numberOfProjects; i++) {
			project = projects.get(i);
			assertTrue(project.client.equals("DTU "+clients[i]));
			assertTrue(project.startDate == i+10);
			assertTrue(project.endDate == i+20);
			assertTrue(project.workLoad == i*20);
		}
	}
	
	@When("I create a new activity")
	public void iCreateANewActivity() {
	    activity = project.createActivity("TestActivity");
	}

	@Then("The activity is created")
	public void theActivityIsCreated() {
	    assertTrue(project.activites.get(0).equals(activity));
	}

	@When("I create a new employee")
	public void iCreateANewEmployee() {
	    employee = app.createEmployee("Oliver");
	}

	@When("I add the employee to the activity")
	public void iAddTheEmployeeToTheActivity() {
	    project.addEmployee(employee);
	    activity.addEmployee(employee);
	}

	@Then("the employee is added to the activity")
	public void theEmployeeIsAddedToTheActivity() {
	    assertTrue(activity.employees.contains(employee));
	}
	
	@When("I remove the employee from the activity")
	public void iRemoveTheEmployeeFromTheActivity() {
	    activity.removeEmployee(employee);
	}

	@Then("The employee is removed")
	public void theEmployeeIsRemoved() {
	    assertFalse(activity.employees.contains(employee));
	}


	
}





