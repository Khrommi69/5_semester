package myPackage;

import java.util.TreeSet;
import java.io.*;

public class Task1 extends TreeSet <Book> {

	//IDE очень хотела чтобы я добавил уникальный идентификатор для этого класса
	private static final long serialVersionUID = 1L;

	public void readInfo (String fileName) {
//		
//		String autor;
//		String yearOfPublication;
//		String numPages;
//		String publishingHouse;
		
		try {
            FileReader fileReader = new FileReader (fileName);
            BufferedReader bufReader = new BufferedReader (fileReader);
            String line;
            while ((line = bufReader.readLine()) != null) {
//            	autor = line;
//                yearOfPublication = bufReader.readLine();
//                numPages = bufReader.readLine();
//                publishingHouse = bufReader.readLine();
                add(new Book(line, Integer.parseInt(bufReader.readLine()), Integer.parseInt(bufReader.readLine()), bufReader.readLine()));
            }
            bufReader.close();
        }
		catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		catch (IOException e) {
            e.printStackTrace();
        }
    }

	
	public void print() {
		for (Book obj : this) {
			System.out.println(obj);
		}
		
//		for (Book x : collection1) {
//			System.out.println(x.autor + " " + x.yearOfPublication + " " + x.numPages + " " + x.publishingHouse);
//		}
	}
	
	
	
}
