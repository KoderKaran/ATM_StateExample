public class TestATM {
	public static void main(String[] args){
		ATMFactory factory = new ATMFactory();
		ATM atm = factory.makeATM(10000, "California", "C:/Path/to/ATM/saving/directory");
		ATMProxy fakeAtm = new ATMProxy(atm);
		while(true){
			fakeAtm.printOptions();
		}
	}
}
