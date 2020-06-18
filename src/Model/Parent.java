package Model;

public class Parent {

	String parentEmail, parentPassword, parentName, parentAddress, parentPhone;
	Integer id;
	boolean valid;
	
	public Parent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Parent(String parentEmail, String parentPassword, String parentName, String parentAddress,
			String parentPhone, boolean valid) {
		
		super();
		this.parentEmail = parentEmail;
		this.parentPassword = parentPassword;
		this.parentName = parentName;
		this.parentAddress = parentAddress;
		this.parentPhone = parentPhone;
		this.valid = valid;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public String getParentPassword() {
		return parentPassword;
	}

	public void setParentPassword(String parentPassword) {
		this.parentPassword = parentPassword;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentAddress() {
		return parentAddress;
	}

	public void setParentAddress(String parentAddress) {
		this.parentAddress = parentAddress;
	}

	public String getParentPhone() {
		return parentPhone;
	}

	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}