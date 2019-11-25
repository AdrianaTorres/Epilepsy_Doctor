package guiDoctor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;

import mainMethodDoctor.MainDoctor;

public class LoginDoctor {
	private JFrame f;
	private JPanel contentPane;
	private JTextField textField;
	
	public LoginDoctor() {
		f= new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
		f.setResizable(false);
		f.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		JPanel panel_1 = new JPanel();
		JPanel panel_2 = new JPanel();
		JPanel panel_3 = new JPanel();
		
		panel_1.setBackground(Color.GRAY);
		panel_2.setBackground(Color.black);
		panel_3.setBackground(Color.BLACK);
		
		Font ui = new Font("Segoe UI", Font.PLAIN,12);
		JLabel label_1= new JLabel("Login");
		JLabel label_2= new JLabel("User Name");
		JLabel label_3= new JLabel("Password");
		
		label_1.setFont(ui);
		label_2.setFont(ui);
		label_3.setFont(ui);
		
		label_1.setForeground(Color.BLACK);
		label_2.setForeground(Color.WHITE);
		label_3.setForeground(Color.WHITE);
		
		JTextField text_1 = new JTextField();
		JPasswordField text_2 = new JPasswordField();
		text_1.setFont(ui);
		text_2.setFont(ui);
		
		text_1.setBackground(Color.GRAY);
		text_2.setBackground(Color.GRAY);
		
		JButton button_1= new JButton("Login");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String temp="";
				for (int i = 0; i < text_2.getPassword().length; i++) {
					temp= temp+text_2.getPassword()[i];			
				}
				System.out.println(temp);
				//MainDoctor.login(text_1.getText(),temp , textField.getText(), LoginDoctor.this);
			}
		});
		JButton button_2= new JButton("Cancel");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		button_1.setBackground(Color.DARK_GRAY);
		button_2.setBackground(Color.DARK_GRAY);
		
		button_1.setForeground(Color.WHITE);
		button_2.setForeground(Color.WHITE);
		
		button_1.setFont(ui);
		button_2.setFont(ui);
		
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.add(label_1);
		contentPane.add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.5, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(80);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 0;
		gbc_horizontalStrut_1.gridy = 0;
		panel_2.add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		Component horizontalStrut = Box.createHorizontalStrut(80);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 3;
		panel_2.add(horizontalStrut, gbc_horizontalStrut);
		contentPane.add(panel_3, BorderLayout.SOUTH);
		
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 1;
		panel_2.add(label_2, gbc_label_1);
		
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 3;
		panel_2.add(label_3, gbc_label_2);
		
		GridBagConstraints gbc_text_1 = new GridBagConstraints();
		gbc_text_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_1.insets = new Insets(0, 0, 5, 5);
		gbc_text_1.gridx = 2;
		gbc_text_1.gridy = 1;
		panel_2.add(text_1, gbc_text_1);
		
		GridBagConstraints gbc_text_2 = new GridBagConstraints();
		gbc_text_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_2.insets = new Insets(0, 0, 5, 5);
		gbc_text_2.gridx = 2;
		gbc_text_2.gridy = 3;
		panel_2.add(text_2, gbc_text_2);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 2;
		gbc_verticalStrut_1.gridy = 2;
		panel_2.add(verticalStrut_1, gbc_verticalStrut_1);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 1;
		gbc_verticalStrut_2.gridy = 0;
		panel_2.add(verticalStrut_2, gbc_verticalStrut_2);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 2;
		gbc_verticalStrut.gridy = 4;
		panel_2.add(verticalStrut, gbc_verticalStrut);
		
		JLabel lblServerIp = new JLabel("Server IP");
		lblServerIp.setForeground(Color.WHITE);
		lblServerIp.setFont(ui);
		GridBagConstraints gbc_lblServerIp = new GridBagConstraints();
		gbc_lblServerIp.insets = new Insets(0, 0, 0, 5);
		gbc_lblServerIp.gridx = 1;
		gbc_lblServerIp.gridy = 5;
		panel_2.add(lblServerIp, gbc_lblServerIp);
		
		textField = new JTextField();
		textField.setBackground(Color.GRAY);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 5;
		panel_2.add(textField, gbc_textField);
		textField.setColumns(10);
		
		panel_3.add(button_1);
		panel_3.add(button_2);
		
		JButton btnCreateProfile = new JButton("Create Profile");
		btnCreateProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//MainDoctor.createProfile(text_1.getText(), text_2.getText(), textField.getText(), LoginDoctor.this);
			}
		});
		btnCreateProfile.setBackground(Color.DARK_GRAY);
		btnCreateProfile.setForeground(Color.WHITE);
		btnCreateProfile.setFont(ui);
		panel_3.add(btnCreateProfile);
		f.setVisible(true);
		
	}
	
	public void dispose() {
		f.dispose();
	}
	
	public void failedConnection() {
		JOptionPane.showMessageDialog(null, "Failed to connect to server", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	public void incorrectPassword() {
		JOptionPane.showMessageDialog(null, "Wrong Password", "WARNING", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void invalidUsername() {
		JOptionPane.showMessageDialog(null, "Username not valid", "WARNING", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void profileNotValid() {
		JOptionPane.showMessageDialog(null, "The username already exists", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}
