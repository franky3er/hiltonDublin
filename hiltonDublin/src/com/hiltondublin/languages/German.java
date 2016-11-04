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
	
	//GUEST
	public String guestWelcome(){
		return "Willkommen zu Gast auf der Seite";
	}
	public String guestRatingHeading(){
		return "Bewertung";
	}
	public String guestRatingErrorMessageFirstName(){
		return "Geben Sie bitte einen Vornamen ein!";
	}
	
	public String guestRatingErrorMessageLastName(){
		return "Geben Sie bitte einen Nachnamen ein!";
	}
	
	public String guestRatingErrorMessageEmail(){
		return "Geben Sie bitte eine Email Adresse ein!";
	}
	
	public String guestRatingFirstName(){
		return "Vorname:";
	}
	
	public String guestRatingLastName(){
		return "Nachname:";
	}
	
	public String guestRatingEmail(){
		return "Email:";
	}
	
	public String guestRatingRoomType(){
		return "Ihr Zimmertyp: ";
	}
	
	public String guestRatingRating(){
		return "Ihre Bewertung: ";
	}
	
	public String guestRatingComment(){
		return "Ihr Kommentar: ";
	}
	
	public String guestRatingCommentDetail(){
		return "Schreiben Sie ein paar Worte zu ihrem ausgew�hlten Zimmertyp...";
	}
	
	public String guestRatingReview(){
		return "Ihre Rezension";
	}
	
	public String guestRatingRoomInformation(){
		return "Rezension";
	}
	
	public String guestRatingGuestInformation(){
		return "Gast Informationen";
	}
	
	public String guestRatingThanksForYourReview(){
		return "Vielen Dank f�r Ihre Bewertung ";
	}
	
	public String guestRatingThanksRoomType(){
		return "Ihr Zimmertyp: ";
	}
	
	public String guestRatingThanksRating(){
		return "Ihre Bewertung: ";
	}
	
	public String guestRatingThanksComment(){
		return "Ihr Kommentar: ";
	}
	
	public String guestRatingRatingBestGrade(){
		return "* 5 -> Beste Note";
	}
	
	public String guestRatingRatingWorstGrade(){
		return "* 1 -> Schlechteste Note";
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
		return "Sie m�ssen als Arbeitskraft eingeloggt sein um Zugang zum Arbeitskraft Bereich zu erhalten!";
	}
		
	//ADMINISTRATOR
	public String administratorAreaHeading(){
		return "Admin Bereich";
	}
	public String administratorLoginMessage(){
		return "Sie m�ssen als Administrator eingeloggt sein um Zugang zum Admin Bereich zu erhalten!";
	}
	
}
