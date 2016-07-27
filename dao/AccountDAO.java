package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import table.Account;

public class AccountDAO {

	private Connection con;  
    private PreparedStatement stat;  
    
    public AccountDAO(Connection con) {  
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
        	account.setUserID(rs.getInt(4));
        }
        return account;
    }
    
    public boolean deleteByAccountName(String accountName) throws SQLException {
    	String sql="delete from Account where accountName=?;";
    	stat = con.prepareStatement(sql);  
        stat.setString(1, accountName); 
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        } 
    }
    
    public boolean updateByAccountName(Account account) throws SQLException {
    	String sql="update Account set accountPassword=?,userID=? where accountName=?;";
    	stat = con.prepareStatement(sql);
    	stat.setString(1, account.getAccountPassword());
    	stat.setInt(2, account.getUserID());
        stat.setString(3, account.getAccountName()); 
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        } 
    }
    
    public boolean doCreate(Account account) throws SQLException {  
        String sql = "insert into Account values(?,?,?,?)";  
        stat = con.prepareStatement(sql);  
        stat.setString(1,account.getAccountName());           
        stat.setString(2,account.getAccountPassword());
        stat.setInt(3,account.getAccountType());
        stat.setInt(4,account.getUserID());
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        }  
    }
    
    public List<Account> findAll() throws SQLException {  
        String sql = "select * from Account";  
        stat = con.prepareStatement(sql);  
        ResultSet rs = stat.executeQuery();  
        Account account = null;  
        List<Account> list = new ArrayList<Account>();  
        while(rs.next()) {  
            account = new Account();  
            account.setAccountName(rs.getString(1));  
            account.setAccountPassword(rs.getString(2));
            account.setAccountType(rs.getInt(3));
            account.setUserID(rs.getInt(4));
            list.add(account);  
        }  
        return list;  
    }
}
