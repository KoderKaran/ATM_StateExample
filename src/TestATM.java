public class TestATM {
	public static void main(String[] args){
		ATMFactory factory = new ATMFactory();
		ATM atm = factory.makeATM(10000, "NJ");
		while(true){
			atm.printOptions();
		}
	}
}
