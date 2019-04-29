package dtu.library.app;

import java.util.ArrayList;
import java.util.Date;

public class Activity {
	public String name;
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	public Project project;
	public int estimatedTime = 0;
	public Date startDate = null;
	public Date endDate = null;
	
	public Activity(String name, Project project) {
		this.name = name;
		this.project = project;
	}
	
	public void addEmployee(Employee employee) {
		//assigns employee to an activity
		if (!employees.contains(employee)){
			employees.add(employee);
		}
		
		project.addEmployee(employee); //adding employee to project
		employee.addActivity(this); //adding activity to employee
		employee.addProject(project); //adding project to employee
	}
	
	public void setEstimatedTime(int estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public void setEndDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public void printStatus() {
		System.out.println("Activity: "+name+" from project "+project.name);
		System.out.println("Members: ");
		for (Employee employee: employees) {
			System.out.print(employee.name+", ");
		}
		System.out.println("\nEstimated time: "+estimatedTime);
		System.out.println("-------------------------");
	}
	
}
