package guiDoctor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import connectionManager.ConnectionManager;
import mainMethodDoctor.MainDoctor;
import mainMethodDoctor.UserProfile;

public class UserConfiguration {
	private JPanel contentPane;
	private JFrame f = new JFrame();
	private String rP=System.getProperty("user.dir")+"\\resources";

	public UserConfiguration(ConnectionManager cm) {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(200, 400, 750, 420);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		f.setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel_3.setLayout(new GridLayout(2,1));
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new BorderLayout());
		panel_4.setBackground(Color.BLACK);
		
		panel_3.add(panel_4);
		panel_3.add(panel_1);

		panel_2.setBackground(Color.BLACK);
		panel.setBackground(Color.BLACK);
		panel_3.setBackground(Color.BLACK);
		panel_1.setBackground(Color.BLACK);

		Font ui = new Font("Seguoe UI", Font.PLAIN,15);

		JLabel label = new JLabel("New Profile");
		label.setFont(ui);
		label.setForeground(Color.WHITE);
		label.setHorizontalTextPosition(SwingConstants.CENTER);

		panel.add(label);

		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0,0,0, 0,1, 0,0,0};
		gbl_panel_1.rowHeights = new int[]{0,0,0, 0,0, 0,0,0};
		gbl_panel_1.columnWeights = new double[]{0.1,0,0.07, 0.0,0.05, 0.0,0,0.07,0.1};
		gbl_panel_1.rowWeights = new double[]{0.1,0,0, 0.0,0.0, 0.0,0,Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		JLabel label_1 = new JLabel("name");
		JLabel label_2 = new JLabel("Surname");/*
		JLabel label_3 = new JLabel("age");
		JLabel label_4 = new JLabel("Weight");*/
		
		ui= new Font("Segoe UI", Font.PLAIN,12);
		
		label_1.setFont(ui);
		label_2.setFont(ui);/*
		label_3.setFont(ui);
		label_4.setFont(ui);*/
		
		label_1.setForeground(Color.WHITE);
		label_2.setForeground(Color.WHITE);/*
		label_3.setForeground(Color.WHITE);
		label_4.setForeground(Color.WHITE);*/
		
		JTextField text_1 = new JTextField();
		JTextField text_2 = new JTextField();/*
		JTextField text_3 = new JTextField();
		JTextField text_4 = new JTextField();*/
		
		text_1.setBackground(Color.DARK_GRAY);
		text_2.setBackground(Color.DARK_GRAY);/*
		text_3.setBackground(Color.DARK_GRAY);
		text_4.setBackground(Color.DARK_GRAY);*/
		
		text_1.setFont(ui);
		text_2.setFont(ui);/*
		text_3.setFont(ui);
		text_4.setFont(ui);*/
		
		text_1.setForeground(Color.WHITE);
		text_2.setForeground(Color.WHITE);/*
		text_3.setForeground(Color.WHITE);
		text_4.setForeground(Color.WHITE);*/
		
		try {
			BufferedImage nominal;
			nominal = ImageIO.read(new File(rP+"\\logo.jpg"));
			Image tmp = nominal.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		    BufferedImage dimg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2d = dimg.createGraphics();
		    g2d.drawImage(tmp, 0, 0, null);
		    g2d.dispose();
		    JLabel picLabel = new JLabel(new ImageIcon(dimg));

			panel_4.add(picLabel,BorderLayout.CENTER);
			
			JSeparator separator = new JSeparator();
			panel_4.add(separator, BorderLayout.SOUTH);
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("failed to import image");
		}
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(40);
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		Component horizontalStrut_3 = Box.createHorizontalStrut(40);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 0;
		gbc_horizontalStrut_1.gridy = 0;
		panel_1.add(horizontalStrut_1,gbc_horizontalStrut_1);
		
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_2.gridx = 1;
		gbc_horizontalStrut_2.gridy = 0;
		panel_1.add(verticalStrut_1,gbc_horizontalStrut_2);
		
		GridBagConstraints gbc_horizontalStrut_3 = new GridBagConstraints();
		gbc_horizontalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_3.gridx = 1;
		gbc_horizontalStrut_3.gridy = 1;
		panel_1.add(label_1,gbc_horizontalStrut_3);
		
		GridBagConstraints gbc_horizontalStrut_4 = new GridBagConstraints();
		gbc_horizontalStrut_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_horizontalStrut_4.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_4.gridx = 2;
		gbc_horizontalStrut_4.gridy = 1;
		panel_1.add(text_1,gbc_horizontalStrut_4);
		
		GridBagConstraints gbc_horizontalStrut_5 = new GridBagConstraints();
		gbc_horizontalStrut_5.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_5.gridx = 1;
		gbc_horizontalStrut_5.gridy = 2;
		panel_1.add(label_2,gbc_horizontalStrut_5);
		
		GridBagConstraints gbc_horizontalStrut_6 = new GridBagConstraints();
		gbc_horizontalStrut_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_horizontalStrut_6.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_6.gridx = 2;
		gbc_horizontalStrut_6.gridy = 2;
		panel_1.add(text_2,gbc_horizontalStrut_6);
		
		GridBagConstraints gbc_horizontalStrut_7 = new GridBagConstraints();
		gbc_horizontalStrut_7.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_7.gridx = 4;
		gbc_horizontalStrut_7.gridy = 0;
		panel_1.add(horizontalStrut_2,gbc_horizontalStrut_7);
		
		/*GridBagConstraints gbc_horizontalStrut_8 = new GridBagConstraints();
		gbc_horizontalStrut_8.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_8.gridx = 6;
		gbc_horizontalStrut_8.gridy = 1;
		panel_1.add(label_3,gbc_horizontalStrut_8);
		
		GridBagConstraints gbc_horizontalStrut_9 = new GridBagConstraints();
		gbc_horizontalStrut_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_horizontalStrut_9.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_9.gridx = 7;
		gbc_horizontalStrut_9.gridy = 1;
		panel_1.add(text_3,gbc_horizontalStrut_9);*/
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 3;
		gbc_horizontalStrut.gridy = 2;
		panel_1.add(horizontalStrut, gbc_horizontalStrut);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_ = new GridBagConstraints();
		gbc_horizontalStrut_.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_.gridx = 5;
		gbc_horizontalStrut_.gridy = 2;
		panel_1.add(horizontalStrut_4, gbc_horizontalStrut_);
		
		/*GridBagConstraints gbc_horizontalStrut_10 = new GridBagConstraints();
		gbc_horizontalStrut_10.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_10.gridx = 6;
		gbc_horizontalStrut_10.gridy = 2;
		panel_1.add(label_4,gbc_horizontalStrut_10);
		
		GridBagConstraints gbc_horizontalStrut_11 = new GridBagConstraints();
		gbc_horizontalStrut_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_horizontalStrut_11.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_11.gridx = 7;
		gbc_horizontalStrut_11.gridy = 2;
		panel_1.add(text_4,gbc_horizontalStrut_11);*/
		
		GridBagConstraints gbc_horizontalStrut_12 = new GridBagConstraints();
		gbc_horizontalStrut_12.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_12.gridx = 8;
		gbc_horizontalStrut_12.gridy = 0;
		panel_1.add(horizontalStrut_3,gbc_horizontalStrut_12);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 4;
		gbc_verticalStrut.gridy = 3;
		panel_1.add(verticalStrut, gbc_verticalStrut);
		
		GridBagConstraints gbc_horizontalStrut_13 = new GridBagConstraints();
		gbc_horizontalStrut_13.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_13.gridx = 0;
		gbc_horizontalStrut_13.gridy = 4;
		panel_1.add(verticalStrut_2,gbc_horizontalStrut_13);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_3 = new GridBagConstraints();
		gbc_verticalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_3.gridx = 4;
		gbc_verticalStrut_3.gridy = 6;
		panel_1.add(verticalStrut_3, gbc_verticalStrut_3);
		
		JCheckBox chckbxMale = new JCheckBox("Male");
		chckbxMale.setSelected(true);
		chckbxMale.setBackground(Color.BLACK);
		chckbxMale.setForeground(Color.WHITE);
		GridBagConstraints gbc_chckbxMale = new GridBagConstraints();
		gbc_chckbxMale.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxMale.gridx = 4;
		gbc_chckbxMale.gridy = 5;
		panel_1.add(chckbxMale, gbc_chckbxMale);
		
		JButton button_1 = new JButton("Ok");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					/*int age = Integer.parseInt(text_3.getText());
					int weight = Integer.parseInt(text_4.getText());
					char gender;
					if(chckbxMale.isSelected()) {
						gender='m';
					}else {
						gender= 'f';
					}*/
					UserProfile up = new UserProfile(text_1.getText(),text_2.getText());
					MainDoctor.requestNewProfile(up, cm);			f.dispose();
				}catch(Exception e){
					JOptionPane.showMessageDialog(f,"Make sure the inputs are in the correct format.","WARNING",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		JButton button_2 = new JButton("Cancel");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f.dispose();
			}
		});
		
		button_1.setBackground(Color.GRAY);
		button_2.setBackground(Color.GRAY);
		
		button_1.setFont(ui);
		button_2.setFont(ui);
		
		panel_2.add(button_1);
		panel_2.add(button_2);
		f.setVisible(true);
	}
}
