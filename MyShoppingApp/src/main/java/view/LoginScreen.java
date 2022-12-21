package view;

import controller.UserController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class LoginScreen {

	private JFrame frame;
	private JTextField userName;
	private JPasswordField password;
	private UserController userController;



	/**
	 * Create the application.
	 */
	public LoginScreen() {
		userController = new UserController();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setResizable(false);
		frame.setAutoRequestFocus(false);
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		userName = new JTextField();
		userName.setBounds(358, 239, 199, 18);
		frame.getContentPane().add(userName);
		userName.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(358, 291, 199, 18);
		frame.getContentPane().add(password);
		
		JLabel userNameLabel = new JLabel("User Name");
		userNameLabel.setBounds(260, 241, 80, 14);
		frame.getContentPane().add(userNameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(260, 293, 91, 14);
		frame.getContentPane().add(passwordLabel);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(469, 395, 98, 24);
		frame.getContentPane().add(loginButton);
		
		JLabel lblNewLabel = new JLabel("WELCOME TO SMART SHOPPING");
		lblNewLabel.setFont(new Font("FreeSerif", Font.BOLD, 30));
		lblNewLabel.setBounds(137, 90, 523, 40);
		frame.getContentPane().add(lblNewLabel);
		
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(257, 395, 98, 24);
		frame.getContentPane().add(registerButton);
		
		JLabel notificationText = new JLabel("");
		notificationText.setHorizontalAlignment(SwingConstants.CENTER);
		notificationText.setFont(new Font("Dialog", Font.PLAIN, 14));
		notificationText.setForeground(Color.RED);
		notificationText.setBounds(315, 181, 199, 24);
		frame.getContentPane().add(notificationText);
		
		JCheckBox chckbxIAmNot = new JCheckBox("I am not a Robot");
		chckbxIAmNot.setBounds(259, 341, 170, 23);
		frame.getContentPane().add(chckbxIAmNot);

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxIAmNot.isSelected()){
					if(!userController.login(userName.getText(), password.getText())){
						notificationText.setText("Invalid Login Credentials!");
					}
					else {
						frame.dispose();
					}
				}
				else {
					notificationText.setText("Robot Verification Failed!");
				}
			}
		});

		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterUserScreen registerUserScreen = new RegisterUserScreen();
				frame.dispose();
				registerUserScreen.getFrame().setVisible(true);

			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}
}
