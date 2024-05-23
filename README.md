# App

[![Static Badge](https://img.shields.io/badge/uri-pink)](https://docs.oracle.com/javase/8/docs/api/java/net/URI.html)
[![Static Badge](https://img.shields.io/badge/filewriter-beige)](https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html)
[![Static Badge](https://img.shields.io/badge/bufferedwriter-wheat)](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedWriter.html)
[![Static Badge](https://img.shields.io/badge/ioexception-sienna)](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html?is-external=true)
[![Static Badge](https://img.shields.io/badge/documentlistener-chocolate)](https://docs.oracle.com/javase/8/docs/api/javax/swing/event/DocumentListener.html)

The Java Swing application offers a GUI for managing a list of names, allowing users to add, view, search, open, remove, and save data from a file.

## Table of Contents

- [About](#about)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## About

The Java Swing application offers a GUI for managing a list of names with linked links, allowing users to add, view, search, open, and remove items, and also supports saving and loading data from a file.

## Features
- Add Name: Add a name to the list with an associated link.
- Search Name: Search and select names from the list.
- Open Link: Open the link associated with a selected name in the default web browser.
- Remove Name: Remove a name from the list.
- Save and Load Data: Save the list of names and links to a file and load it upon startup.

## Installation

- Clone the Repository: Clone this repository to your local machine.
git clone [<repository_url>](https://github.com/Statute8234/App.git)

- Navigate to the Project Directory:
cd App

- Compile the Application:
javac App.java

- Run the Application:
java App

## Usage

- Add a Name:
Enter a name in the input field.
Click the "Search" button to add the name to the list.

- Select a Name:
Click on a name in the list to view and modify its details in the options panel.

- Open Link:
After selecting a name, modify or add the link in the link field.
Click the "Open" button to open the link in the default web browser.

- Remove a Name:
Click the "Remove" button in the options panel to remove the selected name from the list.

## Contributing

Contributions are welcome! To contribute to Monster Maze, follow these steps:

1. Fork the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature/YourFeature
   ```
3. Make your changes and commit them:
   ```bash
   git commit -m "Add new feature"
   ```
4. Push to the branch:
   ```bash
   git push origin feature/YourFeature
   ```
5. Create a new Pull Request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
