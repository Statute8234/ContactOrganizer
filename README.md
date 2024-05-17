# Boggle

[![Static Badge](https://img.shields.io/badge/uri-pink)](https://docs.oracle.com/javase/8/docs/api/java/net/URI.html)
[![Static Badge](https://img.shields.io/badge/filewriter-beige)](https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html)
[![Static Badge](https://img.shields.io/badge/bufferedwriter-wheat)](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedWriter.html)
[![Static Badge](https://img.shields.io/badge/ioexception-sienna)](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html?is-external=true)
[![Static Badge](https://img.shields.io/badge/documentlistener-chocolate)](https://docs.oracle.com/javase/8/docs/api/javax/swing/event/DocumentListener.html)

This Java Swing-based GUI application allows users to dynamically manage a list of names and their associated links. It features a main window, a preferences panel, a search button, list management, an options panel, data persistence, and user interaction. The application reads and writes to a file, handles data updates, and allows users to add, remove, and update list items. Tooltips are provided for additional guidance on button functionalities.

## Table of Contents

- [About](#about)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Rating: 7/10](#Rating)

# About

This Java Swing-based GUI application allows users to dynamically manage a list of names and their associated links. The main window features a JFrame with a border layout, a welcome label, a JPanel for user inputs, and a search button for adding names. The list is populated from a file (memory.txt) upon startup and each name is associated with a link stored in a HashMap. The Options Panel displays details and actions for selected items, allowing users to remove items, open associated links, and update link information. Additional UI components like labels, text fields, and checkboxes are included for user interaction. The application reads from and writes to a file (memory.txt) for data persistence and updates. The application supports adding, removing, and updating list items, and provides tooltips for additional guidance on button functionalities.

# Features

The Java Swing-based GUI application offers a user-friendly interface for managing a dynamic list of names and their associated links. The main window features a JFrame, welcome label, JPanel, and search button for input and addition. Each name in the list is associated with a link, stored in a HashMap. The Options Panel displays details and actions for selected list items, allowing users to remove items, open associated links, and update link information. User interface components like labels, text fields, and checkboxes enhance user interaction. The application ensures data persistence across sessions by reading and writing to the file'memory.txt'. Functionality includes adding, removing, and updating list items, and provides tooltips for button functionalities. Overall, the Java Swing-based GUI application offers a user-friendly interface for managing dynamic lists and links, with features for data persistence and efficient interaction.

# Installation
1) HTTPS - github.com/Statute8234/App.git
2) CLONE - Statute8234/App.git
   
# Usage

This Java Swing application allows users to manage a list of names with associated links using a GUI. To set up, run, and use this program on GitHub, follow these steps:

1. Create a new repository on GitHub and give it a name, description, and visibility (public or private).
2. Clone the repository to your local machine and add the Java program file (`App.java`) and create a `memory.txt` file for data storage.
3. Configure GitHub Actions to automate tasks such as building the project, running tests, or deploying the application.
4. Run the program locally by compiling the program and running it.
5. Usage instructions include launching the application, entering a name, managing the list, editing links, removing names, and opening links.
6. Save and update data by saving and loading the list of names and their associated links in the `memory.txt` file. Ensure that the file is in the same directory as the program or update the file path in the `loadDataFromFile` and `updateDataToFile` methods.
7. Push changes back to GitHub by following the steps provided.

The `memory.txt` file should follow the format: listModel: item1, item2, item3, link: link1, link2, link3.

In conclusion, this Java Swing application allows users to manage a list of names and their associated links using GitHub. The GitHub Actions workflow can be used to automate build and test processes, but it is crucial to ensure the program is correctly configured and tested locally before pushing changes to the repository.

# Rating

The project showcases the use of Java Swing in building a GUI application, including essential features for data management and persistence. The layout and organization of components are logical and user-friendly, and file I/O for data persistence is well-implemented. However, areas for improvement include modularizing the code for better readability and maintainability, enhancing error handling to handle specific exceptions, improving the user experience through better color schemes and fonts, and expanding the functionality with features like searching, sorting, and filtering list items. Testing through unit tests for data management functions and UI testing is also recommended. Overall, the project provides a solid foundation for building dynamic GUI applications with essential functionalities and good practices in data management and persistence.
