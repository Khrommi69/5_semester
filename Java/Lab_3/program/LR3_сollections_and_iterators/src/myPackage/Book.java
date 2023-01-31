
package myPackage;

//Класс - Книга(Автор, Год издания, Количество страниц, Издательство)

public class Book {

	private String autor;
	private int yearOfPublication;
	private int numPages;
	private String publisher;
	
	public Book (String autor, int yearOfPublication, int numPages, String publisher) {
		this.autor = autor;
		this.yearOfPublication = yearOfPublication;
		this.numPages = numPages;
		this.publisher = publisher;
	}
	
	public void printBook () {
		System.out.println(autor + " " + yearOfPublication + " " + numPages + " " + publisher);
	}
	
	
}