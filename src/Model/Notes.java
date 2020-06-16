package Model;

public class Notes {

	private int notesID;
	private String notesTitle, notesContent;
	public boolean valid;	
//	private String teacherEmail;
	
	public Notes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notes(int notesID, String notesTitle, String notesContent, boolean valid) {
		super();
		this.notesID = notesID;
		this.notesTitle = notesTitle;
		this.notesContent = notesContent;
	//	this.teacherEmail = teacherEmail;		
		this.valid = valid;
	}

	public int getNotesID() {
		return notesID;
	}

	public void setNotesID(int notesID) {
		this.notesID = notesID;
	}

/**	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}
*/
	
	public String getNotesTitle() {
		return notesTitle;
	}

	public void setNotesTitle(String notesTitle) {
		this.notesTitle = notesTitle;
	}

	public String getNotesContent() {
		return notesContent;
	}

	public void setNotesContent(String notesContent) {
		this.notesContent = notesContent;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
