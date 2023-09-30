package Attendance;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login {
	
	int usr =0;
	 public void loginView() {
	JFrame frame = new JFrame();
	Font text = new Font("Times NewROMAN", Font.PLAIN, 20);
	Home hm = new Home();
	Teachers tview = new Teachers();
	
	//techereView
	//studentview
	
	//---------------- LOGO----------------------
	JLabel attendance = new JLabel("ATTENDANCE");
	attendance.setForeground(Color.decode("#37474F"));
	attendance.setBounds(100, 275, 400, 50);
	attendance.setFont(new Font("Verdana",Font.BOLD,50));
	frame.add(attendance);
	JLabel management = new JLabel("MANAGMENT SYSTEM");
	management.setForeground(Color.decode("#37474F"));
	management.setBounds(280, 310, 400, 50);
	management.setFont(new Font("Verdana", Font.BOLD, 15));
	frame.add(management);
	
	//----------------Panel---------------------
	JPanel panel = new JPanel();
	panel.setBounds(0, 0,500,600);
	//	panel.setBackground(Color.decode("#DEE4E7"));
	frame.add(panel);
	//----------------------------------------------
	
	//-----------CLOSE-----------------------------
	JLabel x =new JLabel("X");
	x.setForeground(Color.decode("#DEE4E7"));
	x.setBounds(965,20,100,20);
	x.setFont( new Font("Times New Roman",Font.BOLD,20));
	frame.add(x);
	x.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			System.exit(0);
		} 
	});
	//-----------------------------------------------------------------------------
	
	//--------------Minimize-----------------------------------
	JLabel min= new JLabel("_");
	min.setForeground(Color.decode("#DEE4E7"));
	min.setBounds(935,10,100,20);
	min.setFont(new Font("Times New Roman",Font.BOLD,20));
	frame.add(min);
	min.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			frame.setState(JFrame.ICONIFIED);
		}
	});
	//---------------------------------------------------------------
	
	//-------------------LOGINTEXT---------------------
	JLabel lgn =new JLabel("LOGIN");
	lgn.setForeground(Color.decode("#DEE4E7"));
	lgn.setBounds(625,100,350,75);
	lgn.setFont(new Font("Times New Roman",Font.BOLD,75));
	frame.add(lgn);
	//------------------------------------------------------
	
	//---------------user name----------------
	JLabel user = new JLabel("Username");
	user.setForeground(Color.decode("#DEE4E7"));
	user.setBounds(570,250,100,20);
	user.setFont(text);
	frame.add(user);
	//----------------------------------------------------
	
	//------------Name Textfield-------------------------
	JTextField username = new JTextField();
	username.setBackground(Color.decode("#DEE4E7"));
	username.setForeground(Color.decode("#37474F"));
	username.setBounds(570,280,360,35);
	username.setFont(new Font("Times New Roman",Font.BOLD,15));
	frame.add(username);
	//--------------------------------------------------------
	
	//---------------password---
		JLabel pass= new JLabel("Password"); 
		pass.setForeground(Color.decode("#DEE4E7"));
		pass.setBounds(570,350,100,20);
		pass.setFont(text);
		frame.add(pass);
		//----------------------------------------------------
		
		//------------Password textfield------------
		JTextField password = new JTextField();
		password.setBackground(Color.decode("#DEE4E7"));
		password.setForeground(Color.decode("#37474F"));
		password.setBounds(570,385,360,35);
		password.setFont(new Font("Times New Roman",Font.BOLD,15));
		frame.add(password);
		//--------------------------------------------------------
		
	//--------------warning------------------------------
		JLabel warning =new JLabel();
		warning.setBounds(625,450,250,20);
		warning.setForeground(Color.RED);
		warning.setHorizontalAlignment(warning.CENTER);
		frame.add(warning);
	//--------------------------------------------------
		
		
		//------------LOGIN--------------------
		JButton login = new JButton("LOGIN");
		login.setBounds(625,500,250,20);
		login.setFont(new Font("Times New Roman",Font.BOLD,20));
		login.setBackground(Color.decode("#DEE4E7"));
		login.setForeground(Color.decode("#37474F"));
		frame.add(login);
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int res = dbCheck(username.getText(),password.getText());
					if(res==0) {
						warning.setText("NO USER FOUND!!!");
						username.setText("");
						password.setText("");
					} 
					else if(res==-1) {
						warning.setText("WRONG PASSWORD");
						username.setText("");
						password.setText("");
					}
					else {
						if(res==1)
						//	System.out.println("working");
							hm.homeView(usr);
					
					
						else if(res==2)
							tview.teachersView(usr);
						//.out.println("outpu1 excuted completly");	
							else if(res==3)
			//				sview.stView(usr);
								System.out.println("outpu1 excuted completly23");
						frame.dispose();
					}
					}
				
			catch(SQLException e1) {
				e1.printStackTrace();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			}
		});
		
		
		
	
	frame.setSize(1000,600);
	frame.setResizable(false);
	frame.setLayout(null);
	frame.setUndecorated(true);
	frame.setVisible(true);
	frame.setFocusable(true);
	frame.getContentPane().setBackground(Color.decode("#37474F"));
		 
	 }
	
	 public int dbCheck(String name, String password) throws SQLException, ClassNotFoundException {
	     Class.forName("com.mysql.cj.jdbc.Driver");
	     String url = "jdbc:mysql://localhost:3306/attendance";
	     String user = "root";
	     String pass = "root";
	     String sql = "SELECT * FROM user WHERE username = ?"; // Use a placeholder
	     
	     try (Connection con = DriverManager.getConnection(url, user, pass);
	          PreparedStatement preparedStatement = con.prepareStatement(sql)) {

	         // Set the parameter value for the placeholder
	         preparedStatement.setString(1, name);

	         try (ResultSet resultSet = preparedStatement.executeQuery()) {
	             if (resultSet.next()) {
	                 if (resultSet.getString("password").equals(password)) {
	                     usr = resultSet.getInt("id");
	                     return resultSet.getInt("prio");
	                 } else {
	                     return -1;
	                 }
	             } else {
	                 return 0;
	             }
	         }
	     
	     }
	     
	 }
}

	 
//	 public int dbCheck(String name,String password) throws SQLException, ClassNotFoundException{
//		 //enter port,user,password
//		 Class.forName("com.mysql.cj.jdbc.Driver"); 
//		 String url = "jdbc:mysql://localhost:3306/attendance";
//		 String user ="root";
//		 String pass ="root";
//		 String str = "SELECT * FROM user WHERE username ="+ name;
//		 Connection con = DriverManager.getConnection(url, user, pass);
//		 Statement stm= con.createStatement();
//		 ResultSet rst=stm.executeQuery(str);
//		if(rst.next()) {
//		if(rst.getString("password").equals(password)) {
//			
//			usr =rst.getInt("id");
//				return rst.getInt("prio");
//				
//			}
//			else
//				return -1;
//		}
//		else {
//			return 0;
//		}}}
//		 
//		
//	 }
//}
