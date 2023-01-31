package myPackage;

//для работы с коллекциями
import java.util.TreeSet;
import java.io.*;

public class FirstTask extends TreeSet <Book> {
	
	//я не знаю что это, но IDE очень хотела чтобы у меня была эта строчка кода
//	private static final long serialVersionUID = 1L;

	public void readFile(String fileName) {
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while (bufferedReader.ready()) {
//			 	System.out.println(bufferedReader.readLine());
			 	bufferedReader.readLine();
//			 	add(Book(bufferedReader.readLine(), Integer.parseInt(bufferedReader.readLine()), Integer.parseInt(bufferedReader.readLine()), bufferedReader.readLine()));
			}
			 bufferedReader.close();
		}
		catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void print() {
		for (Book obj : this) {
			System.out.println(obj);
		}
	}
	
	
	
}









//FileWriter fileWriter = new FileWriter(fileName);
//BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//bufferedWriter.write("J.K. Rowling\r\n"
//		+ "2012\r\n"
//		+ "400\r\n"
//		+ "Rosman-press\r\n"
//		+ "Otfried Preusler\r\n"
//		+ "2012\r\n"
//		+ "72\r\n"
//		+ "Exmo\r\n"
//		+ "Vladimir Skorobogatov\r\n"
//		+ "2008\r\n"
//		+ "65\r\n"
//		+ "FamilyClub\r\n"
//		+ "Sui Ishida\r\n"
//		+ "2022\r\n"
//		+ "464\r\n"
//		+ "Azbuka-atticus\r\n"
//		+ "Andrey Bludin\r\n"
//		+ "2007\r\n"
//		+ "340\r\n"
//		+ "Makhaon");
//bufferedWriter.close();

//File newFile = new File("Test.txt");
//FileWriter fileWriter = new FileWriter(newFile);
//fileWriter.write("Some строка");
//fileWriter.close();