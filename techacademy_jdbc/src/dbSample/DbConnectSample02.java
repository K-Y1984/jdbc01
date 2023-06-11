package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectSample02 {

    public static void main(String[] args) {
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        	try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "Yamato5050"
            );

            
            stmt = con.createStatement();

            
            String sql = "SELECT * FROM country LIMIT 50";
            rs = stmt.executeQuery(sql);

            
            while( rs.next() ){
                String name = rs.getString("Name");
                System.out.println(name);
            }

           

            sql = "UPDATE country SET Population = 105000 WHERE Code = 'ABW'";
            int count = stmt.executeUpdate(sql);
            System.out.println(count);

        	} catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバのロードに失敗しました。");
            e.printStackTrace();
        	} catch (SQLException e) {
            System.err.println("データベースに異常が発生しました。");
            e.printStackTrace();
        	} finally {
           
        }
    }

}