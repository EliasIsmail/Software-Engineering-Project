
public class Employee {
	private String employeeInitials;
	private int numberOfActivities;
	
	public Employee(String employeeInitials) {
		this.employeeInitials = employeeInitials;
		this.numberOfActivities = 0;
	}
	public int getNumberOfActivities() {
		return numberOfActivities;
	}
	public void setNumberOfActivities(int numberOfActivities) {
		this.numberOfActivities = numberOfActivities;	
	}
	public String getEmployeeInitials() {
		return employeeInitials;
	}
	
}
