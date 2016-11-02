package com.hiltondublin.languages;

public abstract class Language {
	private String name = null;
	
	public String getName() {
		return name;
	}

	
	//NAVIGATIONSLIDE
	public void setName(String name) {
		this.name = name;
	}
	
	public String navigationSlideHome(){
		return "Home";
	}
	
	public String navigationSlideGuest(){
		return "Guest";
	}
	
	public String navigationSlideEmployee(){
		return "Employee";
	}
	
	public String navigationSlideAdmin(){
		return "Admin";
	}
	
	public String navigationSlideDisability(){
		return "Disability";
	}
	
	public String navigationSlideLoginWrongUsername(){
		return "Login failed!";
	}
	
	public String navigationSlideLoginWrongPassword(){
		return "Login failed!";
	}
	
	public String navigationSlideLoginUsername(){
		return "Username";
	}
	
	public String navigationSlideLoginPassword(){
		return "Password";
	}
	
	public String navigationSlideLoginLogin(){
		return "login";
	}
	
	public String navigationSlideLoginLogout(){
		return "logout";
	}
	
	public String navigationSlideLoginLoggedInAs(){
		return "Logged in as";
	}

	
	
	
	//HOME
	public String homeWelcome(){
		return "Welcome to Hilton Dublin";
	}
	
	//EMPLOYEE
	public String employeeAreaHeading(){
		return "Employee Area";
	}
	public String employeeLoginMessage(){
		return "You need to be logged in as an employee to get access to the Employee Area!";
	}
	
	//ADMINISTRATOR
	public String administratorAreaHeading(){
		return "Admin Area";
	}
	public String administratorLoginMessage(){
		return "You need to be logged in as an administrator to get access to the Admin Area!";
	}
}
