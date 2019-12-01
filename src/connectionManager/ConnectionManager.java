package connectionManager;

import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.awt.List;
import java.io.BufferedReader;
import java.net.Socket;
import java.util.ArrayList;

import mainMethodDoctor.UserProfile;
import fileManager.Report;

public class ConnectionManager {
	//private boolean requestedMonitoring;
	private Socket manager;
	private PrintWriter pw;
	private BufferedReader bf;
	private ObjectInputStream object;
	private Thread t;
	private UserProfile up;
	
	public ConnectionManager (String ip) throws Exception {
        try {
        	manager = new Socket(ip, 9000);
        	pw = new PrintWriter(manager.getOutputStream(), true);
        	bf = new BufferedReader(new InputStreamReader(manager.getInputStream()));
        	object = new ObjectInputStream(manager.getInputStream());
        } catch (Exception e) {
        	System.out.println("Could not connect to server!");
        	
        	manager = null;
        	pw = null;
        	bf = null;
        	
        	e.printStackTrace();
        	
        	throw new Exception();
        }
    }
	
	public UserProfile login (String UserName, String Password) throws Exception {
		pw.println("USER REQUESTING LOGIN");
		pw.println(UserName);
		pw.println(Password);
		
		Thread.sleep(100);
		
		String serverAnswer = bf.readLine();
		
		if(serverAnswer.contains("REJECTED")) {
			if(serverAnswer.contains("404")) {
				throw new Exception();
			} else {
				return null;
			}
		} else {
			String name = bf.readLine();
			UserProfile login = new UserProfile(name);
			System.out.println(login.getName());
			this.up = login;
			return login;
		}
	}
	
	public void createProfile(String userName, String password) throws Exception{
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
	}
	
	// This method asks Server for Report name's list and returns this report name's list so that it can be seen on screen:
	public ArrayList<String> askForReports() throws Exception{
		pw.println("USER REQUESTING REPORTS LIST");
		ArrayList<String> reports = new ArrayList<String>();
		try {
			while(!(bf.readLine() == null)) {
				reports.add(bf.readLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reports;
	}
	
	public Report showReport(String reportName) throws Exception{
		pw.println("USER REQUESTING REPORT");
		Report r = new Report();
		try {
			r = (Report) object.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
}


