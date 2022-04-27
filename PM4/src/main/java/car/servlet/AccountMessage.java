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
import car.dal.MessagesDao;
import car.dal.ReviewsDao;
import car.dal.SellerDao;
import car.dal.UserDao;
import car.model.Cars;
import car.model.Messages;
import car.model.Sellers;
import car.model.Users;


@WebServlet("/profile/messages")
public class AccountMessage extends HttpServlet {
	
	protected UserDao userDao;
	protected MessagesDao messagesDao;
	
	
	@Override
	public void init() throws ServletException {
		messagesDao = MessagesDao.getInstance();
		userDao = UserDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Messages> toMessages = new ArrayList<Messages>();
        List<Messages> fromMessages = new ArrayList<Messages>();
        String resultUserId = req.getParameter("userId");
        if(resultUserId == null || resultUserId.trim().isEmpty()){
        	messages.put("success", "Invalid UserID number");
        } else {
	        try {
	        	fromMessages = messagesDao.getSentMessageByUserId(Integer.valueOf(resultUserId));
	        	toMessages = messagesDao.getReceivedMessageByUserId(Integer.valueOf(resultUserId));
	        	Users cur_User = userDao.getUserByUserId(Integer.valueOf(resultUserId));
				if(cur_User == null) {
	        		messages.put("success", "UserID does not exist.");
	        	} else {
	        		req.setAttribute("toMessages", toMessages);
	        		req.setAttribute("fromMessages", fromMessages);
	        	}
	        	messages.put("success", "To messages and From messages for user : " + resultUserId);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }     
        
        req.getRequestDispatcher("/profile/accountmessages.jsp").forward(req, resp);
	}
	
}
