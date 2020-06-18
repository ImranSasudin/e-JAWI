package Model;

public class Student {

	String studentEmail, studentPassword, studentName, studentAddress, studentPhone, parentName, parentEmail;
	boolean valid;
	Integer studentID, parentID;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String studentEmail, String studentPassword, String studentName, String studentAddress,
			String studentPhone, boolean valid) {
		
		super();
		this.studentEmail = studentEmail;
		this.studentPassword = studentPassword;
		this.studentName = studentName;
		this.studentAddress = studentAddress;
		this.studentPhone = studentPhone;
		this.valid = valid;
	}
	
	

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}