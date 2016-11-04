package com.hiltondublin.languages;

public class Korean extends Language{
	public Korean(){
		setName("Korean");
	}
	
	//NAVIGATIONSLIDE
	public String navigationSlideHome(){
		return "홈페이지";
	}
		
	public String navigationSlideGuest(){
		return "게스트";
	}
		
	public String navigationSlideEmployee(){
		return "직원";
	}
		
	public String navigationSlideAdmin(){
		return "관리자";
	}
		
	public String navigationSlideDisability(){
		return "장애우";
	}
		
	public String navigationSlideLoginWrongUsername(){
		return "로그인 실패!";
	}
		
	public String navigationSlideLoginWrongPassword(){
		return "로그인 실패!";
	}
		
	public String navigationSlideLoginUsername(){
		return "고객이름";
	}
		
	public String navigationSlideLoginPassword(){
		return "비밀번호";
	}
		
	public String navigationSlideLoginLogin(){
		return "로그인";
	}
		
	public String navigationSlideLoginLogout(){
		return "로그아웃";
	}
		
	public String navigationSlideLoginLoggedInAs(){
		return "Logged in as";
	}
	//HOME
	public String homeWelcome(){
		return "힐튼 더블린에 오신 것을 환영합니다!";
	}
		
	//GUEST
	public String guestWelcome(){
		return "게스트 페이지에 오신 것을 환영합니다!";
	}
		
	public String guestRatingErrorMessageFirstName(){
		return "이름을 입력해주세요!";
	}
		
	public String guestRatingErrorMessageLastName(){
		return "성을 입력해주세요!";
	}
		
	public String guestRatingErrorMessageEmail(){
		return "이메일 주소를 입력해 주세요!";
	}
		
	public String guestRatingFirstName(){
		return "이름:";
	}
		
	public String guestRatingLastName(){
		return "성:";
	}
		
	public String guestRatingEmail(){
		return "Email:";
	}
		
	public String guestRatingRoomType(){
		return "고객님 방 종류: ";
	}
		
	public String guestRatingRating(){
		return "고객님 평가 등급: ";
	}
		
	public String guestRatingComment(){
		return "고객님 코멘트: ";
	}
		
	public String guestRatingCommentDetail(){
		return "고객님께서 사용하신 방에 대해 평가 부탁드립니다...";
	}
		
	public String guestRatingReview(){
		return "고객님 리뷰";
	}
		
	public String guestRatingRoomInformation(){
		return "등급";
	}
		
	public String guestRatingGuestInformation(){
		return "고객 정보";
	}
		
	public String guestRatingThanksForYourReview(){
		return "고객님의 정성어린 사용후기 감사합니다.";
	}
		
	public String guestRatingThanksRoomType(){
		return "고객님 방 종류: ";
	}
		
	public String guestRatingThanksRating(){
		return "고객님 등급: ";
	}
		
	public String guestRatingThanksComment(){
		return "고객님 코멘트: ";
	}
		
	public String guestRatingRatingBestGrade(){
		return "* 5 -> 최고";
	}
		
	public String guestRatingRatingWorstGrade(){
		return "* 1 -> 최악";
	}
		
	//EMPLOYEE
	public String employeeAreaHeading(){
		return "직원 공간";
	}
	
	public String employeeLoginMessage(){
		return "직원 공간에 접근하기 위해서는 로그인이 필요합니다!";
	}
	
	//ADMINISTRATOR
	public String administratorAreaHeading(){
		return "관리자 모드";
	}
	public String administratorLoginMessage(){
		return "관리자 모드에 접근하기 위해서는 로그인이 필요합니다!";
	}
}
