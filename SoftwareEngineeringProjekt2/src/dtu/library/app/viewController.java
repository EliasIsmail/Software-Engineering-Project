package dtu.library.app;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class viewController {
	
	//init
	static App app = new App();
	static ArrayList<String> scenes = new ArrayList<String>();
	static Project currentProject;
	static Activity currentActivity;
	static int autoCommand = -1;
	static int autoIndex = 0;
	
	public static void main(String args[]) {
		//init scanner
		Scanner scanner = new Scanner(System.in);
		//saves scenes in list so to remember the history. Current scene is the newest added
		scenes.add("Login menu");
		
		//list of the menus UI
		HashMap<String, String[]> actions = new HashMap<String, String[]>();
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
				"getProjects()", //maybe remove
				"getAssignedProjects()", //rename
				"openProject(title)",
				"getSummary()",
				"logout()"
		});
		actions.put("Project", new String[] {
				"createActivity(name)",
				//"getClient()", //removed
				"addEmployee(name)",
				"setLeader(name)",
				//"getLeader()", //removed
				//"getEstimatedTime()", //removed
				"setStartWeek(weekNumber)",
				"setEndWeek(weekNumber)",
				//"getActivities()", //removed
				"openActivity(name)",
				"rename(title)",
				"getSummary()"
		});
		actions.put("Activity", new String[] {
				"addEmployee(name)",
				"setEstimatedTime(estimatedWorkingHours)",
				"setStartWeek(weekNumber)",
				"setEndWeek(weekNumber)",
				"getSummary()"
		});
		actions.put("Log", new String[] {
				"getOverview(yyyy-mm-dd)",
				"addActivity(yyyy-mm-dd,project,activity,hours)",
				"removeActivity(yyyy-mm-dd,project,activity)"
		});
		
		
		//main loop
		while (true) {
			//set scene and list of commands for scene
			String currentScene = getCurrentScene();
			String[] actionsCurrent = actions.get(currentScene);
			
			System.out.println("---------------");
			System.out.println("List of actions for " + currentScene);
			System.out.println();
			if (!currentScene.equals("Login menu") && !currentScene.equals("Main menu")) {
				System.out.println("back()"); //draw back command
			}
			printActions(actionsCurrent);
			System.out.println();
			System.out.print(">>> ");
			
			//managing input
			String[] input = null;
			try {
				if (autoCommand == -1) {
					//user input
					input = splitInput(scanner.nextLine());
				} else {
					input = splitInput(autoCommands());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			try {
				if (input != null) {
					executeCommand(input);
				}
			} catch (NumberFormatException e) {
				System.out.println("The input for the specific command must be a number");
			} catch (ParseException e) {
				System.out.println("Input must be a date in format 'yyyy-mm-dd'");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static String autoCommands() {
		
		//sequence of commands to be executed automatically
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
				"createEmployee(Elias)",
				"createEmployee(Oliver)",
				"createEmployee(Jonas)",
				"createEmployee(Liv)",
				"createEmployee(Frederik)",
				"createEmployee(Rasmus)",
				
				"createProject(Project1, Intern)",
				"openProject(Project1)",
				"setStartWeek(2)",
				"setEndWeek(4)",
				"createActivity(Step1)",
				"openActivity(Step1)",
				"addEmployee(Erik)",
				"setStartWeek(13)",
				"setEndWeek(15)",
				"back()",
				"createActivity(Step2)",
				"openActivity(Step2)",
				"addEmployee(Elias)",
				"setStartWeek(17)",
				"setEndWeek(19)",
				"back()",
				"createActivity(Step3)",
				"openActivity(Step3)",
				"addEmployee(Oliver)",
				"setStartWeek(21)",
				"setEndWeek(23)",
				"back()",
				"back()",
				
				
				"createProject(Project2, Intern)",
				"openProject(Project2)",
				"setStartWeek(25)",
				"setEndWeek(27)",
				"createActivity(Step1)",
				"openActivity(Step1)",
				"addEmployee(Liv)",
				"setStartWeek(5)",
				"setEndWeek(7)",
				"back()",
				"back()",
				
				"createProject(Project3, Intern)",
				"openProject(Project3)",
				"setStartWeek(34)",
				"setEndWeek(36)",
				"createActivity(Step1)",
				"openActivity(Step1)",
				"addEmployee(Erik)",
				"setStartWeek(5)",
				"setEndWeek(10)",
				"back()",
				"back()",
				
				"createProject(bund, Intern)",
				"openProject(bund)",
				"setStartWeek(37)",
				"setEndWeek(39)",
				"addEmployee(Erik)",
				"createActivity(Step1)",
				"openActivity(Step1)",
				"addEmployee(Frederik)",
				"setStartWeek(5)",
				"setEndWeek(7)",
				"back()",
				"createActivity(Step2)",
				"openActivity(Step2)",
				"addEmployee(Rasmus)",
				"setStartWeek(2)",
				"setEndWeek(4)",
				"back()",
				"back()",





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
	
	//print actions for a given scene
	public static void printActions(String[] actions) {
		for (String action: actions) {
			System.out.println(action);
		}
	}
	
	//prints arraylists
	public static void printArrayList(ArrayList<Object> list) {
		for (Object object: list) {
			System.out.println(object);
		}
	}
	
	//parses the raw input
	public static String[] splitInput(String input) throws OperationNotAllowedException {
		String command, parameters;
		if (input.length() < 1) {
			throw new OperationNotAllowedException("Please enter a command.");
		}
		if (input.charAt(input.length()-1) != ')') {
			throw new OperationNotAllowedException("Wrong command format. Make sure to include parenthesis.");
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
	
	//split input to multiple parameters
	public static ArrayList<String> getParameters(String parameter) {
		ArrayList<String> items = new ArrayList<String>(Arrays.asList(parameter.split("\\s*,\\s*")));
		return items;
	}
	
	//set the current scene
	public static void setScene(String newScene) {
		scenes.add(newScene);
	}
	
	//get the current scene
	public static String getCurrentScene() {
		return scenes.get(scenes.size()-1);
	}
	
	//print status for project
	public static void printStatusProject(Project project) throws OperationNotAllowedException {
		System.out.println();
		System.out.println("Project: "+project.getTitle()+", #"+project.getProjectId() + " for " + project.getClient());
		System.out.println("Employees: ");
		for (Employee employee: project.employees) {
			System.out.println(employee.name);
		}
		if (project.getLeader() != null) {
			System.out.println("\nProject leader: "+project.getLeader().name);
		} else {
			System.out.println("\nProject leader: no leader");
		}
		System.out.println("Start week: " + project.startWeek);
		System.out.println("End week: " + project.endWeek);
		System.out.println("Estimated total work: "+project.getEstimatedTime());
		System.out.println("Work done: "+project.getTime());
		System.out.println("Estimated work left: "+(project.getEstimatedTime()-project.getTime()));
		System.out.println("-------------------------");
		
		for (Activity activity: project.activities) {
			printStatusActivity(activity);
		}	
		System.out.println();
	}
	
	//print status activity
	public static void printStatusActivity(Activity activity) {
		System.out.println("Activity: "+activity.name+" from project "+activity.project.getTitle());
		System.out.println("Employees: ");
		for (Employee employee: activity.employees) {
			System.out.println(employee.name);
		}
		System.out.println();
		System.out.println("Start week: " + activity.startWeek);
		System.out.println("End week: " + activity.endWeek);
		System.out.println("Estimated total work: "+activity.estimatedTime);
		System.out.println("Work done: "+activity.time);
		System.out.println("Estimated work left: "+(activity.estimatedTime-activity.time));
		System.out.println("-------------------------");
	}
	
	//executes a given command using methoids from the other classes. This method is the glue that binds together the whole app.
	public static void executeCommand(String[] input) throws Exception {
		
		String command = input[0];
		String parameter = input[1];
		String currentScene = getCurrentScene();
		boolean backOrLogout = false;
		
		//each case represents the actions from a scene
		if (!currentScene.equals("Login menu")){
			switch(command) {
			case "back":
				if (scenes.size() > 2){ //main menu
					scenes.remove(scenes.size()-1);
					backOrLogout = true;
				} else {
					System.out.println("Cannot go further back");
				}
				break;
			
			case "logout":
				scenes.clear();
				scenes.add("Login menu");
				app.loggedIn = false;
				System.out.println("Successfully logged out");
				backOrLogout = true;
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
			
			default:
				if (!backOrLogout) System.out.println("The command doesn't match any on the list, try again");
			}
			
		} else if (currentScene.equals("Main menu")){
			switch(command) {
			//main menu
			case "getSummary":
				for (Project project: app.projects) {
					printStatusProject(project);
				}
				break;
				
			case "openLog":
				setScene("Log");
				break;
				
			case "createEmployee":
				try {
					app.getEmployee(parameter);
					System.out.println("Employee already exists");
				} catch (Exception e){
					app.createEmployee(parameter);
					System.out.println("Employee created successfully");
				}
				break;
		
			case "createProject":
				ArrayList<String> parameters = getParameters(parameter);
				if (parameters.size() < 2) {
					System.out.println("Not enough parameters");
					break;
				}
				try {
					app.getProject(parameters.get(0), parameters.get(1));
					System.out.println("Project already exists");
				} catch (Exception e) {
					app.createProject(parameters.get(0),parameters.get(1));
					System.out.println("Project created successfully");
				}
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
				vacantEmployees = app.getVacantEmployees(startWeek,endWeek);
					
				System.out.println("All vacant employees at "+parameter);
				for (Employee employee: vacantEmployees) {
					System.out.println(employee.name);
				}
				break;
			
			case "getProjects":
				System.out.println("All projects:");
				System.out.println("Title, Client");
				System.out.println();
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
						System.out.println("Project succesfully opened");
						succes = true;
						break;
					}
				}
				if (!succes) {
					System.out.println("Project not found");
				}
				break;
			
			default:
				if (!backOrLogout) System.out.println("The command doesn't match any on the list, try again");
			}
			
		} else if (currentScene.equals("Project")){
			switch(command) {
			
			case "rename":
				currentProject.setTitle(parameter);
				System.out.println("Title renamed to: " + parameter);
				break;
	
			case "createActivity":
				currentProject.createActivity(parameter);
				System.out.println("Activity successfully created");
				break;		
			
			/*
			case "getClient":
				System.out.println("Client is "+currentProject.getClient());
				break;
			*/
				
			case "addEmployee":
				boolean success = false;
				for (Employee employee: app.employees) {
					if (employee.name.equals(parameter)) {
						currentProject.addEmployee(employee);
						success = true;
						break;
					}
				}
				if (!success) System.out.println("Employee not found");
				break;
			
			case "setLeader":
				success = false;
				for (Employee employee: app.employees) {
					if (employee.name.equals(parameter)) {
						currentProject.setLeader(employee);
						System.out.println("Project leader succesfully set");
						success = true;
						break;
					} 
				}
				if(!success) System.out.println("Employee not found");
				break;
			
			/* NOTE: USE getSummary() INSTEAD
			
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
			*/
			
			case "setStartWeek":
				int startWeek = Integer.parseInt(parameter);
				
				currentProject.setStartWeek(startWeek);
				System.out.println("Startweek set to " + startWeek);
				break;
			
			case "setEndWeek":
				int endWeek = Integer.parseInt(parameter);
				currentProject.setEndWeek(endWeek);
				System.out.println("Endweek set to " + endWeek);
				break;
				
			/* NOTE: use getSummary() instead
			case "printStatus":
				currentProject.printStatus();
				break;
			
				
			case "getActivities":
				System.out.println("Projects activities ");
				for (Activity activity: currentProject.activities) {
					System.out.println(activity.name);
				}
				break;
			*/
			
			case "openActivity":
				boolean found = false;
				for (Activity activity: currentProject.activities) {
					if (activity.name.equals(parameter)) {
						currentActivity = activity;
						setScene("Activity");
						System.out.println("Activity successfully found");
						found = true;
						break;
					}
				}
				if (!found) System.out.println("Could not find the specified activity");
				break;
				
			case "getSummary":
				printStatusProject(currentProject);
				break;
			default:
				if (!backOrLogout) System.out.println("The command doesn't match any on the list, try again");
			}
			
		} else if (currentScene.equals("Activity")){
			
			switch(command) {
			case "addEmployee":
				boolean success = false;
				for (Employee employee: app.employees) {
					if (employee.name.equals(parameter)) {
						currentActivity.addEmployee(employee);
						System.out.println("Employee successfully added");
						success = true;
						break;
					}
				}
				if (!success) System.out.println("Employee not found");
				break;
				
			case "setEstimatedTime":
				float estimatedTime = Float.parseFloat(parameter);
				
				currentActivity.setEstimatedTime(estimatedTime);
				System.out.println("Estimated time set to " + estimatedTime + " hours");
				break;
			
			case "setStartWeek":
				int weekNumber = Integer.parseInt(parameter);
				
				currentActivity.setStartWeek(weekNumber);

				System.out.println("Startweek set to " + weekNumber);
				break;
				
			case "setEndWeek":
				weekNumber = Integer.parseInt(parameter);
				currentActivity.setEndWeek(weekNumber);

				System.out.println("Endweek set to " + weekNumber);
				break;
			
			case "getSummary":
				printStatusActivity(currentActivity);
				break;
				
			default:
				if (!backOrLogout) System.out.println("The command doesn't match any on the list, try again");
			}

		} else if (currentScene.equals("Log")){
			switch(command) { 
			case "addActivity":
				ArrayList<String> parameters = getParameters(parameter);
				boolean success = false;
				
				if (parameter.length() < 4) {
					System.out.println("Not enough parameters");
					break;
				}
				Date date;
				if (parameters.get(0).equals("today") || parameters.get(0).equals("Today")) {
					date = app.getCurrentDate();
				} else date = app.getSpecificDate(parameters.get(0));
				
				for (Project project: app.projects) {
					if (project.getTitle().equals(parameters.get(1))) {
						for (Activity activity: project.activities) {
							if (activity.name.equals(parameters.get(2))) {
								currentActivity = activity;
								success = true;
								break;
							}
						}
						break;
					}
				}
				if (success) {
					float hours = Float.parseFloat(parameters.get(3));
					app.user.addActivityToLog(date,currentActivity,hours);
					System.out.println("Successfully added log");
				} else System.out.println("Project or activity not found");
				break;
				
			case "getOverview":
				if (parameter.equals("today") || parameter.equals("Today")) {
					date = app.getCurrentDate();
				} else date = app.getSpecificDate(parameter);
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
			
			case "removeActivity":
				parameters = getParameters(parameter);
				success = false;
				if (parameter.length() < 3) {
					System.out.println("Not enough parameters");
					break;
				}
				date = app.getSpecificDate(parameters.get(0));
				if (parameters.get(0).equals("today") || parameters.get(0).equals("Today")) {
					date = app.getCurrentDate();
				}
				for (Project project: app.projects) {
					if (project.getTitle().equals(parameters.get(1))) {
						for (Activity activity: project.activities) {
							if (activity.name.equals(parameters.get(2))) {
								currentActivity = activity;
								success = true;
								break;
							}
						}
						break;
					}
				}
				if (success) {
					if(app.user.removeLogElement(date, currentActivity)) {
						System.out.println("Log succssfully removed");
					} else {
						System.out.println("No matching log");

					}
				} else System.out.println("Project or activity not found");
				break;
				
			default:
				if (!backOrLogout) System.out.println("The command doesn't match any on the list, try again");
			}
		}
	}
}
