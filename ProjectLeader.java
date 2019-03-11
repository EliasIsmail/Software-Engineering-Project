
public class ProjectLeader extends Employee {
	private String employeeInitials;
	private Project project;

	public ProjectLeader(String employeeInitials) {
		super(employeeInitials);
	}
	public void setProject (Project project) {
		this.project = project; 
	}
	
}
