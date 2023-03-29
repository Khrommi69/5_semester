package application;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javafx.beans.property.*;

public class Book {
	private final SimpleStringProperty author;
	private final SimpleIntegerProperty yearPublished;
	private final SimpleIntegerProperty pageCount;
	private final SimpleStringProperty publisher;

	public Book(String author, int yearPublished, int pageCount, String publisher) {
	    this.author = new SimpleStringProperty(author);
	    this.yearPublished = new SimpleIntegerProperty(yearPublished);
	    this.pageCount = new SimpleIntegerProperty(pageCount);
	    this.publisher = new SimpleStringProperty(publisher);
	}

	public String getAuthor() {
	    return author.get();
	}

	public int getYearPublished() {
	    return yearPublished.get();
	}

	public int getPageCount() {
	    return pageCount.get();
	}

	public String getPublisher() {
	    return publisher.get();
	}

	public void setAuthor(String author) {
	    this.author.set(author);
	}

	public void setYearPublished(int yearPublished) {
	    this.yearPublished.set(yearPublished);
	}

	public void setPageCount(int pageCount) {
	    this.pageCount.set(pageCount);
	}

	public void setPublisher(String publisher) {
	    this.publisher.set(publisher);
	}

	public void write(DataOutputStream output) throws IOException {
	    output.writeUTF(getAuthor());
	    output.writeInt(getYearPublished());
	    output.writeInt(getPageCount());
	    output.writeUTF(getPublisher());
	}

	public static Book read(DataInputStream input) throws IOException {
	    return new Book(input.readUTF(), input.readInt(), input.readInt(), input.readUTF());
	}
}
