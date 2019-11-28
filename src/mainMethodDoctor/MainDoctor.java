package mainMethodDoctor;

import guiDoctor.LoginDoctor;
import guiDoctor.ReportsListScreen;
import connectionManager.ConnectionManager;

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
					window.dispose();
					// 1º tenemos que pedirle al servidor los reports para después poder mostrarlos.
					// Aquí hay que llamar a la clase que crea la ventana que muestra la lista de reports de los pacientes.
					ReportsListScreen reportsScreen =  new ReportsListScreen(up, connectServer);
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
				// If everything goes fine it will open a tab so that the user can write his name.
				// En este caso el doctor solo tiene que meter usuario y contraseña para crear su perfil.
				connectServer.createProfile(userName, password);
				//UserConfiguration uc = new UserConfiguration(cm);
				window.dispose();
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
	
	public static void requestNewProfile(UserProfile up, ConnectionManager cm) {
		cm.sendProfile(up);
		LoginDoctor l = new LoginDoctor();
	}
	
	public static void showReports () {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
