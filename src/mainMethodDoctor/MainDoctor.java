package mainMethodDoctor;

import guiDoctor.LoginDoctor;
import guiDoctor.ReportsListScreen;

import java.util.ArrayList;

import connectionManager.ConnectionManager;
import fileManager.Report;

public class MainDoctor {
	public static void main(String[] args) {
		new LoginDoctor();
	}
	
	// For existing user profile:
	public static void login(String userName, String password, String ip, LoginDoctor window) {
		ConnectionManager connectServer = null;
		try {
			// Calls ConnectionManager, which connects to the server:
			connectServer = new ConnectionManager(ip);
			try {
				// It tries logging in:
				UserProfile up = connectServer.login(userName, password);
				if(up == null) {
					// If ConnectionManager returns a null value it means the server did not find the credentials.
					window.incorrectPassword();
				} else {
					// If ConnectionManager returns a User Profile it means the server DID find the credentials.
					// We then first close the login window:
					window.dispose();
					// Then we ask server for reports to show the reports window:
					showReports(connectServer);
				}
			} catch (Exception e) {
				// If the server found the some of the credentials but the password was wrong it displays this.
				/*Side note, I thing this ought to be changed later on, this could be a security threat...*/
				window.invalidUsername();
				e.printStackTrace();
			}
		} catch (Exception e1) {
			//if the server failed to answer back, display an error message.
			window.failedConnection();
			System.out.println("could not connect to the server.");
		}
	}
	
	// For non-existing user profile:
	public static void createProfile(String userName, String password, String ip, LoginDoctor window) {
		ConnectionManager connectServer = null;
		try {
			connectServer = new ConnectionManager(ip);
			try {
				// This method tries creating the credentials, if the server doesn't allow this it will throw an exception.
				// If everything goes fine it will open a window with a message saying everything went well.
				// Then the same login window will be opened to type name and password to enter the profile.
				connectServer.createProfile(userName, password);
				window.profileCreated();
				window.dispose();
				new LoginDoctor();
			} catch(Exception e1) {
				// If the server response is not valid, the login window will display an error message saying it didn't like what it saw.
				window.profileNotValid();
				e1.printStackTrace();
			}
		} catch (Exception e) {
			/*same thing as before, if the server doesn't answer back tell the user the connection failed or something.*/
			window.failedConnection();
			e.printStackTrace();
		}
	}
	
	public static void showReports (ConnectionManager cm) {
		try {
			// First we ask server for report's names list.
			ArrayList<String> reports = cm.askForReports();
			// Now that we have the report's names, we can show them on a screen:
			new ReportsListScreen(reports);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
