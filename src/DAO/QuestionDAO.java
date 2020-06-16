package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionManager;
import Model.Question;

public class QuestionDAO {

	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;

	static String questionName, option1, option2, option3, option4, answer;
	static Integer quizId, questionId, questionNumber;

	// add question
	public static void add(Question question) {

		questionName = question.getQuestionName();
		questionNumber = question.getQuestionNumber();
		option1 = question.getOption1();
		option2 = question.getOption2();
		option3 = question.getOption3();
		option4 = question.getOption4();
		answer = question.getAnswer();
		quizId = question.getQuizId();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(
					"insert into questions (questionName, questionNumber, option1, option2, option3, option4, answer, quizId)"
							+ " values(?,?,?,?,?,?,?,?)");
			ps.setString(1, questionName);
			ps.setInt(2, questionNumber);
			ps.setString(3, option1);
			ps.setString(4, option2);
			ps.setString(5, option3);
			ps.setString(6, option4);
			ps.setString(7, answer);
			ps.setInt(8, quizId);
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

	// get all questions
	public static List<Question> getAllQuestionById(String id) {
		List<Question> questions = new ArrayList<Question>();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from questions where quizId = '" + id + "'");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Question question = new Question();

				question.setQuestionId(rs.getInt("questionID"));
				question.setQuizId(rs.getInt("quizId"));
				question.setQuestionName(rs.getString("questionName"));
				question.setQuestionNumber(rs.getInt("questionNumber"));
				question.setOption1(rs.getString("option1"));
				question.setOption2(rs.getString("option2"));
				question.setOption3(rs.getString("option3"));
				question.setOption4(rs.getString("option4"));
				question.setAnswer(rs.getString("answer"));
				questions.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return questions;
	}

	// delete question
	public static void deleteQuestion(int questionID) {
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("delete from questions where questionID=?");
			ps.setInt(1, questionID);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
