import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App implements ActionListener, ListSelectionListener {
    private JFrame window;
    private DefaultListModel<String> listModel;
    private JTextField names;
    private JPanel optionsPanel;
    private JLabel title;
    private JList<String> list;
    private Desktop desk = Desktop.getDesktop();
    private JTextField linkField;
    private Map<String, String> itemLinkMap = new HashMap<>();
    private String selectedValue;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().createAndShowGUI());
    }
    
    private void createAndShowGUI() {
        window = new JFrame("App");
        window.setLayout(new BorderLayout());
        // --- welcome ---
        JLabel label = new JLabel("Welcome", JLabel.CENTER);
        label.setFont(new Font("Verdana", Font.PLAIN, 20));
        window.add(label, BorderLayout.NORTH);
        // --- prefrences ---
        JPanel preferencesPanel = new JPanel();
        preferencesPanel.setBackground(Color.darkGray);
        preferencesPanel.setLayout(new BoxLayout(preferencesPanel, BoxLayout.Y_AXIS));
        preferencesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel namesPanel = new JPanel();
        namesPanel.setBackground(Color.darkGray);
        namesPanel.setLayout(new BorderLayout());
        namesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.lightGray), "Input Name", 0, 0, null, Color.lightGray));
        // input names
        names = new JTextField(16);
        namesPanel.add(names, BorderLayout.CENTER);

        JButton searchButton = new JButton("Search ");
        searchButton.addActionListener(this);
        namesPanel.add(searchButton, BorderLayout.EAST);
        // list
        JPanel listPanel = new JPanel();
        listPanel.setBackground(Color.darkGray);
        listPanel.setLayout(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.lightGray), "Names", 0, 0, null, Color.lightGray));
        
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        loadDataFromFile("memory.txt");
        list.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setPreferredSize(new Dimension(300, 300));
        listPanel.add(listScrollPane, BorderLayout.CENTER);
        // Add components to preferences panel
        preferencesPanel.add(namesPanel);
        preferencesPanel.add(Box.createRigidArea(new Dimension(0, 10)));  // Add spacing between panels
        preferencesPanel.add(listPanel);
        window.add(preferencesPanel, BorderLayout.WEST);
        // --- adding options per list item -- 
        optionsPanel = new JPanel();
        optionsPanel.setBackground(Color.white); // Not exactly the color I want
        optionsPanel.setLayout(new BorderLayout());
        optionsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.lightGray), "Options", 0, 0, null, Color.lightGray));
        
        window.add(optionsPanel);
        // --- loop ---
        window.setSize(800, 400);
        window.setVisible(true);
    }
    
    private void loadDataFromFile(String fileName) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            boolean isListModel = false;
            boolean isLink = false;
            List<String> items = new ArrayList<>();
            List<String> links = new ArrayList<>();

            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("listModel:")) {
                    isListModel = true;
                    isLink = false;
                    continue;
                } else if (line.startsWith("link:")) {
                    isListModel = false;
                    isLink = true;
                    continue;
                }
                // ---
                if (isListModel) {
                    String[] listItems = line.split(",");
                    for (String item : listItems) {
                        item = item.trim();
                        listModel.addElement(item);
                        items.add(item);
                    }
                } else if (isLink) {
                    String[] linkItems = line.split(",");
                    for (String link : linkItems) {
                        link = link.trim();
                        links.add(link);
                    }
                }
            }

            // Populate the itemLinkMap with items and their corresponding links
            for (int i = 0; i < items.size() && i < links.size(); i++) {
                itemLinkMap.put(items.get(i), links.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateDataToFile(String fileName, String key, String newValue) {
        List<String> lines = new ArrayList<>();
        boolean isListModel = false;
        boolean isLink = false;
        boolean updated = false;
        
        // Read existing file content
        try {
            lines = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Update the content
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.startsWith("listModel:")) {
                isListModel = true;
                isLink = false;
                continue;
            } else if (line.startsWith("link:")) {
                isListModel = false;
                isLink = true;
                continue;
            }

            if (isListModel && key.equals("listModel")) {
                lines.set(i, newValue);
                updated = true;
                break;
            } else if (isLink && key.equals("link")) {
                lines.set(i, newValue);
                updated = true;
                break;
            }
        }

        // If the key was not found, add new key-value pair
        if (!updated) {
            lines.add(key + ": " + newValue);
        }

        // Write updated content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getListModelContent() {
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < listModel.size(); i++) {
            content.append(listModel.get(i));
            if (i < listModel.size() - 1) {
                content.append(", ");
            }
        }   
        return content.toString();
    }

    private String getItemLink(String item) {
        // System.out.println(itemLinkMap);
        return itemLinkMap.getOrDefault(item.toLowerCase(), "Link not found");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listModel.addElement(names.getText());
        updateDataToFile("memory.txt", "listModel", getListModelContent());
        names.setText("");
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            JList<String> list = (JList<String>) e.getSource();
            String selectedValue = list.getSelectedValue();
            if (selectedValue != null) {
                optionsPanel.removeAll();
                optionsPanel.updateUI();
                optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
                optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                // title
                title = new JLabel("Name: " + selectedValue.toUpperCase());
                title.setFont(new Font("Verdana", Font.BOLD, 16));
                title.setAlignmentX(Component.CENTER_ALIGNMENT);
                optionsPanel.add(title);
                // Add some spacing
                optionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                // Add the remove button
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
                JButton removeButton = new JButton("Remove");
                removeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        listModel.removeElement(selectedValue);
                        updateDataToFile("memory.txt", "listModel", getListModelContent());
                        optionsPanel.removeAll();
                        optionsPanel.revalidate();
                        optionsPanel.repaint();
                    }
                });
                buttonPanel.add(removeButton);
                // open button
                JButton openButton = new JButton("Open");
                openButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            desk = Desktop.getDesktop();
                            desk.browse(new URI(linkField.getText()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                buttonPanel.add(openButton);
                optionsPanel.add(buttonPanel);
                // Add some spacing
                optionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                // link
                JLabel linkLabel = new JLabel("Link:");
                linkLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                optionsPanel.add(linkLabel, BorderLayout.EAST);
                linkField = new JTextField();
                linkField.setMaximumSize(new Dimension(Integer.MAX_VALUE, linkField.getPreferredSize().height));
                linkField.setAlignmentX(Component.CENTER_ALIGNMENT);
                linkField.setText(getItemLink(selectedValue));
                linkField.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        updateLink();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        updateLink();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        updateLink();
                    }

                    private void updateLink() {
                        itemLinkMap.put(selectedValue, linkField.getText());
                        updateDataToFile("memory.txt", "link", getLinkContent());
                    }
                });
                optionsPanel.add(linkField);
                // Add some spacing
                optionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                // adding an avrage time
                JLabel avrageTimeLabel = new JLabel("What avrage time is best:");
                avrageTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                optionsPanel.add(avrageTimeLabel, BorderLayout.EAST);
                JTextField averageTime = new JTextField();
                averageTime.setMaximumSize(new Dimension(Integer.MAX_VALUE, averageTime.getPreferredSize().height));
                averageTime.setAlignmentX(Component.CENTER_ALIGNMENT);
                optionsPanel.add(averageTime);
                // Add some spacing
                optionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                // add checkboxes
                JPanel checkBoxPanel = new JPanel();
                checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
                checkBoxPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                checkBoxPanel.setBackground(Color.lightGray);

                JCheckBox allCheckBox = new JCheckBox("Recommend All", false);
                JCheckBox someCheckBox = new JCheckBox("Recommend Some", false);
                JCheckBox noneCheckBox = new JCheckBox("Recommend None", false);
                // Center align checkboxes
                allCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                someCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                noneCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                checkBoxPanel.add(allCheckBox);
                checkBoxPanel.add(someCheckBox);
                checkBoxPanel.add(noneCheckBox);
                optionsPanel.add(checkBoxPanel);
                // Refresh the panel
                optionsPanel.revalidate();
                optionsPanel.repaint();
            }
        }
    }
}
