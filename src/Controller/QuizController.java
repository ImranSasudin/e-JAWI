package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.QuestionDAO;
import DAO.QuizDAO;
import DAO.StudentDAO;
import Model.Quiz;
import Model.Student;


/**
 * Servlet implementation class QuizController
 */
@WebServlet("/QuizController")
public class QuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST_QUESTIONS = "/question/listQuestions.jsp";
	private static String LIST_QUIZ = "/question/listQuizzes.jsp";
	private static String ANSWER_QUIZ = "/question/answerQuiz.jsp";
	private static String UNANSWERED = "/question/unansweredQ.jsp";

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
			HttpSession session = request.getSession(true);
			Integer id = (Integer) session.getAttribute("currentSessionUserID");
			forward = LIST_QUIZ;
			request.setAttribute("quizzes", QuizDAO.getAllQuizByStudentID(id));
			
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);

		}
		else if (action.equalsIgnoreCase("ViewQuestion")) {
			String quizID = request.getParameter("quizID");
			
			forward=LIST_QUESTIONS;
			request.setAttribute("questions", QuestionDAO.getAllQuestionById(quizID));
			request.setAttribute("quiz", QuizDAO.getQuizByID(quizID));
			
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
		else if (action.equalsIgnoreCase("TakeTest")) {
			String quizID = request.getParameter("quizID");
			
			forward=ANSWER_QUIZ;
			request.setAttribute("questions", QuestionDAO.getAllQuestionById(quizID));
			request.setAttribute("question", QuestionDAO.getFirstQuestion(quizID));
			request.setAttribute("quiz", QuizDAO.getQuizByID(quizID));
			
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
		else if (action.equalsIgnoreCase("unansweredQ")) {
			HttpSession session = request.getSession(true);
			Integer id = (Integer) session.getAttribute("currentSessionUserID");
			Integer quizId = Integer.parseInt(request.getParameter("quizId"));
			String quizIdS = request.getParameter("quizId");
			
			forward=UNANSWERED;
			request.setAttribute("questions", QuestionDAO.getAllUnansweredQuestion(id, quizId));
			request.setAttribute("quiz", QuizDAO.getQuizByID(quizIdS));
			
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
		else if (action.equalsIgnoreCase("submit")) {
			Student student = new Student();
			HttpSession session = request.getSession(true);
			Integer id = (Integer) session.getAttribute("currentSessionUserID");
			Integer quizId = Integer.parseInt(request.getParameter("quizId"));
			
			quiz = QuizDAO.calculateResult(quizId, id);
			
			quiz.setStudentId(id);
			quiz.setQuizId(quizId);
			
			QuizDAO.addResult(quiz);
			
			student = StudentDAO.getStudentByEmail(id);
			
			String fromEmail = "saparitbilal@gmail.com"; //requires valid gmail id
			String password = "paritbilal100";
			
			System.out.println("TLSEmail Start");
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
			props.put("mail.smtp.port", "587"); //TLS Port
			props.put("mail.smtp.auth", "true"); //enable authentication
			props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
			
	                //create Authenticator object to pass in Session.getInstance argument
			Authenticator auth = new Authenticator() {
				//override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			};
			Session sessions = Session.getInstance(props, auth);
			
			String messages = "Tahniah! Anak anda telah berjaya menjawab soalan kuiz. Sila rujuk sistem untuk melihat keputusan pelajar. Terima kasih";;
			System.out.println(messages);
			
			EmailUtil.sendEmail(sessions, student.getParentEmail(),"Kuiz Jawi", messages);
			
			
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('Quiz Submitted!');");
			pw.println("window.location.href='/e-JAWI/QuizController?action=ListQuiz';");
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
