package dtu.library.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Employee {
	public String name;
	
	public ArrayList<Project> assignedProjects = new ArrayList<Project>();
	public ArrayList<Activity> assignedActivities = new ArrayList<Activity>();
	public HashMap<Date,ArrayList<LogElement>> log = new HashMap<Date,ArrayList<LogElement>>();
	
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
		
		//preconditions
		assert activity != null: "Precondition violated";
		assert !assignedActivities.contains(activity): "Precondition violated";
		int assignedActivitiesSizeAtPre = assignedActivities.size();
		
		if (!assignedActivities.contains(activity)) {
			assignedActivities.add(activity);
		}
		
		// postconditions
		assert assignedActivities.size() == assignedActivitiesSizeAtPre +1: "Postcondition violated";
		
		return activity;
	}
	
	public void addActivityToLog(Date date, Activity activity, float hours) throws OperationNotAllowedException {
		if(hours < 0.0) throw new OperationNotAllowedException("Hours worked can't be negative");
		activity.time = activity.time + hours;
		
		//preconditions
		assert date != null: "Precondition violated";
		assert activity != null: "Precondition violated";
		
		int logSizeAtPre;
		if(log.containsKey(date)) {
			logSizeAtPre = log.get(date).size();
		} else {
			logSizeAtPre = 0;
		}
		
		
		activity.time = activity.time + hours;
		LogElement logElement = new LogElement(activity, hours);
		
		if(log.containsKey(date)) {
			//already had elements in specific date
			log.get(date).add(logElement);
		} else {
			//was empty
			log.put(date, new ArrayList<LogElement>());
			log.get(date).add(logElement);
		}
		
		//postconditions
		assert log.get(date).size() == logSizeAtPre +1: "Postcondition violated";
		
	}
	
	public void removeLogElement(Date date, Activity activity, float hours) {
		LogElement logElement = new LogElement(activity, hours);
		
		if(log.containsKey(date)) {
			//already had elements in specific date
			log.get(date).remove(logElement);
			activity.time = activity.time - hours;
		}
	}
	
	public ArrayList<LogElement> getLogElementFromDate(Date date){
		if (log.containsKey(date)) {
			return log.get(date); 
		}
		
		return null;
	}
}







