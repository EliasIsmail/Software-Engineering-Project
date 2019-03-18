package dtu.library.app;

import java.util.ArrayList;

public class Activity {
	public String name;
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	public Project project;
	public int estimatedTime = 0;
	public int startTime = 0;
	public int endTime = 0;
	
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
	
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(int endTime) {
		this.endTime = endTime;
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
