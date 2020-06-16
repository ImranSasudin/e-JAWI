package Model;

public class Notes {

	private int notesID;
	private String notesTitle, notesContent, teacherName;
	public boolean valid;	
	private Integer teacherId;
	
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

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	
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

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
