package com.hiltondublin.languages;

import java.util.List;

import com.hiltondublin.classes.Room;

public abstract class Language {
	private String name = null;
	
	public String getName() {
		return name;
	}
	
	//GENERAL
	public String yes(){
		return "yes";
	}
	public String no(){
		return "no";
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
	
	//GUEST
	public String guestWelcome(){
		return "Welcome to Guest Page";
	}
	public String guestRatingErrorMessageFirstName(){
		return "Please type in a First Name!";
	}
	
	public String guestRatingErrorMessageLastName(){
		return "Please type in a Last Name!";
	}
	
	public String guestRatingErrorMessageEmail(){
		return "Please type in an Email Address!";
	}
	
	public String guestRatingFirstName(){
		return "First Name:";
	}
	
	public String guestRatingLastName(){
		return "Last Name:";
	}
	
	public String guestRatingEmail(){
		return "Email:";
	}
	
	public String guestRatingRoomType(){
		return "Your Room Type: ";
	}
	
	public String guestRatingRating(){
		return "Your Rating: ";
	}
	
	public String guestRatingComment(){
		return "Your Comment: ";
	}
	
	public String guestRatingCommentDetail(){
		return "Write a few Words to your selected room type...";
	}
	
	public String guestRatingReview(){
		return "Your Review";
	}
	
	public String guestRatingRoomInformation(){
		return "Rating";
	}
	
	public String guestRatingGuestInformation(){
		return "Guest Information";
	}
	
	public String guestRatingThanksForYourReview(){
		return "Thank you very much for you review ";
	}
	
	public String guestRatingThanksRoomType(){
		return "Your Room Type: ";
	}
	
	public String guestRatingThanksRating(){
		return "Your Rating: ";
	}
	
	public String guestRatingThanksComment(){
		return "Your Comment: ";
	}
	
	public String guestRatingRatingBestGrade(){
		return "* 5 -> Best Grade";
	}
	
	public String guestRatingRatingWorstGrade(){
		return "* 1 -> Worst Grade";
	}
	
	//EMPLOYEE
	public String employeeAreaHeading(){
		return "Employee Area";
	}
	public String employeeLoginMessage(){
		return "You need to be logged in as an employee to get access to the Employee Area!";
	}
	public String employeeCheckoutButton(){
		return "checkout";
	}
	public String employeeCheckoutRoomNumber(){
		return "Room Number: ";
	}
	public String employeeCheckoutErrorRoomNumberNotInteger(String roomNumber){
		return "Room number '" + roomNumber + "' was not a number! It must be from type integer!";
	}
	public String employeeCheckoutErrorRoomNumberNull(){
		return "Room number can't be null!";
	}
	public String employeeCheckoutErrorRoomNumberEmpty(){
		return "No room number typed in!";
	}
	public String employeeCheckoutErrorNoSuitableReservation(String roomNumber){
		return "No suitable reservation found for room number '" + roomNumber + "'!";
	}
	public String employeeCheckoutErrorAllreadyCheckedOut(String roomNumber){
		return "Room number '" + roomNumber + "' allready checked out!";
	}
	public String employeeCheckoutSuccessfullyCheckedOut(String roomNumber){
		return "Room number '" + roomNumber + "' successfully checked out!";
	}
	public String employeeCheckoutAllRoomsCheckedOut(){
		return "All rooms for this reservation checked out!";
	}
	public String employeeCheckoutOccupiedRooms(List<Room> occupiedRooms){
		String message = "Room number ";
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
		message += " still needs to be checked out for this reservation!";
		
		return message;
	}
	public String employeeCheckoutBillHeadline(){
		return "Bill";
	}
	public String employeeCheckoutBillTotal(){
		return "Total";
	}
	public String employeeCheckoutHeading(){
		return "Checkout";
	}
	public String employeeCheckoutVerificationHeading(){
		return "Verification";
	}
	public String employeeCheckoutVerificationQuestion(String firstName, String lastName){
		return "Is the name of the guest '" + firstName + " " + lastName + "'?  ";
	}
	
	public String employeeChargeProductSuccessfully(){
		return "Successfully charged product to reservation!";
	}
	public String employeeChargeProductHeading(){
		return "Charge Product";
	}
	public String employeeChargeProductProduct(){
		return "Product: ";
	}
	public String employeeChargeProductCharge(){
		return "charge";
	}
	
	//ADMINISTRATOR
	public String administratorAreaHeading(){
		return "Admin Area";
	}
	public String administratorLoginMessage(){
		return "You need to be logged in as an administrator to get access to the Admin Area!";
	}
}
