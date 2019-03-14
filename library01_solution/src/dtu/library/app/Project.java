package dtu.library.app;
import java.util.ArrayList;

public class Project {
	public String name;
	public String client;
	public int projectId;
	public int activityId;
	public int startDate;
	public int endDate;
	public int workLoad;
	public ArrayList<Activity> activites = new ArrayList<Activity>();
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	public ArrayList<Employee> retiredEmployees = new ArrayList<Employee>();
	
	public Project(String name, int projectId) {
		this.name = name;
		this.projectId = projectId;
	}
	
	public void getSummary() {
		System.out.println("Project "+name);
		for (Activity activity: activites) {
			System.out.println("activity "+activity.name);
			System.out.println("Active employees:");
			for (Employee employee: activity.employees) {
				System.out.println(employee.name+" "+employee.id);
			}
		}
	}
	
	public Activity createActivity(String name) {
		Activity activity = new Activity(name,activityId);
		activites.add(activity);
		activityId++;
		return activity;
	}
	
	public void addEmployee(Employee employee) {
		//adds employee to project
		if (!employees.contains(employee)){
			employees.add(employee);
		} else {
			//TODO throw exception
		}
	}
	
	public void removeEmployee(Employee employee) {
		//removes employee from project
		if (employees.contains(employee)){
			retiredEmployees.add(employee);
			employees.remove(employee);
		} else {
			//TODO throw exception
		}
	}
	
	public ArrayList<Employee> getActiveEmployees () {
		ArrayList<Employee> activeEmployees = new ArrayList<Employee>();
		
		for (Activity activity: activites) {
			for (Employee activeEmployee: activity.employees) {
				activeEmployees.add(activeEmployee);
			}
		}
		
		return activeEmployees;
	}
}
