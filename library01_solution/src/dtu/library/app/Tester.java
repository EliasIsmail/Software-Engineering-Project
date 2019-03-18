package dtu.library.app;

import java.util.ArrayList;

public class Tester {
	
	public static void main(String[] args) {
		
		testActivitiesAndProjects();
		
	}
	
	public static void testActivitiesAndProjects() {
		App app = new App();
		Project project1 = app.createProject("Project 1");
		Project project2 = app.createProject("Project 2");
		Activity activity1 = project1.createActivity("Activity 1: Finish use cases");
		Activity activity2 = project2.createActivity("Activity 2: Do the dishes");
		
		String[] names = new String[] {"JONA","ELIA","OLIV","ERIK"};
		for (String name: names) {
			app.createEmployee(name);
		}
		
		activity1.addEmployee(app.employees.get(0));
		activity1.addEmployee(app.employees.get(1));
		activity2.addEmployee(app.employees.get(2));
		activity2.addEmployee(app.employees.get(3));
		
		activity1.estimatedTime = 10;
		activity2.estimatedTime = 20;
		
		activity1.setStartTime(1);
		activity1.setEndTime(10);
		
		activity2.setStartTime(50);
		activity2.setEndTime(52);
		
		project1.printStatus();
		project2.printStatus();
		activity1.printStatus();
		activity2.printStatus();
		
		ArrayList<Employee> occupiedEmployees = app.getOccupiedEmployees(4);
		System.out.println("Occupied: ");
		for (Employee employee: occupiedEmployees) {
			System.out.println(employee.name);
		}
		
		ArrayList<Employee> vacantEmployees = app.getVacantEmployees(4);
		System.out.println("Vacant: ");
		for (Employee employee: vacantEmployees) {
			System.out.println(employee.name);
		}
	}
	
}
