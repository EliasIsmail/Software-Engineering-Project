package dtu.library.app;

import java.util.HashMap;
import java.util.Scanner;

public class viewController {
	
	public static void main(String args[]) {
		Scanner console = new Scanner(System.in);
		
		App app = new App();
		boolean loggedIn = false;
		String scene = "loginScreen";
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
			String input = console.nextLine();
			
			
			
			System.out.println("got input "+input);
			String input2 = console.nextLine();
		}
			
		
		
		
	}
	
	public static void printActions(String[] actions) {
		for (String action: actions) {
			System.out.println(action);
		}
	}
	
	public static String[] getInput() {
		String input = console.nextLine();
	}
	
	public static void executeCommand(String command) {
		switch(command) {
		  case "login":
		    // login code
		    break;
		  case "something":
		    // code block
		    break;
		  default:
		    //no such command
		}
	}
	
	
}
