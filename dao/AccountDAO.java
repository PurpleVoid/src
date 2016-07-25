package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import table.Account;

public class AccountDAO {

	private Connection con;  
    private PreparedStatement stat;  
    
    public void setConnection(Connection con) {  
        this.con = con;  
    } 
    
    public Account findByAccountName(String accountName) throws SQLException {
    	String sql="select * from Account where accountName=?;";
    	stat = con.prepareStatement(sql);  
        stat.setString(1, accountName); 
        ResultSet rs = stat.executeQuery();
        
        Account account = null;
        if (rs.next()) {
        	account = new Account();
        	account.setAccountName(accountName);
        	account.setAccountPassword(rs.getString(2));
        	account.setAccountType(rs.getInt(3));
        	account.setAccountState(rs.getInt(4));
        	account.setUserID(rs.getInt(5));
        }
        return account;
    }
    
    public boolean doCreate(Account account) throws SQLException {  
        String sql = "insert into Account values(?,?,?,?,?)";  
        stat = con.prepareStatement(sql);  
        stat.setString(1,account.getAccountName());           
        stat.setString(2,account.getAccountPassword());
        stat.setInt(3,account.getAccountType());
        stat.setInt(4,account.getAccountState());
        stat.setInt(5,account.getUserID());
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        }  
    }
}
