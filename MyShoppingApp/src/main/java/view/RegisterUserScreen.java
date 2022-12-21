package view;

import controller.UserController;
import model.User;

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
import javax.swing.SwingConstants;

public class RegisterUserScreen {

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
	private String userId;
	private User user;


	/**
	 * Create the application.
	 */
	public RegisterUserScreen() {
		userController = new UserController();
		initialize();
	}

	public RegisterUserScreen(String userId) {
		this.userId = userId;
		this.userController = new UserController();
		initialize();
		if(this.userId != null && userController.getUserById(userId) != null){
			setValues();
		}
	}

	private void setValues(){
		user = userController.getUserById(userId) ;
		if(user.getName() != null) {textFieldName.setText(user.getName());}
		if(user.getStreetName() != null) {textFieldStreetName.setText(user.getStreetName());}
		if(user.getStreetNo() != null) {textFieldStreetNumber.setText(user.getStreetNo());}
		if(user.getPostalCode() != null) {textFieldPostalCode.setText(user.getPostalCode());}
		if(user.getCity() != null) {textFieldCity.setText(user.getCity());}
		if(user.getCountry() != null) {textFieldCountry.setText(user.getCountry());}
		if(user.getUserName() != null) {textFieldUserName.setText(user.getUserName());}
		if(user.getPassword() != null) {textFieldPassword.setText(user.getPassword());}
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
		
		JLabel lblPleaseEnterInformation = new JLabel("Please Enter User Details");
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
				if(userController.registerCustomer(textFieldName.getText(),textFieldStreetName.getText(),
						textFieldStreetName.getText(),textFieldPostalCode.getText(),textFieldCity.getText(), textFieldCountry.getText(),
						textFieldUserName.getText(), textFieldPassword.getText(),user)){
					userController.updateUserList();
					if(user != null){
						CustomerScreen customerScreen = new CustomerScreen(userId);
						customerScreen.getFrame().setVisible(true);
					}
					else{
						LoginScreen loginScreen = new LoginScreen();
						loginScreen.getFrame().setVisible(true);
					}
					frame.dispose();
				}
				else{
					notificationText.setText("Failed. All fields Should be Non Empty!");
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginScreen loginScreen = new LoginScreen();
				loginScreen.getFrame().setVisible(true);
				frame.dispose();
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}
}
