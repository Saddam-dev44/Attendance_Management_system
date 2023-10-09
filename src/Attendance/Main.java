package Attendance;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws Exception, SQLException {
//		Login log = new Login();
//		log.loginView();
		
		Admin a = new Admin();
		try {
			a.adminView();
		} catch (NumberFormatException | SQLException e) {
			System.out.println("frame formatted");
			e.printStackTrace();
		}
	}
		

	}


