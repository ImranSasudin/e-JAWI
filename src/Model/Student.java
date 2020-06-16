package Model;

public class Student {

	String studentEmail, studentPassword, studentName, studentAddress, studentPhone;
	boolean valid;
	
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