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

import DAO.TeacherDAO;
import Model.Teacher;
import DAO.StudentDAO;
import Model.Student;
import DAO.ParentDAO;
import Model.Parent;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TeacherDAO teacherdao;
	private StudentDAO Studentdao;
	private ParentDAO Parentdao;
    HttpServletRequest request;
    HttpServletResponse response;
    String forward="";
    String action="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        teacherdao = new TeacherDAO();
        Studentdao = new StudentDAO();
        Parentdao = new ParentDAO();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	action = request.getParameter("action");
		HttpSession session = request.getSession(true);
		if (action.equalsIgnoreCase("Logout")){ //logout
		session.setAttribute("currentSessionUserID", null);
		session.setAttribute("currentSessionUserName", null);
		session.setAttribute("currentSessionUser", null);
		session.setAttribute("currentSessionUserRole", null);
		session.invalidate();
		
		response.setContentType("text/html");
	      PrintWriter pw = response.getWriter();
	      pw.println("<script>");
	      pw.println("alert('Logout Success');");
	      pw.println("window.location.href='/e-JAWI/index.jsp';");
	      pw.println("</script>");
		}
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("loginParent")) {
            try {
        			
            	String parentEmail = request.getParameter("email");
            	String parentPassword = request.getParameter("password");
            		
            	Parent parent = new Parent();
            	parent.setParentEmail(parentEmail);
            	parent.setParentPassword(parentPassword);

           		parent = ParentDAO.login(parent);

        			if(parent.isValid())
        			{
        				//student = ParentDAO.getParentByEmail(email);
        				HttpSession session = request.getSession(true);
        				session.setAttribute("currentSessionUser", parent.getParentEmail());
        				session.setAttribute("currentSessionUserName", parent.getParentName());
        				response.setContentType("text/html");
        			      PrintWriter pw = response.getWriter();
        			      pw.println("<script>");
        			      pw.println("alert('Login Successful');");
        			      pw.println("window.location.href='/e-JAWI/ParentDashboard.jsp';");
        			      pw.println("</script>");
        			}
        			else
        			{
        				response.setContentType("text/html");
        			      PrintWriter pw = response.getWriter();
        			      pw.println("<script>");
        			      pw.println("alert('Incorrect Email or Password');");
        			      pw.println("window.location.href='/e-JAWI/index.jsp';");
        			      pw.println("</script>");
        			}
        			
        		}

        		catch (Throwable ex) {
        			System.out.println(ex);
        		}
        	}
		
		else if (action.equalsIgnoreCase("loginStudent")) {
            try {
        			
            	String studentEmail = request.getParameter("email");
            	String studentPassword = request.getParameter("password");
            		
            	Student student = new Student();
            	student.setStudentEmail(studentEmail);
           		student.setStudentPassword(studentPassword);

           		student = StudentDAO.login(student);

        			if(student.isValid())
        			{
        				//student = StudentDAO.getStudentByEmail(email);
        				HttpSession session = request.getSession(true);
        				session.setAttribute("currentSessionUser", student.getStudentEmail());
        				session.setAttribute("currentSessionUserID", student.getStudentID());
        				session.setAttribute("currentSessionUserName", student.getStudentName());
        				session.setAttribute("currentSessionUserRole", "Student");
        				
        				response.setContentType("text/html");
        			      PrintWriter pw = response.getWriter();
        			      pw.println("<script>");
        			      pw.println("alert('Login Successful');");
        			      pw.println("window.location.href='/e-JAWI/StudentDashboard.jsp';");
        			      pw.println("</script>");
        			}
        			else
        			{
        				response.setContentType("text/html");
        			      PrintWriter pw = response.getWriter();
        			      pw.println("<script>");
        			      pw.println("alert('Incorrect Email or Password');");
        			      pw.println("window.location.href='/e-JAWI/index.jsp';");
        			      pw.println("</script>");
        			}
        			
        		}

        		catch (Throwable ex) {
        			System.out.println(ex);
        		}
            
            
    		}
		
		else if (action.equalsIgnoreCase("loginTeacher")) {
        	try {
    			
        		String teacherEmail = request.getParameter("email");
        		String teacherPassword = request.getParameter("password");
        		
    			Teacher teacher = new Teacher();
    			teacher.setTeacherEmail(teacherEmail);
    			teacher.setTeacherPassword(teacherPassword);

    			teacher = TeacherDAO.login(teacher);

    			if(teacher.isValid())
    			{
    				//teacher = TeacherDAO.getTeacherByEmail(email);
    				HttpSession session = request.getSession(true);
    				session.setAttribute("currentSessionUserID", teacher.getId());
    				session.setAttribute("currentSessionUser", teacher.getTeacherEmail());
    				session.setAttribute("currentSessionUserRole", teacher.getTeacherRole());
    				session.setAttribute("currentSessionUserName", teacher.getTeacherName());
    				response.setContentType("text/html");
    			      PrintWriter pw = response.getWriter();
    			      pw.println("<script>");
    			      pw.println("alert('Login Successful');");
    			      pw.println("window.location.href='/e-JAWI/TeacherDashboard.jsp';");
    			      pw.println("</script>");
    			}
    			else
    			{
    				response.setContentType("text/html");
    			      PrintWriter pw = response.getWriter();
    			      pw.println("<script>");
    			      pw.println("alert('Incorrect Email or Password');");
    			      pw.println("window.location.href='/e-JAWI/index.jsp';");
    			      pw.println("</script>");
    			}
    			
    		}

    		catch (Throwable ex) {
    			System.out.println(ex);
    		}
      
            
		}
		
		else if (action.equalsIgnoreCase("loginAdmin")) {
        	try {
    			
        		String teacherEmail = request.getParameter("email");
        		String teacherPassword = request.getParameter("password");
        		
    			Teacher teacher = new Teacher();
    			teacher.setTeacherEmail(teacherEmail);
    			teacher.setTeacherPassword(teacherPassword);

    			teacher = TeacherDAO.login(teacher);

    			if(teacher.isValid())
    			{
    				//teacher = TeacherDAO.getTeacherByEmail(email);
    				HttpSession session = request.getSession(true);
    				session.setAttribute("currentSessionUserID", teacher.getId());
    				session.setAttribute("currentSessionUser", teacher.getTeacherEmail());
    				session.setAttribute("currentSessionUserRole", teacher.getTeacherRole());
    				session.setAttribute("currentSessionUserName", teacher.getTeacherName());
    				response.setContentType("text/html");
    			      PrintWriter pw = response.getWriter();
    			      pw.println("<script>");
    			      pw.println("alert('Login Successful');");
    			      pw.println("window.location.href='/e-JAWI/AdminDashboard.jsp';");
    			      pw.println("</script>");
    			}
    			else
    			{
    				response.setContentType("text/html");
    			      PrintWriter pw = response.getWriter();
    			      pw.println("<script>");
    			      pw.println("alert('Incorrect Email or Password');");
    			      pw.println("window.location.href='/e-JAWI/loginAdmin.jsp';");
    			      pw.println("</script>");
    			}
    			
    		}

    		catch (Throwable ex) {
    			System.out.println(ex);
    		}
      
            
		}
		
	}
		
}
