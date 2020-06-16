package Cafe_Main;

import javax.swing.JFrame;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SearchDate extends JFrame {

	private static final long serialVersionUID = -6164585024334891592L;
	
	JTextField yearTf;
	JTextField monthTf;
	JTextField dayTf;
	JButton searchBtn;

	public SearchDate() {
		setTitle("날짜 검색");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 409, 138);
		getContentPane().setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(10, 10, 373, 50);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel year = new JLabel("년");
		year.setBounds(12, 22, 20, 15);
		panel.add(year);
		
		yearTf = new JTextField();
		yearTf.setBounds(33, 19, 65, 21);
		panel.add(yearTf);
		
		JLabel month = new JLabel("월");
		month.setBounds(137, 22, 20, 15);
		panel.add(month);
		
		monthTf = new JTextField();
		monthTf.setBounds(157, 19, 65, 21);
		panel.add(monthTf);
		
		JLabel day = new JLabel("일");
		day.setBounds(271, 22, 20, 15);
		panel.add(day);
		
		dayTf = new JTextField();
		dayTf.setBounds(292, 19, 65, 21);
		panel.add(dayTf);
		
		searchBtn = new JButton("검색");
		searchBtn.setBounds(148, 66, 97, 31);
		getContentPane().add(searchBtn);
		
		setVisible(true);	
	}
}
