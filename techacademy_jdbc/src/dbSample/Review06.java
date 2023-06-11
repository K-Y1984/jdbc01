package dbSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review06 {

public static void main(String[] args) {

    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    	try {

        Class.forName("com.mysql.cj.jdbc.Driver");

        con = DriverManager.getConnection("jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                "root", "Yamato5050");

        System.out.print("検索キーワードを入力してください > ");
        String input = keyIn();

        String sql = "SELECT * FROM person WHERE id = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, Integer.parseInt(input));
        rs = stmt.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.println("name: " + name + ", age: " + age);
        }

    	} catch (ClassNotFoundException e) {
        System.err.println("JDBCドライバのロードに失敗しました。");
        e.printStackTrace();
    	} catch (SQLException e) {
        System.err.println("データベースに異常が発生しました。");
        e.printStackTrace();
    	} finally {

    		if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("ResultSetを閉じるときにエラーが発生しました。");
                e.printStackTrace();
            }
    		}

    		if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Statementを閉じるときにエラーが発生しました。");
                e.printStackTrace();
            }
    		}

    		if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("データベース切断時にエラーが発生しました。");
                e.printStackTrace();
            }
    		}	
    		}
			}


			private static String keyIn() {
			String line = null;
			try {
				BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
				line = key.readLine();
			} catch (IOException e) {

			}
			return line;
}
}
