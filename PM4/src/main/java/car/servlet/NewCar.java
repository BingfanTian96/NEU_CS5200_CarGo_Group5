package car.servlet;

import car.dal.*;
import car.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**   
 * The servlet for create a new car
 * 
 * @author: Bingfan Tian  
 * @date: 2022.04.01 
 */
@WebServlet("/new")
public class NewCar extends HttpServlet {
	protected CarDao carDao;
	
	@Override
	public void init() throws ServletException {
		carDao = CarDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/new/new.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String resultVin = req.getParameter("vin");
        if (resultVin == null || resultVin.trim().isEmpty()) {
            messages.put("success", "Invalid Vin number");
        } else {
        	int resultYear = Integer.valueOf(req.getParameter("year"));
    		String resultMake = req.getParameter("make");
    		String resultModel = req.getParameter("model");
    		String resultTrim = req.getParameter("trim");
    		String resultBody = req.getParameter("body");
    		String resultTransmission = req.getParameter("transmission");
    		String resultState = req.getParameter("state");
    		int resultOdometer = Integer.valueOf(req.getParameter("odometer"));
    		double resultCarCondition = Double.valueOf(req.getParameter("carCondition"));
    		String resultColor = req.getParameter("color");
    		String resultInterior = req.getParameter("interior");
    		int resultMmr = Integer.valueOf(req.getParameter("mmr"));
    		int resultSellingPrice = Integer.valueOf(req.getParameter("sellingPrice"));
    		int resultUserId = Integer.valueOf(req.getParameter("userId"));
    		Users seller = null;
			try {
				seller = UserDao.getInstance().getUserByUserId(resultUserId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Cars car = new Cars(resultVin, resultYear, resultMake, resultModel, resultTrim, 
						resultBody, resultTransmission, resultState, resultOdometer, 
						resultCarCondition, resultColor, resultInterior, resultMmr, 
						resultSellingPrice, seller);
				car = carDao.create(car);
				messages.put("success", "Successfully created new car with vin: " + resultVin);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
        }
        req.getRequestDispatcher("/search/search.jsp").forward(req, resp);
    }
	
}
