import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.table.AbstractTableModel;

public class BookTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private final ArrayList<Book> books;

	public BookTableModel() {
		books = new ArrayList<>();
	}

	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return Integer.class;
		case 2:
			return Integer.class;
		case 3:
			return String.class;
		default:
			return Object.class;
		}
	}

	public int getColumnCount() {
		return 4;
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Марка";
		case 1:
			return "Год выпуска";
		case 2:
			return "Объём двигателя";
		case 3:
			return "Максимальная скорость";
		default:
			return "";
		}
	}

	public int getRowCount() {
		return books.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Book book = getBookAt(rowIndex);
		switch (columnIndex) {
		case 0:
			return book.getAuthor();
		case 1:
			return book.getYear();
		case 2:
			return book.getPageCount();
		case 3:
			return book.getPublisher();
		default:
			return null;
		}
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Book book = getBookAt(rowIndex);
		switch (columnIndex) {
		case 0:
			book.setAuthor((String) value);
			break;
		case 1:
			book.setYear((int) value);
			break;
		case 2:
			book.setPageCount((int) value);
			break;
		case 3:
			book.setPublisher((String) value);
			break;
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	public void addRow(Book book) {
		books.add(book);
		int index = books.size() - 1;
		fireTableRowsInserted(index, index);
	}

	public void deleteRow(int rowIndex) {
		books.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void updateRow(int row, Book book) {
		books.set(row, book);
		fireTableRowsUpdated(row, row);
	}

	public void sortRows() {
		Collections.sort(books, Comparator.comparingInt(Book::getYear));
		fireTableDataChanged();
	}
	
	/*public void sortRows() {
	    Collections.sort(books, Comparator.comparingInt(Book::getPageCount).reversed());
	    fireTableDataChanged();
	}*/

	public Book getBookAt(int rowIndex) {
		return books.get(rowIndex);
	}
}
