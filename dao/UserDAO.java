package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import table.User;

public class UserDAO {

	private Connection con;  
    private PreparedStatement stat;  
    
    public void setConnection(Connection con) {  
        this.con = con;  
    } 
    
    public User findByUserID(int userID) throws SQLException {
    	String sql="select * from User where userID=?;";
    	stat = con.prepareStatement(sql);  
        stat.setInt(1, userID); 
        ResultSet rs = stat.executeQuery();
        
        User user = null;
        if (rs.next()) {
        	user = new User();
        	user.setUserID(userID);
        	user.setUserName(rs.getString(2));  
            user.setMobilePhone(rs.getString(3));
            user.setFixedPhone(rs.getString(4));
            user.setUserEmail(rs.getString(5));
        }
        return user;
    }
    
    public boolean deleteByUserID(int userID) throws SQLException {
    	String sql="delete from User where userID=?;";
    	stat = con.prepareStatement(sql);  
        stat.setInt(1, userID); 
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        } 
    }
    
    public boolean updateByUserID(User user) throws SQLException {
    	String sql="update User set userName=?,mobilePhone=?,fixedPhone=?,userEmail=? where userID=?;";
    	stat = con.prepareStatement(sql);
    	stat.setString(1, user.getUserName());
    	stat.setString(2, user.getMobilePhone());
    	stat.setString(3, user.getFixedPhone());
        stat.setString(4, user.getUserEmail()); 
        stat.setInt(5, user.getUserID()); 
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        } 
    }
    
    public boolean doCreate(User user) throws SQLException {  
        String sql = "insert into User values(?,?,?,?,?)";  
        stat = con.prepareStatement(sql);
        stat.setInt(1, user.getUserID());
        stat.setString(2, user.getUserName());
    	stat.setString(3, user.getMobilePhone());
    	stat.setString(4, user.getFixedPhone());
        stat.setString(5, user.getUserEmail()); 
        
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        }  
    }
    
    public List<User> findAll() throws SQLException {  
        String sql = "select * from User";  
        stat = con.prepareStatement(sql);  
        ResultSet rs = stat.executeQuery();  
        User user = null;  
        List<User> list = new ArrayList<User>();  
        while(rs.next()) {  
            user = new User(); 
            user.setUserID(rs.getInt(1));
            user.setUserName(rs.getString(2));  
            user.setMobilePhone(rs.getString(3));
            user.setFixedPhone(rs.getString(4));
            user.setUserEmail(rs.getString(5));
            list.add(user);  
        }  
        return list;  
    }
}
