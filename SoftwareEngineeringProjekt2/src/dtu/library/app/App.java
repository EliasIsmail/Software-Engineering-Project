package dtu.library.app;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class App {
	public static ArrayList<Employee> employees = new ArrayList<Employee>();
	public ArrayList<Project> projects = new ArrayList<Project>();

	private int projectCounter = 0001;
	
	static boolean loggedIn = false;
	public static Employee user = null;

	
	public App() {
		user = createEmployee("Admin");	
	}
	
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
	
	public void createProject(String title, String client) throws OperationNotAllowedException {
			String projectId = Integer.toString(getCurrentDate().getYear()+1900).substring(2,4)+Integer.toString(projectCounter);

			projectCounter++;	
			Project project = new Project(title, client, projectId);
			projects.add(project);
			return project;
	}
	
	public ArrayList<Employee> getOccupiedEmployees(Date date){
		//employees are occupied if they are assigned to at least one project
		ArrayList<Employee> occupiedEmployees = new ArrayList<Employee>();
		
		for (Employee employee: employees) {
			boolean isOccupied = false;
			innerLoop:
			for (Project project: employee.assignedProjects) {
				if (project.endDate.after(date) && project.startDate.before(date)) {
					isOccupied = true;
					break innerLoop;
				}
			}
			
			if (isOccupied) {
				occupiedEmployees.add(employee);
			}
		}
			
		return occupiedEmployees;
	}
	
	public ArrayList<Employee> getVacantEmployees(Date date){
		ArrayList<Employee> vacantEmployees = new ArrayList<Employee>();
		ArrayList<Employee> occupiedEmployees = getOccupiedEmployees(date);
		
		for (Employee employee: employees) {
			if (!occupiedEmployees.contains(employee)) {
				vacantEmployees.add(employee);
			}
		}
		
		return vacantEmployees;
	}
	
	public Date getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(new Date());
		try {
			return format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Date getSpecificDate(String date) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void login(String loginName) {
		//searches employees to find matching name
		for (Employee employee: employees) {
			if (employee.name.equals(loginName)) {
				//if name is found, user is set to that person
				loggedIn = true;
				user = employee;
			  }
		  }
	}
	
}
