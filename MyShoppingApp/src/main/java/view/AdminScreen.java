package view;

import controller.StoreController;
import controller.UserController;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AdminScreen {

	private JFrame frame;
	private StoreController storeController;
	private UserController userController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminScreen window = new AdminScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminScreen() {
		this.storeController = new StoreController();
		this.userController = new UserController();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcomeAdmin = new JLabel("Welcome Admin");
		lblWelcomeAdmin.setFont(new Font("Dialog", Font.BOLD, 20));
		lblWelcomeAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeAdmin.setBounds(105, 46, 599, 24);
		frame.getContentPane().add(lblWelcomeAdmin);
		
		JLabel lblNewLabel = new JLabel("Store List");
		lblNewLabel.setBounds(64, 82, 70, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblManagerList = new JLabel("Manager List");
		lblManagerList.setBounds(638, 82, 109, 15);
		frame.getContentPane().add(lblManagerList);
		
		String stores[]= { "MondayStore","TuesdayStore","WednesdayStore",
                "ThursdayStore","FridayStore","SaturdayStore","SundayStore","FridayStore","SaturdayStore","SundayStore","FridayStore","SaturdayStore","SundayStore"
                ,"SundayStore","SundayStore","SundayStore","SundayStore","SundayStore","SundayStore","SundayStore","SundayStore","SundayStore"};
		String managers[]= { "MondayManager","TuesdayManager","WednesdayManager",
                "ThursdayManager","FridayManager","SaturdayManager","SundayManager","FridayManager","SaturdayManager","SundayManager"
                ,"SundayManager","FridayManager","SaturdayManager","SundayManager"
                ,"SundayManager","FridayManager","SaturdayManager","SundayManager","SundayManager","FridayManager","SaturdayManager","SundayManager"};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 109, 548, 272);
		frame.getContentPane().add(scrollPane);
		
		JList storeList = new JList();
		scrollPane.setViewportView(storeList);
		
		storeList.setListData(this.storeController.getStoreArrayList().toArray());
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(624, 109, 123, 272);
		frame.getContentPane().add(scrollPane_1);
		
		JList managerList = new JList();
		scrollPane_1.setViewportView(managerList);
		managerList.setListData(this.userController.getManagerList().toArray());
		
		JButton btnAddStore = new JButton("Add Store");
		btnAddStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStoreScreen addStoreScreen = new AddStoreScreen();
				addStoreScreen.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnAddStore.setBounds(40, 415, 132, 25);
		frame.getContentPane().add(btnAddStore);
		
		JButton btnAddRemove = new JButton("Remove Store");

		btnAddRemove.setBounds(40, 452, 132, 25);
		frame.getContentPane().add(btnAddRemove);
		
		JButton btnEditStore = new JButton("Edit Store");
		btnEditStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(storeList.getSelectedIndex() >= 0){
					AddStoreScreen addStoreScreen = new AddStoreScreen(storeList.getSelectedIndex());
					addStoreScreen.getFrame().setVisible(true);
					frame.dispose();
				}
			}
		});
		btnEditStore.setBounds(40, 489, 132, 25);
		frame.getContentPane().add(btnEditStore);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginScreen loginScreen = new LoginScreen();
				loginScreen.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnLogOut.setBounds(600, 527, 156, 25);
		frame.getContentPane().add(btnLogOut);

		btnAddRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(storeList.getSelectedIndex() >= 0){
					if(storeController.removeStore(storeList.getSelectedIndex())){
						storeController.updateStoreList();
						frame.dispose();
						AdminScreen adminScreen = new AdminScreen();
						adminScreen.getFrame().setVisible(true);
					}
				}
			}
		});

		JButton btnSetManager = new JButton("Assign Manager to Store");
		btnSetManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(managerList.getSelectedIndex() >= 0 && storeList.getSelectedIndex() >= 0){
					if(userController.addStoreToManager(managerList.getSelectedIndex(),storeController.getStoreArrayList().get(storeList.getSelectedIndex()).getStoreCode())){
						userController.updateManagerList();
						JOptionPane.showMessageDialog(null,"Manager Assigned Successfully!");
						frame.dispose();
						AdminScreen adminScreen = new AdminScreen();
						adminScreen.getFrame().setVisible(true);
					}
				}
			}
		});
		btnSetManager.setBounds(271, 413, 229, 49);
		frame.getContentPane().add(btnSetManager);

		JButton btnDeassignManager = new JButton("Remove Manager from assigned Store");
		btnDeassignManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(managerList.getSelectedIndex() >= 0 ){
					if(userController.removeStoreFromManager(managerList.getSelectedIndex())){
						userController.updateManagerList();
						JOptionPane.showMessageDialog(null,"Manager Removed from Assigned Store Successfully!");
						frame.dispose();
						AdminScreen adminScreen = new AdminScreen();
						adminScreen.getFrame().setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(null,"Manager Remove Unsuccessful or Manager has no Stores ");
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"Please Select Manager");
				}
			}
		});
		btnDeassignManager.setBounds(233, 489, 315, 49);
		frame.getContentPane().add(btnDeassignManager);
		
		JButton btnAddManager = new JButton("Add Manager");
		btnAddManager.setBounds(600, 415, 156, 25);
		frame.getContentPane().add(btnAddManager);
		
		JButton btnRemoveManager = new JButton("Remove Manager");
		btnRemoveManager.setFont(new Font("Dialog", Font.BOLD, 12));
		btnRemoveManager.setBounds(600, 452, 156, 25);
		frame.getContentPane().add(btnRemoveManager);
		
		JButton btnEditManager = new JButton("Edit Manager");
		btnEditManager.setBounds(600, 489, 156, 25);
		frame.getContentPane().add(btnEditManager);

		btnAddManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterManagerScreen registerManagerScreen = new RegisterManagerScreen();
				registerManagerScreen.getFrame().setVisible(true);
				frame.dispose();
			}
		});

		btnEditManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(managerList.getSelectedIndex() >= 0){
					RegisterManagerScreen registerManagerScreen = new RegisterManagerScreen(managerList.getSelectedIndex());
					registerManagerScreen.getFrame().setVisible(true);
					frame.dispose();
				}
			}
		});

		btnRemoveManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(managerList.getSelectedIndex() >= 0){
					if(userController.removeManager(managerList.getSelectedIndex())){
						userController.updateManagerList();
						frame.dispose();
						AdminScreen adminScreen = new AdminScreen();
						adminScreen.getFrame().setVisible(true);
					}
				}
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}
}
