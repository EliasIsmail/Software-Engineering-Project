package dtu.library.app;

import java.util.HashMap;
import java.util.Scanner;

public class viewController {
	
	static App app = new App();
	static boolean loggedIn = false;
	static Employee user = null;
	static String scene = "loginScreen";
	
	public static void main(String args[]) {
		Scanner console = new Scanner(System.in);
		HashMap<String, String[]> actions = new HashMap<String, String[]>();
		
		
		actions.put("loginScreen", new String[] {
				"login"
		});
		actions.put("mainMenu", new String[] {
				"firstMethod",
				"secondMethod",
				"lastMethod"
		});
		actions.put("lesserMenu", new String[] {
				"someMethod"
		});
		
		while (true) {
			//main loop
			String[] actionsCurrent = actions.get(scene);
			System.out.println("What ya wanna do?");
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
	
	public static void executeCommand(String[] input) {
		String command = input[0];
		String parameter = input[1];
		
		switch(command) {
		  case "login":
			  for (Employee employee: app.employees) {
				  if (employee.name.equals(parameter)) {
					  loggedIn = true;
					  user = employee;
					  scene = "mainMenu";
				  }
			  }
			  if (!loggedIn) {
				  System.out.println("Login failed: No such name");
			  } else {
				  System.out.println("Logged in succesful");
			  }
			  break;
			  
		  case "something":
			  //code block
			  break;
			  
		  default:
		    //no such command
		}
	}
	
	
}
