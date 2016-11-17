package com.hiltondublin.users;

public class Guest extends User {
	private int guestID = -1;
	private String address = null;
	private int passportNr;
	
	public int getGuestID() {
		return guestID;
	}
	public void setGuestID(int guestID) {
		this.guestID = guestID;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getPassportNr() {
		return passportNr;
	}
	public void setPassportNr(int passportNr) {
		this.passportNr = passportNr;
	}
	
}

/*public class DBConnect {
    public static void main(String[args] args) {
        try{
            String host = "";
            String name = "";
            String pass = "";
        
            Connection con = DriverManager.getConnection(host, name, pass);
        }catch( SQLException err){
        System.out.println(err.getMessage( ) );
        }
        
    }
}*/