package gpp;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

@SuppressWarnings("serial")
public class DashboardPanel extends JFrame {

	JTabbedPane tabs;
	Object[][] data = { { 1, "CM3102", "Operating System ", false, false, false, false },
			{ 2, "CM4102", "Seminar", false, false, false, false },
			{ 3, "IT4101", "Software Engineering", false, false, false, false },
			{ 4, "IT4103", "Java Programming II", false, false, false, false },
			{ 5, "IT4104", "Internet Of Things", false, false, false, false },
			{ 6, "CM5101", "ICT", false, false, false, false },
			{ 7, "MA4106", "Information Management", false, false, false, false } };

	private int studentId;

	DashboardPanel(String username) {
		super("GPP Dashboard");
		System.out.println(username);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			Connection conn = Myconnection.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String query = "SELECT * FROM registraion WHERE username = ?";
			// Creating the PreparedStatement object
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("id"));
				studentId = rs.getInt("id");
				break;
			}
			conn.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		tabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
		tabs.setFont(new Font("Arial", Font.BOLD, 20));
		tabs.setBounds(0, 1000, 2000, 100);
		tabs.addTab("About GPP", new AboutGpp());
		tabs.addTab("MyInfo", new MyInfo(username));
		tabs.addTab("Exam Registration", null);//new ExamRegistrationForm(username, studentId, data)
		tabs.addTab("View Exam Form",null); // new ViewExamForm(username, studentId, tabs)
		tabs.addTab("Logout", new JPanel());
		tabs.addTab(username, null);
		tabs.setEnabledAt(5, false);

		tabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println("Tab: " + tabs.getSelectedIndex());
				switch (tabs.getSelectedIndex()) {
				case 0:
					new AboutGpp();
					break;
				case 1:
					new MyInfo(username);
					break;
				case 2:
					tabs.setComponentAt(2, new ExamRegistrationForm(username, studentId, data));
					break;
				case 3:
					tabs.setComponentAt(3, new ViewExamForm(username, studentId, tabs));
					break;
				case 4:
					System.exit(DISPOSE_ON_CLOSE);
					break;
				default:
					new JPanel();
				}

			}
		});

		getContentPane().add(tabs);
	}

	public static void main(String args[]) throws Exception {
		DashboardPanel frame = new DashboardPanel(null);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
}
