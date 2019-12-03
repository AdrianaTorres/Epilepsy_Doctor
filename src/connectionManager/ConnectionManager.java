package connectionManager;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import mainMethodDoctor.UserProfile;
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

		Thread.sleep(100);

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
			while (!(bf.readLine() == null)) {
				// Add patient name:
				reports.add(bf.readLine());
				// Add patient surname:
				reports.add(bf.readLine());
				// Add patient report name:
				reports.add(bf.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reports;
	}

	public Report showReport(String reportName) {
		pw.println("USER REQUESTING REPORT");

		List<Double> time1 = new ArrayList<Double>();
		List<Double> time2 = new ArrayList<Double>();
		List<Double> ecg = new ArrayList<Double>();
		List<Double> eeg = new ArrayList<Double>();
		String comment = "";

		try {
			boolean phaseOneComplete = false;
			boolean comments = false;
			String inputRead;
			while ((inputRead = bf.readLine()) != null) {
				try {
					parser(inputRead);
					if (!phaseOneComplete) {
						time1.add(parser(inputRead)[0]);
						eeg.add(parser(inputRead)[1]);
					}
					if (phaseOneComplete && !comments) {
						time2.add(parser(inputRead)[0]);
						ecg.add(parser(inputRead)[1]);
					}
				} catch (Exception e) {
					if (inputRead.contains("ECG")) {
						phaseOneComplete = true;
					}
					if (inputRead.contains("COMMENTS")) {
						comments = true;
					}
					if (comments && !inputRead.contains("COMMENTS")) {
						comment = comment + "\n" + inputRead;
					}
				}
			}
			try {
				bf.close();
			} catch (Exception e) {
				System.out.println("could not close reader");
				e.printStackTrace();
			}
			return new Report((new List[] { time2, ecg }), (new List[] { time1, eeg }), comment);
		} catch (Exception e) {
			System.out.println("could not read report");
			e.printStackTrace();
			return null;
		}
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
}
