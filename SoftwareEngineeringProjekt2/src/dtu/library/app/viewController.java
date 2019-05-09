package dtu.library.app;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class viewController {
	
	static App app = new App();
	static String scene = "loginScreen";
	static Project currentProject;
	static Activity currentActivity;
	
	public static void main(String args[]) throws OperationNotAllowedException {
		Scanner console = new Scanner(System.in);
		HashMap<String, String[]> actions = new HashMap<String, String[]>();
		
		
		actions.put("loginScreen", new String[] {
				"login(name)"
		});
		actions.put("mainMenu", new String[] {
				"createEmployee(name)",
				"createProject(title,client)",
				"getEmployees()",
				"getOccupiedEmployees(weekNumber)",
				"getVacantEmployees(weekNumber",
				"getProjects()",
				"getAssignedProjects()",
				"openProject(title)"
		});
		actions.put("currentProject", new String[] {
				"createActivity(name)",
				"addEmployee(name)",
				"setLeader(name)",
				"getLeader()",
				"getEstimatedTime()",
				"setStartWeek(weekNumber)",
				"setEndWeek(weekNumber)",
				"printStatus()",
				"getAssignedActivities()",
				"openActivity(name)"
		});
		
		while (true) {
			//main loop
			String[] actionsCurrent = actions.get(scene);
			System.out.println("---------------");
			System.out.println("List of actions:");
			printActions(actionsCurrent);
			System.out.println();
			System.out.print(">>> ");
			
			
			//user input goes here
			String[] input = getInput();
			executeCommand(input);	
		}
	}
	
	public static void printActions(String[] actions) {
		for (String action: actions) {
			System.out.println(action);
		}
	}
	
	public static void printArrayList(ArrayList<Object> list) {
		for (Object object: list) {
			System.out.println(object);
		}
	}
	
	public static String[] getInput() {
		Scanner console = new Scanner(System.in);
		String input = console.nextLine();
		String command, parameters;
		
		for (int i=0; i<input.length(); i++) {
			char c = input.charAt(i);
			
			if (c == '(') {
				command = input.substring(0, i);
				parameters = input.substring(i+1,input.length()-1);
				return new String[] {command,parameters};
			}
		}
		
		return new String[] {"noInput","noInput"};
	}
	
	public static ArrayList<String> getParameters(String parameter) {
		ArrayList<String> items = new ArrayList<String>(Arrays.asList(parameter.split("\\s*,\\s*")));
		return items;
	}
	
	public static void executeCommand(String[] input) throws OperationNotAllowedException {
		
		String command = input[0];
		String parameter = input[1];
		
		switch(command) {
			//login menu
			case "login":
				app.login(parameter);
				if (!app.loggedIn) {
					System.out.println("Login failed: No such name");
				} else {
					System.out.println("Logged in succesful");
					scene = "mainMenu";
				}
				break;
			
			//main menu
			case "createEmployee":
				app.createEmployee(parameter);
				System.out.println("Employee created succesfully");
				break;
		
			case "createProject":
				ArrayList<String> parameters = getParameters(parameter);
				app.createProject(parameters.get(0),parameters.get(1));
				System.out.println("Project created succesfully");
				break;
				
			case "getEmployees":
				System.out.println("All employees");
				for (Employee employee: app.employees) {
					System.out.println(employee.name);
				}
				break;
			case "getOccupiedEmployees":
				ArrayList<Employee> occupiedEmployees = app.getOccupiedEmployees(Integer.parseInt(parameter));
				System.out.println("All occupied employees at "+parameter);
				for (Employee employee: occupiedEmployees) {
					System.out.println(employee.name);
				}
				break;
			
			case "getVacantEmployees":
				ArrayList<Employee> vacantEmployees = app.getVacantEmployees(Integer.parseInt(parameter));
				System.out.println("All vacant employees at "+parameter);
				for (Employee employee: vacantEmployees) {
					System.out.println(employee.name);
				}
				break;
			
			case "getProjects":
				System.out.println("All projects ");
				for (Project project: app.projects) {
					System.out.println(project.getTitle());
				}
				break;	
				
			case "getAssignedProjects":
				System.out.println("All assigned projects ");
				for (Project project: app.user.assignedProjects) {
					System.out.println(project.getTitle());
				}
				break;
			
			case "openProject":
				for (Project project: app.projects) {
					if (project.getTitle().equals(parameter)) {
						currentProject = project;
						scene = "currentProject";
					}
				}
				break;
			
			//current project
			case "createActivity":
				currentProject.createActivity(parameter);
				break;		
			
			case "addEmployee":
				for (Employee employee: app.employees) {
					if (employee.name.equals(parameter)) {
						currentProject.addEmployee(employee);
					}
				}
				break;
			
			case "setLeader":
				for (Employee employee: app.employees) {
					if (employee.name.equals(parameter)) {
						currentProject.setLeader(employee);
					}
				}
				break;
			
			case "getLeader":
				System.out.println("Current leader is "+currentProject.getLeader().name);
				break;
			
			case "getEstimatedTime":
				System.out.println("Estimated time is "+currentProject.getEstimatedTime());
				break;
			
			case "setStartWeek":
				try {
					currentProject.setStartWeek(Integer.parseInt(parameter));
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid number");
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			
			case "setEndWeek":
				try {
					currentProject.setEndWeek(Integer.parseInt(parameter));
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid number");
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "printStatus":
				currentProject.printStatus();
				break;
				
			case "getAssignedActivities":
				System.out.println("All assigned activities ");
				for (Activity activity: currentProject.activities) {
					System.out.println(activity);
				}
				break;
			
			case "openActivity":
				for (Activity activity: currentProject.activities) {
					if (activity.name.equals(parameter)) {
						currentActivity = activity;
						scene = "currentActivity";
					}
				}
				break;
		}
	}
	
	
}
