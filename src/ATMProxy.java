public class ATMProxy implements Proxy {
	private ATM atm;

	public ATMProxy(ATM newATM){
		atm = newATM;
	}

	@Override
	public void printOptions() {
		atm.printOptions();
	}
}
