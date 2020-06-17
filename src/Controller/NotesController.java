package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.NotesDAO;
import Model.Notes;

/**
 * Servlet implementation class NotesController
 */
@WebServlet("/NotesController")
public class NotesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String LIST_NOTES = "/notes/listNotes.jsp";
	private static String UPDATE = "/notes/updateNotes.jsp";
	private static String DELETE = "/notes/deleteNotes.jsp";
	private static String VIEW = "/notes/viewNote.jsp";

	private String forward;
	NotesDAO dao = new NotesDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NotesController() {
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

		if (action.equalsIgnoreCase("listNotes")) {
			forward = LIST_NOTES;
			request.setAttribute("notes", NotesDAO.getAllNotes());
		}
		
		else if (action.equalsIgnoreCase("viewNotes")) {
			forward = VIEW;
			int notesID = Integer.parseInt(request.getParameter("notesID"));
			Notes notes = NotesDAO.getNotesByNotesID(notesID);
			request.setAttribute("notes", notes);
		}

		else if (action.equalsIgnoreCase("updateNotes")) {
			
			forward = UPDATE;
			int notesID = Integer.parseInt(request.getParameter("notesID"));
			Notes notes = NotesDAO.getNotesByNotesID(notesID);
			request.setAttribute("notes", notes);
			
		} 
		else if (action.equalsIgnoreCase("deleteNotes")) {
			
			Notes notes = new Notes();
			int notesID = Integer.parseInt(request.getParameter("notesID"));
			notes.setNotesID(notesID);
			dao.deleteNotes(notesID);
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('The notes has been deleted');");
			pw.println("window.location.href='/e-JAWI/TeacherController?action=listNotes';");
			pw.println("</script>");
			
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
		Notes notes = new Notes();

		if (action.equalsIgnoreCase("Search")) {
			
			int notesID = Integer.parseInt(request.getParameter("notesID"));
			
			notes.setNotesID(notesID);
			
			notes = NotesDAO.getNotesByNotesID(notesID);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/notes/viewNotes.jsp");
			
			request.setAttribute("notes", notes);
			dispatcher.forward(request, response);
			
		} else if (action.equalsIgnoreCase("Add")) {

			String notesTitle = request.getParameter("notesTitle");
			String notesContent = request.getParameter("notesContent");
			
			HttpSession session = request.getSession(true);
			Integer id = (Integer) session.getAttribute("currentSessionUserID");

			notes.setNotesTitle(notesTitle);
			notes.setNotesContent(notesContent);
			notes.setTeacherId(id);

			dao = new NotesDAO();

			NotesDAO.add(notes);
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('The notes has been created');");
			pw.println("window.location.href='/e-JAWI/TeacherController?action=listNotes';");
			pw.println("</script>");

		} else if (action.equalsIgnoreCase("Update")) {
			
			String notesTitle = request.getParameter("notesTitle");
			String notesContent = request.getParameter("notesContent");
			int notesID = Integer.parseInt(request.getParameter("notesID"));
			
			notes.setNotesTitle(notesTitle);
			notes.setNotesContent(notesContent);
			notes.setNotesID(notesID);
			
			try {
				dao.updateNotes(notes);
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('The notes has been updated');");
				pw.println("window.location.href='/e-JAWI/TeacherController?action=listNotes';");
				pw.println("</script>");
			} 
			catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

		else if (action.equalsIgnoreCase("Delete")) {
			
			int notesID = Integer.parseInt(request.getParameter("notesID"));
			
			notes.setNotesID(notesID);
			dao.deleteNotes(notesID);
			
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('The notes has been deleted');");
			pw.println("window.location.href='/e-JAWI/NotesController?action=listNotes';");
			pw.println("</script>");
			
		}

	}
}
