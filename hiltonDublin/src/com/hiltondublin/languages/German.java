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
		return "Falscher Benutzername!";
	}
	
	public String navigationSlideLoginWrongPassword(){
		return "Falsches Passwort!";
	}
	
	public String navigationSlideLoginUsername(){
		return "Benutzername";
	}
	
	public String navigationSlideLoginPassword(){
		return "Passwort";
	}
	
	//HOME
	public String homeWelcome(){
		return "Willkommen bei Hilton Dublin";
	}
	
}
