package project4;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database {
		    public static Connection getConnection() {
		        Connection con = null;
		        try {
		            Class.forName("com.mysql.jdbc.Driver");
		            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud_test", "root", "");
		            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/magasin", "root", "");
		        } catch (ClassNotFoundException | SQLException e) {
		            System.out.println(e);
		        }
		        return con;
		    }
		
	}
