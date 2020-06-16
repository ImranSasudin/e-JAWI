package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.QuestionDAO;
import Model.Question;

/**
 * Servlet implementation class QuestionController
 */
@WebServlet("/QuestionController")
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST_QUESTIONS = "/question/listQuestions.jsp";

	String forward;

	Question question = new Question();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("delete")) {
			Integer id = Integer.parseInt(request.getParameter("questionId"));
			Integer quizId = Integer.parseInt(request.getParameter("quizId"));

			QuestionDAO.deleteQuestion(id);

			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('Question Deleted!');");
			pw.println("window.location.href='/e-JAWI/QuizController?action=ViewQuestion&quizID=" + quizId + "';");
			pw.println("</script>");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("Add")) {
			Integer number = Integer.parseInt(request.getParameter("number"));
			String name = request.getParameter("name");
			String answer = request.getParameter("answer");
			String option1 = request.getParameter("option1");
			String option2 = request.getParameter("option2");
			String option3 = request.getParameter("option3");
			String option4 = request.getParameter("option4");
			Integer quizId = Integer.parseInt(request.getParameter("quizId"));
			String correctAnswer = "";

			if (answer.equalsIgnoreCase("1")) {
				correctAnswer = option1;
			} else if (answer.equalsIgnoreCase("2")) {
				correctAnswer = option2;
			} else if (answer.equalsIgnoreCase("3")) {
				correctAnswer = option3;
			} else if (answer.equalsIgnoreCase("4")) {
				correctAnswer = option4;
			}

			question.setQuestionNumber(number);
			question.setQuestionName(name);
			question.setAnswer(correctAnswer);
			question.setOption1(option1);
			question.setOption2(option2);
			question.setOption3(option3);
			question.setOption4(option4);
			question.setQuizId(quizId);

			QuestionDAO.add(question);

			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('Question Added!');");
			pw.println("window.location.href='/e-JAWI/QuizController?action=ViewQuestion&quizID=" + quizId + "';");
			pw.println("</script>");

		}
	}

}
