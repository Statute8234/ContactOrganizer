import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App implements ActionListener {
    private DefaultListModel<String> listModel;
    private JTextField names;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().createAndShowGUI());
    }
    
    private void createAndShowGUI() {
        JFrame window = new JFrame("App");
        window.setLayout(new BorderLayout());
        // --- welcome ---
        JLabel label = new JLabel("Welcome", JLabel.CENTER);
        label.setFont(new Font("Verdana", Font.PLAIN, 20));
        window.add(label);
        // --- prefrences ---
        JPanel preferences = new JPanel();
        preferences.setBackground(Color.darkGray);
        window.add(preferences);
        // input names
        names = new JTextField(16);
        preferences.add(names);
        JButton searchButton = new JButton("Search ");
        searchButton.addActionListener(this);
        preferences.add(searchButton);
        // list
        listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setPreferredSize(new Dimension(200, 100));
        preferences.add(listScrollPane);
        window.add(preferences);
        // --- loop ---
        window.setSize(800, 400);
        window.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        listModel.addElement(names.getText());
        names.setText("");
    }
}