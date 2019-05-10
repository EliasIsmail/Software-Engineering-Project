package dtu.library.app;
import java.util.ArrayList;

public class Project {
	private String title;
	private String client;
	private String projectId;
	private Employee leader;
	public int startWeek = 0;
	public int endWeek = 0;
	private App app;
	
	
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
	}
	
	public Activity createActivity(String name) throws OperationNotAllowedException {
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
	
	public void setLeader(Employee employee) throws OperationNotAllowedException {
		if (leader == null || leader.equals(app.user)) {
			leader = employee;

		} else throw new OperationNotAllowedException("The user isn't leader of the project");

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
		
	
	public int getEstimatedTime() {
		return endWeek-startWeek;
	}
	
	public void setStartWeek(int startWeek) throws Exception {
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
	
	public void printStatus() throws OperationNotAllowedException {
		if (title == null || projectId == null || leader == null || startWeek == 0 || endWeek == 0) {
			throw new OperationNotAllowedException("Missing information for status report");
		}
		System.out.println("Project: "+title+", #"+projectId + " for " + client);
		System.out.println("Members: ");
		for (Employee employee: employees) {
			System.out.print(employee.name+", ");
		}
		System.out.println("\nProject leader: "+leader.name);
		System.out.println("Start week: " + startWeek);
		System.out.println("Total estimated time: "+getEstimatedTime());
		System.out.println("End week: " + endWeek);
		System.out.println("-------------------------");
	}
	

}
