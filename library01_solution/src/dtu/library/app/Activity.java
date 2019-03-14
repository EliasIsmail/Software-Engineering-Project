package dtu.library.app;

import java.util.ArrayList;

public class Activity {
	public String name;
	public int id;
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	public ArrayList<Employee> retiredEmployees = new ArrayList<Employee>();
	
	public Activity(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public void addEmployee(Employee employee) {
		//assigns employee to an activity
		if (!employees.contains(employee)){
			employees.add(employee);
		} else {
			//TODO throw exception
		}
	}
	
	public void removeEmployee(Employee employee) {
		//unassigns an employee from an activity
		if (employees.contains(employee)){
			employees.remove(employee);
			retiredEmployees.add(employee);
		} else {
			//TODO throw exception
		}
	}
	
}
