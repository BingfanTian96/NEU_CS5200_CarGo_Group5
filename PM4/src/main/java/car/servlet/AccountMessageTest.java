package car.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.dal.CarDao;
import car.dal.ReviewsDao;
import car.dal.SellerDao;
import car.dal.UserDao;
import car.model.Cars;
import car.model.Sellers;
import car.model.Users;


@WebServlet("/profile/messages")
public class AccountMessageTest extends HttpServlet {
	
	protected UserDao userDao;
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        req.getRequestDispatcher("/profile/accountmessages.jsp").forward(req, resp);
	}
	
}
