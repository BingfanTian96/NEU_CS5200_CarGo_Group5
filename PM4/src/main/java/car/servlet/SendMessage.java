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


@WebServlet("/profile/sendmessage")
public class SendMessage extends HttpServlet {

	protected CarDao carDao;
	protected MessagesDao messagesDao;

	@Override
	public void init() throws ServletException {
		carDao = CarDao.getInstance();
		messagesDao = MessagesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		String resultToId = req.getParameter("toId");
		req.setAttribute("toId", resultToId);
		req.getRequestDispatcher("/profile/sendmessage.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		String msgContent = req.getParameter("content");
		if(msgContent == null || msgContent.trim().isEmpty()) {
			messages.put("success", "Please enter a valid message.");
		} else {
			try {
				int fromId = Integer.valueOf(req.getParameter("fromId"));
				int toId = Integer.valueOf(req.getParameter("toId"));
				System.out.println(msgContent);
				System.out.println(toId);
				System.out.println(fromId);
				Long datetime = System.currentTimeMillis();
				Timestamp timestamp = new Timestamp(datetime);
				Messages message = new Messages(timestamp, msgContent, fromId, toId);
				messages.put("success", "Successfully send a message to Seller: " + toId);
				messagesDao.create(message);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		req.getRequestDispatcher("/profile/sendmessage.jsp").forward(req, resp);
	}

}
