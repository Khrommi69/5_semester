package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

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
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class Controller {
	@FXML private TableView<Car> table;
	@FXML private TableColumn<Car, String> colMark;
	@FXML private TableColumn<Car, Integer> colYear;
	@FXML private TableColumn<Car, Float> colVolume, colSpeed;
	@FXML private Button btnAdd, btnLoad, btnSave, btnDelete;
	@FXML private TextField txtMark, txtYear, txtVolume, txtSpeed; 
	@FXML private BarChart<String, Float> chart;
	private ObservableList<Car> carList = FXCollections.observableArrayList();
	private FileChooser fileChooser = new FileChooser();
	
	@FXML 
	private void initialize() {
		fileChooser.setInitialDirectory(new File("."));
		table.setEditable(true);
		table.setItems(carList);

		carList.addListener(new ListChangeListener<Car>() {
			@Override
			public void onChanged(
				javafx.collections.ListChangeListener.Change<? extends Car> arg0) {
					updateChart();
				}
		});
		setColMark();
		setColYear();
		setColVolume();
		setColSpeed();
	}
	
	private void setColMark() {
		colMark.setCellValueFactory(new PropertyValueFactory<>("mark"));
		colMark.setCellFactory(TextFieldTableCell.forTableColumn());
		colMark.setOnEditCommit(event -> {
			String mark = event.getNewValue();
			if (mark.isEmpty()) { 
				showAlert("Пустое поле", "Заполните поле \"Марка\""); 
			}
			else event.getRowValue().setMark(mark);
			updateChart();
		});
	}
	
	private void setColYear() {
		colYear.setCellValueFactory(new PropertyValueFactory<>("productionYear"));
		colYear.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter() {
			@Override
			public Integer fromString(String value) {
				try {
					return Integer.parseInt(value);
				} catch(NumberFormatException e) {
					return null;
				}
			}
		}));
		colYear.setOnEditCommit(event -> {
			try {
				Integer year = event.getNewValue();
				if (year == null) throw new Exception();
				event.getRowValue().setProductionYear(year);	
			} catch(Exception e) {
				showAlert("Ошибка формата", "Введите корректное значение года проиводства.");
			}
			updateChart();
		});
	}
	
	private void setColVolume() {
		colVolume.setCellValueFactory(new PropertyValueFactory<>("engineCapacity"));
		colVolume.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter(){
			@Override
			public Float fromString(String value) {
				try {
					return Float.parseFloat(value);
				} catch(NumberFormatException e) {
					return null;
				}
			}
		}));
		colVolume.setOnEditCommit(event -> {
			try {
				Float volume = event.getNewValue();
				if (volume < 0) throw new Exception();
				event.getRowValue().setEngineCapacity(volume);
			} catch(Exception e) {
				showAlert("Ошибка формата","Введите корректное значение объема двигателя.");
			}
			updateChart();
		});
	}
	
	private void setColSpeed() {
		colSpeed.setCellValueFactory(new PropertyValueFactory<>("maxSpeed"));
		colSpeed.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter() {
			@Override
			public Float fromString(String value) {
				try {
					return Float.parseFloat(value);
				} catch(NumberFormatException e) {
					return null;
				}
			}
		}));
		colSpeed.setOnEditCommit(event -> {
			try {
				Float speed = event.getNewValue();
				if (speed < 0) throw new Exception();
				event.getRowValue().setMaxSpeed(speed);
			} catch (Exception e) {
				event.getRowValue().setEngineCapacity( event.getOldValue());
				showAlert("Ошибка формата","Введите корректное значение максимальной скорости.");
			}
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
		ObservableList<XYChart.Series<String, Float>> data = FXCollections.observableArrayList();
		Optional<XYChart.Series<String, Float>> seriesOptional;
		for(Car car : carList) {
			seriesOptional = data.stream().filter(p -> p.getName().equals(car.getMark())).findFirst();
			if(seriesOptional.isPresent()) {
				seriesOptional.get().getData().add(new XYChart.Data<>(car.getMark(), car.getMaxSpeed()));
			} else {
				ObservableList<XYChart.Data<String, Float>> list = FXCollections.observableArrayList();
				list.add(new XYChart.Data<>(car.getMark(), car.getMaxSpeed()));
				data.add(new XYChart.Series<>(car.getMark(), list));
			}
		}
		table.refresh();
		chart.getData().setAll(data);
	}
	
	@FXML
	public void addEntry() {
		String mark = txtMark.getText(),
			   year = txtYear.getText(),
			   volume = txtVolume.getText(),
			   speed = txtSpeed.getText();
		int yearValue;
		float volumeValue, speedValue;
		
		if (mark.isEmpty() || year.isEmpty() || volume.isEmpty() || speed.isEmpty()) {
			showAlert("Запись не создана", "Заполните все поля.");
		} else {		
			try { yearValue = Integer.valueOf(year); }
			catch(NumberFormatException e){
				showAlert("Ошибка формата","Введите корректное значение года производства.");
				return;
			}
			try {
				volumeValue = Float.valueOf(volume);
				if (volumeValue < 0) throw new NumberFormatException();
			}
			catch(NumberFormatException e){
				showAlert("Ошибка формата","Введите корректное значение объема двигателя.");
				return;
			}
			try {
				speedValue = Float.valueOf(speed);
				if (speedValue < 0) throw new NumberFormatException();
			}
			catch(NumberFormatException e){
				showAlert("Ошибка формата","Введите корректное значение максимальной скорости.");
				return;
			}
			carList.add(new Car(mark, yearValue, volumeValue, speedValue));
			txtMark.clear(); 
			txtYear.clear(); 
			txtVolume.clear(); 
			txtSpeed.clear();
			updateChart();
		} 
	}
	@FXML
	public void deleteEntry() {
		Car car = table.getSelectionModel().getSelectedItem();
		if (car != null) {
			carList.remove(car);
			updateChart();
		}
	}
	@FXML
	public void loadData() {
		fileChooser.setTitle("Загрузка");
		File file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			try {
				DataInputStream input = new DataInputStream(new FileInputStream(file));
				int n = input.readInt();
				ObservableList<Car> list = FXCollections.observableArrayList();
				for (int i = 0; i < n; i++) {
					list.add(Car.read(input));
				}
				carList.setAll(list);
				updateChart();
			} catch (IOException e) {
				showAlert("Ошибка чтения", "Не удалось прочесть данные.");
			}
		}
	}
	@FXML
	public void saveData() {
		fileChooser.setTitle("Сохранение");
		File file = fileChooser.showSaveDialog(new Stage());
		if (file != null) {
			try {
				DataOutputStream output = new DataOutputStream(new FileOutputStream(file));
				output.writeInt(carList.size());
				for(Car car : carList) { car.write(output); }
				updateChart();
			} catch (IOException e) {
				showAlert("Ошибка сохранения", "Не удалось сохранить записи в файле.");
			}
		}
	}
}
