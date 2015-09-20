package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myPackage.Car;
import myPackage.DbLayer;
import myPackage.Help;
import myPackage.Order;

public class ProcessCar extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8249471334243124984L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ordersList = request.getParameter("ordersList");
		String id = request.getParameter("id");
		String save = request.getParameter("save");
		String delete = request.getParameter("delete");
		System.out.println("doPost " + id);
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		String year = request.getParameter("year");
		String vin = request.getParameter("vin");
		String clientId = request.getParameter("clientId");
		String message = "";
		String url = "";
		
		//Check data
		if(Help.checkCar(make, model, year, vin))
		{
			message="Something is wrong, please check all data";
			url = "car.jsp?" + "id=" + id + "&make=" + make + "&model="
					+ model + "&year=" + year + "&vin=" + vin
					+ "&clientId=" + clientId;
		}
		//Add or Save
			else if (save != null) {

			if (id.startsWith("null")) {

				System.out.println("!!!!!Add car!!!! "
						+ request.getParameter("make")
						+ request.getParameter("model")
						+ Integer.parseInt(request.getParameter("year"))
						+ request.getParameter("vin")
						+ request.getParameter("clientId"));

				try {
					if (DbLayer
							.verifyCarExists(vin, Integer.parseInt(clientId))) {
						message = "The car wasn't added because already exists";
						url = "car.jsp";
					} else {

						try {
							DbLayer.addCar(request.getParameter("make"),
									request.getParameter("model"), Integer
											.parseInt(request
													.getParameter("year")),
									request.getParameter("vin"), Integer
											.parseInt(request
													.getParameter("clientId")));
						} catch (NumberFormatException | ClassNotFoundException
								| SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						message = "The car was added successfully!!!!!";
						url = "car.jsp?" + "id=" + DbLayer.getCarID(vin)
								+ "&make=" + make + "&model=" + model
								+ "&year=" + year + "&vin=" + vin
								+ "&clientId=" + clientId;
					}
				} catch (NumberFormatException | ClassNotFoundException
						| SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					DbLayer.editCar(Integer.parseInt(id), make, model,
							Integer.parseInt(year), vin);
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
				message = "The car was edited successfully!!!!!";
				url = "car.jsp?" + "id=" + id + "&make=" + make + "&model="
						+ model + "&year=" + year + "&vin=" + vin
						+ "&clientId=" + clientId;
			}

		} //GetOrdersList
		
		else {
			ArrayList<Order> orders = new ArrayList<Order>();
			try {

				orders = DbLayer.getOrdersList(Integer.parseInt(id));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (ordersList != null) {

				request.setAttribute("ordersList", orders);
				request.setAttribute("carId", id);
				url = "ordersList.jsp";

			}
			//Delete
			
			else if (delete != null) {
				
				if (orders.size() > 0) {
					
					message = "Car cannot be deleted because it has orders";
					url = "car.jsp?" + "id=" + id + "&make=" + make + "&model="
							+ model + "&year=" + year + "&vin=" + vin
							+ "&clientId=" + clientId;
				} else {
					try {
						DbLayer.deleteCar(Integer.parseInt(id));
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
					ArrayList<Car> cars = new ArrayList<Car>();

					try {
						cars = DbLayer.getCarsList(Integer.parseInt(clientId));
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.setAttribute("carsList", cars);
					request.setAttribute("clientId", id);
					request.setAttribute("title","Cars List");
					System.out.println("id in Process Client:"+id);
					
					message = "Car is deleted successfully";
					url = "carsList.jsp";
				}
			}

		}
		
		System.out.println(message);
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
		return;
	}
}
