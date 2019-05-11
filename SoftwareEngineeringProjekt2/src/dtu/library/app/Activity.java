package dtu.library.app;

import java.util.ArrayList;
import java.util.Date;

public class Activity {
	public String name;
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	public Project project;
	public float estimatedTime = 0;
	public float time = 0;

	public int startWeek = 0;
	public int endWeek = 0;

	public Employee employee;
	
	public Activity(String name, Project project) throws OperationNotAllowedException {
		if(name == null || project == null) {
			throw new OperationNotAllowedException("Missing activity information");
	}
		this.name = name;
		this.project = project;
	}
	
	public void addEmployee(Employee employee) throws OperationNotAllowedException {
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
		if (estimatedTime < 0) {
			throw new Exception("Impossible time frame for activity");
		}
		
		this.estimatedTime = estimatedTime;
	}
	

	public void setStartWeek(int startWeek) throws Exception {
		if (project.startWeek != 0 && startWeek < project.startWeek) { //before
			throw new Exception("Activity start date before project start date");
		}
		if (startWeek > 53 || startWeek < 1) {
			throw new Exception("Undefined week number");
		}
//		if (this.startWeek != 0) {
//			throw new Exception("Start week has already been set");
//		}
			
		this.startWeek = startWeek;
	}
	
	public void setEndWeek(int endWeek) throws Exception {
		if (project.endWeek != 0 && endWeek > project.endWeek) { //after
			throw new Exception("Activity end date after project end date");
		}
		this.endWeek = endWeek;
	}
	
	public void printStatus() throws OperationNotAllowedException {
		System.out.println("Activity: "+name+" from project "+project.getTitle());
		System.out.println("Members: ");
		for (Employee employee: employees) {
			System.out.println(employee.name);
		}
		System.out.println("Start week: " + startWeek);
		System.out.println("End week: " + endWeek);
		System.out.println("Estimated total work: "+estimatedTime);
		System.out.println("Work done: "+time);
		System.out.println("Estimated work left: "+(estimatedTime-time));
		System.out.println("-------------------------");
	}
}

