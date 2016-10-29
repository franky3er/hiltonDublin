package com.hiltondublin.users;

import com.hiltondublin.languages.English;
import com.hiltondublin.languages.Language;

public abstract class User {
	protected String firstName = null;
	protected String lastName = null;
	protected String phoneNumber = null;
	protected String email = null;
	protected Language language = null;
	
	public User(){
		setLanguage(new English());
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
}
