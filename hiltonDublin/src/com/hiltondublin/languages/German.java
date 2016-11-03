package com.hiltondublin.languages;

public class German extends Language {
	public German(){
		setName("German");
	}
	
	//Navigation Slide
	public String navigationSlideGuest(){
		return "Gast";
	}
	
	public String navigationSlideEmployee(){
		return "Areitskraft";
	}
	
	public String navigationSlideAdmin(){
		return "Administrator";
	}
	
	public String navigationSlideDisability(){
		return "Behinderung";
	}
	
	public String navigationSlideLoginWrongUsername(){
		return "Login fehlgeschlagen!";
	}
	
	public String navigationSlideLoginWrongPassword(){
		return "Login fehlgeschlagen!";
	}
	
	public String navigationSlideLoginUsername(){
		return "Benutzername";
	}
	
	public String navigationSlideLoginPassword(){
		return "Passwort";
	}
	
	public String navigationSlideLoginLogin(){
		return "einloggen";
	}
	
	public String navigationSlideLoginLogout(){
		return "ausloggen";
	}
	
	public String navigationSlideLoginLoggedInAs(){
		return "Eingeloggt als";
	}
	
	//HOME
	public String homeWelcome(){
		return "Willkommen bei Hilton Dublin";
	}
	
	//EMPLOYEE
	public String employeeAreaHeading(){
		return "Arbeitskraft Bereich";
	}
	public String employeeLoginMessage(){
		return "Sie müssen als Arbeitskraft eingeloggt sein um Zugang zum Arbeitskraft Bereich zu erhalten!";
	}
		
	//ADMINISTRATOR
	public String administratorAreaHeading(){
		return "Admin Bereich";
	}
	public String administratorLoginMessage(){
		return "Sie müssen als Administrator eingeloggt sein um Zugang zum Admin Bereich zu erhalten!";
	}
	
}
