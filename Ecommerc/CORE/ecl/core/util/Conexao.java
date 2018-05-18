
package ecl.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexao {
	
	public static Connection getConnection() 
			throws ClassNotFoundException, 
		SQLException{
		String driver = "com.mysql.jdbc.Driver";
		String serverName = "localhost";    //caminho do servidor do BD
        String mydatabase ="ecl_livros";        //nome do seu banco de dados
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?useSSL=false";
		String user = "root";
		String password = "semsenha";
		Class.forName(driver);
		Connection conn = 
				DriverManager.getConnection( url, user, password);
		
		return conn;
		
		

	}

}
