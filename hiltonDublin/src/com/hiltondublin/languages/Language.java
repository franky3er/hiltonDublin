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
	
	//ADMINISTRATOR
	public String administratorAreaHeading(){
		return "Admin Area";
	}
	public String administratorLoginMessage(){
		return "You need to be logged in as an administrator to get access to the Admin Area!";
	}
}
