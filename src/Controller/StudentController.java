package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ParentDAO;
import DAO.QuizDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;
import Model.Quiz;
import Model.Student;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String VIEW ="/student/viewStudent.jsp";
    private static String UPDATE = "/student/updateStudent.jsp";
    //private static String UPDATEPASS = "/student/updatePass.jsp";
    private static String SEARCH = "/student/createStudent.jsp";
    private static String RESULT = "/student/listResult.jsp";
    private static String LIST = "/student/listStudent.jsp";
   
	String forward;
	private StudentDAO dao;
	Student student = new Student();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentController() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("ViewAccount")) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			student = StudentDAO.getStudentByEmail(id);
			request.setAttribute("student", student);
			forward = VIEW;
		}
		else if(action.equalsIgnoreCase("updateAccount")) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			student = StudentDAO.getStudentByEmail(id);
			request.setAttribute("parents", ParentDAO.getAllParent());
			request.setAttribute("student", student);
			forward = UPDATE;
		}
		else if (action.equalsIgnoreCase("ListQuiz")) {
			HttpSession session = request.getSession(true);
			Integer id = (Integer) session.getAttribute("currentSessionUserID");
			System.out.println(id);
			forward = RESULT;
			
			
			request.setAttribute("quizzes", QuizDAO.getAnsweredQuiz(id));

		}
		else if (action.equalsIgnoreCase("ListStudent")) {
			
			request.setAttribute("students", StudentDAO.getAllStudent());
			request.setAttribute("parents", ParentDAO.getAllParent());
			forward = LIST;
			
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("Register")) {
			Integer parentID = Integer.parseInt(request.getParameter("parent"));
			String studentEmail = request.getParameter("email");
			String studentPassword = request.getParameter("password");
			String studentName = request.getParameter("name");
			String studentAddress = request.getParameter("address");
			String studentPhone = request.getParameter("phone");
			
			student.setParentID(parentID);
			student.setStudentEmail(studentEmail);
			student.setStudentPassword(studentPassword);
			student.setStudentName(studentName);
			student.setStudentAddress(studentAddress);
			student.setStudentPhone(studentPhone);
			
			try {
				StudentDAO.add(student);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('New Student Registered');");
			pw.println("window.location.href='/e-JAWI/StudentController?action=ListStudent';");
			pw.println("</script>");
		}
		
		else if (action.equalsIgnoreCase("UpdateStudent")) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			Integer parentID = Integer.parseInt(request.getParameter("parent"));
			String studentEmail = request.getParameter("email");
			String studentName = request.getParameter("name");
			String studentAddress = request.getParameter("address");
			String studentPhone = request.getParameter("phone");
			
			student.setParentID(parentID);
			student.setStudentID(id);
			student.setStudentEmail(studentEmail);
			student.setStudentName(studentName);
			student.setStudentAddress(studentAddress);
			student.setStudentPhone(studentPhone);
			
			try {
				StudentDAO.updateAccount(student);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('Student Updated');");
			pw.println("window.location.href='/e-JAWI/StudentController?action=ListStudent';");
			pw.println("</script>");
		}
			
	
	}

}
