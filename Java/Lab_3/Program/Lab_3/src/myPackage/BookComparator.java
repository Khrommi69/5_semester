package myPackage;

import java.util.Comparator;

public class BookComparator implements Comparator <Book> {

	@Override
	public int compare (Book obj1, Book obj2) {
		if (obj1.yearOfPublication > obj2.yearOfPublication) {
			return 1;
		}
		else if (obj1.yearOfPublication < obj2.yearOfPublication) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
}
