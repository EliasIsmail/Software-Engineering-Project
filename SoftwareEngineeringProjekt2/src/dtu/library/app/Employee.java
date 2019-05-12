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
		
		assignedActivities.add(activity);
		
		// postconditions
		assert assignedActivities.contains(activity);
		
		return activity;
	}
	
	public void addActivityToLog(Date date, Activity activity, float hours) throws OperationNotAllowedException {
		if(hours <= 0.0) throw new OperationNotAllowedException("Hours worked must be greater than zero");
		activity.time = activity.time + hours;
		
		//preconditions 
		assert date != null: "Precondition violated";
		assert activity != null: "Precondition violated";
		if (log.containsKey(date)) {
			assert !log.get(date).contains(activity);
		}
		
		int logSizeAtPre;
		if(log.containsKey(date)) {
			logSizeAtPre = log.get(date).size();
		} else {
			logSizeAtPre = 0;
		}
		
		LogElement logElement = new LogElement(activity, hours);
		
		if(log.containsKey(date)) {
			for (LogElement check: log.get(date)) {
				if (check.activity.equals(activity)) {
					throw new OperationNotAllowedException("Activity already added to log");
				}
			}
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
	
	public boolean removeLogElement(Date date, Activity activity) {
		boolean removed = false;
		if(log.containsKey(date)) {
			LogElement logElement;
			for (LogElement check: log.get(date)) {
				if(check.activity.equals(activity)) {
					logElement = check;
					activity.time = activity.time - logElement.hours;
					if (log.get(date).size() < 2) {
						log.remove(date);
					} else {
						log.get(date).remove(logElement); 
					}
					removed = true;
					break;
				}
			}	
		}
		return removed;
	}
	
	
	public ArrayList<LogElement> getLogElementFromDate(Date date){
		if (log.containsKey(date)) {
			return log.get(date); 
		}
		 
		return null;
	}
}







