package table;

public class User {

	private Integer userID;
	private String userName;
	private String mobilePhone;
	private String fixedPhone;
	private String userEmail;
	
	public Integer getuserID() {
		return userID;
	}
	public void setuserID(Integer userID) {
		this.userID = userID;
	}
	public String getuserName() {
		return userName;
	}
	public void setuserName(String userName) {
		this.userName = userName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getFixedPhone() {
		return fixedPhone;
	}
	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}
	public String getuserEmail() {
		return userEmail;
	}
	public void setuserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
}
