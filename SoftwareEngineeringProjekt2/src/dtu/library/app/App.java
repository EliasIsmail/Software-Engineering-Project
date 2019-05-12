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
		//create first user
		try {
			user = createEmployee("Admin");
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());
		}
		
		//Setup project for misc registration
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
		if (name == null || name.equals("")) {
			throw new OperationNotAllowedException("Missing parameters");
			//throw new OperationNotAllowedException("Missing parameters");

		}
		
		//creates new employee
		for (Employee employee: employees) {
			if (employee.name.equals(name)) {
				return employee;
			}
		}
		//Employee added to list
		Employee employee = new Employee(name);
		employees.add(employee);
		return employee;			//return employee for easy use
	}
	
	public Project createProject(String title, String client) throws OperationNotAllowedException {
		if (title == null || client == null) {
			throw new OperationNotAllowedException("Missing project information");
			//throw new OperationNotAllowedException("Missing project information");
		}
		
		//preconditions
		assert title != null: "Precondition violated";
		assert client != null: "Precondition violated";
		assert title != "": "Precondition violated";
		assert client != "": "Precondition violated";
		
		int projectsSizeAtPre = projects.size();
		
		
		for (Project project:projects) {
			if (title.equals(project.getTitle())) {
				return project; 
			}
		}
		
		if (title.equals("") || client.equals("")) {
			throw new OperationNotAllowedException("Title and client must not be empty");
			//throw exception
		}
		
		String projectId = Integer.toString(getCurrentDate().getYear()+1900).substring(2,4)+Integer.toString(projectCounter);
		projectCounter++;	
		Project project = new Project(title, client, projectId,this);
		projects.add(project);
		
		// postconditions		
		return project;
	}
	
	public ArrayList<Employee> getOccupiedEmployees(int week){
		//employees are occupied if they are assigned to at least one project
		ArrayList<Employee> occupiedEmployees = new ArrayList<Employee>();
		for (Employee employee: employees) {
			boolean isOccupied = false;
			innerLoop:
			for (Project project: employee.assignedProjects) {
				if (project.endWeek >= week && project.startWeek <= week) {
					isOccupied = true;
					break innerLoop;
				}
			}
			
			//employee is added to the return list
			if (isOccupied) {
				occupiedEmployees.add(employee);
			}
		}
		//return list
		return occupiedEmployees;
	}
	
	public ArrayList<Employee> getOccupiedEmployees(int startWeek, int endWeek) throws OperationNotAllowedException{
		if (startWeek > endWeek || startWeek < 1 || startWeek > 53 ||  endWeek < 1 || endWeek > 53) {
			throw new OperationNotAllowedException("Undefined week number");
			//throw exception
		}
		//create a list of lists for the specified weeks with occupied employees
		ArrayList<ArrayList<Employee>> runs = new ArrayList<ArrayList<Employee>>();
		for (int week=startWeek; week<endWeek; week++) {
			runs.add(getOccupiedEmployees(week));
		}
		
		//gather all employees that are present in all the list
		if (runs.size() > 0) {
			ArrayList<Employee> occupiedTrue = runs.get(0);
			for (ArrayList<Employee> occupied: runs) {
				occupiedTrue.retainAll(occupied);
			}
			//return the list
			return occupiedTrue;
		}
		
		//return empty list
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
	
	public Date getSpecificDate(String date) throws ParseException {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(date);
	}
	
	public void login(String loginName) {
		//searches employees to find matching name
		for (Employee employee: employees) {
			if (employee.name.equals(loginName)) {
				//if name is found, user is set to that person
				loggedIn = true;
				user = employee;
				return;
			} 
		}
	}	
	
	public Project getProject(String title, String client) throws Exception {
		for (Project project: projects) {
			if (project.getTitle().equals(title)) {
				return project;
			}
		}
		throw new Exception("Desired object does not exists");
	}
	
	public Employee getEmployee(String name) throws Exception {
		for (Employee employee: employees) {
			if (employee.name.equals(name)) {
				return employee;
			}
		}
		throw new Exception("Desired object does not exists");
	}
	
}
