package dtu.library.app;
import java.util.ArrayList;

public class App {
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	public ArrayList<Project> projects = new ArrayList<Project>();
	private int projectId = 0;
	
	/*
	 * Projects are created in App
	 * Employees are created in App
	 * Activities are created in Project
	 * Employees are assigned in Activity
	 */

	public Employee createEmployee(String name) {
		//creates new employee
		Employee employee = new Employee(name);
		employees.add(employee);
		return employee;
	}
	
	public Project createProject(String name) {
		Project project = new Project(name, projectId);
		projects.add(project);
		projectId++;
		return project;
	}
	
	public void addEmployeeToActivity(Employee employee, Activity activity) {
		activity.addEmployee(employee); //adding employee to activity
		/*activity.project.addEmployee(employee); //adding employee to project
		employee.addActivity(activity); //adding activity to employee
		employee.addProject(activity.project); //adding project to employee*/
	}
	
	public ArrayList<Employee> getOccupiedEmployees(int fromWeek){
		ArrayList<Employee> occupiedEmployees = new ArrayList<Employee>();
		
		for (Employee employee: employees) {
			boolean isOccupied = true;
			innerLoop:
			for (Project project: employee.assignedProjects) {
				for (Activity activity: project.activities) {
					if (activity.endTime < fromWeek || activity.startTime > fromWeek) {
						isOccupied = false;
						break innerLoop;
					}
				}
			}
			
			if (isOccupied) {
				occupiedEmployees.add(employee);
			}
		}
			
		return occupiedEmployees;
	}
	
	public ArrayList<Employee> getVacantEmployees(int fromWeek){
		ArrayList<Employee> vacantEmployees = new ArrayList<Employee>();
		ArrayList<Employee> occupiedEmployees = getOccupiedEmployees(fromWeek);
		
		for (Employee employee: employees) {
			if (!occupiedEmployees.contains(employee)) {
				vacantEmployees.add(employee);
			}
		}
		
		return vacantEmployees;
	}
}
