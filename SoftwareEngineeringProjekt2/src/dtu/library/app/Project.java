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
	
	public void checkAuthenticity() throws MissingAuthenticity {
		if (leader != null) {
			if (leader != app.user) {
				throw new MissingAuthenticity("User is not project leader");
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
	
	public void addEmployee(Employee employee) throws MissingAuthenticity {
		checkAuthenticity();
		//adds employee to project
		if (!employees.contains(employee)){
			employees.add(employee);
		}
	}
	
	public void setLeader(Employee employee) throws OperationNotAllowedException, MissingAuthenticity {
		checkAuthenticity();
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
		//estimated time is the sum of activities
		int estimatedTime= 0;
		for (Activity activity: activities) {
			estimatedTime = estimatedTime +activity.estimatedTime;
		}
		return estimatedTime;
	}
	
	public void setStartWeek(int startWeek) throws OperationNotAllowedException, MissingAuthenticity {
		checkAuthenticity();
		for (Activity activity: activities) {
			if (startWeek > activity.startWeek) {
				throw new OperationNotAllowedException("Activity start date before project start date");
			}
		}
		this.startWeek = startWeek;
	}
	
	public void setEndWeek(int endWeek) throws OperationNotAllowedException, MissingAuthenticity {
		checkAuthenticity();
		for (Activity activity: activities) {
			if (endWeek < activity.endWeek) { //before
				throw new OperationNotAllowedException("Activity end date after project end date");
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
