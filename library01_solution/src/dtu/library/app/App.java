package dtu.library.app;
import java.util.ArrayList;

public class App {
	public ArrayList<Employee> employees = new ArrayList<Employee>();
	public ArrayList<Project> projects = new ArrayList<Project>();
	private int employeeId = 0;
	private int projectId = 0;
	
	
	public static void main(String[] args) {
		//apps running from here
	}
	
	public Employee createEmployee(String name) {
		//creates new employee
		Employee employee = new Employee(name,employeeId);
		employees.add(employee);
		employeeId++;
		return employee;
	}
	
	public Project createProject(String name) {
		Project project = new Project(name, projectId);
		projects.add(project);
		projectId++;
		return project;
	}
	
	public ArrayList<Employee> getActiveEmployees(){
		ArrayList<Employee> activeEmployees = new ArrayList<Employee>();
		for (Project project: projects) {
			activeEmployees.addAll(project.getActiveEmployees());
		}
		return activeEmployees;
	}
	
	public ArrayList<Employee> getInactiveEmployees(){
		ArrayList<Employee> inactiveEmployees = new ArrayList<Employee>();
		ArrayList<Employee> activeEmployees = getActiveEmployees();
		for (Employee employee: employees) {
			if(!activeEmployees.contains(employee)) {
				inactiveEmployees.add(employee);
			}
		}
		return inactiveEmployees;
	}
	
}
