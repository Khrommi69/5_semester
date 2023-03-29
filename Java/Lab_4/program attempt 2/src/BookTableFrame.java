import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class BookTableFrame extends JFrame {
    private JTable table;
    private BookTableModel tableModel;
    private JTextField authorField, yearField, pageCountField, publisherField;

    public BookTableFrame() {
        setTitle("Таблица книг");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем таблицу и модель таблицы
        BookTableModel tableModel = new BookTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Создаем компоненты JLabel и JTextField для каждого поля
        JLabel authorLabel = new JLabel("Марка");
        JLabel yearLabel = new JLabel("Год выпуска");
        JLabel pageCountLabel = new JLabel("Объём двигателя");
        JLabel publisherLabel = new JLabel("Максимальная скорость");

        authorField = new JTextField(20);
        yearField = new JTextField(10);
        pageCountField = new JTextField(10);
        publisherField = new JTextField(20);

        // Создаем панель для размещения компонентов JLabel и JTextField
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(authorLabel);
        inputPanel.add(authorField);
        inputPanel.add(yearLabel);
        inputPanel.add(yearField);
        inputPanel.add(pageCountLabel);
        inputPanel.add(pageCountField);
        inputPanel.add(publisherLabel);
        inputPanel.add(publisherField);

        // Создаем панель для размещения кнопок
        //JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JButton addButton = new JButton("Добавить");
        JButton editButton = new JButton("Редактировать");
        JButton sortButton = new JButton("Сортировка по году");
        JButton deleteButton = new JButton("Удалить");
        JButton loadButton = new JButton("Добавление из файла");
        JButton saveButton = new JButton("Сохранение в файл");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);


        // Обработчик события выбора строки в таблице
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    Object author = table.getValueAt(selectedRow, 0);
                    Object year = table.getValueAt(selectedRow, 1);
                    Object pageCount = table.getValueAt(selectedRow, 2);
                    Object publisher = table.getValueAt(selectedRow, 3);
                    authorField.setText(author.toString());
                    yearField.setText(year.toString());
                    pageCountField.setText(pageCount.toString());
                    publisherField.setText(publisher.toString());
                }
            }
        });


        // Обработчик события нажатия на кнопку "Добавить"
        addButton.addActionListener(e -> {
        	String author = authorField.getText();
        	String year = yearField.getText();
        	String pageCount = pageCountField.getText();
        	String publisher = publisherField.getText();
        	// Проверяем, что все поля заполнены
        	if (author.isEmpty() || year.isEmpty() || pageCount.isEmpty() || publisher.isEmpty()) {
        	    JOptionPane.showMessageDialog(this, "Заполните все поля!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        	    return;
        	}

        	// Создаем новую запись и добавляем ее в модель таблицы
        	Book book = new Book(author, Integer.parseInt(year), Integer.parseInt(pageCount), publisher);
        	tableModel.addRow(book);

        	// Очищаем поля ввода
        	authorField.setText("");
        	yearField.setText("");
        	pageCountField.setText("");
        	publisherField.setText("");

        	// Сообщаем пользователю об успешном добавлении записи
        	JOptionPane.showMessageDialog(this, "Запись успешно добавлена!", "Успех", JOptionPane.INFORMATION_MESSAGE);
        });
     // Обработчик события нажатия на кнопку "Редактировать"
        editButton.addActionListener(e -> {
        int selectedRow = table.getSelectedRow();
     // Проверяем, что выбрана какая-то строка
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите запись для редактирования!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Получаем данные выбранной записи
        Book book = tableModel.getBookAt(selectedRow);
        String author = authorField.getText();
        String year = yearField.getText();
        String pageCount = pageCountField.getText();
        String publisher = publisherField.getText();

        // Проверяем, что все поля заполнены
        if (author.isEmpty() || year.isEmpty() || pageCount.isEmpty() || publisher.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Заполните все поля!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Обновляем данные записи и обновляем таблицу
        book.setAuthor(author);
        book.setYear(Integer.parseInt(year));
        book.setPageCount(Integer.parseInt(pageCount));
        book.setPublisher(publisher);
        tableModel.fireTableDataChanged();

        // Очищаем поля ввода
        authorField.setText("");
        yearField.setText("");
        pageCountField.setText("");
        publisherField.setText("");

        // Сообщаем пользователю об успешном редактировании записи
        JOptionPane.showMessageDialog(this, "Запись успешно отредактирована!", "Успех", JOptionPane.INFORMATION_MESSAGE);
        });
        // Обработчик события нажатия на кнопку "Удалить"
        deleteButton.addActionListener(e -> {
        int selectedRow = table.getSelectedRow();
        // Проверяем, что выбрана какая-то строка
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите запись для удаления!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Удаляем выбранную запись из модели таблицы и обновляем таблицу
        tableModel.deleteRow(selectedRow);
        tableModel.fireTableRowsDeleted(selectedRow, selectedRow);
        //tableModel.fireTableDataChanged();
        
        // Сообщаем пользователю об успешном удалении записи
        JOptionPane.showMessageDialog(this, "Запись успешно удалена!", "Успех", JOptionPane.INFORMATION_MESSAGE);
        });
        
     // Обработчик события нажатия на кнопку "Добавление из файла"
        loadButton.addActionListener(e -> {
            // Открываем диалоговое окно для выбора файла
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    // Считываем данные из файла
                    List<Book> books = new ArrayList<>();
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        String author = parts[0];
                        int year = Integer.parseInt(parts[1]);
                        int pageCount = Integer.parseInt(parts[2]);
                        String publisher = parts[3];
                        Book book = new Book(author, year, pageCount, publisher);
                        books.add(book);
                    }
                    reader.close();

                    // Добавляем данные в модель таблицы
                    for (Book book : books) {
                        tableModel.addRow(book);
                    }
                    
                 // Уведомляем таблицу об обновлении данных
                    tableModel.fireTableDataChanged();

                    // Сообщаем пользователю об успешном добавлении записей
                    JOptionPane.showMessageDialog(this, "Записи успешно добавлены!", "Успех", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Ошибка чтения файла!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
     // Обработчик события нажатия на кнопку "Сохранение в файл"
        saveButton.addActionListener(e -> {
            // Открываем диалоговое окно для выбора файла
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    // Записываем данные из модели таблицы в файл
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        String author = (String)tableModel.getValueAt(i, 0);
                        int year = (int)tableModel.getValueAt(i, 1);
                        int pageCount = (int)tableModel.getValueAt(i, 2);
                        String publisher = (String)tableModel.getValueAt(i, 3);
                        String line = String.format("%s,%d,%d,%s\n", author, year, pageCount, publisher);
                        writer.write(line);
                    }
                    writer.close();

                    // Сообщаем пользователю об успешном сохранении данных
                    JOptionPane.showMessageDialog(this, "Данные успешно сохранены в файл!", "Успех", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Ошибка записи в файл!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

     // Обработчик события нажатия на кнопку "Сортировка по году"
        sortButton.addActionListener(e -> {
        	tableModel.sortRows();
        });

        
        
     // Добавляем компоненты на форму
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.EAST);
    }  
    
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            BookTableFrame frame = new BookTableFrame();
	            frame.setVisible(true);
	        });
	    }
}    