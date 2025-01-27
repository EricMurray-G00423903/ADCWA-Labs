import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class QuestionOne {
	
	public static void partOne() {
		
		//Part One - Run a SELECT all query
		try {
			
			//Establish Connection Parameters
			MysqlDataSource mysqlDS = new MysqlDataSource();
			mysqlDS.setURL("jdbc:mysql://localhost:3306/salespersondb?serverTimezone=UTC");
			mysqlDS.setUser("root");
			mysqlDS.setPassword("root");
			
			//Establish Connection
			Connection conn = mysqlDS.getConnection();
			
			//Create a Statement
			Statement stmt = conn.createStatement();
			
			//Query to Run
			String query = "SELECT * FROM salesperson_table";
			
			//Create a ResultSet to catch data
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				String sid = rs.getString("sid");
				String fullName = rs.getString("fname") + " " + rs.getString("surname");
				Date dob = rs.getDate("dob");
				String city = rs.getString("city");
				double commission = rs.getDouble("commission");
				
				System.out.println("SID: " + sid + " Name: " + fullName + " DOB: " + dob + " City: " + city + " Commission: "+ commission);
			
			}
			
			conn.close();
			stmt.close();
			rs.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void partTwo() {
		
		//Part Two - Search Query
		try {
			
			//Establish Connection Parameters
			MysqlDataSource mysqlDS = new MysqlDataSource();
			mysqlDS.setURL("jdbc:mysql://localhost:3306/salespersondb?serverTimezone=UTC");
			mysqlDS.setUser("root");
			mysqlDS.setPassword("root");
			
			//Establish Connection
			Connection conn = mysqlDS.getConnection();
			
			//Create Query with ?
			String query = "SELECT * FROM salesperson_table WHERE commission <= ? OR commission IS NULL";
			
			//Create a Prepared Statement
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			//Take in Search from User
			System.out.println("Enter a Commission to Search");
			Scanner scanner = new Scanner(System.in);
			double search = scanner.nextDouble();
			
			//System.out.println("Searching for commission <= " + search);

			
			pstmt.setDouble(1, search);
			
			//Create a ResultSet to catch data
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String sid = rs.getString("sid");
				String fullName = rs.getString("fname") + " " + rs.getString("surname");
				double commission = rs.getDouble("commission");
				if(rs.wasNull()) commission = 0.0;
				
				System.out.println("SID: " + sid + " Name: " + fullName + " Commission: "+ commission);
			
			}
			
			scanner.close();
			conn.close();
			pstmt.close();
			rs.close();
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
