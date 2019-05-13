------------ README ------------

1. Import the project into Eclipse to run the program. (Note that the Eclipse version is 1.8)
2. To run the program pick the viewController.java and run that with Eclipse.

3. How to use the program
--The first screen looks like this:

---------------
List of actions for Login menu

login(name)

>>> 

--It prompts for a login. When you login for the first time, you have to use the Admin login as follows:

---------------
List of actions for Login menu

login(name)

>>> login(Admin)


--Regarding login; An employee can log in, when they have been created.
--As a general rule, one doesn't have to add any symbols other than commas and parentheses when writing the commands.


--After a successful login, you will be met by the main menu, displaying the possible commands
(We added some comments in the form '--comment' to clarify, what it is or does):

---------------
List of actions for Main menu

openLog()		                             	--log for the user to register hours
createEmployee(name)
createProject(title,client)
getEmployees()		                            --returns all employees
getOccupiedEmployees(startWeek,endWeek)	        --returns all occupied employees in the period
getVacantEmployees(startWeek,endWeek)		    --returns all vacant employees in the period
getProjects()		                            --returns all projects
getAssignedProjects()		                    --return the project that the user is assigned to
openProject(title)		                     	--opens a menu with possible commands for a given project
getSummary()		                            --returns a report of all projects (this method acts differently depending on the menu)
logout()		                         		--logs the user out

>>> 

--This is the general idea, which the rest of the program follows.
--Regarding dates; if one needs to call the current date, this can be done by writing 'today' or 'Today' instead of yyyy-mm-dd.

--To help with testing we have created an auto command, which runs a sequence of commands, which is great for setting up tests.
	We have created a command sequence for whomever is evaluating this project, it can be found at line 245 in viewController.java.
	To run these automated sequences, you would write as follows at the login screen:
	
---------------
List of actions for Login menu

login(name)

>>> auto(2)

Enjoy the program!