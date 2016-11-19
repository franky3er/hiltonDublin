package com.hiltondublin.languages;

import java.util.List;

import com.hiltondublin.classes.ConsumerProduct;
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
	public String delete(){
		return "delete";
	}
	public String add(){
		return "add";
	}
	public String search(){
		return "search";
	}
	public String whatWouldYouLikeToDo(){
		return "What would you like to do? ";
	}
	public String modify(){
		return "modify";
	}
	public String register(){
		return "register";
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
	public String guestName(){
		return "Name: ";
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
	public String administratorModifyReservationHeading(){
		return "Modify Reservation";
	}
	public String administratorModifyReservationBookingNummber(){
		return "Booking Number:";
	}
	public String administratorModifyReservationGuestFirstName(){
		return "Guest First Name: ";
	}
	public String administratorModifyReservationGuestLastName(){
		return "Guest Last Name: ";
	}
	public String administratorModifyReservationArrivalDate(){
		return "Arrival Date: ";
	}
	public String administratorModifyReservationDepartureDate(){
		return "Departure Date: ";
	}
	public String administratorModifyReservationRooms(){
		return "Reserved Rooms: ";
	}
	public String administratorModifyReservationConsumerProducts(){
		return "Consumed Products: ";
	}
	public String administratorModifyReservationSearchReservation(){
		return "search";
	}
	public String administratorModifyReservationErrorNoReservationFound(){
		return "No Reservation found";
	}
	public String administratorModifyReservationErrorBookingNumber(){
		return "Booking Number must correspond to type Integer";
	}
	public String administratorModifyReservationModifyButton(){
		return "modify";
	}
	public String administratorModifyReservationReservationDetailsHeading(){
		return "Reservation Details";
	}
	public String administratorModifyReservationGuestDetailsHeading(){
		return "Guest Details";
	}
	public String administratorModifyReservationDetailsSuccessful(){
		return "Modify Reservation Details successful";
	}
	public String administratorModifyReservationNumberOfReservationsFound(int number){
		return "Found " + number + " Reservations";
	}
	public String administratorModifyReservationGuestPhoneNumber(){
		return "Phone Number: ";
	}
	public String administratorModifyReservationGuestEmail(){
		return "Email: ";
	}
	public String administratorModifyReservationGuestAddress(){
		return "Address: ";
	}
	public String administratorModifyReservationGuestPassportNr(){
		return "Passport Number: ";
	}
	public String administratorModifyGuestErrorFirstNameEmpty(){
		return "'Guest First Name' is not allowed to be empty";
	}
	public String administratorModifyGuestErrorLastNameEmpty(){
		return "'Guest Last Name' is not allowed to be empty";
	}
	public String administratorModifyGuestErrorEmailNotInRightFormat(){
		return "'Email' is not in right format";
	}
	public String administratorModifyGuestErrorPassportNrEmpty(){
		return "'Passport Number' is not allowed to be empty";
	}
	public String administratorModifyGuestErrorPassportNrNotInRightFormat(){
		return "'Passport Number' is not in right format. It must be from type Integer.";
	}
	public String administratorModifyGuestDetailsSuccessful(){
		return "Modify Guest Details successful";
	}
	public String administratorModifyRoomDetailsHeading(){
		return "Room Details";
	}
	public String administratorModifyRoomDetailsRoomNumber(){
		return "Room Number: ";
	}
	public String administratorModifyRoomDetailsType(){
		return "Room Type: ";
	}
	public String administratorModifyRoomDetailsSmoking(){
		return "Smoking: ";
	}
	public String administratorModifyRoomDetailsOccupied(){
		return "Occupied: ";
	}
	public String administratorDeleteRoomFromReservationSuccessful(){
		return "Delete Room successful";
	}
	public String administratorDeleteRoomFromReservationError(){
		return "Delete Room failed";
	}
	public String administratorAddRoomToReservationErrorNoAvailableRoom(){
		return "No available room found";
	}
	public String administratorAddRoomToReservationErrorFailed(){
		return "Add room to reservatoin failed";
	}
	public String administratorAddRoomToReservationSuccessful(){
		return "Add room to reservation successful";
	}
	public String administratorModifyProductHeading(){
		return "Product Details";
	}
	public String administratorModifyProductProductName(){
		return "Product Name: ";
	}
	public String administratorModifyProductPrice(){
		return "Price: ";
	}
	public String administratorModifyProductSuccessfulyDeletedFromReservation(){
		return "Successfully deleted product from reservation";
	}
	public String administratorModifyProductErrorDeleteFromReservation(){
		return "Delete product from reservation failed. Found no product to delete.";
	}
	public String administratorModifyProductSuccessfulAddProductToReservation(){
		return "Successfully add product to reservation";
	}
	public String administratorModifyRoomHeading(){
		return "Modify Room";
	}
	public String administratorDeleteRoomSuccessful(){
		return "Successfully delete room. ";
	}
	
	public String administratorModifyRoomSelectModifyRoom(){
		return "Modify Room";
	}
	public String administratorModifyRoomSelectAddRoom(){
		return "Add Room";
	}
	public String administratorModifyRoomSearchRoomErrorRoomNumberNotInRightFormat(){
		return "Room Number must be from type Integer. ";
	}
	public String administratorModifyRoomSearchRoomErrorTypeIDNotInRightFormat(){
		return "Room Type ID must be from type Integer. ";
	}
	public String administratorModifyRoomSearchRoomErrorSmokingNotInRightFormat(){
		return "Smoking must be from type Boolean. ";
	}
	public String administratorModifyRoomSearchRoomErrorOccupiedNotInRightFormat(){
		return "Occupied must be from type Boolean. ";
	}
	public String administratorModifyRoomSearchRoomErrorNoRoomsFound(){
		return "No room found";
	}
	public String administratorModifyRoomSearchRoomSuccessfulFoundRoom(int ammount){
		return "Found " + ammount + " room(s)";
	}
	public String administratorModifyRoomErrorRoomNumberMissing(){
		return "Please type in a room number.";
	}
	public String administratorModifyRoomErrorRoomNumberNotInRightFormat(){
		return "Room number must be from type Integer. ";
	}
	public String administratorModifyRoomErrorRoomNotFound(){
		return "Room not found. ";
	}
	public String administratorModifyRoomSuccessful(){
		return "Successfully modified room. ";
	}
	public String administratorAddRoomErrorRoomNumberMissing(){
		return "Please type in a room number.";
	}
	public String administratorAddRoomErrorRoomNumberNotInRightFormat(){
		return "Room number must be from type Integer. ";
	}
	public String administratorAddRoomErrorRoomAllreadyExist(){
		return "Room allready exist. ";
	}
	public String administratorAddRoomSuccessful(Room room){
		return "Successfully add new room (Room number: " + room.getRoomNumber() + ", Type: " + room.getType().getName() + ", Smoking: " + room.isSmoking() + ", Occupied: " + room.isOccupied() + ") ";
	}
	
	public String administratorModifyProductHead(){
		return "Modify Product";
	}
	public String administratorModifyProductSelectModifyProduct(){
		return "Modify Product";
	}
	public String administratorModifyProductSelectAddProduct(){
		return "Add Product";
	}
	public String administratorModifyProductProductID(){
		return "Product ID: ";
	}
	public String administratorModifyProductErrorProductIDNotInRightFormat(){
		return "Product ID must be from type Integer. ";
	}
	public String administratorModifyProductErrorPriceNotInRightFormat(){
		return "Price must be from type Double. ";
	}
	public String administratorModifyProductSuccessfulFoundProduct(int ammount){
		return "Found " + ammount + " product(s) ";
	}
	public String administratorModifyProductErrorNoRoomFound(){
		return "No products found. ";
	}
	public String administratorModifyProductErrorProductNotFound(){
		return "Product not found. ";
	}
	public String administratorModifyProductErrorProductIDMissing(){
		return "Please type in a product ID. ";
	}
	public String administratorModifyProductErrorProductIDAllreadyExist(){
		return "Product with this product ID allready excist. ";
	}
	public String administratorModifyProductErrorProductNameMissing(){
		return "Please type in a product name. ";
	}
	public String administratorModifyProductErrorPriceMissing(){
		return "Please type in a price. ";
	}
	public String administratorModifyProductSuccessful(){
		return "Modify Product successful. ";
	}
	public String administratorAddProductSuccessful(ConsumerProduct product){
		return "Successfully added new product. (Product ID: " + product.getProductID() + ", Product Name: " + product.getName() + ", Price: " + product.getPrice() + ") "; 
	}
	public String administratorDeleteProductSuccessful(){
		return "Successfully delete product. ";
	}
	
	public String administratorRegisterEmployeeHeading(){
		return "Register Employee";
	}
	public String administratorRegisterEmployeeFirstName(){
		return "FIRST NAME: ";
	}
	public String administratorRegisterEmployeeLastName(){
		return "LAST NAME: ";
	}
	public String administratorRegisterEmployeePhone(){
		return "PHONE: ";
	}
	public String administratorRegisterEmployeeEmail(){
		return "EMAIL: ";
	}
	public String administratorRegisterEmployeeUsername(){
		return "USERNAME: ";
	}
	public String administratorRegisterEmployeeIsAdmin(){
		return "ADMIN: ";
	}
	public String administratorRegisterEmployeePassword(){
		return "PASSWORD: ";
	}
	public String administratorRegisterEmployeePasswordRepeat(){
		return "PASSWORD REPEAT: ";
	}
	public String administratorRegisterEmployeeErrorFirstNameMissing(){
		return "Please type in a first name. ";
	}
	public String administratorRegisterEmployeeErrorLastNameMissing(){
		return "Please type in a last name. ";
	}
	public String administratorRegisterEmployeeErrorEmailMissing(){
		return "Please type in an email address. ";
	}
	public String administratorRegisterEmployeeErrorEmailNotInRightFormat(){
		return "Email is not in right format and can not be validated. ";
	}
	public String administratorRegisterEmployeeErrorUsernameMissing(){
		return "Please type in a username. "; 
	}
	public String administratorRegisterEmployeeErrorPasswordMissing(){
		return "Please type password and password repeat. ";
	}
	public String administratorRegisterEmployeeErrorPasswordNotInRightFormat(){
		return "Password not in right format. It must at least consist of 8 characters. ";
	}
	public String administratorRegisterEmployeeErrorPasswordNotMatchingPasswordRepeat(){
		return "Password not matching with password repeat. ";
	}
	public String administratorRegisterEmployeeErrorUsernameAllreadyExist(){
		return "User allready exist. ";
	}
	public String administratorRegisterEmployeeSuccessful(){
		return "Successfully registered new Employee. ";
	}
	
	
	//EMPLOYEE
	public String employeeCancelReservationHeading(){
		return "Cancel Reservation";
	}
	public String employeeCancelReservationCancelButton(){
		return "cancel";
	}
	public String employeeCancelReservationNumber(int i){
		return "Reservation #" + i;
	}
	public String employeeCancelReservationConfirmation(){
		return "Are you sure you want to cancel the following reservation? ";
	}
	public String employeeCancelReservation(){
		return "Reservation";
	}
	public String employeeCancelReservationSuccessful(){
		return "Successfully canceled reservation. ";
	}
}
