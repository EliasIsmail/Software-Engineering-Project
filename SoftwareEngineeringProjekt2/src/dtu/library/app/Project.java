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
		if (title == null || client == null) {
			throw new OperationNotAllowedException("Missing project information");
		}
		this.title = title;
		this.client = client;
		this.projectId = projectId;
		this.app = app;
		System.out.println(title + " " + client);
	}
	
	public void checkAuthenticity() throws OperationNotAllowedException {
		if (leader != null) {
			if (leader != app.user) {
				throw new OperationNotAllowedException("The user isn't leader of the project");
			}
		}
	}
	
	public Activity createActivity(String name) throws OperationNotAllowedException, MissingAuthenticity {
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
	
	public void setTitle(String newTitle) throws OperationNotAllowedException, MissingAuthenticity {
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
	
	public void setLeader(Employee employee) throws OperationNotAllowedException, MissingAuthenticity {
		checkAuthenticity();
		leader = employee;
	}

	public boolean isLeader() {
		if(app.user.name.equals(leader.name)) {
			return true;
		} else {
			return false;
		}
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
	
	public void setStartWeek(int startWeek) throws OperationNotAllowedException, MissingAuthenticity {
		checkAuthenticity();
		if (startWeek < 0) {
			throw new OperationNotAllowedException("Weeknumbers must be non-negative integers");
		}
		for (Activity activity: activities) {

			if (startWeek > activity.startWeek) {
				activity.startWeek = startWeek;
			}
		}
		this.startWeek = startWeek;
	}
	
	public void setEndWeek(int endWeek) throws OperationNotAllowedException, MissingAuthenticity {
		checkAuthenticity();
		if (endWeek < 0) {
			throw new OperationNotAllowedException("Weeknumbers must be non-negative integers");
		}
		for (Activity activity: activities) {
			if (endWeek < activity.endWeek) {
				activity.endWeek = endWeek;
			}
		}
		this.endWeek = endWeek;
	}
	
	public void printStatus() throws OperationNotAllowedException {
		System.out.println("Project: "+title+", #"+projectId + " for " + client);
		System.out.println("Members: ");
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
