package dtu.library.app;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class viewController {
	
	static App app = new App();
	static ArrayList<String> scenes = new ArrayList<String>();
	static Project currentProject;
	static Activity currentActivity;
	static int autoCommand = -1;
	static int autoIndex = 0;
	
	public static void main(String args[]) {
		Scanner console = new Scanner(System.in);
		HashMap<String, String[]> actions = new HashMap<String, String[]>();
		
		scenes.add("Login menu"); //first scene
		
		actions.put("Login menu", new String[] {
				"login(name)"
		});
		actions.put("Main menu", new String[] {
				"openLog()",
				"createEmployee(name)",
				"createProject(title,client)",
				"getEmployees()",
				"getOccupiedEmployees(startWeek,endWeek)",
				"getVacantEmployees(startWeek,endWeek)",
				"getProjects()",
				"getAssignedProjects()",
				"openProject(title)",
				"getSummary()",
				"logout()"
		});
		actions.put("Project", new String[] {
				"createActivity(name)",
				"getClient()",
				"addEmployee(name)",
				"setLeader(name)",
				"getLeader()",
				"getEstimatedTime()",
				"setStartWeek(weekNumber)",
				"setEndWeek(weekNumber)",
				"getStartWeek()",
				"getEndWeek()",
				"printStatus()",
				"getActivities()",
				"openActivity(name)",
				"getSummary()"
		});
		actions.put("Activity", new String[] {
				"addEmployee(name)",
				"setEstimatedTime(estimatedWorkingHours)",
				"getEstimatedTime()",
				"setStartWeek(weekNumber)",
				"setEndWeek(weekNumber)",
				"printStatus()"
		});
		actions.put("Log", new String[] {
				"getOverview(yyyy-mm-dd)",
				"addActivity(yyyy-mm-dd,project,activity,hours)"
		});
		
		
		while (true) {
			//main loop
			String currentScene = getCurrentScene();
			String[] actionsCurrent = actions.get(currentScene); //newest scene
			System.out.println("---------------");
			System.out.println("List of actions for " + currentScene);
			System.out.println();
			if (!currentScene.equals("Login menu")) {
				System.out.println("back()"); //always go back
			}
			printActions(actionsCurrent);
			System.out.println();
			System.out.print(">>> ");
			
			//input goes here
			String[] input = null;
			try {
				if (autoCommand == -1) {
					//user input
					input = splitInput(console.nextLine());
				} else {
					input = splitInput(autoCommands());
				}
			} catch (OperationNotAllowedException e) {
				System.out.println(e.getMessage());
			}
			
			try {
				if (input != null) {
					executeCommand(input);
				}
			} catch (OperationNotAllowedException | MissingAuthenticity e) {
				System.out.println(e.getMessage());
			}	
		}
	}
	
	public static String autoCommands() {
		
		ArrayList<String[]> commands = new ArrayList<String[]>();
		commands.add(new String[] {
				"login(Admin)",
				"createEmployee(Oliver)",
				"createEmployee(Eric)",
				"logout()",
				"login(Oliver)",
				
				"createProject(Project1,DTU)",
				"openProject(Project1)",
				"addEmployee(Oliver)",
				"addEmployee(Eric)",
				"createActivity(Activity1.1)",
				"createActivity(Activity1.2)",
				"createActivity(Activity1.3)",
				"back()",
				
				"createProject(Project2,DTU)",
				"openProject(Project2)",
				"addEmployee(Oliver)",
				"createActivity(Activity2.1)",
				"createActivity(Activity2.2)",
				"createActivity(Activity2.3)",
				"back()",
				
				"createProject(Project3,DTU)",
				"openProject(Project3)",
				"addEmployee(Oliver)",
				"createActivity(Activity3.1)",
				"createActivity(Activity3.2)",
				"createActivity(Activity3.3)",
				"back()",
				
				"openLog()",
				"addActivity(2019-05-21,Project1,Activity1.1,11)",
				"addActivity(2019-05-21,Project1,Activity1.2,12)",
				"addActivity(2019-05-21,Project1,Activity1.3,13)",
				"addActivity(2019-05-21,Project2,Activity2.1,21)",
				"addActivity(2019-05-21,Project3,Activity3.1,31)",
				"getOverview(2019-05-21)"
		});
		commands.add(new String[] {
				"login(Admin)",
				"createEmployee(Erik)",
				"logout()",
				"login(Erik)",
				"createProject(Project1, Intern)",
				"createProject(Project2, Intern)",
				"createProject(Project3, Intern)",
				"createEmployee(Elias)",
				"createEmployee(Oliver)",
				"createEmployee(Jonas)",
				"openLog()",
				"back()",
				"getEmployees()",
//				"getOccupiedEmployees(3)",
//				"getVacantEmployees(weekNumber)",
				"getProjects()",
				"getAssignedProjects()",
				"openProject(title)",

		});
		
		if (autoCommand >= commands.size()) {
			System.out.println("No such auto command");
			return "back()";
		}
				
		String command = commands.get(autoCommand)[autoIndex];
		
		autoIndex++;
		if (autoIndex == commands.get(autoCommand).length) { //if output is the last command
			System.out.println("Done executing the following commands");
			for (String item: commands.get(autoCommand)) {
				System.out.println(item);
			}
			autoCommand = -1;
			autoIndex = 0;
		}
		return command;
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
	
	public static String[] splitInput(String input) throws OperationNotAllowedException {
		String command, parameters;
		if (input.length() < 1) {
			throw new OperationNotAllowedException("Please enter a command.");
		}
		if (input.charAt(input.length()-1) != ')') {
			throw new OperationNotAllowedException("Missing parathesis. Please close command with ')'");
		}
		
		for (int i=0; i<input.length(); i++) {
			char c = input.charAt(i);
			
			if (c == '(') {
				command = input.substring(0, i);
				parameters = input.substring(i+1,input.length()-1);
				return new String[] {command,parameters};
			}
		}
		
		return null;
	}
	
	public static ArrayList<String> getParameters(String parameter) {
		ArrayList<String> items = new ArrayList<String>(Arrays.asList(parameter.split("\\s*,\\s*")));
		return items;
	}
	
	public static void setScene(String newScene) {
		scenes.add(newScene);
	}
	
	public static String getCurrentScene() {
		return scenes.get(scenes.size()-1);
	}
	
	public static void executeCommand(String[] input) throws OperationNotAllowedException, MissingAuthenticity {
		
		String command = input[0];
		String parameter = input[1];
		String currentScene = getCurrentScene();
		
		if (!currentScene.equals("Login menu")){
			switch(command) {
			case "back":
				if (scenes.size() > 2){ //main menu
					scenes.remove(scenes.size()-1);
					System.out.println("Newest Scene "+scenes.get(scenes.size()-1));
				} else {
					System.out.println("Cannot go further back");
				}
				break;
			
			case "logout":
				scenes.clear();
				scenes.add("Login menu");
				app.loggedIn = false;
				break;
			}
		} 
		
		if (currentScene.equals("Login menu")){
			switch(command) {
			//login menu
		
			case "login":
				app.login(parameter);
				if (!app.loggedIn) {
					System.out.println("Login failed: No such name");
				} else {
					System.out.println("Logged in succesful");
					setScene("Main menu");
				}
				break;
			
			case "auto":
				autoCommand = Integer.parseInt(parameter);
				System.out.println("Executing action sequence");
				break;
			}
			
		} else if (currentScene.equals("Main menu")){
			switch(command) {
			//main menu
			case "getSummary":
				for (Project project: app.projects) {
					project.printStatus();
				}
				break;
				
			case "openLog":
				setScene("Log");
				break;
				
			case "createEmployee":
				app.createEmployee(parameter);
				System.out.println("Employee created succesfully");
				break;
		
			case "createProject":
				ArrayList<String> parameters = getParameters(parameter);
				if (parameters.size() < 2) {
					System.out.println("Not enough parameters");
					break;
				}
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
				parameters = getParameters(parameter);
				int startWeek = Integer.parseInt(parameters.get(0));
				int endWeek = Integer.parseInt(parameters.get(1));
				
				ArrayList<Employee> occupiedEmployees = app.getOccupiedEmployees(startWeek,endWeek);
				System.out.println("All occupied employees at "+parameter);
				for (Employee employee: occupiedEmployees) {
					System.out.println(employee.name);
				}
				break;
			
			case "getVacantEmployees":
				ArrayList<Employee> vacantEmployees = null;
				parameters = getParameters(parameter);
				startWeek = Integer.parseInt(parameters.get(0));
				endWeek = Integer.parseInt(parameters.get(1));
				try {
					vacantEmployees = app.getVacantEmployees(startWeek,endWeek);
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid number");
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("Something went wrong. Try a different parameter");
				}
				System.out.println("All vacant employees at "+parameter);
				for (Employee employee: vacantEmployees) {
					System.out.println(employee.name);
				}
				break;
			
			case "getProjects":
				System.out.println("All projects:");
				for (Project project: app.projects) {
					System.out.println(project.getTitle()+", "+project.getClient());
				}
				break;	
				
			case "getAssignedProjects":
				System.out.println("All assigned projects:");
				for (Project project: app.user.assignedProjects) {
					System.out.println(project.getTitle());
				}
				break;
			
			case "openProject":
				boolean succes = false;
				for (Project project: app.projects) {
					if (project.getTitle().equals(parameter)) {
						currentProject = project;
						setScene("Project");
						System.out.println("Project succesfully found");
						succes = true;
						break;
					}
				}
				if (!succes) {
					System.out.println("Project not found");
				}
				break;
			}
			
		} else if (currentScene.equals("Project")){
			switch(command) {
			
			case "rename":
				try {
					currentProject.setTitle(parameter);
				} catch (Exception e1) {
					System.out.println("Title already in use");
					e1.printStackTrace();
				}
			
			case "createActivity":
				currentProject.createActivity(parameter);
				break;		
			
			case "getClient":
				System.out.println("Client is "+currentProject.getClient());
				break;
				
			case "addEmployee":
				for (Employee employee: app.employees) {
					if (employee.name.equals(parameter)) {
						currentProject.addEmployee(employee);
						System.out.println("Employee succesfully added");
						break;
					}
				}
				System.out.println("Employee not found");
				break;
			
			case "setLeader":
				for (Employee employee: app.employees) {
					if (employee.name.equals(parameter)) {
						currentProject.setLeader(employee);
						System.out.println("Project leader succesfully set");
						break;
					} 
				}
				System.out.println("Employee not found");
				break;
			
			case "getLeader":
				Employee leader = currentProject.getLeader();
				
				if (leader != null) {
					System.out.println("Current leader is "+leader.name);
				} else {
					System.out.println("No leader set for project");
				}
				break;
			
			case "getEstimatedTime":
				System.out.println("Estimated time is "+currentProject.getEstimatedTime()+" hours");
				break;
			
			case "setStartWeek":
				int startWeek = Integer.parseInt(parameter);
				
				if (startWeek < 1) {
					System.out.println("Please enter a weeknumber greater than 1");
					break;
				}
				try {
					currentProject.setStartWeek(startWeek);
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid number");
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			
			case "setEndWeek":
				int endWeek = Integer.parseInt(parameter);
				
				if (endWeek < 1) {
					System.out.println("Please enter a weeknumber greater than 0");
					break;
				}
				
				try {
					currentProject.setEndWeek(endWeek);
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid number");
					e.printStackTrace();
				} catch (Exception e) { //if activity is longer than project
					e.printStackTrace();
				}
				break;
				
			case "printStatus":
				currentProject.printStatus();
				break;
				
			case "getActivities":
				System.out.println("Projects activities ");
				for (Activity activity: currentProject.activities) {
					System.out.println(activity.name);
				}
				break;
			
			case "openActivity":
				for (Activity activity: currentProject.activities) {
					if (activity.name.equals(parameter)) {
						currentActivity = activity;
						setScene("Activity");
						break;
					}
				}
				System.out.println("Could not find the specified activity");
				break;
			case "getSummary":
				currentProject.printStatus();
			}
			
		} else if (currentScene.equals("Activity")){
			
			switch(command) {
			case "addEmployee":
				boolean succes = false;
				for (Employee employee: app.employees) {
					if (employee.name.equals(parameter)) {
						currentActivity.addEmployee(employee);
						System.out.println("Succesfully added the employee");
						succes = true;
						break;
					}
				}
				if (!succes) {
					System.out.println("Employee not found");
				}
				break;
				
			case "setEstimatedTime":
				int estimatedTime = Integer.parseInt(parameter);
				
				if (estimatedTime < 0) {
					System.out.println("Please enter a non-negative number");
					break;
				}
				
				try {
					currentActivity.setEstimatedTime(estimatedTime);
				} catch (Exception e2) {
					System.out.print("Please enter valid integer");
					e2.printStackTrace();
				}
				break;
			
			case "setStartWeek":
				int weekNumber = Integer.parseInt(parameter);
				
				if (weekNumber < 1) {
					System.out.println("Please enter a weeknumber greater than 0");
					break;
				}
				
				try {
					currentActivity.setStartWeek(weekNumber);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case "setEndWeek":
				weekNumber = Integer.parseInt(parameter);
				if (weekNumber < 1) {
					System.out.println("Please enter a weeknumber over 0");
					break;
				}
				
				try {
					currentActivity.setEndWeek(weekNumber);
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid number");
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("Unexpected exception - try another parameter");
					e.printStackTrace();
				}
				break;
			
			case "printStatus":
				currentActivity.printStatus();
				break;
			}
		} else if (currentScene.equals("Log")){
			switch(command) { 
			case "addActivity":
				ArrayList<String> parameters = getParameters(parameter);
				if (parameter.length() < 3) {
					System.out.println("Not enough parameters");
					break;
				}
				Date date = app.getSpecificDate(parameters.get(0));
				if (parameters.get(0).equals("today") || parameters.get(0).equals("Today")) {
					date = app.getCurrentDate();
				}
				for (Project project: app.projects) {
					if (project.getTitle().equals(parameters.get(1))) {
						for (Activity activity: project.activities) {
							if (activity.name.equals(parameters.get(2))) {
								currentActivity = activity;
								break;
							}
						}
						break;
					}
				}
				int hours = Integer.parseInt(parameters.get(3));
				app.user.addActivityToLog(date,currentActivity,hours);
				break;
				
			case "getOverview":
				date = app.getSpecificDate(parameter);
				ArrayList<LogElement> logs = app.user.getLogElementFromDate(date);
				if (logs == null) {
					System.out.println("No logs at the given date");
					break;
				}
				float totalHours = 0;
				System.out.println("Log for "+parameter);
				System.out.println("...............");
				
				ArrayList<Project> projects = new ArrayList<Project>();
				for (LogElement logElement: logs) {
					if (!projects.contains(logElement.activity.project)) {
						projects.add(logElement.activity.project);
					}
				}
				
				for (Project project: projects) {
					System.out.println(project.getTitle()+":");
					for (LogElement logElement: logs) {
						if (logElement.activity.project.equals(project)) {
							System.out.println(logElement.activity.name+", "+logElement.hours+" hours");
							totalHours = totalHours +logElement.hours;
						}
					}
					System.out.println();
				}
				
				System.out.println("Total "+totalHours+" hours");
				break;
			}
		}
	}
}
