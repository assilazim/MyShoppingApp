package view;

import controller.StoreController;
import controller.UserController;
import model.Store;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.Color;

public class CustomerScreen {

	private JFrame frame;
	private JTextField textFieldCity;
	private JTextField textFieldCountry;
	private StoreController storeController;
	private Store selectedStore;
	private String userId;
	private UserController userController;

	/**
	 * Create the application.
	 */
	public CustomerScreen(String userId) {
		this.userController = new UserController();
		this.storeController = new StoreController();
		this.userId = userId;
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
		
		JLabel lblWelcome = new JLabel("Welcome! Please Select Store");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Dialog", Font.BOLD, 20));
		lblWelcome.setBounds(146, 12, 531, 15);
		frame.getContentPane().add(lblWelcome);
		
		JLabel lblSearchStoreBy = new JLabel("Search Store by Location");
		lblSearchStoreBy.setBounds(27, 57, 232, 15);
		frame.getContentPane().add(lblSearchStoreBy);

		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(34, 98, 101, 15);
		frame.getContentPane().add(lblCity);

		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(34, 126, 101, 15);
		frame.getContentPane().add(lblCountry);

		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(160, 96, 187, 19);
		frame.getContentPane().add(textFieldCity);

		textFieldCountry = new JTextField();
		textFieldCountry.setColumns(10);
		textFieldCountry.setBounds(160, 124, 187, 19);
		frame.getContentPane().add(textFieldCountry);
		
		JButton btnSearchStore = new JButton("Search Store");
		btnSearchStore.setBounds(445, 79, 149, 49);
		frame.getContentPane().add(btnSearchStore);

		JButton btnResetStore = new JButton("Reset Search");
		btnResetStore.setBounds(625, 79, 149, 49);
		frame.getContentPane().add(btnResetStore);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 180, 740, 251);
		frame.getContentPane().add(scrollPane);
		
		JList storeList = new JList();
		scrollPane.setViewportView(storeList);
		
		JButton btnGoToStore = new JButton("Go to Store");

		btnGoToStore.setBounds(625, 467, 149, 49);
		frame.getContentPane().add(btnGoToStore);
		
		JButton btnEditMyInfo = new JButton("Edit My Info");

		btnEditMyInfo.setBounds(434, 467, 149, 49);
		frame.getContentPane().add(btnEditMyInfo);

		JButton btnLogOut = new JButton("Log Out");

		btnLogOut.setBounds(246, 467, 149, 49);
		frame.getContentPane().add(btnLogOut);

		btnGoToStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedStore = (Store)storeList.getSelectedValue();
				if(selectedStore != null) {
					StoreShoppingScreen storeShoppingScreen = new StoreShoppingScreen(selectedStore.getStoreCode(),userId);
					storeShoppingScreen.getFrame().setVisible(true);
					frame.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null,"Please Select Store");
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
				List<Store> storeListFiltered = storeController.filterStoresList(textFieldCity.getText(),textFieldCountry.getText());
				storeList.setListData(storeListFiltered.toArray());
				textFieldCity.setText("");
				textFieldCountry.setText("");
			}
		});

		btnResetStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storeList.setListData(storeController.getStoreArrayList().toArray());
				textFieldCity.setText("");
				textFieldCountry.setText("");
			}
		});

		storeList.setListData(storeController.getStoreArrayList().toArray());
		
		JButton btnDeleteMyAccount = new JButton("Delete My Account");
		btnDeleteMyAccount.setBounds(34, 467, 165, 49);
		frame.getContentPane().add(btnDeleteMyAccount);

		btnEditMyInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					RegisterUserScreen registerUserScreen = new RegisterUserScreen(userId);
					registerUserScreen.getFrame().setVisible(true);
					frame.dispose();
			}
		});

		btnDeleteMyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userController.deleteUserById(userId)){
					JOptionPane.showMessageDialog(null,"Account Deleted Successfully!");
					userController.updateUserList();
					LoginScreen loginScreen = new LoginScreen();
					loginScreen.getFrame().setVisible(true);
					frame.dispose();
				}
			}
		});

	}

	public JFrame getFrame() {
		return frame;
	}
}
