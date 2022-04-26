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

import car.dal.CarDao;
import car.dal.SellerDao;
import car.dal.UserDao;
import car.model.Cars;
import car.model.Sellers;
import car.model.Users;


/**   
 * @author: Bingfan Tian  
 * @date: 2022.04.01 
 */
@WebServlet("/update")
public class UpdateCar extends HttpServlet {
	
	protected CarDao carDao;
	
	@Override
	public void init() throws ServletException {
		carDao = CarDao.getInstance();
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
				System.out.println(curCar.getSeller().getUserId());
				req.setAttribute("car", curCar);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
        req.getRequestDispatcher("/update/update.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String resultVin = req.getParameter("vin");
        if (resultVin == null || resultVin.trim().isEmpty()) {
            messages.put("success", "Invalid Vin number");
        } else {
			try {
				Cars cur_car = carDao.getCarByVin(resultVin);
	        	if(cur_car == null) {
	        		messages.put("success", "Vin does not exist. No update to perform.");
	        	}
	    		String resultTrim = req.getParameter("trim");
	    		String resultState = req.getParameter("state");
	    		int resultOdometer = Integer.valueOf(req.getParameter("odometer"));
	    		double resultCarCondition = Double.valueOf(req.getParameter("carCondition"));
	    		String resultColor = req.getParameter("color");
	    		String resultInterior = req.getParameter("interior");
	    		int resultMmr = Integer.valueOf(req.getParameter("mmr"));
	    		int resultSellingPrice = Integer.valueOf(req.getParameter("sellingPrice"));
	    		int resultUserId = Integer.valueOf(req.getParameter("userId"));
	    		Users seller = null;
				seller = UserDao.getInstance().getUserByUserId(resultUserId);
				
				carDao.updateCar(cur_car, resultTrim, resultState, resultOdometer, 
	        			resultCarCondition, resultColor, resultInterior, resultMmr, 
	        			resultSellingPrice, seller);
	        	messages.put("success", "Successfully update new car with vin: " + resultVin);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
        }
        req.getRequestDispatcher("/search/search.jsp").forward(req, resp);
    }
	
}
