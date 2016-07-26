package factory;

import java.sql.Connection;
import java.sql.SQLException;

import connection.DatabaseConnection;
import dao.AccountDAO;
import dao.UserDAO;

public class DAOFactory {

	public static AccountDAO createAccount() throws SQLException {
		
		AccountDAO ad = new AccountDAO();
		ad.setConnection(DatabaseConnection.getConnection());
        return ad;  
    }  
	
	public static UserDAO createUser() throws SQLException {
		
		UserDAO ad = new UserDAO();
		ad.setConnection(DatabaseConnection.getConnection());
        return ad;  
    } 
}
