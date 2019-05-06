package dtu.library.app;
import java.util.ArrayList;
import java.util.Date;

public class Project {
	public String title;
	public String client;
	private int projectId;
	public Employee leader;
	public Date startDate = null;
	public Date endDate = null;
	
	public ArrayList<Activity> activities = new ArrayList<Activity>();
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	
	public Project(String title, String client, int projectId) {
		this.title = title;
		this.client = client;
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
	public boolean isLeader(Employee employee) {
		if(employee == leader) {
			return true;
		} else return false;
	}
		
	
	public int getEstimatedTime() {
		int estimatedTime= 0;
		for (Activity activity: activities) {
			estimatedTime = estimatedTime +activity.estimatedTime;
		}
		return estimatedTime;
	}
	
	public void setStartDate(Date startDate) throws Exception {
		for (Activity activity: activities) {
			if (startDate.after(activity.startDate)) {
				throw new Exception("Acitivity start date before project start date");
			}
		}
		this.startDate = startDate;
	}
	
	public void setEndDate(Date endDate) throws Exception {
		for (Activity activity: activities) {
			if (endDate.after(activity.endDate)) {
				throw new Exception("Acitivity start date before project start date");
			}
		}
		this.endDate = endDate;
	}
	
	public void printStatus() {
		System.out.println("Project "+title);
		System.out.println("Members: ");
		for (Employee employee: employees) {
			System.out.print(employee.name+", ");
		}
		System.out.println("\nProject leader: "+leader);
		System.out.println("Total estimated time: "+getEstimatedTime());
		System.out.println("-------------------------");
	}
}
