package dtu.library.app;

import java.util.ArrayList;
import java.util.Date;

public class Activity {
	public String name;
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	public Project project;
	public int estimatedTime = 0;
	public int startWeek;
	public int endWeek;
	
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
	
	public void setStartWeek(int startWeek) throws Exception {
		if (startWeek < project.startWeek) { //before
			throw new Exception("Activity start date before project start date");
		}
		this.startWeek = startWeek;
	}
	
	public void setEndDate(int endWeek) throws Exception {
		if (endWeek > project.endWeek) { //after
			throw new Exception("Activity end date after project end date");
		}
		this.endWeek = endWeek;
	}
	
	public void printStatus() {
		System.out.println("Activity: "+name+" from project "+project.getTitle());
		System.out.println("Members: ");
		for (Employee employee: employees) {
			System.out.print(employee.name+", ");
		}
		System.out.println("\nEstimated time: "+estimatedTime);
		System.out.println("-------------------------");
	}
	
}
