package presentation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView {

	private JFrame frmLogin;
	private JTextField textField_username;
	private JPasswordField textField_password;
	private JTextField textField_nameNewUser;
	private JTextField textField_ageNewUser;
	private JTextField textField_usernameNewUser;
	private JTextField textField_passwordNewUser;

	public LoginView() {
		initialize();
	}

	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Log-in");
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(44, 53, 78, 14);
		frmLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(44, 78, 78, 14);
		frmLogin.getContentPane().add(lblPassword);
		
		textField_username = new JTextField();
		textField_username.setBounds(132, 50, 127, 20);
		frmLogin.getContentPane().add(textField_username);
		textField_username.setColumns(10);
		
		textField_password = new JPasswordField();
		textField_password.setBounds(132, 75, 127, 20);
		frmLogin.getContentPane().add(textField_password);
		
		JButton btnLogin = new JButton("Log-in");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setBounds(304, 69, 107, 23);
		frmLogin.getContentPane().add(btnLogin);
		
		JLabel lblNewVisitorPlease = new JLabel("New visitor? Please register below:");
		lblNewVisitorPlease.setBounds(93, 123, 215, 14);
		frmLogin.getContentPane().add(lblNewVisitorPlease);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(44, 160, 46, 14);
		frmLogin.getContentPane().add(lblName);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(44, 185, 46, 14);
		frmLogin.getContentPane().add(lblAge);
		
		JLabel lblUsername_1 = new JLabel("Username:");
		lblUsername_1.setBounds(44, 210, 78, 14);
		frmLogin.getContentPane().add(lblUsername_1);
		
		JLabel lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setBounds(44, 235, 78, 14);
		frmLogin.getContentPane().add(lblPassword_1);
		
		textField_nameNewUser = new JTextField();
		textField_nameNewUser.setBounds(132, 157, 127, 20);
		frmLogin.getContentPane().add(textField_nameNewUser);
		textField_nameNewUser.setColumns(10);
		
		textField_ageNewUser = new JTextField();
		textField_ageNewUser.setBounds(132, 182, 127, 20);
		frmLogin.getContentPane().add(textField_ageNewUser);
		textField_ageNewUser.setColumns(10);
		
		textField_usernameNewUser = new JTextField();
		textField_usernameNewUser.setBounds(132, 207, 127, 20);
		frmLogin.getContentPane().add(textField_usernameNewUser);
		textField_usernameNewUser.setColumns(10);
		
		textField_passwordNewUser = new JTextField();
		textField_passwordNewUser.setBounds(132, 232, 127, 20);
		frmLogin.getContentPane().add(textField_passwordNewUser);
		textField_passwordNewUser.setColumns(10);
		
		JButton btnRegister = new JButton("<html>Create account<br />& Log-in</html>");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegister.setBounds(304, 181, 107, 43);
		frmLogin.getContentPane().add(btnRegister);
	}
}
