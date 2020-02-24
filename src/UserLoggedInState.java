import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

public class UserLoggedInState implements ATMState, Serializable {
	private ATM atm;

	public UserLoggedInState(ATM newAtm){
		atm = newAtm;
	}

	@Override
	public void seeRemainingBalance() {
		HashMap<String, String> account = atm.getCurrAccount();
		HashMap<HashMap<String,String>, Double> accountList = atm.getAccountCreds();
		double currBal = accountList.get(account);
		System.out.println("Your current balance is: $" + currBal);
	}

	@Override
	public void deposit(double amount) {
		HashMap<String, String> account = atm.getCurrAccount();
		HashMap<HashMap<String,String>, Double> accountList = atm.getAccountCreds();
		double currBal = accountList.get(account);
		accountList.replace(account, currBal+amount);
		double newBal = accountList.get(account);
		System.out.println("Deposit successful! Your new balance is: $" + newBal);
		atm.setRemainingMoneyInATM(atm.getRemainingMoneyInATM() + amount);
	}

	@Override
	public void withdraw(double amount) {
		HashMap<String, String> account = atm.getCurrAccount();
		HashMap<HashMap<String,String>, Double> accountList = atm.getAccountCreds();
		double currBal = accountList.get(account);
		if(amount > currBal){
			System.out.println("You are attempting to withdraw more money than you have. Please try again!");
		}else{
			accountList.replace(account, currBal - amount);
			double newBal = accountList.get(account);
			System.out.println("Withdraw successful! Your new balance is: $" + newBal);
			atm.setRemainingMoneyInATM(atm.getRemainingMoneyInATM() - amount);
		}
	}

	@Override
	public void makeAccount(String user, String pass) {
		System.out.println("You need to log out before making a new account!");
	}

	@Override
	public void login(String user, String pass) {
		System.out.println("You are already logged in!");
	}

	@Override
	public void logout() {
		atm.setState(new LoggedOutState(atm));
		atm.setCurrAccount(null);
		System.out.println("You have successfully logged out!");
	}

	@Override
	public void printOptions() {
		System.out.println("1-View Balance\n2-Deposit Money\n3-Withdraw Money\n4-Log Out");
		Scanner scn = new Scanner(System.in);
		int inp = scn.nextInt();
		if(inp == 1){
			atm.seeBalance();
		}else if(inp == 2){
			atm.deposit();
		}else if(inp == 3){
			atm.withdraw();
		}else if(inp == 4){
			atm.logout();
			atm.setState(new LoggedOutState(atm));
		}else{
			System.out.println("Invalid input please try again!");
		}
	}
}
