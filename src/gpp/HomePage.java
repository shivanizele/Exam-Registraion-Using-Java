package gpp;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class HomePage extends JFrame {

	JTabbedPane tabs;

	public HomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
		tabs.setFont(new Font("Arial", Font.BOLD, 20));
		tabs.setBounds(0, 1000, 2000, 100);
		tabs.addTab("Login", new LoginForm());
		tabs.addTab("Registration", new Reg());
		getContentPane().add(tabs);
	}

	public static void main(String args[]) throws Exception {
		HomePage frame = new HomePage();
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
}
