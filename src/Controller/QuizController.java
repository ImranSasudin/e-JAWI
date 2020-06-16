package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.QuestionDAO;
import DAO.QuizDAO;
import Model.Quiz;

/**
 * Servlet implementation class QuizController
 */
@WebServlet("/QuizController")
public class QuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST_QUESTIONS = "/question/listQuestions.jsp";

	String forward;

	Quiz quiz = new Quiz();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuizController() {
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

		if (action.equalsIgnoreCase("ListQuiz")) {

		}
		else if (action.equalsIgnoreCase("ViewQuestion")) {
			String quizID = request.getParameter("quizID");
			
			forward=LIST_QUESTIONS;
			request.setAttribute("questions", QuestionDAO.getAllQuestionById(quizID));
			request.setAttribute("quiz", QuizDAO.getQuizByID(quizID));
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("Acion: " + action);
		
		if (action.equalsIgnoreCase("Add")) {
			String name = request.getParameter("name");
			HttpSession session = request.getSession(true);
			Integer id = (Integer) session.getAttribute("currentSessionUserID");

			quiz.setTeacherId(id);
			quiz.setQuizName(name);

			QuizDAO.add(quiz);

			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('Quiz Added!');");
			pw.println("window.location.href='/e-JAWI/TeacherController?action=ListQuiz';");
			pw.println("</script>");
		}
	}

}
