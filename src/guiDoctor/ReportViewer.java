package guiDoctor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import fileManager.Report;
import guiDoctor.ReportsListScreen;
//import guiPatient.PatientViewer;
import guiDoctor.StaticGraph;
import mainMethodDoctor.UserProfilePatient;

public class ReportViewer {
	private JFrame f =new JFrame();
	private JPanel contentPane;
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text4;
	private JTextArea textArea; 
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;

	private double[]timeEEG;
	private double[]timeECG;
	private double[]ECGdata;
	private double[]EEGdata;

	private int indexECG;
	private int indexEEG;

	private StaticGraph eegGraph;
	private StaticGraph ecgGraph;

	private int defaultShiftECG;
	private int defaultShiftEEG;

	public ReportViewer(UserProfilePatient user,Report rep) {

		defaultShiftECG=50;
		defaultShiftEEG=50;
		indexECG=0;
		indexEEG=0;
		timeEEG=new double[rep.getEegData()[0].size()];
		EEGdata=new double[rep.getEegData()[0].size()];

		timeECG=new double[rep.getEcgData()[1].size()];
		ECGdata=new double[rep.getEcgData()[1].size()];

		Iterator iterator_1 =rep.getEcgData()[1].iterator();
		int i=0;
		for (Iterator iterator = rep.getEcgData()[0].iterator(); iterator.hasNext();) {
			timeECG[i]=(double)iterator.next();
			ECGdata[i]=(double)iterator_1.next();
			i++;
		}
		i=0;
		iterator_1 =rep.getEegData()[1].iterator();
		for (Iterator iterator = rep.getEegData()[0].iterator(); iterator.hasNext();) {
			timeEEG[i]=(double)iterator.next();
			EEGdata[i]=(double)iterator_1.next();
			i++;
		}

		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		f.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(SystemColor.windowBorder);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));

		panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 2, 0, 0));

		panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setLayout(new BorderLayout());
		panel_6.setBackground(Color.BLACK);

		JPanel panel_11 = new JPanel();
		panel_11.setLayout(new FlowLayout());
		panel_11.setBackground(Color.BLACK);

		panel_6.add(panel_11,BorderLayout.SOUTH);
		
		button_1=new JButton("next");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_6.remove(ecgGraph);
				ecgGraph= displayForwardECG();
				panel_6.add(ecgGraph);
				panel_6.setVisible(false);
				panel_6.setVisible(true);
			}
		});
		button_2=new JButton("previous");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_6.remove(ecgGraph);
				ecgGraph= displayBackwardECG();
				panel_6.add(ecgGraph);
				panel_6.setVisible(false);
				panel_6.setVisible(true);
			}
		});

		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.DARK_GRAY);
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(Color.DARK_GRAY);

		button_1.setFont(new Font("Segoe UI",Font.PLAIN,11));
		button_2.setFont(new Font("Segoe UI",Font.PLAIN,11));

		panel_11.add(button_2,BorderLayout.SOUTH);
		panel_11.add(button_1,BorderLayout.SOUTH);

		JLabel label_1= new JLabel("Shifting Distance:");
		label_1.setForeground(Color.WHITE);
		label_1.setBackground(Color.BLACK);
		label_1.setFont(new Font("Segoue UI",Font.PLAIN,11));
		ecgGraph=displayForwardECG();
		panel_6.add(ecgGraph,BorderLayout.CENTER);
		panel_11.add(label_1,BorderLayout.SOUTH);

		JTextField text_1 = new JTextField();
		text_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(!text_1.getText().equals("")) {
					try {
						int shift =Integer.parseInt(text_1.getText());
						ReportViewer.this.defaultShiftEEG=shift;
						//reScaleEEG();
					}catch(Exception e) {
						e.printStackTrace();
						System.out.println("Cannot complete request.");
					}
				}
			}
		});
		text_1.setFont(new Font("Segoe UI", Font.PLAIN,11));
		text_1.setBackground(Color.DARK_GRAY);
		text_1.setForeground(Color.WHITE);
		text_1.setText(""+this.defaultShiftEEG);

		panel_11.add(text_1,BorderLayout.SOUTH);

		panel_5.add(panel_6);

		panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setLayout(new BorderLayout());
		panel_7.setBackground(Color.BLACK);
		JPanel panel_12 = new JPanel();
		panel_12.setLayout(new FlowLayout());
		panel_12.setBackground(Color.BLACK);
		panel_7.add(panel_12,BorderLayout.SOUTH);

		button_3=new JButton("next");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_7.remove(eegGraph);
				eegGraph= displayForwardEEG();
				panel_7.add(eegGraph);
				panel_7.setVisible(false);
				panel_7.setVisible(true);
			}
		});
		button_4=new JButton("previous");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_7.remove(eegGraph);
				eegGraph= displayBackwardEEG();
				panel_7.add(eegGraph);
				panel_7.setVisible(false);
				panel_7.setVisible(true);
			}
		});

		button_3.setForeground(Color.WHITE);
		button_3.setBackground(Color.DARK_GRAY);
		button_4.setForeground(Color.WHITE);
		button_4.setBackground(Color.DARK_GRAY);

		button_3.setFont(new Font("Segoe UI",Font.PLAIN,11));
		button_4.setFont(new Font("Segoe UI",Font.PLAIN,11));

		panel_12.add(button_4,BorderLayout.SOUTH);
		panel_12.add(button_3,BorderLayout.SOUTH);

		JLabel label_2= new JLabel("Shifting Distance:");
		label_2.setForeground(Color.WHITE);
		label_2.setBackground(Color.BLACK);
		label_2.setFont(new Font("Segoue UI",Font.PLAIN,11));

		panel_12.add(label_2,BorderLayout.SOUTH);

		JTextField text_2 = new JTextField();
		text_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(!text_2.getText().equals("")) {
					try {
						int shift =Integer.parseInt(text_2.getText());
						ReportViewer.this.defaultShiftECG=shift;
						//reScaleECG();
					}catch(Exception e) {
						e.printStackTrace();
						System.out.println("Cannot complete request.");
					}
				}
			}
		});
		text_2.setFont(new Font("Segoe UI", Font.PLAIN,11));
		text_2.setBackground(Color.DARK_GRAY);
		text_2.setForeground(Color.WHITE);
		text_2.setText(""+this.defaultShiftECG);

		panel_12.add(text_2,BorderLayout.SOUTH);
		eegGraph=displayForwardEEG();
		panel_7.add(eegGraph,BorderLayout.CENTER);

		panel_5.add(panel_7);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_8.setBackground(SystemColor.windowBorder);
		panel.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.GRAY);
		panel_10.setLayout(new GridLayout(11,10));

		JLabel label1=new JLabel("Name");
		JLabel label2=new JLabel("Surname");
		JLabel label3=new JLabel("Age");
		JLabel label4=new JLabel("Weight");

		Font ui=new Font("Segoe UI",Font.PLAIN,14);
		label1.setFont(ui);
		label2.setFont(ui);
		label3.setFont(ui);
		label4.setFont(ui);
		text1=new JTextField();
		text2=new JTextField();
		text3=new JTextField();
		text4=new JTextField();
		text1.setBackground(Color.GRAY);
		text2.setBackground(Color.GRAY);
		text3.setBackground(Color.GRAY);
		text4.setBackground(Color.GRAY);
		text1.setBorder(new LineBorder(Color.DARK_GRAY));
		text2.setBorder(new LineBorder(Color.DARK_GRAY));
		text3.setBorder(new LineBorder(Color.DARK_GRAY));
		text4.setBorder(new LineBorder(Color.DARK_GRAY));
		text1.setFont(ui);
		text2.setFont(ui);
		text3.setFont(ui);
		text4.setFont(ui);


		JLabel filler=new JLabel();

		panel_10.add(label1);
		panel_10.add(text1);
		panel_10.add(label2);
		panel_10.add(text2);

		panel_10.add(filler);
		panel_10.add(filler);
		panel_10.add(filler);
		panel_10.add(filler);

		panel_10.add(label3);
		panel_10.add(text3);
		panel_10.add(label4);
		panel_10.add(text4);

		panel_8.add(panel_10);
		textArea = new JTextArea();
		textArea.setFont(ui);
		textArea.setLineWrap(true);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textArea.setBackground(new Color(64, 64, 64));
		panel_8.add(textArea);
		textArea.setText(rep.getComments());

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.desktop);
		JLabel title =new JLabel("Patient Monotorer 3000");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Segoe UI",80,15));
		panel_1.setLayout(new GridLayout(1,3));
		panel_1.add(title,Component.LEFT_ALIGNMENT);
		JLabel devBuild=new JLabel("devBuild alpha 2.0");
		devBuild.setForeground(Color.white);
		devBuild.setFont(new Font("Segoe UI",80,15));
		panel_1.add(devBuild);
		JLabel stats=new JLabel("Telemedicine 2019-2020");
		stats.setForeground(Color.white);
		stats.setFont(new Font("Segoe UI",80,15));
		panel_1.add(stats);
		contentPane.add(panel_1, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.desktop);
		contentPane.add(panel_2, BorderLayout.SOUTH);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.desktop);
		contentPane.add(panel_3, BorderLayout.WEST);

		panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.desktop);
		panel_4.setLayout(new GridLayout(2, 1, 0, 0));
		contentPane.add(panel_4, BorderLayout.EAST);

		try {
			URL nominal;
			if(user.getGender()=='m') {
				nominal = ReportViewer.class.getResource("/NominalMale.jpg");
			}else {
				nominal = ReportViewer.class.getResource("/NominalFemale.jpg");
			}
			JLabel picLabel = new JLabel(new ImageIcon(nominal));
			panel_4.add(picLabel);
		} catch (Exception e) {
			System.out.println("Failed to import image!");
			e.printStackTrace();
		}

		JPanel panel_9=new JPanel();
		panel_9.setBackground(Color.BLACK);


		panel_4.add(panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[] {1};
		gbl_panel_9.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_9.columnWeights = new double[]{1.0};
		gbl_panel_9.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.1, 0.0, 0.0};
		panel_9.setLayout(gbl_panel_9);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblStatus.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.insets = new Insets(0, 0, 5, 0);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 0;
		panel_9.add(lblStatus, gbc_lblStatus);

		JButton stop = new JButton("close");
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f.dispose();
				// que se cierre la ventana q muestra el report habiendose quedado la otra abierta, luego no hace falta llamarla otra vez.
				//MainScreen.invokeMe();
				//new ReportsListScreen();
			}
		});
		stop.setForeground(Color.BLACK);
		stop.setBackground(Color.GRAY);
		stop.setFont(new Font("Segoe UI",Font.PLAIN,11));
		GridBagConstraints gbc_lblStop = new GridBagConstraints();
		gbc_lblStop.insets = new Insets(0, 0, 5, 0);
		gbc_lblStop.gridx = 0;
		gbc_lblStop.gridy = 2;
		gbc_lblStop.fill = GridBagConstraints.HORIZONTAL;
		panel_9.add(stop, gbc_lblStop);

		Component verticalStrut_1 = Box.createVerticalStrut(10);
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		GridBagConstraints gbc_lbVS_1 = new GridBagConstraints();
		gbc_lbVS_1.insets = new Insets(0, 0, 5, 0);
		gbc_lbVS_1.gridx = 0;
		gbc_lbVS_1.gridy = 1;
		panel_9.add(verticalStrut_1, gbc_lbVS_1);
		GridBagConstraints gbc_lbVS_2 = new GridBagConstraints();
		gbc_lbVS_2.insets = new Insets(0, 0, 5, 0);
		gbc_lbVS_2.gridx = 0;
		gbc_lbVS_2.gridy = 3;
		panel_9.add(verticalStrut_2, gbc_lbVS_2);
		GridBagConstraints gbc_lbVS_3 = new GridBagConstraints();
		gbc_lbVS_3.insets = new Insets(0, 0, 5, 0);
		gbc_lbVS_3.gridx = 0;
		gbc_lbVS_3.gridy = 5;
		panel_9.add(verticalStrut_3, gbc_lbVS_3);


		this.setAge(user.getAge());
		this.setWeight(user.getWeight());
		this.setName(user.getName());
		this.setSurName(user.getSurname());
		f.setVisible(true);
	}
	public void setName(String name) {
		this.text1.setText(name);
		text1.setVisible(false);
		text1.setVisible(true);
	}
	public void setSurName(String surname) {
		this.text2.setText(surname);
		text2.setVisible(false);
		text2.setVisible(true);
	}
	public void setAge(int age) {
		this.text3.setText(""+age);
		text3.setVisible(false);
		text3.setVisible(true);
	}
	public void setWeight(int weight) {
		this.text4.setText(""+weight);
		text4.setVisible(false);
		text4.setVisible(true);
	}
	public void setComment(String comment) {
		this.textArea.setText(comment);
		textArea.setVisible(false);
		textArea.setVisible(true);
	}
	private StaticGraph displayForwardECG() {
		double[]data= new double[defaultShiftECG];
		double[]time= new double[defaultShiftECG];

		List <Double> xAxis = new ArrayList <Double>();
		List <Double> yAxis = new ArrayList <Double>();
		button_2.setEnabled(true);
		
		for (int i=0; i< defaultShiftECG; i++) {
			if(indexECG+i<(ECGdata.length-1)) {
				data[i]=ECGdata[indexECG+i];
				time[i]=timeECG[indexECG+i];
			}else if(i>2){
				data[i]=0;
				time[i]=time[i-1]+(time[i-1]-time[i-2]);
				button_1.setEnabled(false);
			}
		}
		if((indexECG+defaultShiftECG)<(ECGdata.length-1)){
			indexECG+=defaultShiftECG;
		}else {
			indexECG=ECGdata.length-1;
		}
		for (int i = 0; i < time.length; i++) {
			xAxis.add(time[i]);
			yAxis.add(data[i]);
		}
		StaticGraph s= new StaticGraph(new List[] {xAxis, yAxis},"ECG");
		return s;
	}
	private StaticGraph displayForwardEEG() {
		double[]data= new double[defaultShiftEEG];
		double[]time= new double[defaultShiftEEG];

		List <Double> xAxis = new ArrayList <Double>();
		List <Double> yAxis = new ArrayList <Double>();

		button_4.setEnabled(true);
		for (int i=0; i< defaultShiftEEG; i++) {
			if(indexEEG+i<(EEGdata.length-1)) {
				data[i]=ECGdata[indexEEG+i];
				time[i]=timeECG[indexEEG+i];
			}else if(i>2){
				data[i]=0;
				time[i]=time[i-1]+(time[i-1]-time[i-2]);
				button_3.setEnabled(false);
			}
		}
		if((indexEEG+defaultShiftEEG)<(EEGdata.length-1)){
			indexEEG+=defaultShiftEEG;
		}else {
			indexEEG=EEGdata.length-1;
		}
		for (int i = 0; i < time.length; i++) {
			xAxis.add(time[i]);
			yAxis.add(data[i]);
		}
		StaticGraph s= new StaticGraph(new List[] {xAxis, yAxis},"EEG");
		return s;
	}
	private StaticGraph displayBackwardECG() {
		double[]data= new double[defaultShiftECG];
		double[]time= new double[defaultShiftECG];

		List <Double> xAxis = new ArrayList <Double>();
		List <Double> yAxis = new ArrayList <Double>();
		button_1.setEnabled(true);
		for (int i=0; i< defaultShiftECG; i++) {
			if(indexECG-i>=(0)) {
				data[i]=ECGdata[indexECG-i];
				time[i]=timeECG[indexECG-i];
			}else if(i>2){
				data[i]=0;
				time[i]=0-i*(timeECG[1]-timeECG[2]);
				button_2.setEnabled(false);
			}
		}
		if((indexECG-defaultShiftECG)>=0){
			indexECG-=defaultShiftECG;
		}else {
			indexECG=0;
		}
		for (int i = 0; i < time.length; i++) {
			xAxis.add(time[i]);
			yAxis.add(data[i]);
		}
		StaticGraph s= new StaticGraph(new List[] {xAxis, yAxis},"ECG");
		return s;
	}
	private StaticGraph displayBackwardEEG() {
		double[]data= new double[defaultShiftEEG];
		double[]time= new double[defaultShiftEEG];

		List <Double> xAxis = new ArrayList <Double>();
		List <Double> yAxis = new ArrayList <Double>();
		button_3.setEnabled(true);
		for (int i=0; i< defaultShiftEEG; i++) {
			if(indexEEG-i>=(0)) {
				data[i]=ECGdata[indexEEG-i];
				time[i]=timeECG[indexEEG-i];
			}else if(i>2){
				data[i]=0;
				time[i]=0-i*(timeEEG[1]-timeEEG[2]);
				button_4.setEnabled(false);
			}
		}
		if((indexEEG-defaultShiftEEG)>=0){
			indexEEG-=defaultShiftEEG;
		}else {
			indexEEG=0;
		}
		for (int i = 0; i < time.length; i++) {
			xAxis.add(time[i]);
			yAxis.add(data[i]);
		}
		StaticGraph s= new StaticGraph(new List[] {xAxis, yAxis},"EEG");
		return s;
	}
}
