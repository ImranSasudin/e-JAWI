package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionManager;
import Model.Quiz;

public class QuizDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;

	static String quizName;
	static Integer teacherId, quizID, studentId, count;

	// add quiz
	public static void add(Quiz quiz) {

		quizName = quiz.getQuizName();
		teacherId = quiz.getTeacherId();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("insert into quiz (QuizName, teacherId)" + " values(?,?)");
			ps.setString(1, quizName);
			ps.setInt(2, teacherId);
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

	// calculate result
	public static Quiz calculateResult(Integer quizId, Integer studentid) {

		Quiz quiz = new Quiz();
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select count(DISTINCT(q.questionid)) from studentanswer s join "
					+ "questions q where s.choosenanswer = q.answer and q.quizID = ? and s.studentid = ?");

			ps.setInt(1, quizId);
			ps.setInt(2, studentid);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				quiz.setCount(rs.getInt(1));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quiz;
	}

	// add result
	public static void addResult(Quiz quiz) {

		quizID = quiz.getQuizId();
		studentId = quiz.getStudentId();
		count = quiz.getCount();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon
					.prepareStatement("insert into result (quizId, studentID, correctAnswer)" + " values(?,?,?)");
			ps.setInt(1, quizID);
			ps.setInt(2, studentId);
			ps.setInt(3, count);
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

	// get all quizzes
	public static List<Quiz> getAllQuizById(Integer id) {
		List<Quiz> quizzes = new ArrayList<Quiz>();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from quiz where teacherId = '" + id + "'");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Quiz quiz = new Quiz();

				quiz.setQuizId(rs.getInt("QuizId"));
				quiz.setQuizName(rs.getString("QuizName"));
				quiz.setTeacherId(rs.getInt("teacherId"));
				quizzes.add(quiz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quizzes;
	}

	// get all quizzes
	public static List<Quiz> getAllQuizByStudentID(Integer id) {
		List<Quiz> quizzes = new ArrayList<Quiz>();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("SELECT\r\n" + "    q.QuizId,\r\n" + "    QuizName,\r\n"
					+ "    teacherId,\r\n" + "    teacherName,\r\n" + "    COUNT(questionid)\r\n" + "FROM\r\n"
					+ "    quiz q\r\n" + "JOIN teachers t ON\r\n" + "    (q.teacherId = t.id)\r\n"
					+ "JOIN questions ques ON\r\n" + "    (q.quizID = ques.quizID)\r\n" + "WHERE\r\n"
					+ "    q.QuizId not IN(\r\n" + "    SELECT\r\n" + "        quizID from result\r\n" + "    WHERE\r\n"
					+ "        studentID = '" + id + "'\r\n" + ")\r\n" + "GROUP BY\r\n" + "    1,\r\n" + "    2,\r\n"
					+ "    3,\r\n" + "    4");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Quiz quiz = new Quiz();

				quiz.setQuizId(rs.getInt("QuizId"));
				quiz.setQuizName(rs.getString("QuizName"));
				quiz.setTeacherId(rs.getInt("teacherId"));
				quiz.setTeacherName(rs.getString("teacherName"));
				quiz.setCount(rs.getInt(5));
				quizzes.add(quiz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quizzes;
	}

	public static Quiz getQuizByID(String quizID) {
		Quiz quiz = new Quiz();
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon
					.prepareStatement("select * from quiz q join teachers t on (q.teacherId = t.id) where q.quizid=?");

			ps.setString(1, quizID);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				quiz.setQuizId(rs.getInt("QuizId"));
				quiz.setQuizName(rs.getString("QuizName"));
				quiz.setTeacherName(rs.getString("teacherName"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quiz;
	}

	public static List<Quiz> getAnsweredQuiz(Integer studentid) {
		List<Quiz> quizzes = new ArrayList<Quiz>();
		System.out.println(studentid);
		
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon
					.prepareStatement("select quiz.quizName, correctanswer, teacherName, count(q.questionID) from result r join questions q on "
							+ "(r.quizid = q.quizid) join quiz on (quiz.QuizId=q.quizID) join teachers t on (t.id = quiz.teacherId) where r.studentID = ? group by 1,2,3 ");

			ps.setInt(1, studentid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Quiz quiz = new Quiz();
				
				quiz.setQuizName(rs.getString(1));
				quiz.setCorrectanswer(rs.getInt(2));
				quiz.setTeacherName(rs.getString(3));
				quiz.setCount(rs.getInt(4));
				
				quizzes.add(quiz);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quizzes;
	}

}
