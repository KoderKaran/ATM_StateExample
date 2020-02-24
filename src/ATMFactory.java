import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ATMFactory {

	public ATM makeATM(double startingBalance, String location, String pathName){
		ATM temp = null;
		File file = new File(pathName);
		String[] filesInDir = file.list();
		String atmFileName = location + "ATM.ser";
		if (filesInDir != null) {
			for(String fname : filesInDir){
				if (atmFileName.equals(fname)) {
					try {
						FileInputStream atmFile = new FileInputStream(atmFileName);
						ObjectInputStream input = new ObjectInputStream(atmFile);
						temp = (ATM)input.readObject();
						atmFile.close();
						input.close();
					} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		if(temp == null){
			System.out.println("Made new ATM!");
			temp = new ATM(startingBalance, location);
			System.out.println(temp);
			return temp;
		}else{
			System.out.println("Retrieved old ATM!");
			return temp;
		}
	}
}
