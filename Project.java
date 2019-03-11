import java.util.*;
public class Project {
	private int numberOfActivities;
	private int projectNumber;
	private ProjectLeader projectLeader;
	private Activity activity;
	private ArrayList<Activity> activities;
	
	public Project (int projectNumber) {
		this.projectNumber = projectNumber;
		this.numberOfActivities = 0;
		this.projectLeader=null;
		
	}
	public Project (ProjectLeader projectLeader, int projectNumber) {
		this.setProjectLeader(projectLeader);
		this.setProjectNumber(projectNumber);
		
	}
	public int getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(int projectNumber) {
		this.projectNumber = projectNumber;
	}
	public ProjectLeader getProjectLeader() {
		return projectLeader;
	}
	public void setProjectLeader(ProjectLeader projectLeader) {
		this.projectLeader = projectLeader;
	}
	public int getNumberOfActivities() {
		return numberOfActivities;
	}
	public void setNumberOfActivities(int numberOfActivities) {
		this.numberOfActivities = numberOfActivities;
	}
	public void addActivity(Activity activity) {
		activities.add(activity);
	}
	public void removeActivity(Activity activity) {
		activities.remove(activity);
	}

}
