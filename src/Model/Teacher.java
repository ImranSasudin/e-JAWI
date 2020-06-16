package Model;

public class Teacher {

	String teacherEmail, teacherPassword, teacherName, teacherAddress, teacherPhone, teacherRole;
	Integer id;
	boolean valid;
	
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(String teacherEmail, String teacherPassword, String teacherName, String teacherAddress,
			String teacherPhone, String teacherRole, boolean valid) {
		super();
		this.teacherEmail = teacherEmail;
		this.teacherPassword = teacherPassword;
		this.teacherName = teacherName;
		this.teacherAddress = teacherAddress;
		this.teacherPhone = teacherPhone;
		this.teacherRole = teacherRole;
		this.valid = valid;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public String getTeacherPassword() {
		return teacherPassword;
	}

	public void setTeacherPassword(String teacherPassword) {
		this.teacherPassword = teacherPassword;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherAddress() {
		return teacherAddress;
	}

	public void setTeacherAddress(String teacherAddress) {
		this.teacherAddress = teacherAddress;
	}

	public String getTeacherPhone() {
		return teacherPhone;
	}

	public void setTeacherPhone(String teacherPhone) {
		this.teacherPhone = teacherPhone;
	}

	public String getTeacherRole() {
		return teacherRole;
	}

	public void setTeacherRole(String teacherRole) {
		this.teacherRole = teacherRole;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
