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
			System.out.println(e.getMessage());			//Never fails
		}
		
		//Setup project for misc registration
		Project misc;
		try {
			misc = createProject("Misc","Administration");
			misc.createActivity("Holiday");
			misc.createActivity("SickDay");
			misc.createActivity("Course");
		} catch (OperationNotAllowedException e) {
			System.out.println(e.getMessage());			//Never fails
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
		if (title == null || client == null || title.equals("") || client.equals("")) { //1
			throw new OperationNotAllowedException("Missing project information");
		}
		
		//preconditions
		assert title != null: "Precondition violated";
		assert client != null: "Precondition violated";
		assert title != "": "Precondition violated";
		assert client != "": "Precondition violated";
	 
		int projectsSizeAtPre = projects.size();
		
		
		for (Project project:projects) {
			if (title.equals(project.getTitle())) { //2
				return project; 
			}
		}
		
		String projectId = Integer.toString(getCurrentDate().getYear()+1900).substring(2,4)+Integer.toString(projectCounter);
		projectCounter++;
		Project project = new Project(title, client, projectId,this); 
		projects.add(project); //3
		
		// postconditions		
		try {
			assert getProject(title, client) != null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
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
		ArrayList<Employee> occupiedTrue = runs.get(0);
		for (ArrayList<Employee> occupied: runs) {
			occupiedTrue.retainAll(occupied);
		}
		//return the list
		return occupiedTrue;
	}
	
	public ArrayList<Employee> getVacantEmployees(int weekStart, int weekEnd) throws Exception {
		ArrayList<Employee> vacantEmployees = new ArrayList<Employee>();
		ArrayList<Employee> occupiedEmployees = getOccupiedEmployees(weekStart,weekEnd);
		//add all employees who aren't part of the occupied employees
		for (Employee employee: employees) {
			if (!occupiedEmployees.contains(employee)) {
				vacantEmployees.add(employee);
			}
		}
		//throw exception if there are no vacant employees else returns list
		if(vacantEmployees.isEmpty()) throw new Exception("Currently no available employees");
		return vacantEmployees;
	}
	
	public Date getCurrentDate() {
		//returns todays date
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(new Date());
		try {
			return format.parse(dateString);
		} catch (Exception e) {			//Never fails
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Date getSpecificDate(String date) throws ParseException {
		//converts from string to date
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
		//find specific project by name
		for (Project project: projects) {
			if (project.getTitle().equals(title)) {
				return project;
			}
		}
		//else throw exception
		throw new Exception("Desired object does not exists");
	}
	
	public Employee getEmployee(String name) throws Exception {
		//find specific employee
		for (Employee employee: employees) {
			if (employee.name.equals(name)) {
				return employee;
			}
		}
		//throw exception
		throw new Exception("Desired object does not exists");
	}
	
}
