package dtu.library.app;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class viewController {
	
	static App app = new App();
	static String scene = "loginScreen";
	
	public static void main(String args[]) throws OperationNotAllowedException {
		Scanner console = new Scanner(System.in);
		HashMap<String, String[]> actions = new HashMap<String, String[]>();
		
		
		actions.put("loginScreen", new String[] {
				"login(name)"
		});
		actions.put("mainMenu", new String[] {
				"createEmployee(name)",
				"createProject(title,client)",
				"getOccupiedEmployees(yyyy-MM-dd)"
		});
		actions.put("lesserMenu", new String[] {
				"someMethod"
		});
		
		while (true) {
			//main loop
			String[] actionsCurrent = actions.get(scene);
			System.out.println("---------------");
			System.out.println("List of actions:");
			printActions(actionsCurrent);
			
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
		return (ArrayList<String>) Arrays.asList(parameter.split(","));
	}
	
	public static void executeCommand(String[] input) throws OperationNotAllowedException {
		
		String command = input[0];
		String parameter = input[1];
		
		switch(command) {
		
			case "login":
				app.login(parameter);
				if (!app.loggedIn) {
					System.out.println("Login failed: No such name");
				} else {
					System.out.println("Logged in succesful");
					scene = "mainMenu";
				}
				break;
			
			case "createEmployee":
				app.createEmployee(parameter);
				System.out.println("Employee created succesfully");
				break;
		
			case "createProject":
				ArrayList<String> parameters = getParameters(parameter);
				app.createProject(parameters.get(0),parameters.get(1));
				System.out.println("Project created succesfully");
				break;
			
			case "getOccupiedEmployees":
				ArrayList<Employee> occupiedEmployees = app.getOccupiedEmployees(app.getSpecificDate(parameter));
				System.out.println("All occupied employees at "+parameter);
				for (Employee employee: occupiedEmployees) {
					System.out.println(employee);
				}
				break;
			
			case "getVacantEmployees":
				ArrayList<Employee> vacantEmployees = app.getVacantEmployees(app.getSpecificDate(parameter));
				System.out.println("All vacant employees at "+parameter);
				for (Employee employee: vacantEmployees) {
					System.out.println(employee);
				}
				break;
				
			case "getAllProjects":
				ArrayList<Employee> vacantEmployees = app.getVacantEmployees(app.getSpecificDate(parameter));
				System.out.println("All vacant employees at "+parameter);
				for (Employee employee: vacantEmployees) {
					System.out.println(employee);
				}
				break;
				
			}
	}
	
	
}
