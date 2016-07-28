package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import table.ReturnSale;

public class ReturnSaleDAO {
	private Connection con;  
    private PreparedStatement stat; 
    private ReturnSale returnSale;
    
    public ReturnSaleDAO(Connection con) {  
        this.con = con;  
    } 
    
    public ReturnSale findByCommodityName(String commodityName) throws SQLException {
    	String sql="select * from ReturnSale where commodityName=?;";
    	stat = con.prepareStatement(sql);  
        stat.setString(1, commodityName); 
        ResultSet rs = stat.executeQuery();
        Calendar calendar = Calendar.getInstance();
        
        if (rs.next()) {
        	returnSale = new ReturnSale();
        	returnSale.setSaleID(rs.getInt(1));
        	returnSale.setCommodityID(rs.getInt(2));
        	returnSale.setCommodityName(commodityName);
        	returnSale.setReturnNumber(rs.getInt(4));
        	returnSale.setTotalMoney(rs.getDouble(5));
        	returnSale.setReturnOperator(rs.getString(6));
        	
        	calendar.setTime(rs.getDate(7));
        	returnSale.setReturnTime(calendar);
        	
        }
        return returnSale;
    }
    
    public boolean deleteBySaleID(String saleID) throws SQLException {
    	String sql="delete from ReturnSale where saleID=?;";
    	stat = con.prepareStatement(sql);  
        stat.setString(1, saleID); 
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        } 
    }
    
    public boolean updateBySaleID(ReturnSale returnSale) throws SQLException {
    	String sql="update ReturnSale set commodityID=?,commodityName=?,returnNumber=?,totalMoney=?,returnOperator=?,returnTime=? where saleID=?;";
    	stat = con.prepareStatement(sql);
    	
    	stat.setInt(1, returnSale.getCommodityID());
    	stat.setString(2, returnSale.getCommodityName());
        stat.setInt(3, returnSale.getReturnNumber());
        stat.setDouble(4, returnSale.getTotalMoney());
        stat.setString(5, returnSale.getReturnOperator());
        stat.setDate(6, (Date) returnSale.getReturnTime().getTime());      
        stat.setInt(7, returnSale.getSaleID());
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        } 
    }
    
    public boolean doCreate(ReturnSale returnSale) throws SQLException {  
        String sql = "insert into ReturnSale values(?,?,?,?,?,?,?)";  
        stat = con.prepareStatement(sql);  
        stat.setInt(1, returnSale.getSaleID());
        stat.setInt(2, returnSale.getCommodityID());
        stat.setString(3, returnSale.getCommodityName());
        stat.setInt(4, returnSale.getReturnNumber());
        stat.setDouble(5, returnSale.getTotalMoney());
        stat.setString(6, returnSale.getReturnOperator());
        stat.setDate(7, (Date) returnSale.getReturnTime().getTime());
        
        int update = stat.executeUpdate();  
        if (update > 0) {  
            return true;  
        }  
        else {  
            return false;  
        }  
    }
    
    public List<ReturnSale> findAll() throws SQLException {  
        String sql = "select * from ReturnSale;";  
        stat = con.prepareStatement(sql);  
        ResultSet rs = stat.executeQuery();  
        ReturnSale returnSale = null;  
        List<ReturnSale> list = new ArrayList<ReturnSale>();  
        Calendar calendar=Calendar.getInstance();
        
        while(rs.next()) {  
            returnSale=new ReturnSale();
            returnSale.setSaleID(rs.getInt(1));
            returnSale.setCommodityID(rs.getInt(2));
            returnSale.setCommodityName(rs.getString(3));
            returnSale.setReturnNumber(rs.getInt(4));
            returnSale.setTotalMoney(rs.getDouble(5));
            returnSale.setReturnOperator(rs.getString(6));
            
            calendar.setTime(rs.getDate(7));
            returnSale.setReturnTime(calendar);
            
            list.add(returnSale);  
        }  
        return list;  
    }
}
