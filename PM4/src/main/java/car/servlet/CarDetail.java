package car.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.dal.*;
import car.model.*;


/**   
 * @author: Bingfan Tian  
 * @date: 2022.04.01 
 */
@WebServlet("/detail")
public class CarDetail extends HttpServlet {

	protected CarDao carDao;
	protected MessagesDao messagesDao;
	protected UserDao userDao;

	@Override
	public void init() throws ServletException {
		carDao = CarDao.getInstance();
		messagesDao = MessagesDao.getInstance();
		userDao = UserDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		String resultVin = req.getParameter("vin");
		if (resultVin == null || resultVin.trim().isEmpty()) {
			messages.put("success", "Invalid Vin number");
		} else {
			try {
				Cars curCar = carDao.getCarByVin(resultVin);
				if(curCar == null) {
					messages.put("success", "Vin does not exist. No update to perform.");
				}
				req.setAttribute("car", curCar);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.getRequestDispatcher("/cardetail/cardetail.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		String msgContent = req.getParameter("content");
		String resultVin = req.getParameter("vin");
		if(msgContent == null || msgContent.trim().isEmpty()) {
			messages.put("success", "Please enter a valid message.");
		} else {
			try {
				int fromId = Integer.valueOf(req.getParameter("fromId"));
				int toId = Integer.valueOf(req.getParameter("toId"));
				Users from = userDao.getUserByUserId(fromId);
				Users to = userDao.getUserByUserId(toId);
				Long datetime = System.currentTimeMillis();
				Timestamp timestamp = new Timestamp(datetime);
				Messages message = new Messages(timestamp, msgContent, from, to);
				Users users = userDao.getUserByUserId(toId);
				messages.put("success", "Successfully send a message to Seller: " + users.getFirstName());
				messagesDao.create(message);
				req.setAttribute("vin", resultVin);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		req.getRequestDispatcher("/cardetail/cardetail.jsp").forward(req, resp);
	}

}
