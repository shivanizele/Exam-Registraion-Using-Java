package gpp;

import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

@SuppressWarnings("serial")
public class MyInfo extends JPanel {
	String fullName = "";
	String email = "";
	String enrollNo = "";

	JLabel labelName = new JLabel("Name: ");
	JLabel labelEmail = new JLabel("Email: ");
	JLabel labelEnroll = new JLabel("Enroll No: ");

	

	MyInfo(String username) {

		setLayout(null);

		try {
			Connection conn = Myconnection.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String query = "SELECT * FROM registraion WHERE username = ?";
			// Creating the PreparedStatement object
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(2));
				System.out.println(rs.getString("name"));
				fullName = rs.getString("name");
				email = rs.getString("email_id");
				enrollNo = rs.getString("username");
				break;
			}
			conn.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JLabel header = new JLabel("");
		header.setOpaque(true);
		header.setIcon(new ImageIcon(Reg.class.getResource("/images/header.jpg")));
		header.setBounds(10, 25, 1380, 100);
		add(header);
		//
		JLabel labelNameValue = new JLabel(fullName);
		JLabel labelEmailValue = new JLabel(email);
		JLabel labelEnrollValue = new JLabel(enrollNo);
		
		labelName.setBounds(360, 120, 1000, 100);
		labelName.setFont(new Font("Arial", Font.BOLD, 20));
		labelEmail.setBounds(360, 170, 1000, 100);
		labelEmail.setFont(new Font("Arial", Font.BOLD, 20));
		labelEnroll.setBounds(360, 220, 1000, 100);
		labelEnroll.setFont(new Font("Arial", Font.BOLD, 20));

		labelNameValue.setBounds(470, 120, 1000, 100);
		labelNameValue.setFont(new Font("Arial", Font.BOLD, 20));
		labelEmailValue.setBounds(470, 170, 1000, 100);
		labelEmailValue.setFont(new Font("Arial", Font.BOLD, 20));
		labelEnrollValue.setBounds(470, 220, 1000, 100);
		labelEnrollValue.setFont(new Font("Arial", Font.BOLD, 20));

		add(labelNameValue);
		add(labelEmailValue);
		add(labelEnrollValue);
		add(labelName);
		add(labelEmail);
		add(labelEnroll);
	}
}
