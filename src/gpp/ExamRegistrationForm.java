package gpp;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

@SuppressWarnings("serial")
public class ExamRegistrationForm extends JPanel  {
	private JTable table;
	private String name = "";
	private boolean isFormSubmitted = false;
	int studentId;
	JButton bt = new JButton("Save");
	Object[] columnNames = { "ID", "Course_Code", "Course_Name", "Theory", "Practical", "Oral", "Term_Work" };

	ExamRegistrationForm(String username, int studentId2, Object[][] data) {
		name = username;
		try {
			Connection conn = Myconnection.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String query = "SELECT * FROM registraion WHERE username = ?";
			// Creating the PreparedStatement object
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
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
		try {
			Connection conn = Myconnection.getConnection();
			Statement stmt = (Statement) conn.createStatement();
			String query = "SELECT * FROM student_exam_form WHERE student_id = ?";
			// Creating the PreparedStatement object
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, studentId);
			ResultSet rs = (ResultSet) pstmt.executeQuery();

			System.out.println("Row count " + rs.getRow());
			isFormSubmitted = rs.getRow() > 0 ? true : false;
			System.out.println("Is form SUBMITTED befor WHile : " + isFormSubmitted);
			while (rs.next()) {
				isFormSubmitted = true;
				break;
			}
			System.out.println("Is form SUBMITTED AFTER WHile : " + isFormSubmitted);
			conn.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		JLabel header = new JLabel("");
		header.setOpaque(true);
		header.setIcon(new ImageIcon(Reg.class.getResource("/images/header.jpg")));
		header.setBounds(10, 25, 5000, 100);
		add(header);

		JLabel formStatus = new JLabel("You have already submitted the examinition form.");
		formStatus.setBounds(10, 120, 100, 25);
		formStatus.setFont(new Font("Arial", Font.BOLD, 20));
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
//	    setting width for column
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

//		bt.addActionListener(this);
		bt.setBounds(200, 800, 100, 100);
		//check logged user already submitted the exam form
		if (!isFormSubmitted) {
			add(bt);
			add(scrollPane);
		} else
			add(formStatus);

		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = Myconnection.getConnection();
					Statement stmt = (Statement) conn.createStatement();
					String query = "SELECT * FROM registraion WHERE username = ?";
					// Creating the PreparedStatement object
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, name);
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

				String query = " insert into student_exam_form (course_code, course_name, theory, practical,oral,term_work,student_id)"
						+ " values (?,?,?,?,?,?,?)";
				try {
					for (int i = 0; i < data.length; i++) {
						HashMap<Integer, String> map = new HashMap<>();
						for (int j = 0; j < data.length; j++) {
							Object value = table.getModel().getValueAt(i, j);
							data[i][j] = table.getModel().getValueAt(i, j);
							map.put(j, value.toString());
						}

						Connection conn = Myconnection.getConnection();
						Statement stmt = (Statement) conn.createStatement();
						PreparedStatement preparedStmt = conn.prepareStatement(query);

						for (Integer key : map.keySet()) {
							System.out.println(key + " = " + map.get(key));
							if (key != 0) {
								if (map.get(key).equals("true") || map.get(key).equals("false")) {
									int flag = map.get(key).equals("true") ? 1 : 0;
									preparedStmt.setInt(key, flag);
								} else {
									preparedStmt.setString(key, map.get(key));
								}
							}
						}
						preparedStmt.setInt(7, studentId);
						preparedStmt.execute();
						conn.close();
					}
					bt.setVisible(false);
					table.setVisible(false);
					isFormSubmitted = true;
					JOptionPane.showMessageDialog(bt, "Exam form submitted successfully !");
					new ExamRegistrationForm(name, studentId, data);
//					new ViewExamForm(name, studentId, data);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(bt, "Something went wrong !");
				}
			}
		});

	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//
//		try {
//			Connection conn = Myconnection.getConnection();
//			Statement stmt = (Statement) conn.createStatement();
//			String query = "SELECT * FROM registraion WHERE username = ?";
//			// Creating the PreparedStatement object
//			PreparedStatement pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, name);
//			ResultSet rs = (ResultSet) pstmt.executeQuery();
//			while (rs.next()) {
//				System.out.println(rs.getString("id"));
//				studentId = rs.getInt("id");
//				break;
//			}
//			conn.close();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//
//		String query = " insert into student_exam_form (course_code, course_name, theory, practical,oral,term_work,student_id)"
//				+ " values (?,?,?,?,?,?,?)";
//		try {
//			for (int i = 0; i < data1.length; i++) {
//				HashMap<Integer, String> map = new HashMap<>();
//				for (int j = 0; j < data1.length; j++) {
//					Object value = table.getModel().getValueAt(i, j);
//					data[i][j] = table.getModel().getValueAt(i, j);
//					map.put(j, value.toString());
//				}
//
//				Connection conn = Myconnection.getConnection();
//				Statement stmt = (Statement) conn.createStatement();
//				PreparedStatement preparedStmt = conn.prepareStatement(query);
//
//				for (Integer key : map.keySet()) {
//					System.out.println(key + " = " + map.get(key));
//					if (key != 0) {
//						if (map.get(key).equals("true") || map.get(key).equals("false")) {
//							int flag = map.get(key).equals("true") ? 1 : 0;
//							preparedStmt.setInt(key, flag);
//						} else {
//							preparedStmt.setString(key, map.get(key));
//						}
//					}
//				}
//				preparedStmt.setInt(7, studentId);
//				preparedStmt.execute();
//				conn.close();
//			}
//			this.setVisible(false);
//			table.setVisible(false);
//			isFormSubmitted = true;
//			JOptionPane.showMessageDialog(this, "Exam form submitted successfully !");
//			new ExamRegistrationForm(name, studentId, data);
//			new ViewExamForm(name, studentId, data);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			JOptionPane.showMessageDialog(this, "Something went wrong !");
//		}
//
//	}
}
