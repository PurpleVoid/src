package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import table.Commodity;

public class CommodityDAO {

	private Connection con;  
    private PreparedStatement stat;  
    
    public void setConnection(Connection con) {  
        this.con = con;  
    } 
    
    public Commodity findByCommodityID(int commodityID) throws SQLException, ParseException {
    	String sql="select * from Commodity where commodityID=?;";
    	stat = con.prepareStatement(sql);  
        stat.setInt(1, commodityID); 
        ResultSet rs = stat.executeQuery();
        Calendar calendar=Calendar.getInstance();
        		
        Commodity commodity = null;
        if (rs.next()) {
        	commodity = new Commodity();
        	commodity.setCommodityID(commodityID);
        	commodity.setCommodityName(rs.getString(2));
        	
        	calendar.setTime(rs.getDate(3));
        	commodity.setProduceDate(calendar);
        	
        	commodity.setInPrice(rs.getDouble(4));
        	commodity.setSalePrice(rs.getDouble(5));
        	
        	calendar.setTime(rs.getDate(6));
        	commodity.setStorageTime(calendar);
        	
        	commodity.setDownLimit(rs.getInt(7));
        	commodity.setUnitName(rs.getString(8));
        	commodity.setProviderNumber(rs.getInt(9));
        	
        	calendar.setTime(rs.getDate(10));
        	commodity.setInDate(calendar);
        	
        }
        return commodity;
    }
    
    public boolean deleteByCommodityID(int commodityID) throws SQLException {
    	String sql="delete from User where userID=?;";
    	stat = con.prepareStatement(sql);  
        stat.setInt(1, commodityID); 
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        } 
    }
    
    public boolean updateByCommodityID(Commodity commodity) throws SQLException {
    	String sql="update Commodity set commodityName=?,produceDate=?,inPrice=?,salePrice=?,storageTime=?,downLimit=?,unitName=?,providerNumber=?,inDate=? where commodityID=?;";
    	stat = con.prepareStatement(sql);
    	stat.setString(1, commodity.getCommodityName());
    	stat.setDate(2, (java.sql.Date) commodity.getProduceDate().getTime());
    	stat.setDouble(3, commodity.getInPrice());
        stat.setDouble(4, commodity.getSalePrice()); 
        stat.setDate(5, (java.sql.Date) commodity.getStorageTime().getTime()); 
        stat.setInt(6, commodity.getDownLimit());
        stat.setString(7, commodity.getUnitName());
        stat.setInt(8, commodity.getProviderNumber());
        stat.setDate(9, (java.sql.Date) commodity.getInDate().getTime());
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        } 
    }
    
    public boolean doCreate(Commodity commodity) throws SQLException {  
        String sql = "insert into Commodity values(?,?,?,?,?,?,?,?,?,?)";  
        stat = con.prepareStatement(sql);
        stat.setInt(1, commodity.getCommodityID());
        stat.setString(2, commodity.getCommodityName());
    	stat.setDate(3, (java.sql.Date) commodity.getProduceDate().getTime());
    	stat.setDouble(4, commodity.getInPrice());
        stat.setDouble(5, commodity.getSalePrice()); 
        stat.setDate(6, (java.sql.Date) commodity.getStorageTime().getTime());
        stat.setInt(7, commodity.getDownLimit());
        stat.setString(8, commodity.getUnitName());
        stat.setInt(9, commodity.getProviderNumber());
        stat.setDate(10, (java.sql.Date) commodity.getInDate().getTime());
        
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        }  
    }
    
    public List<Commodity> findAll() throws SQLException {  
        String sql = "select * from Commodity";  
        stat = con.prepareStatement(sql);  
        ResultSet rs = stat.executeQuery();  
        Commodity commodity = null;  
        List<Commodity> list = new ArrayList<Commodity>();  
        Calendar calendar=Calendar.getInstance();
        while(rs.next()) {  
            commodity = new Commodity(); 
            commodity.setCommodityID(rs.getInt(1));
            commodity.setCommodityName(rs.getString(2));
            
            calendar.setTime(rs.getDate(3));
            commodity.setProduceDate(calendar);
            
            commodity.setInPrice(rs.getDouble(4));
            commodity.setSalePrice(rs.getDouble(5));
            
            calendar.setTime(rs.getDate(6));
            commodity.setStorageTime(calendar);
            
            commodity.setDownLimit(rs.getInt(7));
            commodity.setUnitName(rs.getString(8));
            commodity.setProviderNumber(rs.getInt(9));
            
            calendar.setTime(rs.getDate(10));
            commodity.setInDate(calendar);
            list.add(commodity);  
        }  
        return list;  
    }
}
