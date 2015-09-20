package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myPackage.Client;
import myPackage.DbLayer;

@WebServlet(name = "ClientsServlet", urlPatterns = { "/ClientsList" })
public class ClientsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("first_name");
		String surname = request.getParameter("surname");
		// Set response content type
		ArrayList<Client> clients = new ArrayList<Client>();
		System.out.println(name + " " + surname);
		try {
			clients = DbLayer.getClientsList(name, surname);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("clientsList", clients);
		//Decides what page to send the request data to
	    RequestDispatcher view = request.getRequestDispatcher("clientsList.jsp");
	    //Forward to the page and pass the request and response information
	    view.forward(request, response);
		
	}

}
