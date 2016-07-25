package factory;

import java.sql.Connection;
import java.sql.SQLException;

import connection.DatabaseConnection;
import dao.AccountDAO;

public class DAOFactory {

	public static AccountDAO createAccount() throws SQLException {
		
		AccountDAO ad = new AccountDAO();
		ad.setConnection(DatabaseConnection.getConnection());
        return ad;  
    }  
}
