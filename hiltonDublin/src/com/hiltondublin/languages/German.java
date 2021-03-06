package com.hiltondublin.languages;

import java.util.List;

import com.hiltondublin.classes.ConsumerProduct;
import com.hiltondublin.classes.Room;

public class German extends Language {
	public German(){
		setName("German");
	}
	
	//GENERAL
	public String yes(){
		return "Ja";
	}
	public String no(){
		return "Nein";
	}
	public String delete(){
		return "l�schen";
	}
	public String add(){
		return "add";
	}
	public String search(){
		return "suchen";
	}	
	public String whatWouldYouLikeToDo(){
		return "Was m�chten sie tun? ";
	}
	public String modify(){
		return "modifizieren";
	}
	public String firstName(){
		return "VORNAME";
	}
	public String lastName(){
		return "NACHNAME";
	}
	public String address(){
		return "ADRESSE";
	}
	public String email(){
		return "EMAIL";
	}
	public String phone(){
		return "TELEFON";
	}
	public String passportNr(){
		return "PASSNUMMER";
	}
	public String checkin(){
		return "einchecken";
	}
	public String weekDay(){
		return "Wochentag: ";
	}
	public String price(){
		return "Preis: ";
	}
	public String date(){
		return "Datum: ";
	}
	public String comment(){
		return "Kommentar: ";
	}
	public String rooms(){
		return "Zimmer: ";
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
	
	public String gustRatingRatingSimple(){
		return "Bewertung";
	}
	public String guestRatingRoomTypeSimple(){
		return "Zimmertyp";
	}
	public String guestRatingCommentSimple(){
		return "Kommentar";
	}
	public String guestRatingReviewsOurGuests(){
		return "Bewertungen unserer G�ste ";
	}
	public String guestRatingOverall(){
		return "insgesamt";
	}
	
	//HOME
	public String homeWelcome(){
		return "Willkommen bei Hilton Dublin";
	}
	public String homeWelcomeText(){
		String welcomeText = "Dank unserer zahlreichen Kunden, die uns schon seit �ber einem Jahrhundert aus der ganzen Welt besuchen kommen, sind wir stolz sagen zu k�nnen, dass 'Hilton Hotels and Resorts' eines der angesagtesten Hotels auf der ganzen Welt ist."
				+ "<br> Und um diesem Ruf gerecht zu werden versprechen wir auch weiterhin unseren G�sten ihren Aufenthalt mit attraktive Produkten, einen freundlichen und verl�sslichen Service so angehnehm wie m�glich zu gestalten. ";
	
		return welcomeText;
	}
	public String homeBookNow(){
		return "Jetzt buchen!";
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
	public String employeeCheckoutHeading(){
		return "Auschecken";
	}
	public String employeeChargeProductHeading(){
		return "Produkt Verrechnung";
	}
	public String employeeCheckoutVerificationHeading(){
		return "Verifizierung";
	}
	public String employeeCheckoutVerificationQuestion(String firstName, String lastName){
		return "Ist der Name vom Gast '" + firstName + " " + lastName + "'?  ";
	}
	public String employeeChargeProductProduct(){
		return "Produkt: ";
	}
	public String employeeChargeProductCharge(){
		return "verrechnen";
	}
	public String employeeCheckinHeading(){
		return "Einchecken";
	}
	public String employeeCheckinErrorNoReservationFound(){
		return "Keine Reservierung gefunden. ";
	}
	public String employeeCheckinReservationsFound(int ammount){
		return ammount + " Reservierung(en) gefunden.";
	}
	public String checkinReservationSuccessful(){
		return "Erfolgreich eingecheckt. ";
	}
	public String employeeCheckinReservationConfirmation(){
		return "Sind sie sicher, dass sie in folgende Reservierung einchecken wollen? ";
	}
		
	//ADMINISTRATOR
	public String administratorAreaHeading(){
		return "Admin Bereich";
	}
	public String administratorLoginMessage(){
		return "Sie m�ssen als Administrator eingeloggt sein um Zugang zum Admin Bereich zu erhalten!";
	}
	public String administratorModifyReservationHeading(){
		return "Reservierung modifizieren";
	}
	public String administratorModifyReservationBookingNummber(){
		return "Buchungsnummer: ";
	}
	public String administratorModifyReservationGuestFirstName(){
		return "Gast Vorname: ";
	}
	public String administratorModifyReservationGuestLastName(){
		return "Gast Nachname: ";
	}
	public String administratorModifyReservationArrivalDate(){
		return "Ankunftsdatum: ";
	}
	public String administratorModifyReservationDepartureDate(){
		return "Abreisedatum: ";
	}
	public String administratorModifyReservationRooms(){
		return "Reservierte Zimmer: ";
	}
	public String administratorModifyReservationConsumerProducts(){
		return "Konsumierte Produkte: ";
	}
	public String administratorModifyReservationSearchReservation(){
		return "suchen";
	}
	public String administratorModifyReservationErrorNoReservationFound(){
		return "Keine Reservierung gefunden";
	}
	public String administratorModifyReservationErrorBookingNumber(){
		return "Buchungsnummer muss vom Typ Integer sein";
	}
	public String administratorModifyReservationModifyButton(){
		return "modifizieren";
	}
	public String administratorModifyReservationReservationDetailsHeading(){
		return "Reservierung Details";
	}
	public String administratorModifyReservationGuestDetailsHeading(){
		return "Guest Details";
	}
	public String administratorModifyReservationDetailsSuccessful(){
		return "Reservierungs Details erfolgreich modifiziert";
	}
	public String administratorModifyReservationNumberOfReservationsFound(int number){
		return number + " Reservierungen gefunden";
	}
	public String administratorModifyReservationGuestPhoneNumber(){
		return "Telefonummer: ";
	}
	public String administratorModifyReservationGuestAddress(){
		return "Adresse: ";
	}
	public String administratorModifyReservationGuestPassportNr(){
		return "Reisepass Nummer: ";
	}
	public String administratorModifyGuestErrorFirstNameEmpty(){
		return "Das Feld 'Gast Vorname' darf nicht leer sein";
	}
	public String administratorModifyGuestErrorLastNameEmpty(){
		return "Das Feld 'Gast Nachname' darf nicht leer sein";
	}
	public String administratorModifyGuestErrorEmailNotInRightFormat(){
		return "Das Feld 'Email' ist nicht im richtigen Format";
	}
	public String administratorModifyGuestErrorPassportNrEmpty(){
		return "Das Feld 'Passnummer' darf nicht leer sein";
	}
	public String administratorModifyGuestErrorPassportNrNotInRightFormat(){
		return "'Passnummer' ist nicht im richtigen Format. Sie muss vom Typ Integer sein.";
	}
	public String administratorModifyGuestDetailsSuccessful(){
		return "Erfolgreich Gast Details modifiziert";
	}
	public String administratorModifyRoomDetailsHeading(){
		return "Zimmer Details";
	}
	public String administratorModifyRoomDetailsRoomNumber(){
		return "Zimmernummer: ";
	}
	public String administratorModifyRoomDetailsType(){
		return "Zimmertyp: ";
	}
	public String administratorModifyRoomDetailsSmoking(){
		return "Rauchen: ";
	}
	public String administratorModifyRoomDetailsOccupied(){
		return "Besetzt: ";
	}
	public String administratorDeleteRoomFromReservationSuccessful(){
		return "Zimmer erfolgreich von der Reservierung gel�scht";
	}
	public String administratorDeleteRoomFromReservationError(){
		return "Zimmer nicht erfolgreich von der Reservierung gel�scht";
	}
	public String administratorAddRoomToReservationErrorNoAvailableRoom(){
		return "Keine verf�gbaren Zimmer dieses Zimmertyps gefunden";
	}
	public String administratorAddRoomToReservationErrorFailed(){
		return "Zuordnung des Zimmers zu dieser Reservierung fehlgeschlagen";
	}
	public String administratorAddRoomToReservationSuccessful(){
		return "Zuordnung des Zimmers zu dieser Reservierung erfolgreich";
	}
	public String administratorModifyProductHeading(){
		return "Produkt Details";
	}
	public String administratorModifyProductProductName(){
		return "Produktname: ";
	}
	public String administratorModifyProductPrice(){
		return "Preis: ";
	}
	public String administratorModifyProductSuccessfulyDeletedFromReservation(){
		return "Erfolgreich Product von der Reservierung gel�scht";
	}
	public String administratorModifyProductErrorDeleteFromReservation(){
		return "L�schung des Produkts von der Reservierung fehlgeschlagen. Kein Produkt gefunden.";
	}
	public String administratorModifyProductSuccessfulAddProductToReservation(){
		return "Erfolgreich Produkt zur Reservierung hinzugef�gt";
	}
	public String administratorModifyRoomHeading(){
		return "Zimmer modifizieren";
	}
	public String administratorDeleteRoomSuccessful(){
		return "Zimmer erfolgreich gel�scht. ";
	}
	
	public String administratorModifyRoomSelectModifyRoom(){
		return "Zimmer modifizieren";
	}
	public String administratorModifyRoomSelectAddRoom(){
		return "Zimmer hinzuf�gen";
	}
	public String administratorModifyRoomSelectModifyRoomType(){
		return "Zimmertyp modifizieren";
	}
	public String administratorModifyRoomSelectAddRoomType(){
		return "Zimmertyp hinzuf�gen";
	}
	public String administratorModifyRoomSearchRoomErrorRoomNumberNotInRightFormat(){
		return "Zimmernummer muss vom Typ Integer sein. ";
	}
	public String administratorModifyRoomSearchRoomErrorTypeIDNotInRightFormat(){
		return "Zimmertyp ID muss vom Typ Integer sein. ";
	}
	public String administratorModifyRoomSearchRoomErrorSmokingNotInRightFormat(){
		return "Rauchen muss vom Typ Boolean sein. ";
	}
	public String administratorModifyRoomSearchRoomErrorOccupiedNotInRightFormat(){
		return "Besetzt muss vom Typ Boolean sein. ";
	}
	public String administratorModifyRoomSearchRoomErrorNoRoomsFound(){
		return "Kein Zimmer gefunden";
	}
	public String administratorModifyRoomSearchRoomSuccessfulFoundRoom(int ammount){
		return ammount + " Zimmer gefunden";
	}
	public String administratorModifyRoomErrorRoomNumberMissing(){
		return "Bitte geben sie eine Zimmernummer ein.";
	}
	public String administratorModifyRoomErrorRoomNumberNotInRightFormat(){
		return "Zimmernummer muss vom Typ Integer sein. ";
	}
	public String administratorModifyRoomErrorRoomNotFound(){
		return "Zimmer nicht gefunden. ";
	}
	public String administratorModifyRoomSuccessful(){
		return "Erfolgreich Zimmer modifiziert. ";
	}
	public String administratorAddRoomErrorRoomNumberMissing(){
		return "Bitte geben sie eine Zimmernummer ein.";
	}
	public String administratorAddRoomErrorRoomNumberNotInRightFormat(){
		return "Zimmernummer muss vom Typ Integer sein. ";
	}
	public String administratorAddRoomErrorRoomAllreadyExist(){
		return "Zimmer existiert bereits. ";
	}
	public String administratorAddRoomSuccessful(Room room){
		return "Erfolgreich neues Zimmer hinzugef�gt (Zimmernummer: " + room.getRoomNumber() + ", Typ: " + room.getType().getName() + ", Rauchen: " + room.isSmoking() + ", Besetzt: " + room.isOccupied() + ") ";
	}
	public String administratorDeleteProductSuccessful(){
		return "Produkt erfolgreich gel�scht. ";
	}
	
	public String administratorModifyProductHead(){
		return "Produkt modifizieren";
	}
	public String administratorModifyProductSelectModifyProduct(){
		return "Produkt modifizieren";
	}
	public String administratorModifyProductSelectAddProduct(){
		return "Produkt hinzuf�gen";
	}
	public String administratorModifyProductProductID(){
		return "Produkt ID: ";
	}
	public String administratorModifyProductErrorProductIDNotInRightFormat(){
		return "Produkt ID muss vom Typ Integer sein. ";
	}
	public String administratorModifyProductErrorPriceNotInRightFormat(){
		return "Preis muss vom Typ Double sein. ";
	}
	public String administratorModifyProductSuccessfulFoundProduct(int ammount){
		return ammount + " Produkt(e) gefunden ";
	}
	public String administratorModifyProductErrorNoRoomFound(){
		return "Keine Produkte gefunden. ";
	}
	public String administratorModifyProductErrorProductNotFound(){
		return "Produkt nicht gefunden. ";
	}
	public String administratorModifyProductErrorProductIDMissing(){
		return "Geben sie bitte eine Produkt ID ein. ";
	}
	public String administratorModifyProductErrorProductIDAllreadyExist(){
		return "Ein Produkt mit dieser Produkt ID existiert schon. ";
	}
	public String administratorModifyProductErrorProductNameMissing(){
		return "Geben sie bitte einen Produkt Namen ein. ";
	}
	public String administratorModifyProductErrorPriceMissing(){
		return "Geben sie bitte einen Preis ein. ";
	}
	public String administratorModifyProductSuccessful(){
		return "Erfolgreich Produkt modifiziert. ";
	}
	public String administratorAddProductSuccessful(ConsumerProduct product){
		return "Erfolgreich neues Produkt hinzugef�gt. (Produkt ID: " + product.getProductID() + ", Produkt Name: " + product.getName() + ", Preis: " + product.getPrice() + ") "; 
	}
	public String administratorModifyRoomTypeSelectRoomType(){
		return "Zimmertyp Auswahl: ";
	}
	public String administratorModifyRoomTypeDetails(){
		return "Zimmertyp Details: ";
	}
	public String roomTypeName(){
		return "Zimmertyp Name: ";
	}
	public String roomTypePictureResource(){
		return "Bild Resource: ";
	}
	public String roomTypeStandardPrice(){
		return "Standard Preis: ";
	}
	public String roomTypeDescription(){
		return "Beschreibung: ";
	}
	public String administratorModifyRoomTypeErrorRoomTypeNameMissing(){
		return "Zimmertyp Name fehlt. ";
	}
	public String administratorModifyRoomTypeErrorStandardPriceMissing(){
		return "Preis fehlt. ";
	}
	public String administratorModifyRoomTypeErrorStandardPriceNotInRightFormat(){
		return "Preis ist nicht im richtigen Format. Es muss vom Typ Double sein. ";
	}
	public String administratorModifyRoomTypeSuccessful(){
		return "Erfolgreich Zimmertyp modifiziert. ";
	}
	public String administratorModifyRoomTypeWeekdayPrices(){
		return "Wochtentag Preise: ";
	}
	public String administratorModifyRoomTypeSpecialPrices(){
		return "Spezial Preise: ";
	}
	public String administratorDeleteWeekdayPriceSuccessful(){
		return "Erfolgreich Wochentagpreis gel�scht. ";
	}
	public String administratorAddWeekdayPriceErrorWeekdayAllreadyExist(){
		return "Wochentag Preis existiert bereits. ";
	}
	public String administratorAddWeekdayPriceSuccessful(){
		return "Erfolgreich neuen Wochentag Preis hinzugef�gt. ";
	}
	public String administratorDeleteSpecialPriceSuccessful(){
		return "Erfolgreich Spezial Preis gel�scht. ";
	}
	public String administratorAddSpecialPriceSuccessful(){
		return "Erfolgreich neuen Spezial Preis hinzugef�gt. ";
	}
	public String administratorAddSpecialPriceErrorDateAllreadyExist(){
		return "Spezial Preis existiert bereits. ";
	}
	public String administratorAddRoomTypeSuccessful(){
		return "Erfolgreich neuen Zimmertyp hinzugef�gt. ";
	}
	
	public String administratorRegisterEmployeeHeading(){
		return "Arbeitskraft Registrierung";
	}
	public String administratorRegisterEmployeeFirstName(){
		return "VORNAME: ";
	}
	public String administratorRegisterEmployeeLastName(){
		return "NACHNAME: ";
	}
	public String administratorRegisterEmployeePhone(){
		return "TELEFONUMMER: ";
	}
	public String administratorRegisterEmployeeEmail(){
		return "EMAIL: ";
	}
	public String administratorRegisterEmployeeUsername(){
		return "BENUTZERNAME: ";
	}
	public String administratorRegisterEmployeePassword(){
		return "PASSWORT: ";
	}
	public String administratorRegisterEmployeePasswordRepeat(){
		return "PASSWORT WIEDERHOLEN: ";
	}
	public String administratorRegisterEmployeeErrorFirstNameMissing(){
		return "Bitte geben sie einen Vornamen ein. ";
	}
	public String administratorRegisterEmployeeErrorLastNameMissing(){
		return "Bitte geben sie einen Nachnamen ein. ";
	}
	public String administratorRegisterEmployeeErrorEmailMissing(){
		return "Bitte geben sie eine Email adresse ein. ";
	}
	public String administratorRegisterEmployeeErrorEmailNotInRightFormat(){
		return "Email ist nicht im richtigen Format und kann nicht validiert werden. ";
	}
	public String administratorRegisterEmployeeErrorUsernameMissing(){
		return "Bitte geben sie einen Benutzernamen ein. "; 
	}
	public String administratorRegisterEmployeeErrorPasswordMissing(){
		return "Bitte geben sie ein Passwort und die Passwort Wiederholung ein. ";
	}
	public String administratorRegisterEmployeeErrorPasswordNotInRightFormat(){
		return "Passwort nicht im richtigen Format. Es muss aus mindestens 8 Zeichen bestehen. ";
	}
	public String administratorRegisterEmployeeErrorPasswordNotMatchingPasswordRepeat(){
		return "Passwort passt nicht zur Passwort Wiederholung. ";
	}
	public String administratorRegisterEmployeeErrorUsernameAllreadyExist(){
		return "Benutzer existiert bereits. ";
	}
	public String administratorRegisterEmployeeSuccessful(){
		return "Erfolgreich neue Arbeitskraft registriert. ";
	}
	public String reservationSuccessful(){
		return "Reservierung erfolgreich";
	}
	
	//EMPLOYEE
	public String employeeCancelReservationHeading(){
		return "Reservierung Stornieren";
	}
	public String employeeCancelReservationCancelButton(){
		return "stornieren";
	}
	public String employeeCancelReservationNumber(int i){
		return "Reservierung #" + i;
	}
	public String employeeCancelReservationConfirmation(){
		return "Sind sie sicher, dass sie folgende Reservierung stornieren wollen? ";
	}
	public String employeeCancelReservation(){
		return "Reservierung";
	}
	public String employeeCancelReservationSuccessful(){
		return "Reservierung erfolgreich storniert. ";
	}
	
	
	
	
	//RESERVATION
	public String reservationOnlineHeading(){
		return "Online Reservierung";
	}
	public String reservationHeading(){
		return "Reservierung";
	}
	public String reservationGuestDetails(){
		return "Gast Details";
	}
	public String reservationBookingDetails(){
		return "Buchungs Details";
	}
	public String reservationRoomDetails(){
		return "Zimmer Details";
	}
	public String reserveAndPay(){
		return "Reservieren und Bezahlen";
	}
	public String reserve(){
		return "Reservieren";
	}
	public String reservationArrivalDate(){
		return "Anreisedatum";
	}
	public String reservationDepartureDate(){
		return "Abreisedatum";
	}
	public String reservationSmoking(){
		return "Raucher";
	}
	public String reservationPerson(){
		return "Personen";
	}
	public String reservationRoomType(){
		return "Zimmertyp";
	}
	public String reservationRoomTypeDescription(){
		return "Beschreibung";
	}
	public String reservationRoomTypePicture(){
		return "Bild";
	}
	public String reservationRoomTypeAmmount(){
		return "Anzahl Zimmer: ";
	}
	public String reservationErrorFirstNameMissing(){
		return "Bitte geben sie einen Vornamen ein. ";
	}
	public String reservationErrorLastNameMissing(){
		return "Bitte geben sie einen Nachnamen ein. ";
	}
	public String reservationErrorAddressMissing(){
		return "Bitte geben sie eine Addresse ein. ";
	}
	public String reservationErrorEmailMissing(){
		return "Bitte geben sie eie Email Adresse ein. ";
	}
	public String reservationErrorEmailNotInRightFormat(){
		return "Die Email Adresse ist nicht im richtigen Format. Bitte �berpr�fen sie die Email Adresse. ";
	}
	public String reservationErrorPassportNrMissing(){
		return "Geben sie bitte eine Passnummer ein. ";
	}	
	public String reservationErrorPassportNrNotInRightFormat(){
		return "Die Passnummer muss vom Typ Integer sein. ";
	}
	public String reservationErrorDateDepartureDateBeforeArrivalDate(){
		return "Das Abreisedatum muss mindestens ein Tag sp�ter sein als das Anreisedatum. ";
	}
	public String reservationErrorNoRoomsSelected(){
		return "Please select at least one room from a room type. ";
	}
	public String reservationErrorBookedOut(){
		return "Es tut uns wirklich leid. Aber wir haben zur Zeit leider nicht gen�gend Zimmer ihrer Anfrage entsprechend. ";
	}
	public String reservationBookingNumber(){
		return "BOOKING NUMBER: ";
	}
	public String reservationRooms(){
		return "Zimmer: ";
	}
	public String reservationBookingOverview(){
		return "Buchungs�bersicht: ";
	}
	public String reservationFirstName(){
		return "Vorname: ";
	}
	public String reservationLastName(){
		return "Nachname: ";
	}
	
	public String paymentInformation(){
		return "Bezahlungs Inforamtionen";
	}
	public String paymentCardHolder(){
		return "KARTENINHABER";
	}
	public String paymentCardNumber(){
		return "KARTENNUMMER";
	}
	public String paymentCvvCode(){
		return "CVV PR�FZAHL";
	}
	public String paymentExpiration(){
		return "ABLAUFDATUM (Jahr, Monat)";
	}
	public String paymentYear(){
		return "Jahr";
	}
	public String paymentMonth(){
		return "Monat";
	}
	public String paymentPay(){
		return "bezahlen";
	}
	public String paymentReset(){
		return "zur�cksetzen";
	}
	public String paymentErrorCardNumberMissing(){
		return "Kartennummer fehlt. ";
	}
	public String paymentErrorCardNumberInvalid(){
		return "Kartennummer nicht g�ltig. ";
	}
	public String paymentErrorCreditCardMissing(){
		return "Bitte w�hlen sie eine Kreditkarte aus. ";
	}
	public String paymentErrorCreditCardUnknown(){
		return "Unbekannte Kredit Karte. ";
	}
	public String paymentErrorCvvCodeMissing(){
		return "CVV Code fehlt. ";
	}
	public String paymentErrorCvvCodeInvalid(){
		return "CVV Code ung�ltig. ";
	}
	public String paymentErrorExpirationYearMissing(){
		return "Ablaufjahr fehlt. ";
	}
	public String paymentErrorExpirationMonthMissing(){
		return "Ablaufmonat fehlt. ";
	}
	public String paymentErrorCardHolderMissing(){
		return "Karteninhaber fehlt. ";
	}
	public String paymentSuccessful(){
		return "Gl�ckwunsch! Sie haben so eben erfolgreich ihre Reservierung abgeschlossen und bezahlt. "
				+ "Wir freuen uns sie schon bald bei Hilton Dublin begr��en zu d�rfen! "
				+ "Bitte behalten sie sich ihre Buchungsnummer im Fall, dass sie fragen an uns haben. ";
	}
	public String paymentYourBooking(){
		return "Ihre Buchung: ";
	}
}
