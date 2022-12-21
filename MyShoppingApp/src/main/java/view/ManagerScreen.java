package view;

import controller.StoreController;
import model.Item;
import model.Store;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JCheckBox;

public class ManagerScreen {

	private JFrame frame;
	private JTextField textFieldStreetName;
	private JTextField textFieldCity;
	private JTextField textFieldCountry;
	private Store store;
	private String storeCode;
	private StoreController storeController;
	private JTextField textFieldCategory;
	private JTextField textFieldAisle;

	public JFrame getFrame() {
		return frame;
	}


	/**
	 * Create the application.
	 */
	public ManagerScreen(String storeCode) {
		this.storeCode = storeCode;
		this.storeController = new StoreController();
		if(storeCode != null){
			this.store = storeController.getStoreByStoreCode(storeCode);
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome Manager");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Dialog", Font.BOLD, 20));
		lblWelcome.setBounds(227, 12, 336, 33);
		frame.getContentPane().add(lblWelcome);

		JLabel storeNameLbl = new JLabel();
		storeNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		storeNameLbl.setFont(new Font("Dialog", Font.BOLD, 14));
		storeNameLbl.setBounds(151, 34, 526, 26);
		frame.getContentPane().add(storeNameLbl);

		JLabel lblSearchStoreBy = new JLabel("Add Item");
		lblSearchStoreBy.setBounds(27, 57, 232, 15);
		frame.getContentPane().add(lblSearchStoreBy);
		
		JLabel lblStreetName = new JLabel("Item Name");
		lblStreetName.setBounds(37, 84, 101, 15);
		frame.getContentPane().add(lblStreetName);
		
		JLabel lblCity = new JLabel("Price");
		lblCity.setBounds(37, 113, 101, 15);
		frame.getContentPane().add(lblCity);
		
		JLabel lblCountry = new JLabel("Quantity");
		lblCountry.setBounds(37, 141, 101, 15);
		frame.getContentPane().add(lblCountry);
		
		textFieldStreetName = new JTextField();
		textFieldStreetName.setBounds(163, 82, 187, 19);
		frame.getContentPane().add(textFieldStreetName);
		textFieldStreetName.setColumns(10);
		
		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(163, 111, 187, 19);
		frame.getContentPane().add(textFieldCity);
		
		textFieldCountry = new JTextField();
		textFieldCountry.setColumns(10);
		textFieldCountry.setBounds(163, 139, 187, 19);
		frame.getContentPane().add(textFieldCountry);
		
		JButton btnSearchStore = new JButton("Add Item");
		btnSearchStore.setBounds(445, 79, 149, 49);
		frame.getContentPane().add(btnSearchStore);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 258, 716, 207);
		frame.getContentPane().add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(604, 488, 149, 49);
		frame.getContentPane().add(btnLogOut);
		
		JButton btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.setBounds(621, 79, 149, 49);
		frame.getContentPane().add(btnRemoveItem);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(37, 170, 101, 15);
		frame.getContentPane().add(lblCategory);
		
		textFieldCategory = new JTextField();
		textFieldCategory.setColumns(10);
		textFieldCategory.setBounds(163, 168, 187, 19);
		frame.getContentPane().add(textFieldCategory);
		
		JLabel Aisle = new JLabel("Aisle");
		Aisle.setBounds(37, 199, 101, 15);
		frame.getContentPane().add(Aisle);
		
		textFieldAisle = new JTextField();
		textFieldAisle.setColumns(10);
		textFieldAisle.setBounds(163, 197, 187, 19);
		frame.getContentPane().add(textFieldAisle);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("On Sale");
		chckbxNewCheckBox.setBounds(36, 227, 129, 23);
		frame.getContentPane().add(chckbxNewCheckBox);

		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() >= 0){
					if(storeController.removeItemFromStore(storeCode,list.getSelectedIndex())){
						storeController.updateStoreList();
						ManagerScreen managerScreen = new ManagerScreen(storeCode);
						managerScreen.getFrame().setVisible(true);
						frame.dispose();
					}
				}
			}
		});

		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginScreen loginScreen = new LoginScreen();
				loginScreen.getFrame().setVisible(true);
				frame.dispose();
			}
		});

		btnSearchStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldStreetName.isValid() && textFieldCity.isValid() && textFieldCountry.isValid() && store != null
				&& textFieldAisle.isValid() && textFieldCategory.isValid()){
					try {
						double price = Double.parseDouble(textFieldCity.getText());
						int qty = Integer.parseInt(textFieldCountry.getText());
						String aisle = textFieldAisle.getText();
						String category = textFieldCategory.getText();
						boolean isSale = chckbxNewCheckBox.isSelected();
						Item item = new Item(textFieldStreetName.getText(),price, qty,aisle,category,isSale);
						if(storeController.addItemToStore(store.getStoreCode(),item)){
							storeController.updateStoreList();
							ManagerScreen managerScreen = new ManagerScreen(storeCode);
							managerScreen.getFrame().setVisible(true);
							frame.dispose();
						}
					}
					catch (NumberFormatException error){
						System.out.println(error);
					}

				}
			}
		});


		if(store == null){
			storeNameLbl.setText("You Have No Stores Currently!");
		}
		else{
			storeNameLbl.setText("Let's Manage Your Store. Your Store Name : "+store.getName());
			list.setListData(store.getItemArrayList().toArray());

		}

	}
}
