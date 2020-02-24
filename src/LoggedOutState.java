import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

public class LoggedOutState implements ATMState, Serializable {

	private ATM atm;

	public LoggedOutState(ATM newAtm){
		atm = newAtm;
	}

	@Override
	public void seeRemainingBalance() {
		System.out.println("Sorry you need to be logged in as an admin to see the ATM's remaining balance!");
	}

	@Override
	public void deposit(double amount) {
		System.out.println("Sorry you need to be logged in to deposit money!");
	}

	@Override
	public void withdraw(double amount) {
		System.out.println("Sorry you need to be logged in to withdraw money!");
	}

	@Override
	public void makeAccount(String user, String pass) {
		HashMap<String, String> tempHash = new HashMap<String, String>();
		boolean usernameTaken = false;
		for(HashMap<String, String> key:atm.getAccountCreds().keySet()){
			if(key.containsKey(user)){
				System.out.println("The user name " + user + " already exists! Please try again.");
				usernameTaken = true;
				break;
			}
		}
		if(!usernameTaken){
			tempHash.put(user, pass);
			atm.getAccountCreds().put(tempHash, 0.0);
			System.out.println("Account created!");
		}
	}

	@Override
	public void login(String user, String pass) {
		boolean loggedIn = false;
		for(HashMap<String,String> key:atm.getAccountCreds().keySet()){
			if(key.containsKey(user) && key.get(user).equals(pass)){
				if(user.equals("Admin")){
					atm.setState(new AdminLoggedInState(atm));
				}else{
					atm.setState(new UserLoggedInState(atm));
				}
				loggedIn = true;
				atm.setCurrAccount(key);
			}
		}
		if(loggedIn){
			System.out.println("You have logged in successfully! " + atm.getState());
		}else{
			System.out.println("Your username or password is incorrect! Please try again!");
		}
	}

	@Override
	public void logout() {
		System.out.println("You need to be logged in to log out!");
	}

	@Override
	public void printOptions() {
		System.out.println("1-Log in\n2-Sign up");
		Scanner scn = new Scanner(System.in);
		int inp = scn.nextInt();
		if(inp == 1){
			atm.login();
		}else if(inp == 2){
			atm.makeAccount();
		}else{
			System.out.println("Invalid input please try again!");
		}
	}
}
