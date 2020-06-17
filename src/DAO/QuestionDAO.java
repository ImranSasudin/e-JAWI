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
	static Integer quizId, questionId, questionNumber, studentId;

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

	// add question
	public static void addStudentAnswer(Question question) {

		studentId = question.getStudentId();
		questionId = question.getQuestionId();
		answer = question.getAnswer();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(
					"insert into studentanswer (studentid, questionid, choosenanswer)" + " values(?,?,?)");
			ps.setInt(1, studentId);
			ps.setInt(2, questionId);
			ps.setString(3, answer);
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

	// add question
	public static void updateStudentAnswer(Question question) {

		studentId = question.getStudentId();
		questionId = question.getQuestionId();
		answer = question.getAnswer();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon
					.prepareStatement("update studentanswer set choosenanswer=? where studentid=? and questionId=? ");
			ps.setInt(2, studentId);
			ps.setInt(3, questionId);
			ps.setString(1, answer);
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

	// get all questions
	public static List<Question> getAllUnansweredQuestion(Integer studentid, Integer quizId) {
		List<Question> questions = new ArrayList<Question>();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select DISTINCT(questionNumber) from questions q join studentanswer s "
					+ "where studentid = '"+ studentid +"' and q.questionID not in (select questionID from studentanswer where studentid = '"+ studentid +"') and quizID = '"+ quizId +"'");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Question question = new Question();

				question.setQuestionNumber(rs.getInt("questionNumber"));

				questions.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return questions;
	}

	// get 1st questions
	public static Question getFirstQuestion(String id) {
		Question question = new Question();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon
					.prepareStatement("select * from questions where quizId = '" + id + "' and questionNumber = 1");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				question.setQuestionId(rs.getInt("questionID"));
				question.setQuizId(rs.getInt("quizId"));
				question.setQuestionName(rs.getString("questionName"));
				question.setQuestionNumber(rs.getInt("questionNumber"));
				question.setOption1(rs.getString("option1"));
				question.setOption2(rs.getString("option2"));
				question.setOption3(rs.getString("option3"));
				question.setOption4(rs.getString("option4"));
				question.setAnswer(rs.getString("answer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return question;
	}

	// get last questions
	public static Integer getLastQuestion(String id) {
		Integer lastQ = null;
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(
					"select * from questions where quizId = '" + id + "' order by questionNumber desc limit 1");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				lastQ = rs.getInt("questionNumber");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastQ;
	}

	// check student already answer that Q or not
	public static Question CheckAnswer(Integer studentID, Integer questionNumber) {
		Question question = new Question();
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon
					.prepareStatement("select * from studentanswer s join questions q on (s.questionid = q.questionID) "
							+ "where studentid = '" + studentID + "' and questionnumber = '" + questionNumber + "'");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				question.setValid(true);
				question.setStudentId(rs.getInt("studentid"));
				question.setQuestionId(rs.getInt("questionid"));
				question.setAnswer(rs.getString("choosenanswer"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return question;
	}

	// get 1st questions
	public static Question getNextQuestion(Integer id, Integer questionNumber) {
		Question question = new Question();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from questions where quizId = '" + id
					+ "' and questionNumber = '" + questionNumber + "'");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				question.setQuestionId(rs.getInt("questionID"));
				question.setQuizId(rs.getInt("quizId"));
				question.setQuestionName(rs.getString("questionName"));
				question.setQuestionNumber(rs.getInt("questionNumber"));
				question.setOption1(rs.getString("option1"));
				question.setOption2(rs.getString("option2"));
				question.setOption3(rs.getString("option3"));
				question.setOption4(rs.getString("option4"));
				question.setAnswer(rs.getString("answer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return question;
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
