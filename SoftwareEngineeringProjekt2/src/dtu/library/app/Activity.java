package dtu.library.app;

import java.util.ArrayList;
import java.util.Date;

public class Activity {
	public String name;
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	public Project project;
	public int estimatedTime = 0;
	public int startWeek = 0;
	public int endWeek;
	public Employee employee;
	
	public Activity(String name, Project project) throws OperationNotAllowedException {
		if(name == null || project == null) {
			throw new OperationNotAllowedException("Missing activity information");
	}
		this.name = name;
		this.project = project;
	}
	
	public void addEmployee(Employee employee) throws MissingAuthenticity {
		//assigns employee to an activity
		if (!employees.contains(employee)){
			employees.add(employee);
		}
		
		project.addEmployee(employee); //adding employee to project
		employee.addActivity(this); //adding activity to employee
		employee.addProject(project); //adding project to employee
	}
	
	public void setEstimatedTime(int estimatedTime) throws Exception {
		if (project.isLeader() == false) {
			throw new Exception("Only project leaders can edit times");
		}
		if (estimatedTime < 1) {
			throw new Exception("Impossible time frame for activity");
		}
		if (this.startWeek == 0) {
			throw new Exception("Start week of activity hasn't been set yet");
		}
		this.estimatedTime = estimatedTime;
		this.endWeek = estimatedTime + startWeek;
	}
	

	public void setStartWeek(int startWeek) throws Exception {
		if (startWeek < project.startWeek) { //before
			throw new Exception("Activity start date before project start date");
		}
		if (startWeek > 53 || startWeek < 1) {
			throw new Exception("Undefined week number");
		}
		if (this.startWeek != 0) {
			throw new Exception("Start week has already been set");
		}
			
		this.startWeek = startWeek;
	}
	
	public void setEndWeek(int endWeek) throws Exception {
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
