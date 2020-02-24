import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class AdminLoggedInState implements ATMState, Serializable {
	private ATM atm;

	public AdminLoggedInState(ATM newAtm){
		atm = newAtm;
	}

	@Override
	public void seeRemainingBalance() {
		System.out.println("The balance in the atm is: $" + atm.getRemainingMoneyInATM());
	}

	@Override
	public void deposit(double amount) {
		atm.setRemainingMoneyInATM(atm.getRemainingMoneyInATM() + amount);
		System.out.println("The new balance of the ATM is: $" + atm.getRemainingMoneyInATM());
	}

	@Override
	public void withdraw(double amount) {
		System.out.println("You cannot withdraw money from the remaining balance of the ATM. Please login to your personal account to withdraw money.");
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
		System.out.println("1-View Balance\n2-Deposit Money\n3-Log Out");
		Scanner scn = new Scanner(System.in);
		int inp = scn.nextInt();
		if(inp == 1){
			atm.seeBalance();
		}else if(inp == 2){
			atm.deposit();
		}else if(inp == 3){
			atm.logout();
			atm.setState(new LoggedOutState(atm));
		}else{
			System.out.println("Invalid input please try again!");
		}
	}
}
