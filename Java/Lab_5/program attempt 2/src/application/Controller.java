package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;




import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class Controller {
	@FXML private TableView<Book> table;
	@FXML private TableColumn<Book, String> colAuthor;
	@FXML private TableColumn<Book, Integer> colYearPublished;
	@FXML private TableColumn<Book, Integer> colPageCount;
	@FXML private TableColumn<Book, String> colPublisher;
	@FXML private Button btnAdd, btnLoad, btnSave, btnDelete;
	@FXML private TextField txtAuthor, txtYearPublished, txtPageCount, txtPublisher; 
	@FXML private BarChart<String, Integer> chart;
	private ObservableList<Book> bookList = FXCollections.observableArrayList();
	private FileChooser fileChooser = new FileChooser();
	
	@FXML 
	private void initialize() {
		fileChooser.setInitialDirectory(new File("."));
		table.setEditable(true);
		table.setItems(bookList);

		bookList.addListener(new ListChangeListener<Book>() {
			@Override
			public void onChanged(
				javafx.collections.ListChangeListener.Change<? extends Book> arg0) {
					updateChart();
				}
		});
		setColAuthor();
		setColYearPublished();
		setColPageCount();
		setColPublisher();
	}
	
	private void setColAuthor() {
		colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
		colAuthor.setCellFactory(TextFieldTableCell.forTableColumn());
		colAuthor.setOnEditCommit(event -> {
			String author = event.getNewValue();
			if (author.isEmpty()) { 
				showAlert("Пустое поле", "Заполните поле \"Марка\""); 
			}
			else event.getRowValue().setAuthor(author);
			updateChart();
		});
	}
	
	private void setColYearPublished() {
		colYearPublished.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
		colYearPublished.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter() {
			@Override
			public Integer fromString(String value) {
				try {
					return Integer.parseInt(value);
				} catch(NumberFormatException e) {
					return null;
				}
			}
		}));
		colYearPublished.setOnEditCommit(event -> {
			try {
				Integer year = event.getNewValue();
				if (year == null) throw new Exception();
				event.getRowValue().setYearPublished(year);	
			} catch(Exception e) {
				showAlert("Ошибка формата", "Введите корректное значение года выпуска.");
			}
			updateChart();
		});
	}
	
	private void setColPageCount() {
		colPageCount.setCellValueFactory(new PropertyValueFactory<>("pageCount"));
		colPageCount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter(){
			@Override
			public Integer fromString(String value) {
				try {
					return Integer.parseInt(value);
				} catch(NumberFormatException e) {
					return null;
				}
			}
		}));
		colPageCount.setOnEditCommit(event -> {
			try {
				Integer volume = event.getNewValue();
				if (volume < 0) throw new Exception();
				event.getRowValue().setPageCount(volume);
			} catch(Exception e) {
				showAlert("Ошибка формата","Введите корректное значение объема двигателя.");
			}
			updateChart();
		});
	}
	
	private void setColPublisher() {
		colPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		colPublisher.setCellFactory(TextFieldTableCell.forTableColumn());
		colPublisher.setOnEditCommit(event -> {
			String publisher = event.getNewValue();
			if (publisher.isEmpty()) { 
				showAlert("Пустое поле", "Заполните поле \"Максимальная скорость\""); 
			}
			else event.getRowValue().setPublisher(publisher);
			updateChart();
		});
	}
		
	private void showAlert(String header, String text) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Ошибка");
		alert.setHeaderText(header);
		alert.setContentText(text);
		alert.show();
	}
	
	public void updateChart() {
		ObservableList<XYChart.Series<String, Integer>> data = FXCollections.observableArrayList();
		Optional<XYChart.Series<String, Integer>> seriesOptional;
		for(Book book : bookList) {
			seriesOptional = data.stream().filter(p -> p.getName().equals(book.getAuthor())).findFirst();
			if(seriesOptional.isPresent()) {
				seriesOptional.get().getData().add(new XYChart.Data<>(book.getAuthor(), book.getPageCount()));
			} else {
				ObservableList<XYChart.Data<String, Integer>> list = FXCollections.observableArrayList();
				list.add(new XYChart.Data<>(book.getAuthor(), book.getPageCount()));
				data.add(new XYChart.Series<>(book.getAuthor(), list));
			}
		}
		table.refresh();
		chart.getData().setAll(data);
	}
	
	@FXML
	public void addEntry() {
		String author = txtAuthor.getText(),
			   year = txtYearPublished.getText(),
			   volume = txtPageCount.getText(),
			   publisher = txtPublisher.getText();
		int yearValue, volumeValue;
		
		if (author.isEmpty() || year.isEmpty() || volume.isEmpty() || publisher.isEmpty()) {
			showAlert("Запись не создана", "Заполните все поля.");
		} else {		
			try { yearValue = Integer.valueOf(year); }
			catch(NumberFormatException e){
				showAlert("Ошибка формата","Введите корректное значение года выпуска.");
				return;
			}
			try {
				volumeValue = Integer.valueOf(volume);
				if (volumeValue < 0) throw new NumberFormatException();
			}
			catch(NumberFormatException e){
				showAlert("Ошибка формата","Введите корректное значение объём двигателя.");
				return;
			}
			
			bookList.add(new Book(author, yearValue, volumeValue, publisher));
			txtAuthor.clear(); 
			txtYearPublished.clear(); 
			txtPageCount.clear(); 
			txtPublisher.clear();
			updateChart();
		} 
	}
	@FXML
	public void deleteEntry() {
		Book book = table.getSelectionModel().getSelectedItem();
		if (book != null) {
			bookList.remove(book);
			updateChart();
		}
	}
	/*@FXML
	public void loadData() {
		fileChooser.setTitle("Загрузка");
		File file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			try {
				DataInputStream input = new DataInputStream(new FileInputStream(file));
				int n = input.readInt();
				ObservableList<Book> list = FXCollections.observableArrayList();
				for (int i = 0; i < n; i++) {
					list.add(Book.read(input));
				}
				bookList.setAll(list);
				updateChart();
			} catch (IOException e) {
				showAlert("Ошибка чтения", "Не удалось прочесть данные.");
			}
		}
	}*/
	@FXML
	public void loadData() {
		fileChooser.setTitle("Загрузка");
		File file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				ObservableList<Book> list = FXCollections.observableArrayList();
				String line;
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(";");
					String author = parts[0];
					int yearPublished = Integer.parseInt(parts[1]);
					int pageCount = Integer.parseInt(parts[2]);
					String publisher = parts[3];
					list.add(new Book(author, yearPublished, pageCount, publisher));
				}
				bookList.setAll(list);
				updateChart();
			} catch (IOException e) {
				showAlert("Ошибка чтения", "Не удалось прочесть данные.");
			}
		}
	}

	/*@FXML
	public void saveData() {
		fileChooser.setTitle("Сохранение");
		File file = fileChooser.showSaveDialog(new Stage());
		if (file != null) {
			try {
				DataOutputStream output = new DataOutputStream(new FileOutputStream(file));
				output.writeInt(bookList.size());
				for(Book book : bookList) { book.write(output); }
				updateChart();
			} catch (IOException e) {
				showAlert("Ошибка сохранения", "Не удалось сохранить записи в файле.");
			}
		}
	}*/
	@FXML
	public void saveData() {
	    fileChooser.setTitle("Сохранение");
	    File file = fileChooser.showSaveDialog(new Stage());
	    if (file != null) {
	        try {
	            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	            ObservableList<Book> list = bookList;
	            /*writer.write(Integer.toString(list.size()));
	            writer.newLine();*/
	            for (Book book : list) {
	                writer.write(book.getAuthor() + ";" + book.getYearPublished() + ";" + book.getPageCount() + ";"
	                            + book.getPublisher());
	                writer.newLine();
	            }
	            writer.close();
	            updateChart();
	        } catch (IOException e) {
	            showAlert("Ошибка сохранения", "Не удалось сохранить записи в файле.");
	        }
	    }
	}


}
