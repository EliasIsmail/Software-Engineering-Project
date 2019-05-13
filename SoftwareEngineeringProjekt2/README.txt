------------ README ------------

1. Import the project into Eclipse to run the program. (Note the version is 1.8)
2. To run the program pick the viewController.java and run that with Eclipse.

3. How to use the program
--The first screen looks like this:

---------------
List of actions for Login menu

login(name)

>>> 

--It prompts for a login. The first login i always the admin login, typed like this:

---------------
List of actions for Login menu

login(name)

>>> login(Admin)

--Regarding login; An employee can log in, when they have been created.
--As a general rule, one doesn't have to any symbols other than comma when writing the commands.
--The next screen looks like this (Added some comments in the form '--comment' to clarify, what it is or does):

---------------
List of actions for Main menu

openLog()		--log for the user to register hours
createEmployee(name)
createProject(title,client)
getEmployees()		--returns all employees
getOccupiedEmployees(startWeek,endWeek)			--returns all occupied employees in the period
getVacantEmployees(startWeek,endWeek)		--returns all vacant employees in the period
getProjects()		--return all project
getAssignedProjects()		--return the project that the user is assigned to
openProject(title)		--opens menu for a project
getSummary()		--returns a report over all projects (this method acts differently depending on the menu)
logout()		--logs out

>>> 

--This is the general idea, which the rest of the project follows.
--Regarding dates; if one needs to call the current date, this can be done with writing 'today' or 'Today' instead of yyyy-mm-dd.

--To help with testing we have created an auto command, which runs a sequence of commands, which is great for setting test up.
	We have created a command sequence for whomever is evaluating this project, it can be found at line 245 in viewController.java.
	To run this sequence write like this in the login screen:
	
---------------
List of actions for Login menu

login(name)

>>> auto(2)

Enjoy the program!