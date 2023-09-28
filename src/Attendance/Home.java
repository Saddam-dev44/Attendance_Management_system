package Attendance;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home {
	
	public void homeView(int id) throws SQLException, ClassNotFoundException {
		JFrame frame = new JFrame();
		Font btn = new Font("Times New Roman", Font.BOLD, 20);
		Admin adm = new Admin();
		
		//------------------------CLOSE---------------------------
		JLabel x = new JLabel("X");
		x.setForeground(Color.decode("#37474F"));
		x.setBounds(965, 10, 100, 20);
		x.setFont(new Font("Times New Roman", Font.BOLD, 20));
		frame.add(x);
		x.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		//----------------------------------------------------------
				
		//-----------------------MINIMIZE-----------------------------
		JLabel min = new JLabel("_");
		min.setForeground(Color.decode("#37474F"));
		min.setBounds(935, 0, 100, 20);
		frame.add(min);
		min.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		//-------------------------------------------------------------
		
		//------------------Panel----------------------------------
		JPanel panel = new  JPanel();
		panel.setBounds(0, 0, 1000, 35);
		panel.setBackground(Color.decode("#DEE4E7"));
		frame.add(panel);
		//------------------------
		
		
		//-------------------Welcome---------------------------------
		JLabel welcome = new JLabel("Welcome "+getUser(id)+",");
		welcome.setForeground(Color.decode("#DEE4E7"));
		welcome.setBounds(10, 50, 250, 20);
		welcome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.add(welcome);
		
		
		
		
		frame.setSize(1000,600);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);  
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.getContentPane().setBackground(Color.decode("#37474F"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

	private String getUser(int id) throws SQLException, ClassNotFoundException {
		     Class.forName("com.mysql.cj.jdbc.Driver");
		     String url = "jdbc:mysql://localhost:3306/attendance";
		     String user = "root";
		     String pass = "root";
		     String sql = "SELECT * FROM user WHERE id = ?"; // Use a placeholder
		     
		     try (Connection con = DriverManager.getConnection(url, user, pass);
		          PreparedStatement preparedStatement = con.prepareStatement(sql)) {

		         // Set the parameter value for the placeholder
		         preparedStatement.setInt(1, id);

		         try (ResultSet resultSet = preparedStatement.executeQuery()) {
		                if (resultSet.next()) {
		                    return resultSet.getString("name");
		                } else {
		                    // Handle the case where no user with the given ID was found
		                    return null;
		                }
		         }
		     
		     }
		     
		 
	


	}

}
