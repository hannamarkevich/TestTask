package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myPackage.Car;
import myPackage.DbLayer;
import myPackage.Help;
import myPackage.Order;

public class ProcessClient extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String carList = request.getParameter("carsList");
		String save = request.getParameter("save");
		String delete = request.getParameter("delete");
		String name = request.getParameter("firstName");
		String surname = request.getParameter("lastName");
		String birthDate = request.getParameter("date");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String email = request.getParameter("email");

		String message = "";
		String url = "";
		String id = request.getParameter("id");
		// Check data
		if (Help.checkClient(name, surname, birthDate, phone, address, email)) {
			message = "Something is wrong, please check all data";
			url = "client.jsp?" + "id=" + id + "&firstName=" + name
					+ "&lastName=" + surname + "&birthDate=" + birthDate
					+ "&address=" + address + "&phone=" + phone + "&email="
					+ email;
		}
		// Add or Save
		else if (save != null) {

			if (id.startsWith("null")) {

				try {
					if (DbLayer.verifyClientExists(name, surname, phone, email)) {
						message = "The client wasn't added because already exists";
						url = "client.jsp";
					} else {

						try {
							DbLayer.addClient(name, surname,
									Help.convertToDate(birthDate), address,
									phone, email);
						} catch (NumberFormatException | ClassNotFoundException
								| SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						message = "The client was added successfully!!!!!";
						url = "client.jsp?id="
								+ DbLayer.getClientID(name, surname, phone,
										email) + "&firstName=" + name
								+ "&lastName=" + surname + "&date=" + birthDate
								+ "&phone=" + phone + "&address=" + address
								+ "&clientId=" + email;
					}
				} catch (NumberFormatException | ClassNotFoundException
						| SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					DbLayer.editClient(Integer.parseInt(id), name, surname,
							Help.convertToDate(birthDate), address, phone,
							email);
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
				message = "The client was edited successfully!!!!!";
				url = "client.jsp?" + "id=" + Integer.parseInt(id)
						+ "&firstName=" + name + "&lastName=" + surname
						+ "&phone=" + phone + "&address=" + address
						+ "&clientId=" + email;
			}

		} // GetCarsList

		else {
			ArrayList<Car> cars = new ArrayList<Car>();
			try {

				cars = DbLayer.getCarsList(Integer.parseInt(id));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (carList != null) {

				request.setAttribute("carsList", cars);
				request.setAttribute("clientId", id);
				url = "carsList.jsp";

			}
			// Delete

			else if (delete != null) {

				if (cars.size() > 0) {

					message = "Client cannot be deleted because it has cars";
					url = "client.jsp?" + "id=" + id + "&firstName=" + name
							+ "&lastName=" + surname + "&birthDate="
							+ birthDate + "&address=" + address + "&phone="
							+ phone + "&email=" + email;
				} else {
					try {
						DbLayer.deleteClient(Integer.parseInt(id));
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

					message = "Client is deleted successfully";
					url = "index.jsp";
				}
			}

		}
		System.out.println(message);
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
		return;
	}
}
