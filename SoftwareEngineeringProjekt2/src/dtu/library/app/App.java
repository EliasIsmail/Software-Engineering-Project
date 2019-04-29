package dtu.library.app;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	
	public Project createProject(String title, String client) {
		Project project = new Project(title, client, projectId);
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
	
	public ArrayList<Employee> getOccupiedEmployees(Date date){
		ArrayList<Employee> occupiedEmployees = new ArrayList<Employee>();
		
		for (Employee employee: employees) {
			boolean isOccupied = false;
			innerLoop:
			for (Activity activity: employee.assignedActivities) {
				if (activity.endDate.after(date) && activity.startDate.before(date)) {
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
