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

import car.dal.UserDao;
import car.model.Users;


// @author xii12
@WebServlet("/signin")
public class SignIn extends HttpServlet {

	protected UserDao userDao;
       

	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
    	System.out.println("get");
        //Just render the JSP.   
        req.getRequestDispatcher("/signin/signin.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        Users user = null;
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String email = req.getParameter("email");
        req.setAttribute("email", email);
    	String password = req.getParameter("password");
        if (email == null || email.trim().isEmpty() ||
        		password == null || password.trim().isEmpty()) {
            messages.put("success", "Please enter all valid parameters.");
        } else {
			try {
				user = userDao.getUserByEmail(email);
				if (!(user == null) && user.getPassword().equals(password)) {
		        	messages.put("success", "Successed! UserName: " + user.getFirstName());
				} else {
		        	messages.put("success", "Password is wrong.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
//    			throw new IOException(e);
			}
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
			messages.put("email", email);
        	
        }
        req.setAttribute("user", user);
        
        req.getRequestDispatcher("/signin/signin.jsp").forward(req, res);
	}



}
