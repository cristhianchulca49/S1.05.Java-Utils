# 1.5 Java Utils

## 📄 Description
This project contains the implementation of five exercises from **Sprint 1.05** - File system utilities and serialization in Java.

### Exercise 1: Alphabetical Directory Listing
Class that lists the contents of a directory received as a parameter in **alphabetical order**.

### Exercise 2: Recursive Directory Tree
Extends the previous functionality to list a **directory tree** with all its levels recursively, showing:

- Type indicator: `[D]` for directories, `[F]` for files  
- Last modification date  
- Alphabetical order within each level  

### Exercise 3: Save Result to TXT
Modifies the previous exercise to **save the result in a TXT file** instead of printing it to the console.

### Exercise 4: Read TXT File
Functionality to **read any TXT file** and display its content in the console.

### Exercise 5: Object Serialization
Serializes a Java object to a `.ser` file and then deserializes it, demonstrating **object persistence**.

## 💻 Technologies Used
- Java 17  
- Maven - Dependency management and build  
- IntelliJ IDEA - Integrated Development Environment  
- Git - Version control  
- JUnit 5 (Jupiter) - Testing framework  
  - `@Test` - Unit tests  
  - `@TempDir` - Temporary directories for tests  
- Java I/O:  
  - `File`, `BufferedWriter`, `Scanner`  
  - `ObjectInputStream`, `ObjectOutputStream`  
  - `Serializable`

## 📋 Requirements
To run this project you need:

- Java Development Kit (JDK) 17 or higher  
- Maven 3.6+  
- IntelliJ IDEA (Community or Ultimate) or any Java-compatible IDE  
- Git to clone the repository

## 🛠️ Installation
Clone this repository:  
git clone https://github.com/cristhianchulca49/S4.JUnit.git

---
🤝 Contributions are welcome! 
- Please follow these steps to contribute: 
- Fork the repository Create a new branch: git checkout -b feature/NewFeature 
- Make your changes and commit them: git commit -m 'Add New Feature' 
- Push the changes to your branch: git push origin feature/NewFeature 
- Open a Pull Request
