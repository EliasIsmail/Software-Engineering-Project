package dtu.library.app;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class App {
	public static ArrayList<Employee> employees = new ArrayList<Employee>();
	public ArrayList<Project> projects = new ArrayList<Project>();

	private int projectCounter = 1001;
	
	static boolean loggedIn = false;
	public static Employee user = null;

	public App() {
		try {
			user = createEmployee("Admin");
		} catch (OperationNotAllowedException e) {
			//this will never happen
			System.out.println(e.getMessage());
		}	
		Project misc;
		try {
			misc = createProject("Misc","Administration");
			misc.createActivity("Holiday");
			misc.createActivity("SickDay");
			misc.createActivity("Course");
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Projects are created in App
	 * Employees are created in App
	 * Activities are created in Project
	 * Employees are assigned in Activity
	 */

	public Employee createEmployee(String name) throws OperationNotAllowedException {
		//creates new employee
		for (Employee employee: employees) {
			if (employee.name.equals(name)) {
				throw new OperationNotAllowedException("Name already in use");
			}
		}
		Employee employee = new Employee(name);
		employees.add(employee);
		return employee;
	}
	


	public Project createProject(String title, String client) throws OperationNotAllowedException {
			String projectId = Integer.toString(getCurrentDate().getYear()+1900).substring(2,4)+Integer.toString(projectCounter);
			projectCounter++;	
			Project project = new Project(title, client, projectId,this);
			projects.add(project);
			return project;
	}
	
	public ArrayList<Employee> getOccupiedEmployees(int week){
		//employees are occupied if they are assigned to at least one project
		ArrayList<Employee> occupiedEmployees = new ArrayList<Employee>();
		
		for (Employee employee: employees) {
			boolean isOccupied = false;
			innerLoop:
			for (Project project: employee.assignedProjects) {
				if (project.endWeek > week && project.startWeek < week) {
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
	
	public ArrayList<Employee> getOccupiedEmployees(int startWeek, int endWeek){
		ArrayList<ArrayList<Employee>> runs = new ArrayList<ArrayList<Employee>>();
		for (int week=startWeek; week<endWeek; week++) {
			runs.add(getOccupiedEmployees(week));
		}
		
		if (runs.size() > 0) {
			ArrayList<Employee> occupiedTrue = runs.get(0);
			for (ArrayList<Employee> occupied: runs) {
				occupiedTrue.retainAll(occupied);
			}
			return occupiedTrue;
		}
		
		return new ArrayList<Employee>();
	}
	
	public ArrayList<Employee> getVacantEmployees(int weekStart, int weekEnd) throws Exception {
		ArrayList<Employee> vacantEmployees = new ArrayList<Employee>();
		ArrayList<Employee> occupiedEmployees = getOccupiedEmployees(weekStart,weekEnd);
		for (Employee employee: employees) {
			if (!occupiedEmployees.contains(employee)) {
				vacantEmployees.add(employee);
			}
		}
		if(vacantEmployees.isEmpty()) {
			throw new Exception("Currently no available employees");
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
	
	public void login(String loginName) {
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
