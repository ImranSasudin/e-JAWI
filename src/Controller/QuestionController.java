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
import Model.Question;

/**
 * Servlet implementation class QuestionController
 */
@WebServlet("/QuestionController")
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST_QUESTIONS = "/question/listQuestions.jsp";
	private static String ANSWER_QUIZ = "/question/answerQuiz.jsp";
	private static String UPDATE_QUIZ = "/question/updateQuiz.jsp";

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

		else if (action.equalsIgnoreCase("nextQ")) {
			Question question = new Question();
			HttpSession session = request.getSession(true);
			Integer studentId = (Integer) session.getAttribute("currentSessionUserID");
			Integer number = Integer.parseInt(request.getParameter("number"));
			Integer quizId = Integer.parseInt(request.getParameter("quizId"));
			String quizIdS = request.getParameter("quizId");

			question = QuestionDAO.CheckAnswer(studentId, number, quizId);

			

			if (question.isValid()) { // already answered
				// rediret to update
				forward = UPDATE_QUIZ;
				request.setAttribute("choosenanswer", question.getAnswer());
			} else {
				forward = ANSWER_QUIZ;
			}

			request.setAttribute("questions", QuestionDAO.getAllQuestionById(quizIdS));
			request.setAttribute("question", QuestionDAO.getNextQuestion(quizId, number));
			request.setAttribute("quiz", QuizDAO.getQuizByID(quizIdS));
			
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);

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

		else if (action.equalsIgnoreCase("SaveAnswer")) {
			HttpSession session = request.getSession(true);
			Integer studentId = (Integer) session.getAttribute("currentSessionUserID");

			Integer number = Integer.parseInt(request.getParameter("questionNumber"));
			Integer id = Integer.parseInt(request.getParameter("questionID"));
			String answer = request.getParameter("answer");
			Integer quizId = Integer.parseInt(request.getParameter("quizId"));
			String quizIdS = request.getParameter("quizId");

			question.setAnswer(answer);
			question.setQuestionId(id);
			question.setStudentId(studentId);

			QuestionDAO.addStudentAnswer(question);

			number = number + 1; // next question number

			Integer lastQ = QuestionDAO.getLastQuestion(quizIdS); // get last qNum of that quiz

			if (lastQ < number) { // at last question
				response.sendRedirect("/e-JAWI/QuizController?action=unansweredQ&quizId=" + quizId);
			}else {
				response.sendRedirect("/e-JAWI/QuestionController?action=nextQ&quizId=" + quizId + "&number=" + number);
			}


		} else if (action.equalsIgnoreCase("UpdateAnswer")) {
			HttpSession session = request.getSession(true);
			Integer studentId = (Integer) session.getAttribute("currentSessionUserID");

			Integer number = Integer.parseInt(request.getParameter("questionNumber"));
			Integer id = Integer.parseInt(request.getParameter("questionID"));
			String answer = request.getParameter("answer");
			Integer quizId = Integer.parseInt(request.getParameter("quizId"));
			String quizIdS = request.getParameter("quizId");

			question.setAnswer(answer);
			question.setQuestionId(id);
			question.setStudentId(studentId);

			QuestionDAO.updateStudentAnswer(question);

			number = number + 1; // next question number

			Integer lastQ = QuestionDAO.getLastQuestion(quizIdS); // get last qNum of that quiz

			if (lastQ < number) { // at last question
				response.sendRedirect("/e-JAWI/QuizController?action=unansweredQ&quizId=" + quizId);
			}
			else {
				response.sendRedirect("/e-JAWI/QuestionController?action=nextQ&quizId=" + quizId + "&number=" + number);
			}


		}
	}

}
