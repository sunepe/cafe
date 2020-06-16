package Cafe_Main;

// menu ���� gui

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateMenu extends JFrame {

	private static final long serialVersionUID = -7512119197350873613L;
	
	private JTextField menuCodetf;
	private JTextField menuNametf;
	private JTextField menuPricetf;
	
	
	
	
	public CreateMenu() {
		


		setTitle("�޴�����");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 259, 344);
		getContentPane().setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(12, 10, 82, 220);
		getContentPane().add(panel1);
		panel1.setLayout(null);
		
		JLabel menuCodeLbl = new JLabel("�޴� �ڵ�");
		menuCodeLbl.setBounds(12, 30, 65, 36);
		panel1.add(menuCodeLbl);
		
		JLabel menuNameLbl = new JLabel("�޴� �̸�");
		menuNameLbl.setBounds(12, 90, 65, 36);
		panel1.add(menuNameLbl);
		
		JLabel menuPriceLbl = new JLabel("�޴� ����");
		menuPriceLbl.setBounds(12, 150, 65, 36);
		panel1.add(menuPriceLbl);
		
		JPanel panel3 = new JPanel();
		panel3.setBounds(95, 10, 140, 220);
		getContentPane().add(panel3);
		panel3.setLayout(null);
		
		menuCodetf = new JTextField();
		menuCodetf.setBounds(12, 33, 104, 33);
		panel3.add(menuCodetf);
		menuCodetf.setColumns(10);
		
		menuNametf = new JTextField();
		menuNametf.setColumns(10);
		menuNametf.setBounds(12, 94, 104, 33);
		panel3.add(menuNametf);
		
		menuPricetf = new JTextField();
		menuPricetf.setColumns(10);
		menuPricetf.setBounds(12, 153, 104, 33);
		panel3.add(menuPricetf);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(12, 240, 223, 60);
		getContentPane().add(panel2);
		panel2.setLayout(null);
		
		JButton btnNewButton = new JButton("����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CoffeeDAO coffeeDAO = new CoffeeDAO();	
				String menuCode = menuCodetf.getText();
				String menuName = menuNametf.getText();
				// �޴������� int�� �޾ƾ� �ϱ� ������ textfield�� �ۼ��� ���� integer�� ����ȯ ���Ѿ� ��
				int menuPrice = Integer.parseInt(menuPricetf.getText());
				
				int n = coffeeDAO.menuAdd(menuCode, menuName, menuPrice);
				// ������� 1�� ������
				if(n==1) {
					// JOptionPane.YES_OPTION : Ȯ�ι�ư�� �ִ� ConfirmDialog
					int result = JOptionPane.showConfirmDialog(null, "�޴��� �����Ǿ����ϴ�.", "�޴�����", JOptionPane.YES_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						dispose();
					}
				}
			}
		});
		btnNewButton.setBounds(58, 0, 97, 40);
		panel2.add(btnNewButton);		
		
		setVisible(true);

	}
}
