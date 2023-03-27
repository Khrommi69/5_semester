package education;

//JFrame

//import javax.swing.JFrame;
//
//public class App {
//	
//    public static void main(String[] args) {
//        new MyFrame(); // Создаем экземпляр класса и отображаем окно на экране
//    }
//    
//}
//
//
//
//class MyFrame extends JFrame {
//
//    public MyFrame() {
//        super("ХУЙ"); // Устанавливаем заголовок окна
//        setSize(400, 300); // Устанавливаем размер окна
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Устанавливаем режим закрытия окна
//        setVisible(true); // Отображаем окно на экране
//    }
//}
//


//JLabel

//import javax.swing.*;
//
//public class App {
//
//    public static void main(String[] args) {
//        // Создание нового фрейма
//        JFrame frame = new JFrame("JLabel Example");
//
//        // Создание новой метки
//        JLabel label = new JLabel("Привет, мир!");
//
//        // Установка позиции и размеров метки
//        label.setBounds(100, 100, 200, 50);
//
//        // Установка иконки для метки
//        ImageIcon icon = new ImageIcon("icon.png");
//        label.setIcon(icon);
//
//        // Установка горизонтального выравнивания текста
//        label.setHorizontalAlignment(JLabel.CENTER);
//
//        // Установка вертикального выравнивания текста
//        label.setVerticalAlignment(JLabel.CENTER);
//
//        // Добавление метки на фрейм
//        frame.add(label);
//
//        // Установка размеров фрейма и его видимость
//        frame.setSize(400, 300);
//        frame.setVisible(true);
//    }
//}


// getDisplayedMnemonic() 

/*
 * import javax.swing.JFrame; import javax.swing.JLabel; import java.awt.Font;
 * 
 * public class App { public static void main(String[] args) { JFrame frame =
 * new JFrame("JLabel Example");
 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 * 
 * JLabel label = new JLabel("Hello, world!"); label.setFont(new Font("Serif",
 * Font.BOLD, 24)); label.setHorizontalAlignment(JLabel.CENTER);
 * label.setVerticalAlignment(JLabel.CENTER); label.setDisplayedMnemonic('H');
 * label.setLabelFor(label); // указываем, что метка относится к самой себе
 * 
 * frame.getContentPane().add(label); frame.pack(); frame.setVisible(true); } }
 */


//ComboBox

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App implements ActionListener {
    private JComboBox<String> comboBox;

    public App() {
        JFrame frame = new JFrame("JComboBox Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Select a fruit:");
        panel.add(label);

        comboBox = new JComboBox<>();
        comboBox.addItem("Apple");
        comboBox.addItem("Banana");
        comboBox.addItem("Orange");
        comboBox.addActionListener(this);
        panel.add(comboBox);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            String selectedFruit = (String) comboBox.getSelectedItem();
            JOptionPane.showMessageDialog(null, "You selected: " + selectedFruit);
        }
    }
}