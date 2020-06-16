package Cafe_Main;

// ���� gui

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;

public class Sales extends JFrame {

	private static final long serialVersionUID = -6764304939888109722L;

	Vector<Info> rowData = null;
	CoffeeDAO coffeeDAO = null;

	String[] ColName = {"���� ���", "MenuCode", "�޴�", "����", "��¥"};
	String[] sellName = {"�޴�", "�Ǹ��ܼ�"};
	JTable table;
	JTable table2;
	JLabel totalLabel;
	JLabel cashLabel;
	JLabel cardLabel;


	public Sales() {		
		super("������");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(1020, 120, 592, 461);

		coffeeDAO = new CoffeeDAO();
		rowData = coffeeDAO.GetAllSellList();

		Panel tablePanel = new Panel();
		tablePanel.setBounds(10, 10, 562, 285);
		getContentPane().add(tablePanel);
		tablePanel.setLayout(null);	
		
		
		

		table = new JTable(coffeeDAO.makeArr(coffeeDAO.GetAllSellList()),ColName);
		// �� ���� ����
		table.getColumn(ColName[4]).setPreferredWidth(170);
		table.setRowHeight(38);
		table.setBounds(10, 10, 562, 285);
		tablePanel.add(table);

		table.getTableHeader().setReorderingAllowed(false); // �� �� �巡�� ���� 
		table.getTableHeader().setResizingAllowed(false); // �� �� ������ ���� ����
		table.setRowSelectionAllowed(false); // �� ���� �� �巡�� ����
		
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 562, 285);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tablePanel.add(scrollPane);

		Panel buttonPanel = new Panel();
		buttonPanel.setBounds(10, 357, 562, 65);
		getContentPane().add(buttonPanel);

		JButton posBtn = new JButton("POS");
		posBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//â�ݱ�
				setVisible(false);
			}
		});
		buttonPanel.setLayout(new GridLayout(0, 4, 20, 0));
		buttonPanel.add(posBtn);

		JButton salesBtn = new JButton("����");
		salesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//���̺� ��ü ���� ����Ʈ ���
				rowData =coffeeDAO.GetAllSellList();
				int totalSum=0;
				for(int i=0; i<rowData.size();i++){					
					totalSum += rowData.get(i).getPrice();
				}			
				rowData = coffeeDAO.GetTotalCash();
				int totalCash = 0;
				for (int i = 0; i < rowData.size(); i++) {
					totalCash += rowData.get(i).getTotalCash();
				}
				rowData = coffeeDAO.GetTotalCard();
				int totalCard = 0;
				for (int i = 0; i < rowData.size(); i++) {
					totalCard += rowData.get(i).getTotalCard();
				}		
				totalLabel.setText("�� ���� : " + Integer.toString(totalSum));				
				cardLabel.setText("ī�� ���� : " + Integer.toString(totalCard));
				cashLabel.setText("���� ���� : " + Integer.toString(totalCash));
			}
		});
		buttonPanel.add(salesBtn);

		JButton todayBtn = new JButton("���� ����");
		todayBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy�� MM�� dd��");
				Date now = new Date();
				String currentTime = simpleDateFormat.format (now);
				
				rowData =coffeeDAO.currentCellTotal();
				int currentTotal = 0;
				String beforeDate;
				for (int i = 0; i < rowData.size(); i++) {				
					beforeDate = rowData.get(i).getDate();
					if (currentTime.compareTo(beforeDate) == 0) {
						currentTotal = rowData.get(i).getPrice();	
					}									
				}
				
				rowData =coffeeDAO.currentCellCash();
				int currentCash = 0;
				for (int i = 0; i < rowData.size(); i++) {				
					beforeDate = rowData.get(i).getDate();
					if (currentTime.compareTo(beforeDate) == 0) {
						currentCash = rowData.get(i).getTotalCash();	
					}									
				}
				
				rowData =coffeeDAO.currentCellCard();
				int currentCard = 0;
				for (int i = 0; i < rowData.size(); i++) {				
					beforeDate = rowData.get(i).getDate();
					if (currentTime.compareTo(beforeDate) == 0) {
						currentCard = rowData.get(i).getTotalCard();	
					}									
				}				
				totalLabel.setText("������ ���� : " + Integer.toString(currentTotal));		
				cashLabel.setText("���� ���� ���� : " + Integer.toString(currentCash));
				cardLabel.setText("���� ī�� ���� : " + Integer.toString(currentCard));
				
			}
		});
		buttonPanel.add(todayBtn);


		JButton menuBtn = new JButton("�޴����Ǹ�");
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//���̺� �޴��� �ǸŸ���Ʈ
				rowData =coffeeDAO.GetAllSellList();
				scrollPane.setVisible(false);
				table2 = new JTable(coffeeDAO.makelistArr(coffeeDAO.Getsellcount()),sellName);
				table2.setRowHeight(38);
				table2.setBounds(1, 27, 450, 288);
				table2.getTableHeader().setReorderingAllowed(false); // �� �� �巡�� ���� 
				table2.getTableHeader().setResizingAllowed(false); // �� �� ������ ���� ����
				table2.setRowSelectionAllowed(false); // �� ���� �� �巡�� ����
				
				
				
				tablePanel.add(table2);
				
				
				

				JScrollPane scrollPane = new JScrollPane(table2);
				scrollPane.setBounds(0, 0, 562, 285);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				tablePanel.add(scrollPane);				
			}
		});
		buttonPanel.add(menuBtn);

		Panel salesPanel = new Panel();
		salesPanel.setBounds(10, 309, 574, 39);
		getContentPane().add(salesPanel);
		salesPanel.setLayout(null);

		totalLabel = new JLabel("");
		totalLabel.setFont(new Font("HY����M", Font.PLAIN, 15));
		totalLabel.setBounds(12, 0, 173, 35);
		salesPanel.add(totalLabel);

		cashLabel= new JLabel("");
		cashLabel.setFont(new Font("HY����M", Font.PLAIN, 15));
		cashLabel.setBounds(204, 0, 173, 35);
		salesPanel.add(cashLabel);

		cardLabel = new JLabel("");
		cardLabel.setFont(new Font("HY����M", Font.PLAIN, 15));
		cardLabel.setBounds(401, 0, 173, 35);
		salesPanel.add(cardLabel);

		setResizable(false);
		getContentPane().setLayout(null);

		setVisible(true);
	}
}