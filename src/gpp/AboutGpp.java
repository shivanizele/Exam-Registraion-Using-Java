package gpp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
class AboutGpp extends JPanel {
	Container c;
	final String text = "The Government Polytechnic, Pune, was established in the year 1957. Thirty seven years later, in May 1994, the institute was awarded academic autonomy. Today it houses in its 28 acre campus, a main building, students hostels, classrooms for various faculties including Electronic Computers, Library, Canteen, post office and other facilities and has the capacity to groom more than 300 students in various engineering fields every year. Over the last four decades, the institute has produced more than 8000 diploma engineers in different disciplines, has won several awards in academics as well as socio-cultural activities and on the whole has succeeded in contributing humble positive efforts towards the building of a progressive society.";
	JLabel aboutGPPContent = new JLabel("<html>" + text + "</html>");

	JLabel visionLabel = new JLabel("Vision");
	final String visionContext = "To develop self reliant, versatile, innovative, quality conscious engineers for betterment of society.";
	JLabel visionContextLabel = new JLabel("<html>" + visionContext + "</html>");

	JLabel missionLabel = new JLabel("Mission");
//	final String  missionContext = "To develop self reliant, versatile, innovative, quality conscious engineers for betterment of society.";
	JLabel missionContextLabel = new JLabel("<html>"
			+ "<ul><li>Update curricula in association with stakeholders.</li><li>Modernize infrastructure & facilities.\r\n"
			+ "</li><li>Set up strategic alliance with industries</li> <li>Enhance e-governance</li> <li>Continuous development of faculty & staff.</li><ul>"
			+ "</html>");

	Font f = new Font("Arial", Font.BOLD, 16);

	AboutGpp() {
		setLayout(null);
		JLabel header = new JLabel("");
		header.setOpaque(true);
		header.setIcon(new ImageIcon(Reg.class.getResource("/images/header.jpg")));
		header.setBounds(10, 25, 1380, 100);
		add(header);
		aboutGPPContent.setBounds(360, 80, 1000, 430);
		aboutGPPContent.setFont(new Font("Arial", Font.ITALIC, 25));
		add(aboutGPPContent);

		visionLabel.setBounds(360, 260, 1000, 430);
		visionLabel.setFont(new Font("Arial", Font.BOLD, 50));
		add(visionLabel);

		visionContextLabel.setBounds(360, 320, 1000, 430);
		visionContextLabel.setFont(new Font("Arial", Font.ITALIC, 25));
		add(visionContextLabel);

		missionLabel.setBounds(360, 380, 1000, 430);
		missionLabel.setFont(new Font("Arial", Font.BOLD, 50));
		add(missionLabel);

		missionContextLabel.setBounds(360, 500, 1000, 430);
		missionContextLabel.setFont(new Font("Arial", Font.ITALIC, 25));
		add(missionContextLabel);

	}

}
