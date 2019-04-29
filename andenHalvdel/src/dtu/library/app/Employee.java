package dtu.library.app;

import java.util.ArrayList;
import java.util.HashMap;

public class Employee {
	public String name;
	
	public ArrayList<Project> assignedProjects = new ArrayList<Project>();
	public ArrayList<Activity> assignedActivities = new ArrayList<Activity>();
	public HashMap<Integer,ArrayList<LogElement>> log = new HashMap<Integer,ArrayList<LogElement>>();
	
	public Employee(String name) {
		this.name = name;
	}
	
	public Project addProject(Project project) {
		if (!assignedProjects.contains(project)) {
			assignedProjects.add(project);
		}
		return project;
	}
	
	public Activity addActivity(Activity activity) {
		if (!assignedActivities.contains(activity)) {
			assignedActivities.add(activity);
		}
		return activity;
	}
	
	public void logHours(int date, Activity activity, int hours) {
		LogElement logElement = new LogElement(activity, hours);
		if(log.containsKey(date)) {
			//already had elemenets
			log.get(date).add(logElement);
		} else {
			//was empty
			log.put(date, new ArrayList<LogElement>());
			log.get(date).add(logElement);
		}
	}
	
	public ArrayList<LogElement> getLogElementFromDate(int date){
		return log.get(date);
	}
}







