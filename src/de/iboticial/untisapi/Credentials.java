package de.iboticial.untisapi;

public class Credentials {

	private String username;
	private String password;
	private String schoolname;

	public Credentials(String newUsername, String newPassword, String newSchoolname) {

		username = newUsername;
		password = newPassword;
		schoolname = newSchoolname;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

}
