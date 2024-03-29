package dtu.library.app;

import java.util.ArrayList;
import java.util.Date;

public class Activity {
	public String name;
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	public Project project;
	public float estimatedTime = 0;
	public float time = 0;

	public int startWeek = 0;
	public int endWeek = 0;

	public Employee employee;
	
	public Activity(String name, Project project) throws OperationNotAllowedException {
		if(name == null || project == null || name.equals("")) {
			throw new OperationNotAllowedException("Missing activity information");
	}
		this.name = name;
		this.project = project;
	}
	
	public void addEmployee(Employee employee) throws OperationNotAllowedException {
		//assigns employee to an activity
		if (employees.contains(employee)) throw new OperationNotAllowedException("Activity is already assigned to employee"); 
		
		employees.add(employee);
		project.addEmployee(employee); //adding employee to project
		employee.addActivity(this); //adding activity to employee
		employee.addProject(project); //adding project to employee
	}
	
	public void setEstimatedTime(float estimatedTime) throws Exception {
		project.checkAuthenticity();
		if (estimatedTime < 0) {
			throw new Exception("Please enter valid integer");
		}
		this.estimatedTime = estimatedTime;
	}
	

	public void setStartWeek(int startWeek) throws OperationNotAllowedException  {
		project.checkAuthenticity();
		
		/* TODO 
		if (project.startWeek != 0 && startWeek < project.startWeek) { //before
			throw new Exception("Activity startweek before project startweek");
		}else if (startWeek > 53 || startWeek < 1) {
			throw new Exception("Undefined week number");
		}
		*/

		this.startWeek = startWeek;
	}
	
	public void setEndWeek(int endWeek) throws OperationNotAllowedException {
		project.checkAuthenticity();
		
		/* TODO
		if (project.endWeek != 0 && endWeek > project.endWeek) { //after
			throw new Exception("Activity endweek after project endweek");
		} else if (endWeek > 53 || endWeek < 1) {
			throw new Exception("Undefined week number");
		} */
		
		this.endWeek = endWeek;
	}
}

