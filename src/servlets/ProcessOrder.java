package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myPackage.DbLayer;
import myPackage.Help;

public class ProcessOrder extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5879747531585104662L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String save = request.getParameter("save");
		String delete = request.getParameter("delete");
		System.out.println("doPost " + id);
		String date = request.getParameter("date");
		String amount = request.getParameter("amount");
		String status = request.getParameter("status");
		String carId = request.getParameter("carId");
		String message = "";
		String url = "";
		// Check
		if (Help.checkOrder(amount)) {
			message = ("Something is wrong. Please check the data");
			url = "order.jsp?id=" + id + "&date=" + date + "&amount=" + amount
					+ "&status=" + status + "&carId=" + carId;
		}
		// Add or Save
		else if (save != null) {

			if (id.startsWith("null")) {

				System.out.println("!!!!!Add order!!!! " + date + amount
						+ status + carId);

				try {
					DbLayer.addOrder(Integer.parseInt(amount), status,
							Integer.parseInt(carId));
				} catch (NumberFormatException | ClassNotFoundException
						| SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				message = "The order was added successfully!!!!!";
				int orderId = 0;
				try {
					orderId = DbLayer.getOrderID(date,
							Integer.parseInt(amount), status,
							Integer.parseInt(carId));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(orderId);
				url = "order.jsp?id=" + orderId + "&date=" + date + "&amount="
						+ amount + "&status=" + status + "&carId=" + carId;

			}

			else {
				try {
					DbLayer.editOrder(Integer.parseInt(id),
							Integer.parseInt(amount), status);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				message = "The order was edited successfully!!!!!";
				url = "order.jsp?" + "id=" + Integer.parseInt(id) + "&date="
						+ date + "&amount=" + amount + "&status=" + status
						+ "&carId=" + carId;
			}
		}
		// delete
		else if (delete != null) {

			try {
				DbLayer.deleteOrder(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			message = "Order is deleted successfully";
			url = "index.jsp";

		}
		System.out.println(message);
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
		return;

	}
}
