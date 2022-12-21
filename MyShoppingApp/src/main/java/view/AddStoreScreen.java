package view;

import controller.StoreController;
import model.Store;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddStoreScreen {

	private JFrame frame;
	private JTextField textFieldName;
	private JTextField textFieldStreetName;
	private StoreController storeController;
	private JTextField textFieldStreetNumber;
	private JTextField textFieldPostalCode;
	private JTextField textFieldCity;
	private JTextField textFieldCountry;
	private JTextField textFieldOpenHours;
	private Integer editStoreScreenIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStoreScreen window = new AddStoreScreen();
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
	public AddStoreScreen() {
		this.storeController = new StoreController();
		initialize();
	}

	public AddStoreScreen(Integer editStoreScreenIndex) {
		this.editStoreScreenIndex = editStoreScreenIndex;
		this.storeController = new StoreController();
		initialize();
		if(this.editStoreScreenIndex != null && storeController.getStoreArrayList().get(editStoreScreenIndex) != null){
			setValues();
		}
	}

	private void setValues(){
		Store store = storeController.getStoreArrayList().get(editStoreScreenIndex);
		if(store.getName() != null) {textFieldName.setText(store.getName());}
		if(store.getStreetName() != null) {textFieldStreetName.setText(store.getStreetName());}
		if(store.getStreetNo() != null) {textFieldStreetNumber.setText(store.getStreetNo());}
		if(store.getPostalCode() != null) {textFieldPostalCode.setText(store.getPostalCode());}
		if(store.getCity() != null) {textFieldCity.setText(store.getCity());}
		if(store.getCountry() != null) {textFieldCountry.setText(store.getCountry());}
		if(store.getOpeningHours() != null) {textFieldOpenHours.setText(store.getOpeningHours());}
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
		
		JLabel lblPleaseEnterInformation = new JLabel("Please Enter Information");
		lblPleaseEnterInformation.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPleaseEnterInformation.setBounds(254, 104, 331, 15);
		frame.getContentPane().add(lblPleaseEnterInformation);
		
		JLabel lblStreetName = new JLabel("Street Name");
		lblStreetName.setBounds(204, 229, 106, 15);
		frame.getContentPane().add(lblStreetName);
		
		JLabel lblStreetNumber = new JLabel("Street Number");
		lblStreetNumber.setBounds(204, 257, 106, 15);
		frame.getContentPane().add(lblStreetNumber);
		
		JLabel lblPostalCode = new JLabel("Postal Code");
		lblPostalCode.setBounds(204, 284, 106, 15);
		frame.getContentPane().add(lblPostalCode);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(204, 311, 106, 15);
		frame.getContentPane().add(lblCity);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(204, 335, 106, 15);
		frame.getContentPane().add(lblCountry);

		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(340, 197, 245, 19);
		frame.getContentPane().add(textFieldName);

		textFieldStreetName = new JTextField();
		textFieldStreetName.setColumns(10);
		textFieldStreetName.setBounds(340, 225, 245, 19);
		frame.getContentPane().add(textFieldStreetName);
		
		textFieldStreetNumber = new JTextField();
		textFieldStreetNumber.setColumns(10);
		textFieldStreetNumber.setBounds(340, 255, 245, 19);
		frame.getContentPane().add(textFieldStreetNumber);
		
		textFieldPostalCode = new JTextField();
		textFieldPostalCode.setColumns(10);
		textFieldPostalCode.setBounds(340, 282, 245, 19);
		frame.getContentPane().add(textFieldPostalCode);
		
		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(340, 307, 245, 19);
		frame.getContentPane().add(textFieldCity);
		
		textFieldCountry = new JTextField();
		textFieldCountry.setColumns(10);
		textFieldCountry.setBounds(340, 333, 245, 19);
		frame.getContentPane().add(textFieldCountry);
		
		JButton btnCancel = new JButton("Cancel");

		btnCancel.setBounds(230, 451, 117, 25);
		frame.getContentPane().add(btnCancel);
		
		JButton btnSave = new JButton("Save");

		btnSave.setBounds(454, 451, 117, 25);
		frame.getContentPane().add(btnSave);

		JLabel lblOpenHours = new JLabel("Open Hours");
		lblOpenHours.setBounds(204, 364, 106, 15);
		frame.getContentPane().add(lblOpenHours);
		
		textFieldOpenHours = new JTextField();
		textFieldOpenHours.setColumns(10);
		textFieldOpenHours.setBounds(340, 362, 245, 19);
		frame.getContentPane().add(textFieldOpenHours);
		
		JLabel lblStoreName = new JLabel("Store Name");
		lblStoreName.setBounds(204, 199, 106, 15);
		frame.getContentPane().add(lblStoreName);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Store store = new Store(storeController.getValidStoreCode(),textFieldName.getText(),textFieldStreetName.getText(),textFieldStreetNumber.getText(),
						textFieldPostalCode.getText(),textFieldCity.getText(),textFieldCountry.getText(),true,textFieldOpenHours.getText());

				if(storeController.addStore(store, editStoreScreenIndex)){
					storeController.updateStoreList();
					AdminScreen adminScreen = new AdminScreen();
					adminScreen.getFrame().setVisible(true);
					frame.dispose();
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminScreen adminScreen = new AdminScreen();
				adminScreen.getFrame().setVisible(true);
				frame.dispose();
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

	public int getEditStoreScreen() {
		return editStoreScreenIndex;
	}

	public void setEditStoreScreen(int editStoreScreen) {
		this.editStoreScreenIndex = editStoreScreen;
	}
}
