package car.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
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
 * @date: 2022.04.22
 */
@WebServlet("/profile/account")
public class AccountInfo extends HttpServlet {
	
	protected UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String resultUserId = req.getParameter("userId");
        if(resultUserId == null || resultUserId.trim().isEmpty()){
        	messages.put("success", "Invalid UserID number");
        } else {
	        try {
	        	Users cur_User = userDao.getUserByUserId(Integer.valueOf(resultUserId));
				if(cur_User == null) {
	        		messages.put("success", "UserID does not exist.");
	        	}
				req.setAttribute("user", cur_User);
	        	messages.put("success", " Account info about " + resultUserId);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }     
        req.getRequestDispatcher("/profile/accountinfo.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String resultUserId = req.getParameter("userId");
        if(resultUserId == null || resultUserId.trim().isEmpty()){
        	messages.put("success", "Invalid UserID number");
        } else {
			try {
				Users cur_User = userDao.getUserByUserId(Integer.valueOf(resultUserId));
	        	if(cur_User == null) {
	        		messages.put("success", "UserID does not exist.");
	        	}
	    		String resultFirstName = req.getParameter("firstName");
	    		String resultLastName = req.getParameter("lastName");
	    		String resultEmail = req.getParameter("email");
	    		String resultPassword = req.getParameter("password");
				
				userDao.updateUser(cur_User, resultFirstName, resultLastName, resultEmail, resultPassword);
	        	messages.put("success", "Successfully update new User info for: " + resultEmail);
	        	messages.put("previousFirstName", resultFirstName);
	        	messages.put("previousLastName", resultLastName);
	        	messages.put("previousEmail", resultEmail);
	        	messages.put("previousPassword", resultPassword);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
        }
        req.getRequestDispatcher("/profile/accountinfo.jsp").forward(req, resp);
    }
	
	
}
