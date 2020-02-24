public class TestATM {
	public static void main(String[] args){
		ATMFactory factory = new ATMFactory();
		ATM atm = factory.makeATM(10000, "NJ", "C:/Path/to/ATMs/here");
		while(true){
			atm.printOptions();
		}
	}
}
