package dtu.library.app;
import java.util.ArrayList;

public class Project {
	private String title;
	private String client;
	private String projectId;
	private Employee leader = null;
	public int startWeek = 0;
	public int endWeek = 0;
	private App app;
	
	//	When a project leader is not yet chosen, any person can change the project
	
	
	public ArrayList<Activity> activities = new ArrayList<Activity>();
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	
	public Project(String title, String client, String projectId, App app) throws OperationNotAllowedException {
		this.title = title;
		this.client = client;
		this.projectId = projectId;
		this.app = app;
	}
	
	public void checkAuthenticity() throws OperationNotAllowedException {
		//preconditions
		//TODO save this contract
		
		assert true;
		
		if (leader != null) { //1
			if (leader != app.user) { //2
				throw new OperationNotAllowedException("User must be leader to execute operation");
			}
		}
		
		//postconditions 
		assert true;
	}
	
	public Activity createActivity(String name) throws OperationNotAllowedException {
		checkAuthenticity();
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
	
	public void setTitle(String newTitle) throws OperationNotAllowedException {
		checkAuthenticity();
		for (Project project: app.projects) {
			if (newTitle.equals(project.getTitle())){
				throw new OperationNotAllowedException ("Project title already used");
			}
		}
		title = newTitle;
	}
	
	public String getClient() {
		return client;
	}
	
	public void addEmployee(Employee employee) throws OperationNotAllowedException {
		checkAuthenticity();
		//adds employee to project
		if (!employees.contains(employee)){
			employees.add(employee);
		}
	}
	
	public void setLeader(Employee employee) throws OperationNotAllowedException {
		checkAuthenticity();
		leader = employee;
	}
	
	public Employee getLeader() {
		return leader;
	}
		
	
	public float getEstimatedTime() {
		//estimated time is the sum of activities
		float estimatedTime= 0;
		for (Activity activity: activities) {
			estimatedTime = estimatedTime +activity.estimatedTime;
		}
		return estimatedTime;
	}
	
	public float getTime() {
		//estimated time is the sum of activities
		float time= 0;
		for (Activity activity: activities) {
			time = time +activity.time;
		}
		return time;
	}
	
	public void setStartWeek(int startWeek) throws OperationNotAllowedException {
		checkAuthenticity();
		if (startWeek < 1 || 53 < startWeek) {
			throw new OperationNotAllowedException("Undefined week number");
		}
		for (Activity activity: activities) {

			if (startWeek > activity.startWeek) {
				activity.startWeek = startWeek;
				if (startWeek > activity.endWeek) {
					activity.endWeek = startWeek + 1;
				}
			}
		}
		this.startWeek = startWeek;
	}
	
	public void setEndWeek(int endWeek) throws OperationNotAllowedException {
		checkAuthenticity();
		if (endWeek < 1 || 53 < endWeek) {
			throw new OperationNotAllowedException("Undefined week number");
		}
		for (Activity activity: activities) {
			if (endWeek < activity.endWeek) {
				activity.endWeek = endWeek;
				if (startWeek < activity.startWeek) {
					activity.startWeek = startWeek - 1;
				}
			}
		}
		this.endWeek = endWeek;
	}
	
	public void printStatus() throws OperationNotAllowedException {
		System.out.println("Project: "+title+", #"+projectId + " for " + client);
		System.out.println("Employees: ");
		for (Employee employee: employees) {
			System.out.println(employee.name);
		}
		if (leader != null) {
			System.out.println("\nProject leader: "+leader.name);
		} else {
			System.out.println("\nProject leader: no leader");
		}
		System.out.println("Start week: " + startWeek);
		System.out.println("End week: " + endWeek);
		System.out.println("Estimated total work: "+getEstimatedTime());
		System.out.println("Work done: "+getTime());
		System.out.println("Estimated work left: "+(getEstimatedTime()-getTime()));
		System.out.println("-------------------------");
		
		for (Activity activity: activities) {
			activity.printStatus();
		}
		System.out.println("-------------------------");
		
	}
	

}
