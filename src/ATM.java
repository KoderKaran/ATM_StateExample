import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

public class ATM implements Serializable {
	private static final long serialversionUID = 5723894576L;
	private String fileName;
	private double remainingMoneyInATM;
	private ATMState state;
	private HashMap<HashMap<String, String>, Double> accountCreds;
	private transient HashMap<String, String> currAccount;

	public ATM(double initMoneyInATM, String location){
		remainingMoneyInATM = initMoneyInATM;
		accountCreds = new HashMap<HashMap<String, String>, Double>();
		HashMap<String, String> adminCreds = new HashMap<String, String>();
		adminCreds.put("Admin", "password123");
		accountCreds.put(adminCreds, 0.0);
		fileName = location + "ATM.ser";
		state = new LoggedOutState(this);
		this.save();
	}

	public void login(){
		Scanner scn = new Scanner(System.in);
		System.out.print("Username:");
		String user = scn.next();
		System.out.print("Password:");
		String pass = scn.next();
		state.login(user, pass);
	}

	public void makeAccount(){
		Scanner scn = new Scanner(System.in);
		System.out.print("Desired Username:");
		String user = scn.next();
		System.out.print("Password:");
		String pass = scn.next();
		state.makeAccount(user, pass);
		this.save();
	}

	public void seeBalance(){
		state.seeRemainingBalance();
	}

	public void deposit(){
		Scanner scn = new Scanner(System.in);
		state.seeRemainingBalance();
		System.out.print("Amount to deposit: $");
		double amount = scn.nextDouble();
		state.deposit(amount);
		this.save();
	}

	public void withdraw(){
		Scanner scn = new Scanner(System.in);
		System.out.println("Current balance: $" + accountCreds.get(currAccount));
		System.out.print("Amount to withdraw: $");
		double amount = scn.nextDouble();
		state.withdraw(amount);
		this.save();
	}

	public void logout(){
		state.logout();
	}

	public void printOptions(){
		state.printOptions();
	}

	public ATMState getState(){
		return state;
	}

	public void save(){
		try{
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream obj = new ObjectOutputStream(file);
			obj.writeObject(this);
			obj.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setState(ATMState newState){
		state = newState;
	}

	public HashMap<HashMap<String, String>, Double> getAccountCreds(){
		return accountCreds;
	}

	public HashMap<String, String> getCurrAccount(){
		return currAccount;
	}

	public void setCurrAccount(HashMap<String, String> newCurrAccount){
		currAccount = newCurrAccount;
	}

	public double getRemainingMoneyInATM() {
		return remainingMoneyInATM;
	}

	public void setRemainingMoneyInATM(double remainingMoneyInATM) {
		this.remainingMoneyInATM = remainingMoneyInATM;
	}

	public String getFileName() { return fileName; }

}
