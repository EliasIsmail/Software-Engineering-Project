package dtu.library.app;
import java.util.ArrayList;

public class Project {
	public String name;
	public String client;
	private int projectId;
	public Employee leader;
	
	public ArrayList<Activity> activities = new ArrayList<Activity>();
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	
	public Project(String name, int projectId) {
		this.name = name;
		this.projectId = projectId;
	}
	
	public Activity createActivity(String name) {
		Activity activity = new Activity(name,this);
		activities.add(activity);
		return activity;
	}
	
	public int getProjectId() {
		return projectId;
	}
	
	public void addEmployee(Employee employee) {
		//adds employee to project
		if (!employees.contains(employee)){
			employees.add(employee);
		}
	}
	
	public void setLeader(Employee employee) {
		leader = employee;
	}
	
	public int getEstimatedTime() {
		int estimatedTime= 0;
		for (Activity activity: activities) {
			estimatedTime = estimatedTime +activity.estimatedTime;
		}
		return estimatedTime;
	}
	
	public void printStatus() {
		System.out.println("Project "+name);
		System.out.println("Members: ");
		for (Employee employee: employees) {
			System.out.print(employee.name+", ");
		}
		System.out.println("\nProject leader: "+leader);
		System.out.println("Total estimated time: "+getEstimatedTime());
		System.out.println("-------------------------");
	}
}
