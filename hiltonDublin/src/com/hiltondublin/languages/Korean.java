package com.hiltondublin.languages;

import java.util.List;

import com.hiltondublin.classes.Room;

public class Korean extends Language{
	public Korean(){
		setName("Korean");
	}
	//GENERAL
	public String yes(){
		return "";
	}
	public String no(){
		return "아니";
	}
	public String delete(){
		return "삭제";
	}
	public String add(){
		return "추가";
	}
	public String search(){
		return "검색";
	}
	//NAVIGATIONSLIDE
	public String navigationSlideHome(){
		return "홈페이지";
	}
		
	public String navigationSlideGuest(){
		return "고객";
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
		return "고객님 페이지에 오신 것을 환영합니다!";
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
	public String employeeCheckoutButton(){
		return "체크아웃";
	}
	public String employeeCheckoutRoomNumber(){
		return "방번호: ";
	}
	public String employeeCheckoutErrorRoomNumberNotInteger(String roomNumber){
		return "이런 방번호 '" + roomNumber + "' 존재하지 않습니다! 숫자를 입력해주세요!";
	}
	public String employeeCheckoutErrorRoomNumberNull(){
		return "방번호를 반드시 입력해주세요!";
	}
	public String employeeCheckoutErrorRoomNumberEmpty(){
		return "방번호를 반드시 입력해주세요!";
	}
	public String employeeCheckoutErrorNoSuitableReservation(String roomNumber){
		return "입력하신 방번호는 예약이 되어 있지 않습니다 '" + roomNumber + "'!";
	}
	public String employeeCheckoutErrorAllreadyCheckedOut(String roomNumber){
		return "방번호 '" + roomNumber + "' 이미 체크아웃 되었습니다!";
	}
	public String employeeCheckoutSuccessfullyCheckedOut(String roomNumber){
		return "방번호 '" + roomNumber + "' 성공적으로 체크아웃되엇습니다!";
	}
	public String employeeCheckoutAllRoomsCheckedOut(){
		return "모든 방이 체크아웃 되었습니다!";
	}
	public String employeeCheckoutOccupiedRooms(List<Room> occupiedRooms){
		String message = "방번호 ";
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
		message += " 여전히 체크아웃이 되어야합니다!";
		
		return message;
	}
	public String employeeCheckoutBillHeadline(){
		return "계산서";
	}
	public String employeeCheckoutBillTotal(){
		return "총합계";
	}
	public String employeeCheckoutHeading(){
		return "체크아웃";
	}
	public String employeeCheckoutVerificationHeading(){
		return "검증";
	}
	public String employeeCheckoutVerificationQuestion(String firstName, String lastName){
		return "고객님의 이름이 맞습니까? '" + firstName + " " + lastName + "'  ";
	}
	
	public String employeeChargeProductSuccessfully(){
		return "선택하신 상품이 성공적으로 추가되었습니다!";
	}
	public String employeeChargeProductHeading(){
		return "요금추가";
	}
	public String employeeChargeProductProduct(){
		return "상품: ";
	}
	public String employeeChargeProductCharge(){
		return "요금";
	}
	
	//ADMINISTRATOR
	public String administratorAreaHeading(){
		return "관리자 모드";
	}
	public String administratorLoginMessage(){
		return "관리자 모드에 접근하기 위해서는 로그인이 필요합니다!";
	}
	public String administratorModifyReservationHeading(){
		return "예약정보 수정";
	}
	public String administratorModifyReservationBookingNummber(){
		return "예약번호:";
	}
	public String administratorModifyReservationGuestFirstName(){
		return "고객님 이름: ";
	}
	public String administratorModifyReservationGuestLastName(){
		return "고객님 성: ";
	}
	public String administratorModifyReservationArrivalDate(){
		return "Arrival Date: ";
	}
	public String administratorModifyReservationDepartureDate(){
		return "출발 날짜: ";
	}
	public String administratorModifyReservationRooms(){
		return "예약된 방: ";
	}
	public String administratorModifyReservationConsumerProducts(){
		return "소비된 상품들: ";
	}
	public String administratorModifyReservationSearchReservation(){
		return "검색";
	}
	public String administratorModifyReservationErrorNoReservationFound(){
		return "예약정보가 없습니다.";
	}
	public String administratorModifyReservationErrorBookingNumber(){
		return "예약번호는 숫자입니다.";
	}
	public String administratorModifyReservationModifyButton(){
		return "수정";
	}
	public String administratorModifyReservationReservationDetailsHeading(){
		return "예약 상세 정보";
	}
	public String administratorModifyReservationGuestDetailsHeading(){
		return "고객 상세 정보";
	}
	public String administratorModifyReservationDetailsSuccessful(){
		return "예약 정보가 성공적으로 수정되었습니다.";
	}
	public String administratorModifyReservationNumberOfReservationsFound(int number){
		return number + "개의 예약이 발견되었습니다.";
	}
	public String administratorModifyReservationGuestPhoneNumber(){
		return "전화번호: ";
	}
	public String administratorModifyReservationGuestEmail(){
		return "Email: ";
	}
	public String administratorModifyReservationGuestAddress(){
		return "주소: ";
	}
	public String administratorModifyReservationGuestPassportNr(){
		return "여권 번호: ";
	}
	public String administratorModifyGuestErrorFirstNameEmpty(){
		return "'고객님 이름'은 반드시 입력해주시기 바랍니다.";
	}
	public String administratorModifyGuestErrorLastNameEmpty(){
		return "'고객님 성'을 반드시 입력해주시기 바랍니다.";
	}
	public String administratorModifyGuestErrorEmailNotInRightFormat(){
		return "유효한 이메일을 입력해 주시기 바랍니다.";
	}
	public String administratorModifyGuestErrorPassportNrEmpty(){
		return "'여권번호'를 반드시 입력해 주시기 바랍니다.";
	}
	public String administratorModifyGuestErrorPassportNrNotInRightFormat(){
		return "잘못된 '여권번호'입니다.";
	}
	public String administratorModifyGuestDetailsSuccessful(){
		return "고객 정보가 성공적으로 변경되었습니다.";
	}
	public String administratorModifyRoomDetailsHeading(){
		return "방 상세 정보";
	}
	public String administratorModifyRoomDetailsRoomNumber(){
		return "방번호: ";
	}
	public String administratorModifyRoomDetailsType(){
		return "방종류: ";
	}
	public String administratorModifyRoomDetailsSmoking(){
		return "흡연유무: ";
	}
	public String administratorModifyRoomDetailsOccupied(){
		return "Occupied: ";
	}
	public String administratorDeleteRoomFromReservationSuccessful(){
		return "방이 성공적으로 삭제되었습니다.";
	}
	public String administratorDeleteRoomFromReservationError(){
		return "방을 삭제하지 못했습니다.";
	}
	public String administratorAddRoomToReservationErrorNoAvailableRoom(){
		return "추가할 수 있는 방이 없습니다.";
	}
	public String administratorAddRoomToReservationErrorFailed(){
		return "방 추가에 실패하였습니다.";
	}
	public String administratorAddRoomToReservationSuccessful(){
		return "방 추가에 성공하였습니다.";
	}
	public String administratorModifyProductHeading(){
		return "상품 상세 정보";
	}
	public String administratorModifyProductProductName(){
		return "상품 이름: ";
	}
	public String administratorModifyProductPrice(){
		return "가격: ";
	}
	public String administratorModifyProductSuccessfulyDeletedFromReservation(){
		return "상품을 성공적으로 삭제하였습니다.";
	}
	public String administratorModifyProductErrorDeleteFromReservation(){
		return "상품을 삭제하지 못했습니다. 삭제할 수 있는 상품을 발견하지 못했습니다.";
	}
	public String administratorModifyProductSuccessfulAddProductToReservation(){
		return "상품을 성공적으로 추가하였습니다.";
	}
	public String administratorModifyRoomHeading(){
		return "방 정보 수정";
	}
}
