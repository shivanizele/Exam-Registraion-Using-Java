package gpp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

@SuppressWarnings("serial")
public class Reg extends JPanel {

	JLabel l1, l2, l3, l4, l5, l6, l7, l8;
	JTextField tf1, tf2, tf5, tf6, tf7, username;
	JButton btn1, btn2;
	JPasswordField password;
	JLabel msg = new JLabel();

	public Reg() {
		Font f = new Font("Serif", Font.BOLD, 30);
		setSize(1380, 690);
		setLayout(null);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon background_image = new ImageIcon("header1.jpg");
		Image img = background_image.getImage();
		Image temp_img = img.getScaledInstance(1380, 690, Image.SCALE_SMOOTH);
		background_image = new ImageIcon(temp_img);

//		JButton btnLogout = new JButton("logout");
//		btnLogout.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				dispose();
//			}
//		});

		JLabel header = new JLabel("");
		header.setOpaque(true);
		header.setIcon(new ImageIcon(Reg.class.getResource("header1.jpg")));
		header.setBounds(10, 25, 1380, 100);
		add(header);

		l1 = new JLabel("Student Registration Form :");
		l1.setForeground(Color.blue);
		l1.setFont(new Font("Serif", Font.BOLD, 40));
		l2 = new JLabel("Name:");
		l2.setFont(new Font("Serif", Font.BOLD, 20));
		l3 = new JLabel("Email-ID:");
		l3.setFont(new Font("Serif", Font.BOLD, 20));
		l4 = new JLabel("Username:");
		l4.setFont(new Font("Serif", Font.BOLD, 20));
		l5 = new JLabel("Confirm Password:");
		l5.setFont(new Font("Serif", Font.BOLD, 20));
		l6 = new JLabel("Country:");
		l6.setFont(new Font("Serif", Font.BOLD, 20));
		l7 = new JLabel("State:");
		l7.setFont(new Font("Serif", Font.BOLD, 20));
		l8 = new JLabel("Phone No:");
		l8.setFont(new Font("Serif", Font.BOLD, 20));
		tf1 = new JTextField();
		tf2 = new JTextField();
		username = new JTextField();
		password = new JPasswordField();
		tf5 = new JTextField();
		tf6 = new JTextField();
		tf7 = new JTextField();
		btn1 = new JButton("Submit");
		btn2 = new JButton("Clear");
		btn1.setFont(new Font("Serif", Font.BOLD, 20));
		btn2.setFont(new Font("Serif", Font.BOLD, 20));

		l1.setBounds(328, 120, 717, 78);

		l2.setBounds(360, 200, 200, 30);
		l3.setBounds(360, 250, 200, 30);
		l4.setBounds(360, 300, 200, 30);
		l5.setBounds(360, 350, 200, 30);
		l6.setBounds(360, 400, 200, 30);
		l7.setBounds(360, 450, 200, 30);
		l8.setBounds(360, 500, 200, 30);

		tf1.setBounds(580, 200, 200, 30);
		tf2.setBounds(580, 250, 200, 30);
		username.setBounds(580, 300, 200, 30);
		password.setBounds(580, 350, 200, 30);
		tf5.setBounds(580, 400, 200, 30);
		tf6.setBounds(580, 450, 200, 30);
		tf7.setBounds(580, 500, 200, 30);

		btn1.setBounds(450, 550, 100, 30);
		btn2.setBounds(570, 550, 100, 30);

		add(l1);
		add(l2);
		add(tf1);
		add(l3);
		add(tf2);
		add(l4);
		add(username);
		add(l5);
		add(password);
		add(l6);
		add(tf5);
		add(l7);
		add(tf6);
		add(l8);
		add(tf7);
		add(btn1);
		add(btn2);
		add(msg);
		btn1.addActionListener(new ActionListener() {
			private Statement stmt;

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				if (e.getActionCommand() == "Submit") {
					String name = tf1.getText();
					String email = tf2.getText();
					String user_name = username.getText();
					String passwordValue = password.getText();
					String country = tf5.getText();
					String state = tf6.getText();
					String phone_number = tf7.getText();
					if (name.isEmpty() || email.isEmpty() || user_name.isEmpty() || passwordValue.isEmpty()
							|| country.isEmpty() || state.isEmpty() || phone_number.isEmpty()) {
						JOptionPane.showMessageDialog(btn1, "Please fill out all the field");
					} else {
						try {
							Connection conn = Myconnection.getConnection();
							stmt = (Statement) conn.createStatement();
							String query = " insert into registraion (name, email_id, password, country,state,phone_no,username)"
									+ " values (?, ?, ?, ?,?,?,?)";
							PreparedStatement preparedStmt = conn.prepareStatement(query);
							preparedStmt.setString(1, name);
							preparedStmt.setString(2, email);
							preparedStmt.setString(3, passwordValue);
							preparedStmt.setString(4, country);
							preparedStmt.setString(5, state);
							preparedStmt.setString(6, phone_number);
							preparedStmt.setString(7, user_name);
							preparedStmt.execute();

							// store data in login table;

							String query1 = " insert into login (username,password)" + " values (?, ?)";
							PreparedStatement preparedStmt1 = conn.prepareStatement(query1);
							preparedStmt1.setString(1, user_name);
							preparedStmt1.setString(2, passwordValue);

							preparedStmt1.execute();
							conn.close();
							JOptionPane.showMessageDialog(btn1, "Registration done successfully !");
							tf1.setText("");
							tf2.setText("");
							username.setText("");
							password.setText("");
							tf5.setText("");
							tf6.setText("");
							tf7.setText("");
							
						} catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(btn1, "Something went wrong !");
						}

					}

				}
			}
		});

		JLabel label = new JLabel("New label");
		label.setBounds(344, 36, 46, 14);
		add(label);
//		setVisible(true);
	}

	public static void main(String args[]) {
		new Reg();
	}

}
