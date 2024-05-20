package gpp;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

@SuppressWarnings("serial")
public class ViewExamForm extends JPanel {
	private String name;
	private int studentId;
	private JTable table;

	Object[] columnNames = { "ID", "Course_Code", "Course_Name", "Theory", "Practical", "Oral", "Term_Work" };
	Object[][] data = { { 1, "CM3102", "Operating System ", false, false, false, false },
			{ 2, "CM4102", "Seminar", false, false, false, false },
			{ 3, "IT4101", "Software Engineering", false, false, false, false },
			{ 4, "IT4103", "Java Programming II", false, false, false, false },
			{ 5, "IT4104", "Internet Of Things", false, false, false, false },
			{ 6, "CM5101", "ICT", false, false, false, false },
			{ 7, "MA4106", "Information Management", false, false, false, false } };
	private boolean isFormSubmitted = false;;

	ViewExamForm(String username, int studentId2, JTabbedPane tabs) {
		name = username;
		studentId = studentId2;
		System.out.println("ViewExamForm " + studentId);

		Object[][] newData = data;// { {} };
		try {
			Connection conn = Myconnection.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String query = "SELECT * FROM student_exam_form WHERE student_id = ?";
			// Creating the PreparedStatement object
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, studentId);
			ResultSet rs = (ResultSet) pstmt.executeQuery();

			int rowCount = 0;

			while (rs.next()) {
				isFormSubmitted = true;
				int id = rowCount + 1;
				String courseCode = rs.getString("course_code");
				String courseName = rs.getString("course_name");
				boolean theory = rs.getInt("theory") == 1 ? true : false;
				boolean practical = rs.getInt("practical") == 1 ? true : false;
				boolean oral = rs.getInt("oral") == 1 ? true : false;
				boolean term_work = rs.getInt("term_work") == 1 ? true : false;
				newData[rowCount][0] = id;
				newData[rowCount][1] = courseCode;
				newData[rowCount][2] = courseName;
				newData[rowCount][3] = theory;
				newData[rowCount][4] = practical;
				newData[rowCount][5] = oral;
				newData[rowCount][6] = term_work;
				rowCount++;
			}
			conn.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		data = newData;

		JButton b = new JButton("View Exam Form");
		b.setBounds(20, 130, 100, 100);
		JLabel header = new JLabel("");
		header.setOpaque(true);
		header.setIcon(new ImageIcon(Reg.class.getResource("/images/header.jpg")));
		header.setBounds(10, 25, 1380, 100);
		add(header);

		if (!isFormSubmitted)
			add(b);

		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("VIEW");
				new DashboardPanel(name);
			}
		});
		System.out.println("isFormSubmitted " + isFormSubmitted);

		if (isFormSubmitted) {
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data.length; j++) {
//					System.out.println(data[i][i]);
				}
			}
			DefaultTableModel model = new DefaultTableModel(data, columnNames);
			table = new JTable(model) {
				@Override
				public Class getColumnClass(int column) {
					switch (column) {
					case 0:
						return Integer.class;
					case 1:
						return String.class;
					case 2:
						return String.class;
					case 3:
						return Boolean.class;
					case 4:
						return Boolean.class;
					case 5:
						return Boolean.class;
					case 6:
						return Boolean.class;
					default:
						return Boolean.class;
					}
				}
			};

			table.setPreferredScrollableViewportSize(new Dimension(1000, 210));
			table.setRowHeight(30);
			table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));

			TableColumn col0 = table.getColumnModel().getColumn(0);
			col0.setPreferredWidth(5);

			TableColumn col1 = table.getColumnModel().getColumn(1);
			col1.setPreferredWidth(25);

			TableColumn col2 = table.getColumnModel().getColumn(2);
			col2.setPreferredWidth(40);

			TableColumn col3 = table.getColumnModel().getColumn(3);
			col3.setPreferredWidth(10);

			TableColumn col4 = table.getColumnModel().getColumn(4);
			col4.setPreferredWidth(10);

			TableColumn col5 = table.getColumnModel().getColumn(5);
			col5.setPreferredWidth(10);

			TableColumn col6 = table.getColumnModel().getColumn(6);
			col6.setPreferredWidth(10);
			JScrollPane scrollPane = new JScrollPane(table);
			System.out.println("Adding table to contoninae");
			add(scrollPane);
			System.out.println("Added to container");

		}
	}
}
