package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import table.Provider;

public class ProviderDAO {
	
	private Connection con;  
    private PreparedStatement stat;

    public void setConnection(Connection con) {  
        this.con = con;  
    } 
    
    public Provider findByProviderName(String ProviderName) throws SQLException {
    	String sql="select * from Provider where ProviderName=?;";
    	stat = con.prepareStatement(sql);  
        stat.setString(2, ProviderName); 
        ResultSet rs = stat.executeQuery();
        
        Provider Provider = null;
        if (rs.next()) {
        	Provider = new Provider();
        	Provider.setProviderID(rs.getInt(1));
        	Provider.setProviderName(ProviderName);
        	Provider.setProviderAddress(rs.getString(3));
        	Provider.setProviderPhone(rs.getString(4));
        }
        return Provider;
    }
    
    public Provider findByProviderID(int ProviderID) throws SQLException {
    	String sql="select * from Provider where ProviderID=?;";
    	stat = con.prepareStatement(sql);  
        stat.setInt(1, ProviderID); 
        ResultSet rs = stat.executeQuery();
        
        Provider Provider = null;
        if (rs.next()) {
        	Provider = new Provider();
        	Provider.setProviderID(ProviderID);
        	Provider.setProviderName(rs.getString(2));
        	Provider.setProviderAddress(rs.getString(3));
        	Provider.setProviderPhone(rs.getString(4));
        }
        return Provider;
    }
    
    public Provider findByProviderPhone(String ProviderPhone) throws SQLException {
    	String sql="select * from Provider where ProviderPhone=?;";
    	stat = con.prepareStatement(sql);  
        stat.setString(4, ProviderPhone); 
        ResultSet rs = stat.executeQuery();
        
        Provider Provider = null;
        if (rs.next()) {
        	Provider = new Provider();
        	Provider.setProviderID(rs.getInt(1));
        	Provider.setProviderName(rs.getString(2));
        	Provider.setProviderAddress(ProviderPhone);
        	Provider.setProviderPhone(rs.getString(4));
        }
        return Provider;
    }
    
    public boolean deleteByProviderName(String ProviderName) throws SQLException {
    	String sql="delete from Provider where ProviderName=?;";
    	stat = con.prepareStatement(sql);  
        stat.setString(2, ProviderName); 
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        } 
    }
    
    public boolean updateByProviderName(Provider Provider) throws SQLException {
    	String sql="update Provider set ProviderID=?,ProviderAddress=?,ProviderPhone where ProviderName=?;";
    	stat = con.prepareStatement(sql);
    	stat.setInt(1, Provider.getProviderID());
    	stat.setString(3, Provider.getProviderAddress());
    	stat.setString(4, Provider.getProviderPhone());
        stat.setString(2, Provider.getProviderName()); 
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        } 
    }
    
    public boolean doCreate(Provider Provider) throws SQLException {  
        String sql = "insert into Provider values(?,?,?,?)";  
        stat = con.prepareStatement(sql);  
        stat.setInt(1,Provider.getProviderID());           
        stat.setString(2,Provider.getProviderName());
        stat.setString(3,Provider.getProviderAddress());
        stat.setString(4,Provider.getProviderPhone());
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        }  
    }
    
    public ArrayList<Provider> findAll() throws SQLException {  
        String sql = "select * from Provider";  
        stat = con.prepareStatement(sql);  
        ResultSet rs = stat.executeQuery();  
        Provider Provider = null;  
        ArrayList<Provider> list = new ArrayList<Provider>();  
        while(rs.next()) {  
            Provider = new Provider();  
            Provider.setProviderID(rs.getInt(1));  
            Provider.setProviderName(rs.getString(2));
            Provider.setProviderAddress(rs.getString(3));
            Provider.setProviderPhone(rs.getString(4));
            list.add(Provider);  
        }  
        return list;  
    }
    
    
}
