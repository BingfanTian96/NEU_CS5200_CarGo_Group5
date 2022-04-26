package car.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import car.model.Messages;
import car.model.Users;


/**
 * Messages Dao
 */
public class MessagesDao{
    protected ConnectionManager connectionManager;

    private static MessagesDao instance = null;
    protected MessagesDao() {
        connectionManager = new ConnectionManager();
    }
    public static MessagesDao getInstance() {
        if(instance == null) {
            instance = new MessagesDao();
        }
        return instance;
    }
    
    public Messages create(Messages message) throws SQLException {
        String insertMessage =
            "INSERT INTO Messages(SentTime, Content, FromId, ToId) " +
            "VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            // Reviews has an auto-generated key. So we want to retrieve that key.
            insertStmt = connection.prepareStatement(insertMessage,
                Statement.RETURN_GENERATED_KEYS);
         
            insertStmt.setTimestamp(1, new Timestamp(message.getSendTime().getTime()));
            insertStmt.setString(2, message.getContent());
            insertStmt.setInt(3, message.getFromId());
            insertStmt.setInt(4, message.getToId());

            insertStmt.executeUpdate();
            
            resultKey = insertStmt.getGeneratedKeys();
            int messageId = -1;
            if(resultKey.next()) {
                messageId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            message.setMessageId(messageId);
            return message;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
            if(resultKey != null) {
                resultKey.close();
            }
        }
    }
    
    public Messages getMessageByMessageId(int messageId) throws SQLException {
        String selectMessage =
            "SELECT MessageId, SentTime, Content, FromId, ToId" +
            "FROM Messages " +
            "WHERE MessageId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectMessage);
            selectStmt.setInt(1, messageId);
            results = selectStmt.executeQuery();
            
            if(results.next()) {
                int resultMessageId = results.getInt("MessageId");
                Date date =  new Date(results.getTimestamp("SentTime").getTime());
                String messageContent = results.getString("Content");
                
                int fromId = results.getInt("FromId");
                int toId = results.getInt("ToId");
                
                Messages message = new Messages(resultMessageId,date,messageContent,fromId,toId);
                return message;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }
    
    /**
     * Get the Reviews record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a list of Reviews.
     */
    public List<Messages> getSentMessageByUserId(int userId) throws SQLException {
        List<Messages> messages = new ArrayList<>();
        String selectMessage =
            "SELECT MessageId, SentTime, Content, FromId, ToId" +
            "FROM Messages INNER JOIN Users ON Messages.FromId = Users.UserId" +
            "WHERE userId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        UserDao userDao = UserDao.getInstance();
    
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectMessage);
            selectStmt.setInt(1, userId);
            results = selectStmt.executeQuery();
            
            while(results.next()) {
                int resultMessageId = results.getInt("MessageId");
                Date date =  new Date(results.getTimestamp("SentTime").getTime());
                String messageContent = results.getString("Content");
                
                int fromId = results.getInt("FromId");
                int toId = results.getInt("ToId");
    
                Messages message = new Messages(resultMessageId,date,messageContent,fromId,toId);
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return messages;
    }
    
    /**
     * Get the Reviews record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a list of Reviews.
     */
    public List<Messages> getReceivedMessageByUserId(int userId) throws SQLException {
        List<Messages> messages = new ArrayList<>();
        String selectMessage =
            "SELECT MessageId, SentTime, Content, FromId, ToId" +
            "FROM Messages INNER JOIN Users ON Messages.toId = Users.UserId" +
            "WHERE userId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        UserDao userDao = UserDao.getInstance();
    
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectMessage);
            selectStmt.setInt(1, userId);
            results = selectStmt.executeQuery();
            
            while(results.next()) {
                int resultMessageId = results.getInt("MessageId");
                Date date =  new Date(results.getTimestamp("SentTime").getTime());
                String messageContent = results.getString("Content");
                
                int fromId = results.getInt("FromId");
                int toId = results.getInt("ToId");
    
                Messages message = new Messages(resultMessageId,date,messageContent,fromId,toId);
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return messages;
    }
    
}
