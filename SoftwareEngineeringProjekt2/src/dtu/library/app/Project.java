package dtu.library.app;
import java.util.ArrayList;
import java.util.Date;

public class Project {
	private String title;
	private String client;
	private String projectId;
	private Employee leader;
	public int startWeek;
	public int endWeek;
	
	public ArrayList<Activity> activities = new ArrayList<Activity>();
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	
	public Project(String title, String client, String projectId) throws OperationNotAllowedException {
		if (title == null || client == null) {
			throw new OperationNotAllowedException("Missing project information");
		}
		this.title = title;
		this.client = client;
		this.projectId = projectId;
	}
	
	public Activity createActivity(String name) {
		Activity activity = new Activity(name,this);
		activities.add(activity);
		return activity;
	}
	
	public String getProjectId() {
		return projectId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getClient() {
		return client;
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
	
	public Employee getLeader() {
		return leader;
	}
		
	
	public int getEstimatedTime() {
		int estimatedTime= 0;
		for (Activity activity: activities) {
			estimatedTime = estimatedTime +activity.estimatedTime;
		}
		return estimatedTime;
	}
	
	public void setStartDate(int startWeek) throws Exception {
		for (Activity activity: activities) {
			if (startWeek > activity.startWeek) {
				throw new Exception("Activity start date before project start date");
			}
		}
		this.startWeek = startWeek;
	}
	
	public void setEndWeek(int endWeek) throws Exception {
		for (Activity activity: activities) {
			if (endWeek < activity.endWeek) { //before
				throw new Exception("Activity end date after project end date");
			}
		}
		this.endWeek = endWeek;
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
