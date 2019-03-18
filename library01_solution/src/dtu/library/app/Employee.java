package dtu.library.app;

import java.util.ArrayList;

public class Employee {
	public String name;
	
	public ArrayList<Project> assignedProjects = new ArrayList<Project>();
	public ArrayList<Activity> assignedActivities = new ArrayList<Activity>();
	
	public Employee(String name) {
		this.name = name;
	}
	
	public void addProject(Project project) {
		if (!assignedProjects.contains(project)) {
			assignedProjects.add(project);
		}
	}
	
	public void addActivity(Activity activity) {
		if (!assignedActivities.contains(activity)) {
			assignedActivities.add(activity);
		}
	}
}







