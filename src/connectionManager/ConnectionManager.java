package connectionManager;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import mainMethodDoctor.UserProfile;
import mainMethodDoctor.UserProfilePatient;
import fileManager.Report;

public class ConnectionManager {
	private Socket manager;
	private PrintWriter pw;
	private BufferedReader bf;
	private UserProfile up;

	public ConnectionManager(String ip) throws Exception {
		try {
			manager = new Socket(ip, 9009);
			pw = new PrintWriter(manager.getOutputStream(), true);
			bf = new BufferedReader(new InputStreamReader(manager.getInputStream()));
		} catch (Exception e) {
			System.out.println("Could not connect to server!");

			manager = null;
			pw = null;
			bf = null;

			e.printStackTrace();

			throw new Exception();
		}
	}

	public UserProfile login(String UserName, String Password) throws Exception {
		pw.println("USER REQUESTING LOGIN");
		pw.println(UserName);
		pw.println(Password);

		String serverAnswer = bf.readLine();

		if (serverAnswer.contains("REJECTED")) {
			if (serverAnswer.contains("404")) {
				throw new Exception();
			} else {
				return null;
			}
		} else {
			String name = bf.readLine();
			String surname = bf.readLine();
			UserProfile login = new UserProfile(name, surname);
			System.out.println(login.getName() + " " + login.getSurname());
			this.up = login;
			return up;
		}
	}

	public void createProfile(String userName, String password) throws Exception {
		pw.println("USER REQUESTING NEW PROFILE");
		pw.println(userName);
		pw.println(password);
		String serverReply = bf.readLine();
		if (!serverReply.equals("CONFIRM")) {
			throw new Exception();
		}
	}

	public void sendProfile(UserProfile up) {
		pw.println("USER REQUESTING NEW USER PROFILE");
		pw.println(up.getName());
		pw.println(up.getSurname());
	}

	// This method asks Server for Report name's list and returns this report name's
	// list so that it can be seen on screen:
	public ArrayList<String> askForReports() throws Exception {
		pw.println("USER REQUESTING REPORTS LIST");
		ArrayList<String> reports = new ArrayList<String>();
		try {
			String temp="";
			while (!(temp == null)) {
				temp=bf.readLine();
				if(temp==null || temp.equals("DONE")){
					System.out.println("We are done with the reports");
					break;
				}
				reports.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reports;
	}

	public Report showReport(String reportName) {
		pw.println("USER REQUESTING REPORT");
		pw.println(reportName);
		List<Double> ecg = new ArrayList<Double>();
		List<Double> eeg = new ArrayList<Double>();
		List<Double> timeEEG = new ArrayList<Double>();
		List<Double> timeECG = new ArrayList<Double>();
		String temp;
		String instruction = "";
		String comments = "";
		int counter = 0;
		while (true) {
			try {
				temp = bf.readLine();
				try {
					double data = Double.parseDouble(temp);
					if (instruction.contains("ECG")) {
						if (counter % 2 == 0) {
							timeECG.add(data);
						} else {
							ecg.add(data);
						}
						counter++;
					}
					if (instruction.contains("EEG")) {
						if (counter % 2 == 0) {
							timeEEG.add(data);
						} else {
							eeg.add(data);
						}
						counter++;
					}

				} catch (Exception e) {
					System.out.println(temp);
					instruction = temp;
					counter = 0;
					if (instruction.contains("COMMENTS")) {
						comments = temp;
					}
					if (instruction.contains("DONE")) {
						break;
					}
				}
				
			} catch (IOException e) {
				System.out.println("Error reading report ");
				e.printStackTrace();
			}
		}
		Report report= new Report();
		List[] ecgData= new List[] {timeECG,ecg};
		List[] eegData= new List[] {timeEEG,eeg};
		report.setEcgData(ecgData);
		report.setEegData(eegData);
		report.setComments(comments);
		return report;
	}

	private static double[] parser(String data) {
		char[] temp = data.toCharArray();
		double time = 0;
		double input = 0;
		String helper = "";
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != ' ') {
				helper = helper + temp[i];
			} else {
				time = Double.parseDouble(helper);
				helper = "";
			}
			if (i == temp.length - 1) {
				input = Double.parseDouble(helper);
			}
		}
		return new double[] { time, input };
	}

	public void terminateSession() {
		pw.println("FINISHED MONITORING");
	}

	public UserProfilePatient getPatientProfile(String name) {
		pw.println("USER REQUESTING PATIENT PROFILE");
		pw.println(name);
		try {
			String n=bf.readLine();
			String s=bf.readLine();
			int w = Integer.parseInt(bf.readLine());
			int a = Integer.parseInt(bf.readLine());
			char g= bf.readLine().toCharArray()[0];
			UserProfilePatient upp= new UserProfilePatient(n, s, w, a, g);
			return upp;
		}catch(Exception e) {
			System.out.println("Could not recieve a proper response.");
			return null;
		}
		
	}
}
