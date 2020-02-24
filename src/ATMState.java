public interface ATMState {
	void seeRemainingBalance();
	void deposit(double amount);
	void withdraw(double amount);
	void makeAccount(String user, String pass);
	void login(String user, String pass);
	void logout();
	void printOptions();
}
