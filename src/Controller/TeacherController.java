package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.NotesDAO;
import DAO.QuizDAO;
import DAO.TeacherDAO;
import Model.Teacher;

/**
 * Servlet implementation class TeacherController
 */
@WebServlet("/TeacherController")
public class TeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String VIEW = "/teacher/viewTeacher.jsp";
	private static String UPDATE = "/teacher/updateTeacher.jsp";
	private static String UPDATEPASS = "/teacher/updatePass.jsp";
	private static String SEARCH = "/teacher/createTeacher.jsp";
	private static String LIST_NOTES = "/teacher/listNotes.jsp";
	private static String LIST_QUIZ = "/teacher/listQuizzes.jsp";
	private static String LIST = "/teacher/listTeacher.jsp";

	String forward;
	private TeacherDAO dao;
	Teacher teacher = new Teacher();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("listNotes")) {
			HttpSession session = request.getSession(true);
			Integer id = (Integer) session.getAttribute("currentSessionUserID");
			forward = LIST_NOTES;
			request.setAttribute("notes", NotesDAO.getAllNotesById(id));
		} else if (action.equalsIgnoreCase("ListQuiz")) {
			
			HttpSession session = request.getSession(true);
			Integer id = (Integer) session.getAttribute("currentSessionUserID");
			forward = LIST_QUIZ;
			request.setAttribute("quizzes", QuizDAO.getAllQuizById(id));

		} else if (action.equalsIgnoreCase("ViewAccount")) {
			
			String email = request.getParameter("email");
			teacher = TeacherDAO.getTeacherByEmail(email);
			request.setAttribute("teacher", teacher);
			forward = VIEW;
			
		} else if (action.equalsIgnoreCase("updateAccount")) {
			
			String id = request.getParameter("id");
			teacher = TeacherDAO.getTeacherByEmail(id);
			request.setAttribute("teacher", teacher);
			forward = UPDATE;
			
		} else if (action.equalsIgnoreCase("updatePass")) {
			
			forward = UPDATEPASS;
			String email = request.getParameter("email");
			Teacher teacher = TeacherDAO.getTeacherByEmail(email);
			request.setAttribute("teacher", teacher);
			
		} else if (action.equalsIgnoreCase("ListTeacher")) {
			
			request.setAttribute("teachers", TeacherDAO.getAllTeacher());
			forward = LIST;
			
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
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("Register")) {
			String teacherEmail = request.getParameter("email");
			String teacherPassword = request.getParameter("password");
			String teacherName = request.getParameter("name");
			String teacherAddress = request.getParameter("address");
			String teacherPhone = request.getParameter("phone");
			String teacherRole = request.getParameter("role");

			teacher.setTeacherEmail(teacherEmail);
			teacher.setTeacherPassword(teacherPassword);
			teacher.setTeacherName(teacherName);
			teacher.setTeacherAddress(teacherAddress);
			teacher.setTeacherPhone(teacherPhone);
			teacher.setTeacherRole(teacherRole);

			try {
				TeacherDAO.add(teacher);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('New Teacher Registered');");
			pw.println("window.location.href='/e-JAWI/TeacherController?action=ListTeacher';");
			pw.println("</script>");
		} else if (action.equalsIgnoreCase("UpdateTeacher")) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String teacherEmail = request.getParameter("email");
			String teacherName = request.getParameter("name");
			String teacherAddress = request.getParameter("address");
			String teacherPhone = request.getParameter("phone");

			teacher.setId(id);
			teacher.setTeacherEmail(teacherEmail);
			teacher.setTeacherName(teacherName);
			teacher.setTeacherAddress(teacherAddress);
			teacher.setTeacherPhone(teacherPhone);

			try {
				TeacherDAO.updateAccount(teacher);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('Teacher Updated');");
			pw.println("window.location.href='/e-JAWI/TeacherController?action=ListTeacher';");
			pw.println("</script>");
		}

	}

}
