package gpp;

import javax.swing.*;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

@SuppressWarnings("serial")
class LoginForm extends JPanel implements ActionListener {
	Container c;

	JLabel u = new JLabel("Username : ");
	JLabel p = new JLabel("Password : ");
	JLabel su = new JLabel("Success");
	JLabel msg = new JLabel("Invalid username or password");
	JTextField un = new JTextField();
	JPasswordField pa = new JPasswordField();

	Font f = new Font("Arial", Font.BOLD, 16);
	JButton bt = new JButton("Login");

	LoginForm() {
		setLayout(null);
		JLabel header = new JLabel("");
		header.setOpaque(true);
		header.setIcon(new ImageIcon(Reg.class.getResource("/images/header.jpg")));
		header.setBounds(10, 25, 1380, 100);
		add(header);

		p.setFont(new Font("Serif", Font.BOLD, 20));
		u.setFont(new Font("Serif", Font.BOLD, 20));

		u.setBounds(360, 200, 200, 30);
		p.setBounds(360, 250, 200, 30);

		un.setBounds(580, 200, 200, 30);
		pa.setBounds(580, 250, 200, 30);

		bt.setBounds(500, 300, 100, 30);
		bt.setFont(new Font("Serif", Font.BOLD, 20));
		bt.addActionListener(this); // assigning event handler to button
		add(u);// adding to container
		add(p);
		add(un);
		add(bt);
		add(su);
		add(msg);
		add(pa);
	}

	public void actionPerformed(ActionEvent e) {
		String user = un.getText();
		String pswd = pa.getText();
		if (e.getSource() == bt) { 
			if (!user.isEmpty() && !pswd.isEmpty()) {
				try {
					Connection conn = Myconnection.getConnection();
					Statement stmt = (Statement) conn.createStatement();
					ResultSet rs = (ResultSet) stmt.executeQuery("select * from login");
					boolean isValidUser = false;
					while (rs.next()) {
						if (rs.getString(2).equals(user) && rs.getString(3).equals(pswd)) {
							isValidUser = true;
							break;
						}
					}
					conn.close();
					if (isValidUser) {
						System.out.println("Logged in !");
						DashboardPanel d = new DashboardPanel(user);
						d.setVisible(isValidUser);
						d.setSize(400, 400);
					} else {
						JOptionPane.showMessageDialog(this, "Invalid Credentials");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, "Something went wrong..");
				}

			} else {
				JOptionPane.showMessageDialog(this, "Please fill out all the field");
			}
		}
	}

//	public static void main(String args[]) {
//		LoginForm frame = new LoginForm();
//		frame.setVisible(true);
//		frame.setTitle("Login Form");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setBounds(100, 100, 700, 500);
//	}// END OF METHOD
}// END OF CLASS
