package dtu.library.app;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;


public class Tester {
	
	private static int projectCounter = 0001;
	
	public static void main(String[] args) {

		
		
		
	}
	
	public static Date getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(new Date());
		try {
			return format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getSpecificDate(String date) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void testActivitiesAndProjects() {
		App app = new App();
		Project project1 = app.createProject("Project 1");
		Project project2 = app.createProject("Project 2");
		Activity activity1 = project1.createActivity("Activity 1: Finish use cases");
		Activity activity2 = project2.createActivity("Activity 2: Do the dishes");
		
		String[] names = new String[] {"JONA","ELIA","OLIV","ERIK"};
		for (String name: names) {
			app.createEmployee(name);
		}
		
		activity1.addEmployee(app.employees.get(0));
		activity1.addEmployee(app.employees.get(1));
		activity1.addEmployee(app.employees.get(2));
		activity2.addEmployee(app.employees.get(2));
		activity2.addEmployee(app.employees.get(0));
		activity2.addEmployee(app.employees.get(3));
		
		activity1.estimatedTime = 10;
		activity2.estimatedTime = 10;
		
		activity1.setStartDate(app.getSpecificDate("2019-04-01"));
		activity1.setEndDate(app.getSpecificDate("2019-04-10"));
		
		activity2.setStartDate(app.getSpecificDate("2019-05-01"));
		activity2.setEndDate(app.getSpecificDate("2019-05-10"));
		
		project1.printStatus();
		project2.printStatus();
		activity1.printStatus();
		activity2.printStatus();
		
		ArrayList<Employee> occupiedEmployees = app.getOccupiedEmployees(app.getSpecificDate("2019-04-05"));
		System.out.println("Occupied: ");
		for (Employee employee: occupiedEmployees) {
			System.out.println(employee.name);
		}
		
		ArrayList<Employee> vacantEmployees = app.getVacantEmployees(app.getSpecificDate("2019-04-05"));
		System.out.println("Vacant: ");
		for (Employee employee: vacantEmployees) {
			System.out.println(employee.name);
		}
		
		System.out.println();
		Employee jonas = app.employees.get(0);
		activity2.addEmployee(jonas);
		Date today = app.getCurrentDate();
		
		jonas.addActivityToLog(today, activity1, 1);
		jonas.addActivityToLog(today, activity1, 2);
		jonas.addActivityToLog(today, activity2, 3);
		jonas.addActivityToLog(today, activity1, 4);
		
		/*
		for (int date = 10; date < 13; date++) {
			System.out.println("Looking at date "+date);
			ArrayList<LogElement> dailyLog = jonas.getLogElementFromDate(date);
			for (LogElement logElement: dailyLog) {
				System.out.println("worked on "+logElement.activity.name+", in "+logElement.hours+" hours");
			}
		}
		*/
	}
	
}
