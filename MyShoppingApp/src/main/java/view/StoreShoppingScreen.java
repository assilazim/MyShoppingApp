package view;

import controller.StoreController;
import controller.UserController;
import model.Item;
import model.User;

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
import java.util.List;
import java.awt.Color;

public class StoreShoppingScreen {

	private JFrame frame;
	private JTextField textFieldStreetName;
	private JTextField textFieldCity;
	private StoreController storeController;
	private List<Item> itemList;
	private String storeCode;
	private UserController userController;
	private String userId;

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public StoreShoppingScreen(String storeCode, String userId) {
		this.userId = userId;
		this.userController = new UserController();
		this.storeCode = storeCode;
		this.storeController = new StoreController();
		this.itemList = this.storeController.getStoreByStoreCode(storeCode).getItemArrayList();
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
		
		JLabel lblWelcome = new JLabel("Please Select Items and add to List");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Dialog", Font.BOLD, 20));
		lblWelcome.setBounds(134, 12, 543, 15);
		frame.getContentPane().add(lblWelcome);

		JLabel lblCity = new JLabel("Item Name");
		lblCity.setBounds(39, 76, 101, 15);
		frame.getContentPane().add(lblCity);

		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(165, 74, 187, 19);
		frame.getContentPane().add(textFieldCity);
		
		JButton btnSearchItem = new JButton("Search Item");
		btnSearchItem.setBounds(259, 115, 127, 34);
		frame.getContentPane().add(btnSearchItem);

		JButton btnResetSearch = new JButton("Reset Search");
		btnResetSearch.setBounds(94, 115, 141, 34);
		frame.getContentPane().add(btnResetSearch);
		
		JButton btnAddToMyList = new JButton("Add to My List");
		btnAddToMyList.setBounds(587, 66, 149, 49);
		frame.getContentPane().add(btnAddToMyList);
		
		JButton btnGoToStore = new JButton("Go Back");
		btnGoToStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerScreen customerScreen = new CustomerScreen(userId);
				customerScreen.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnGoToStore.setBounds(625, 467, 149, 49);
		frame.getContentPane().add(btnGoToStore);
		
		JLabel lblNewLabel = new JLabel("My List");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(577, 140, 197, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JList jItemList = new JList();
		jItemList.setBounds(23, 181, 566, 248);
		frame.getContentPane().add(jItemList);

		jItemList.setListData(itemList.toArray());
		
		JList myList = new JList();
		myList.setBounds(601, 181, 187, 248);
		frame.getContentPane().add(myList);
		if(userController.getUserById(userId) != null) {
			myList.setListData(userController.getUserById(userId).getPurchasedItemList().toArray());
		}

		btnSearchItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldCity.isValid()) {
					jItemList.setListData(storeController.filterStoreItems(storeCode,textFieldCity.getText()).toArray());
				}
			}
		});

		btnResetSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jItemList.setListData(itemList.toArray());
			}
		});

		btnAddToMyList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item)jItemList.getSelectedValue();
				if(item != null && userController.addItemToLoggedUserList(userId,item)){
					userController.updateUserList();
					StoreShoppingScreen storeShoppingScreen = new StoreShoppingScreen(storeCode,userId);
					storeShoppingScreen.getFrame().setVisible(true);
					frame.dispose();
				}
			}
		});
	}

}
