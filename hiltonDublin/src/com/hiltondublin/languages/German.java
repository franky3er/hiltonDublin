package com.hiltondublin.languages;

public class German extends Language {
	public German(){
		setName("German");
	}
	
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
	
	//HOME
	public String homeWelcome(){
		return "Willkommen bei Hilton Dublin";
	}
	
}
