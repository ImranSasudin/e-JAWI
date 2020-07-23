package DAO;

import java.sql.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionManager;
import Model.Comment;
import Model.Notes;
import Model.Teacher;

public class NotesDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;

	static int notesID;
	static String notesTitle, notesContent, comments, timestamps, studentName;
	static Integer teacherId, studentId;

	// add notes
	public static void add(Notes notes) {

		notesID = notes.getNotesID();
		notesTitle = notes.getNotesTitle();
		notesContent = notes.getNotesContent();
		teacherId = notes.getTeacherId();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon
					.prepareStatement("insert into note (notesTitle, notesContent, teacherId)" + " values(?,?,?)");
			ps.setString(1, notesTitle);
			ps.setString(2, notesContent);
			ps.setInt(3, teacherId);
			ps.executeUpdate();

			System.out.println("Your Notes Title is " + notesTitle);
			System.out.println("Your Notes Content is " + notesContent);
			// System.out.println("Your Teacher Email is " + teacherEmail);

		}

		catch (Exception ex) {
			System.out.println("failed: An Exception has occured!" + ex);
		}

		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					ps = null;
				}
				if (currentCon != null) {
					try {
						currentCon.close();
					} catch (Exception e_) {
						currentCon = null;
					}
				}
			}
		}
	}

	// add notes
	public static void addComment(Comment comment) {

		notesID = comment.getNotesId();
		studentId = comment.getStudentId();
		comments = comment.getComment();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(
					"insert into comment (student_id, notes_id, comment, timestamps)" + " values(?,?,?,NOW())");
			ps.setInt(1, studentId);
			ps.setInt(2, notesID);
			ps.setString(3, comments);
			ps.executeUpdate();

		}

		catch (Exception ex) {
			System.out.println("failed: An Exception has occured!" + ex);
		}

		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					ps = null;
				}
				if (currentCon != null) {
					try {
						currentCon.close();
					} catch (Exception e_) {
						currentCon = null;
					}
				}
			}
		}
	}

	// list all product
	public static List<Comment> getCommentById(int notesId) {

		List<Comment> comments = new ArrayList<Comment>();

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "select s.studentName, c.comment, DATE_FORMAT(c.timestamps, '%d/%m/%y %I:%i %p') as date from comments c join students s on "
					+ "(c.student_id = s.id) join note n on (n.notesID = c.notes_id) where n.notesID = '" + notesId
					+ "'";
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				Comment comment = new Comment();

				comment.setStudentName(rs.getString(1));
				comment.setComment(rs.getString(2));
				comment.setTimestamps(rs.getString(3));
				// notes.setTeacherEmail(rs.getString("teacherEmail"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comments;
	}

	// list all product
	public static boolean checkComment(int notesId, int studentId) {

		boolean valid = false;
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "select s.studentName, c.comment, DATE_FORMAT(c.timestamps, '%d/%m/%y %I:%i %p') "
					+ "as date from comments c join students s on (c.student_id = s.id) "
					+ "join note n on (n.notesID = c.notes_id) where n.notesID = '"+ notesId +"' "
					+ "and s.id = '"+ studentId +"'";
			
			ResultSet rs = stmt.executeQuery(q);

			if (rs.next()) {
				valid = true;			
			}
			else {
				valid = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return valid;
	}

	// list all product
	public static List<Notes> getAllNote() {

		List<Notes> note = new ArrayList<Notes>();

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "select * from note";
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				Notes notes = new Notes();

				notes.setNotesID(rs.getInt("notesID"));
				notes.setNotesTitle(rs.getString("notesTitle"));
				notes.setNotesContent(rs.getString("notesContent"));
				// notes.setTeacherEmail(rs.getString("teacherEmail"));
				note.add(notes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return note;
	}

	// get all notes
	public static List<Notes> getAllNotes() {
		List<Notes> note = new ArrayList<Notes>();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from note n join teachers t on (n.teacherId = t.id)");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Notes notes = new Notes();

				notes.setNotesID(rs.getInt("notesID"));
				notes.setNotesTitle(rs.getString("notesTitle"));
				notes.setNotesContent(rs.getString("notesContent"));
				notes.setTeacherName(rs.getString("teacherName"));
				note.add(notes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return note;
	}

	// get all notes
	public static List<Notes> getAllNotesById(Integer id) {
		List<Notes> note = new ArrayList<Notes>();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from note where teacherId = '" + id + "'");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Notes notes = new Notes();

				notes.setNotesID(rs.getInt("notesID"));
				notes.setNotesTitle(rs.getString("notesTitle"));
				notes.setNotesContent(rs.getString("notesContent"));
				notes.setTeacherId(rs.getInt("teacherId"));
				note.add(notes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return note;
	}

	public static Notes getNotesByNotesID(int notesID) {
		Notes notes = new Notes();
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon
					.prepareStatement("select * from note n join teachers t on (n.teacherId = t.id) where notesID=?");

			ps.setInt(1, notesID);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				notes.setNotesID(rs.getInt("notesID"));
				notes.setNotesTitle(rs.getString("notesTitle"));
				notes.setNotesContent(rs.getString("notesContent"));
				notes.setTeacherName(rs.getString("teacherName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return notes;
	}

	public Notes getNote(Notes bean) {

		notesID = bean.getNotesID();

		String searchQuery = "select * from note where notesID='" + notesID + "'";

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// if user exists set the isValid variable to true
			if (more) {
				int notesID = rs.getInt("notesID");

				bean.setNotesID(notesID);
				bean.setValid(true);
			}

			else if (!more) {
				System.out.println("Sorry");
				bean.setValid(false);
			}

		}

		catch (Exception ex) {
			System.out.println(" An Exception has occurred! " + ex);
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}

		return bean;
	}

	// update notes
	public void updateNotes(Notes bean) throws NoSuchAlgorithmException {

		notesID = bean.getNotesID();
		notesTitle = bean.getNotesTitle();
		notesContent = bean.getNotesContent();
		// teacherEmail = bean.getTeacherEmail();

		String searchQuery = "UPDATE note SET notesTitle = '" + notesTitle + "', notesContent='" + notesContent
				+ "' WHERE notesID= '" + notesID + "'";

		try {

			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Notes getNotes(Notes bean) {

		notesID = bean.getNotesID();

		String searchQuery = "select * from note where notesID='" + notesID + "'";

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// if id exists set the isValid variable to true
			if (more) {
				int notesID = rs.getInt("notesID");

				bean.setNotesID(notesID);
				bean.setValid(true);
			}

			else if (!more) {
				System.out.println("Sorry");
				bean.setValid(false);
			}

		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}

		return bean;
	}

	// delete notes
	public void deleteNotes(int notesID) {
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("delete from note where notesID=?");
			ps.setInt(1, notesID);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
