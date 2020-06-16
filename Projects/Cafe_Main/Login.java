package Cafe_Main;

// login class

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Login extends JFrame {

	private static final long serialVersionUID = -4628569482773516880L;

	Vector<Info> rowData = null;
	CoffeeDAO coffeeDAO = null;
	
	private JTextField textField;
	private JPasswordField passwordField;

	public Login() {
	
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 201);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 125, 91);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setBounds(96, 10, 29, 29);
		panel.add(lblNewLabel);

		JLabel lblPaword = new JLabel("PASSWORD :");
		lblPaword.setBounds(40, 49, 85, 29);
		panel.add(lblPaword);

		Panel panel_1 = new Panel();
		panel_1.setBounds(131, 0, 243, 91);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		textField = new JTextField();
		textField.setBounds(0, 10, 181, 28);
		panel_1.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(0, 51, 181, 28);
		panel_1.add(passwordField);

		Panel panel_2 = new Panel();
		panel_2.setBounds(0, 97, 410, 64);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JButton loginBtn = new JButton("�α���");
		loginBtn.setBounds(287, 10, 111, 34);
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();
				String pw = "";
				// passwordfield�� string������ ���� �ʱ� ������ �� �ܾ char�迭�� �־��� �� string���� ����ȯ �����־�� �Ѵ�
				char[] secret_pw  = passwordField.getPassword();
				
				CoffeeDAO coffeeDAO = new CoffeeDAO();
				
				for (char cha : secret_pw ) {
					Character.toString(cha);
					//pw �� �����ϱ�, pw �� ���� ��������� ����, ���� ������ �̾ �����ϴ� ���׿�����
					pw += (pw.equals("")) ? ""+cha+"" : ""+cha+"";
				}
				try {
					// coffeeDAO.login(id, pw) ������� 1�̸�
					if(coffeeDAO.login(id, pw) == 1) {
						new Main();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "id �Ǵ� password�� Ʋ���ϴ�");
						System.exit(0);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		panel_2.add(loginBtn);
		
		JButton resetBtn = new JButton("RESET");
		resetBtn.setBounds(148, 10, 111, 34);
		resetBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		panel_2.add(resetBtn);
		
		JButton guestBtn = new JButton("GUEST");
		guestBtn.setBounds(12, 10, 111, 34);
		guestBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new guestMain();
				dispose();
				
			}
			
		}) ;
		
		
		
		
		
		
		panel_2.add(guestBtn);
		
		setVisible(true);	
		setResizable(false);
	}

	public static void main(String[] args) {		
		new Login();
	}
}