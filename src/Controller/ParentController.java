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

import DAO.ParentDAO;
import DAO.QuizDAO;
import DAO.TeacherDAO;
import Model.Parent;

/**
 * Servlet implementation class ParentController
 */
@WebServlet("/ParentController")
public class ParentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String VIEW = "/parent/viewAccount.jsp";
	private static String UPDATE = "/parent/updateParent.jsp";
	private static String UPDATE_ACCOUNT = "/parent/updateAccount.jsp";
	// private static String UPDATEPASS = "/parent/updatePass.jsp";
	private static String SEARCH = "/parent/createParent.jsp";
	private static String LIST = "/parent/listParent.jsp";
	private static String CHILDREN_LIST = "/parent/listStudent.jsp";
	private static String RESULT = "/parent/listResult.jsp";

	String forward;
	private ParentDAO dao;
	Parent parent = new Parent();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ParentController() {
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

		if (action.equalsIgnoreCase("ViewAccount")) {
			HttpSession session = request.getSession(true);
			Integer id = (Integer) session.getAttribute("currentSessionUserID");
			parent = ParentDAO.getParentById(id);
			request.setAttribute("parent", parent);
			forward = VIEW;
		} else if (action.equalsIgnoreCase("updateAccount")) {
			String id = request.getParameter("id");
			parent = ParentDAO.getParentByEmail(id);
			request.setAttribute("parent", parent);
			forward = UPDATE;
		} else if (action.equalsIgnoreCase("listparents")) {

			request.setAttribute("parents", ParentDAO.getAllParent());
			forward = LIST;
		} else if (action.equalsIgnoreCase("childrenlist")) {
			
			HttpSession session = request.getSession(true);
			Integer id = (Integer) session.getAttribute("currentSessionUserID");

			request.setAttribute("students", ParentDAO.getAllChildren(id));
			forward = CHILDREN_LIST;
		}
		else if (action.equalsIgnoreCase("viewresult")) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			
			forward = RESULT;
			
			request.setAttribute("quizzes", QuizDAO.getAnsweredQuiz(id));

		}
		else if (action.equalsIgnoreCase("editAccount")) {

			HttpSession session = request.getSession(true);
			Integer id = (Integer) session.getAttribute("currentSessionUserID");
			parent = ParentDAO.getParentById(id);
			request.setAttribute("parent", parent);
			forward = UPDATE_ACCOUNT;

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
			String parentEmail = request.getParameter("email");
			String parentPassword = request.getParameter("password");
			String parentName = request.getParameter("name");
			String parentAddress = request.getParameter("address");
			String parentPhone = request.getParameter("phone");

			parent.setParentEmail(parentEmail);
			parent.setParentPassword(parentPassword);
			parent.setParentName(parentName);
			parent.setParentAddress(parentAddress);
			parent.setParentPhone(parentPhone);

			try {
				ParentDAO.add(parent);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('New Parent Registered');");
			pw.println("window.location.href='/e-JAWI/ParentController?action=ListParents';");
			pw.println("</script>");
		} else if (action.equalsIgnoreCase("UpdateParent")) {

			Integer id = Integer.parseInt(request.getParameter("id"));
			String parentEmail = request.getParameter("email");
			String parentName = request.getParameter("name");
			String parentAddress = request.getParameter("address");
			String parentPhone = request.getParameter("phone");

			parent.setId(id);
			parent.setParentEmail(parentEmail);
			parent.setParentName(parentName);
			parent.setParentAddress(parentAddress);
			parent.setParentPhone(parentPhone);

			try {
				ParentDAO.updateAccount(parent);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('Parent Updated');");
			pw.println("window.location.href='/e-JAWI/ParentController?action=ListParents';");
			pw.println("</script>");
		}
		
		else if (action.equalsIgnoreCase("UpdateAccount")) {

			HttpSession session = request.getSession(true);
			Integer id = (Integer) session.getAttribute("currentSessionUserID");
			String parentEmail = request.getParameter("email");
			String parentName = request.getParameter("name");
			String parentAddress = request.getParameter("address");
			String parentPhone = request.getParameter("phone");

			parent.setId(id);
			parent.setParentEmail(parentEmail);
			parent.setParentName(parentName);
			parent.setParentAddress(parentAddress);
			parent.setParentPhone(parentPhone);

			try {
				ParentDAO.updateAccount(parent);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('Account Updated');");
			pw.println("window.location.href='/e-JAWI/ParentController?action=ViewAccount';");
			pw.println("</script>");
		}
	}

}
