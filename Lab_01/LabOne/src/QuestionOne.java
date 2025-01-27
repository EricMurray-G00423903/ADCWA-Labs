import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class QuestionOne {
	
	public static void main(String[] args) {
		
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
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
