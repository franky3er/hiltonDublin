package com.hiltondublin.languages;

import java.util.List;

import com.hiltondublin.classes.Room;

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
	public String employeeCheckoutButton(){
		return "checkout";
	}
	public String employeeCheckoutRoomNumber(){
		return "Zimmernummer: ";
	}
	public String employeeCheckoutErrorRoomNumberNotInteger(String roomNumber){
		return "Zimmernummer '" + roomNumber + "' ist keine Nummer! Sie muss vom Typ Integer sein!";
	}
	public String employeeCheckoutErrorRoomNumberNull(){
		return "Zimmernummer kann nicht vom Typ null sein!";
	}
	public String employeeCheckoutErrorRoomNumberEmpty(){
		return "Keine Zimmernummer eingegeben!";
	}
	public String employeeCheckoutErrorNoSuitableReservation(String roomNumber){
		return "Keine passende Reservierung gefunden f�r Zimmernummer '" + roomNumber + "'!";
	}
	public String employeeCheckoutErrorAllreadyCheckedOut(String roomNumber){
		return "Zimmernummer '" + roomNumber + "' bereits ausgecheckt!";
	}
	public String employeeCheckoutSuccessfullyCheckedOut(String roomNumber){
		return "Zimmernummer '" + roomNumber + "' erfolgreich ausgecheckt!";
	}
	public String employeeCheckoutAllRoomsCheckedOut(){
		return "Alle Zimmer f�r diese Reservierung ausgecheckt!";
	}
	public String employeeCheckoutOccupiedRooms(List<Room> occupiedRooms){
		String message = "Zimmernummer ";
		boolean firstOccupiedRoom = true;
		for(Room occupiedRoom : occupiedRooms){
			if(firstOccupiedRoom){
				firstOccupiedRoom = false;
			}
			else {
				message += ", ";
			}
			message += "'" + occupiedRoom.getRoomNumber() + "'";
		}
		message += " muss / m�ssen f�r diese Reservierung noch ausgecheckt werden!";
		
		return message;
	}
	public String employeeCheckoutBillHeadline(){
		return "Rechnung";
	}
	public String employeeCheckoutBillTotal(){
		return "Total";
	}
	public String employeeChargeProductSuccessfully(){
		return "Produkt erfolgreich auf Reservation verbucht!";
	}
	public String employeeChargeProductHeading(){
		return "Produkt Verrechnung";
	}
	public String employeeChargeProductProduct(){
		return "Produkt: ";
	}
	public String employeeChargeProductCharge(){
		return "verrechnen";
	}
		
	//ADMINISTRATOR
	public String administratorAreaHeading(){
		return "Admin Bereich";
	}
	public String administratorLoginMessage(){
		return "Sie m�ssen als Administrator eingeloggt sein um Zugang zum Admin Bereich zu erhalten!";
	}
	
}
