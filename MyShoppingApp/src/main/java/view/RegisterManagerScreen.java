package view;

import controller.StoreController;
import controller.UserController;
import model.Manager;
import model.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterManagerScreen {

	private JFrame frame;
	private JTextField textFieldName;
	private JTextField textFieldStreetName;
	private JTextField textFieldStreetNumber;
	private JTextField textFieldPostalCode;
	private JTextField textFieldCity;
	private JTextField textFieldCountry;
	private JTextField textFieldUserName;
	private JTextField textFieldPassword;
	private UserController userController;
	private  Integer editManagerScreenIndex;
	private Manager manager;


	/**
	 * Create the application.
	 */
	public RegisterManagerScreen() {
		userController = new UserController();
		initialize();
	}

	public RegisterManagerScreen(Integer editManagerScreenIndex) {
		this.editManagerScreenIndex = editManagerScreenIndex;
		this.userController = new UserController();
		initialize();
		if(this.editManagerScreenIndex != null && userController.getManagerList().get(editManagerScreenIndex) != null){
			setValues();
		}
	}

	private void setValues(){
		manager = userController.getManagerList().get(editManagerScreenIndex);
		if(manager.getName() != null) {textFieldName.setText(manager.getName());}
		if(manager.getStreetName() != null) {textFieldStreetName.setText(manager.getStreetName());}
		if(manager.getStreetNo() != null) {textFieldStreetNumber.setText(manager.getStreetNo());}
		if(manager.getPostalCode() != null) {textFieldPostalCode.setText(manager.getPostalCode());}
		if(manager.getCity() != null) {textFieldCity.setText(manager.getCity());}
		if(manager.getCountry() != null) {textFieldCountry.setText(manager.getCountry());}
		if(manager.getUserName() != null) {textFieldUserName.setText(manager.getUserName());}
		if(manager.getPassword() != null) {textFieldPassword.setText(manager.getPassword());}
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
		
		JLabel lblPleaseEnterInformation = new JLabel("Please Enter Manager Details");
		lblPleaseEnterInformation.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPleaseEnterInformation.setBounds(239, 37, 331, 15);
		frame.getContentPane().add(lblPleaseEnterInformation);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(189, 132, 70, 15);
		frame.getContentPane().add(lblName);
		
		JLabel lblStreetName = new JLabel("Street Name");
		lblStreetName.setBounds(189, 162, 106, 15);
		frame.getContentPane().add(lblStreetName);
		
		JLabel lblStreetNumber = new JLabel("Street Number");
		lblStreetNumber.setBounds(189, 190, 106, 15);
		frame.getContentPane().add(lblStreetNumber);
		
		JLabel lblPostalCode = new JLabel("Postal Code");
		lblPostalCode.setBounds(189, 217, 106, 15);
		frame.getContentPane().add(lblPostalCode);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(189, 244, 106, 15);
		frame.getContentPane().add(lblCity);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(189, 268, 106, 15);
		frame.getContentPane().add(lblCountry);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(189, 295, 106, 15);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(189, 322, 106, 15);
		frame.getContentPane().add(lblPassword);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(325, 130, 245, 19);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldStreetName = new JTextField();
		textFieldStreetName.setColumns(10);
		textFieldStreetName.setBounds(325, 158, 245, 19);
		frame.getContentPane().add(textFieldStreetName);
		
		textFieldStreetNumber = new JTextField();
		textFieldStreetNumber.setColumns(10);
		textFieldStreetNumber.setBounds(325, 188, 245, 19);
		frame.getContentPane().add(textFieldStreetNumber);
		
		textFieldPostalCode = new JTextField();
		textFieldPostalCode.setColumns(10);
		textFieldPostalCode.setBounds(325, 215, 245, 19);
		frame.getContentPane().add(textFieldPostalCode);
		
		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(325, 240, 245, 19);
		frame.getContentPane().add(textFieldCity);
		
		textFieldCountry = new JTextField();
		textFieldCountry.setColumns(10);
		textFieldCountry.setBounds(325, 266, 245, 19);
		frame.getContentPane().add(textFieldCountry);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setColumns(10);
		textFieldUserName.setBounds(325, 293, 245, 19);
		frame.getContentPane().add(textFieldUserName);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(325, 320, 245, 19);
		frame.getContentPane().add(textFieldPassword);
		
		JButton btnCancel = new JButton("Cancel");

		btnCancel.setBounds(213, 395, 117, 25);
		frame.getContentPane().add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(437, 395, 117, 25);
		frame.getContentPane().add(btnSave);
		
		JLabel notificationText = new JLabel("");
		notificationText.setHorizontalAlignment(SwingConstants.CENTER);
		notificationText.setForeground(Color.RED);
		notificationText.setBounds(171, 81, 440, 19);
		frame.getContentPane().add(notificationText);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userController.registerManager(textFieldName.getText(),textFieldStreetName.getText(),
						textFieldStreetName.getText(),textFieldPostalCode.getText(),textFieldCity.getText(), textFieldCountry.getText(),
						textFieldUserName.getText(), textFieldPassword.getText(),manager)){
					userController.updateManagerList();
					AdminScreen adminScreen = new AdminScreen();
					adminScreen.getFrame().setVisible(true);
					frame.dispose();
				}
				else{
					notificationText.setText("Failed. All fields Should be Non Empty!");
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
}
