package Model;

public class Comment {

	private String comment, timestamps, studentName;
	public boolean valid;	
	private Integer studentId,notesId;
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getTimestamps() {
		return timestamps;
	}
	public void setTimestamps(String timestamps) {
		this.timestamps = timestamps;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getNotesId() {
		return notesId;
	}
	public void setNotesId(Integer notesId) {
		this.notesId = notesId;
	}
	
	
	
}
