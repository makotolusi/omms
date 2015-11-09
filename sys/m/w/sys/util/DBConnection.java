package m.w.sys.util;
import java.io.FileInputStream;  
import java.io.IOException;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.sql.Statement;
import java.util.Properties;  

import m.w.frs.mgserver.domain.Order;

public class DBConnection {  
    private Properties props;  
    private String url;  
    private String user;  
    private String password;  
    public DBConnection()throws IOException ,ClassNotFoundException{  
        this("app.properties");  
    }  
    public DBConnection(String configFile)throws IOException,ClassNotFoundException{  
        props = new Properties();  
        props.load(this.getClass().getClassLoader()  
                .getResourceAsStream(configFile));  
        url = props.getProperty("db.url");  
        user = props.getProperty("db.username");  
        password = props.getProperty("db.password");  
        Class.forName(props.getProperty("db.driver"));  
    }  
  
    public void closeConnection(Connection conn) {  
        try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
  
    }  
  
    public Connection getConnection() throws SQLException {  
        // TODO Auto-generated method stub  
        return DriverManager.getConnection(url,user,password);  
    }  
    
}  