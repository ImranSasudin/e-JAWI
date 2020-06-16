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
	static Integer teacherId, quizID;

	// add quiz
	public static void add(Quiz quiz) {

		quizName = quiz.getQuizName();
		teacherId = quiz.getTeacherId();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon
					.prepareStatement("insert into quiz (QuizName, teacherId)" + " values(?,?)");
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
	
	public static Quiz getQuizByID(String quizID) {
		Quiz quiz = new Quiz();
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from quiz q join teachers t on (q.teacherId = t.id) where q.quizid=?");

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

}
