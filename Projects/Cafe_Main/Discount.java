package Cafe_Main;

// menu ���� gui

import javax.swing.JButton;
import javax.swing.JFrame;

public class Discount extends JFrame {

	private static final long serialVersionUID = 8951179806606896315L;
	
	JButton btn10, btn20, btnFree;

	public Discount() {
		setTitle("����");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(1020, 100, 373, 100);
		getContentPane().setLayout(null);
		
		btn10 = new JButton("10%����");
		btn10.setBounds(12, 10, 97, 45);
		getContentPane().add(btn10);
		
		
		btn20 = new JButton("20%����");
		btn20.setBounds(110, 10, 97, 45);
		getContentPane().add(btn20);
		
		btnFree = new JButton("����Ʈ���������");
		btnFree.setBounds(208, 10, 147, 45);
		getContentPane().add(btnFree);
	
		
		setResizable(false);
		setVisible(true);
	}
}