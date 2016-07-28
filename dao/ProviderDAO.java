package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import table.Provider;

public class ProviderDAO {
	
	private Connection con;  
    private PreparedStatement stat;
    private Provider provider;

    public ProviderDAO(Connection con) {  
        this.con = con;  
    } 
    
    public Provider findByProviderName(String providerName) throws SQLException {
    	String sql="select * from Provider where providerName=?;";
    	stat = con.prepareStatement(sql);  
        stat.setString(1, providerName); 
        ResultSet rs = stat.executeQuery();
        
        if (rs.next()) {
        	provider = new Provider();
        	provider.setProviderID(rs.getInt(1));
        	provider.setProviderName(providerName);
        	provider.setProviderAddress(rs.getString(3));
        	provider.setProviderPhone(rs.getString(4));
        }
        return provider;
    }
    
    public Provider findByProviderID(int providerID) throws SQLException {
    	String sql="select * from Provider where providerID=?;";
    	stat = con.prepareStatement(sql);  
        stat.setInt(1, providerID); 
        ResultSet rs = stat.executeQuery();
        
        if (rs.next()) {
        	provider = new Provider();
        	provider.setProviderID(providerID);
        	provider.setProviderName(rs.getString(2));
        	provider.setProviderAddress(rs.getString(3));
        	provider.setProviderPhone(rs.getString(4));
        }
        return provider;
    }
    
    public Provider findByProviderPhone(String providerPhone) throws SQLException {
    	String sql="select * from Provider where providerPhone=?;";
    	stat = con.prepareStatement(sql);  
        stat.setString(1, providerPhone); 
        ResultSet rs = stat.executeQuery();
        
        if (rs.next()) {
        	provider = new Provider();
        	provider.setProviderID(rs.getInt(1));
        	provider.setProviderName(rs.getString(2));
        	provider.setProviderAddress(providerPhone);
        	provider.setProviderPhone(rs.getString(4));
        }
        return provider;
    }
    
    public boolean deleteByProviderID(String providerID) throws SQLException {
    	String sql="delete from Provider where providerID=?;";
    	stat = con.prepareStatement(sql);  
        stat.setString(1, providerID); 
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        } 
    }
    
    public boolean updateByProviderID(Provider provider) throws SQLException {
    	String sql="update Provider set providerName=?,providerAddress=?,providerPhone=? where providerID=?;";
    	stat = con.prepareStatement(sql);
    	stat.setString(1, provider.getProviderName());  	
    	stat.setString(2, provider.getProviderAddress());
    	stat.setString(3, provider.getProviderPhone());
    	stat.setInt(4, provider.getProviderID());
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        } 
    }
    
    public boolean doCreate(Provider provider) throws SQLException {  
        String sql = "insert into Provider values(?,?,?,?)";  
        stat = con.prepareStatement(sql);  
        stat.setInt(1,provider.getProviderID());           
        stat.setString(2,provider.getProviderName());
        stat.setString(3,provider.getProviderAddress());
        stat.setString(4,provider.getProviderPhone());
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        }  
    }
    
    public List<Provider> findAll() throws SQLException {  
        String sql = "select * from Provider";  
        stat = con.prepareStatement(sql);  
        ResultSet rs = stat.executeQuery();  
        List<Provider> list = new ArrayList<Provider>();  
        while(rs.next()) {  
            provider = new Provider();  
            provider.setProviderID(rs.getInt(1));  
            provider.setProviderName(rs.getString(2));
            provider.setProviderAddress(rs.getString(3));
            provider.setProviderPhone(rs.getString(4));
            list.add(provider);  
        }  
        return list;  
    }
       
}
