package com.example.lab13;

import CitationPackage.*;
import UserPackage.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;

public class Main extends Application {

    private static int n = 1;
    private static HashMap<String, String> configData = new HashMap<>();
    private static CitationList citationList = new CitationList();
    private static List<Citation> a = new ArrayList<>();
    private static UserList userList = new UserList();
    private static Scanner scanner = new Scanner(System.in);
    private static TableView<Citation> tableView = new TableView<>();
    private static List<Citation> cList = new ArrayList<>();
    private static BorderPane root = new BorderPane();
    private static MenuBar menuBar = new MenuBar();
    private static Label statusLabel = new Label("OK");

    private static Menu fileMenu = new Menu("File");
    private static Menu toolMenu = new Menu("Tool");

    private static MenuItem openFile = new MenuItem("Open");

    private static MenuItem saveFile = new MenuItem("Save as");
    private static MenuItem about = new MenuItem("About");
    private static MenuItem Quit = new MenuItem("Quit");
    private static MenuItem CD = new MenuItem("Citation Details");
    private static MenuItem SU = new MenuItem("Show Users");

    private static FileChooser fileChooser = new FileChooser();

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Initialize the TableView and columns
        initializeTableView();
        updateCitationTable();
        setUpMenu(primaryStage);  // Set up the menu
        root.setTop(menuBar);
        root.setCenter(tableView);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private static void initializeTableView() {
        TableColumn<Citation, Integer> citationIDColumn = new TableColumn<>("Citation ID");
        citationIDColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        TableColumn<Citation, String> typeOfOffenseColumn = new TableColumn<>("Offense");
        typeOfOffenseColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfOffense"));
        TableColumn<Citation, String> descriptionColumn = new TableColumn<>("Des");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<Citation, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<Citation, Integer> userIDColumn = new TableColumn<>("Officer ID");
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));

        tableView.getColumns().addAll(citationIDColumn,typeOfOffenseColumn,descriptionColumn,dateColumn,userIDColumn);
    }


    private static void updateCitationTable() {
        tableView.getItems().clear();
        if(cList.isEmpty()){
            cList = citationList.getListOfCitations();
        }
        for (Citation citation : cList) {
            tableView.getItems().add(citation);
        }
    }
    public static void setUpMenu(Stage stage) {
        fileMenu.getItems().add(openFile);
        fileMenu.getItems().add(saveFile);
        fileMenu.getItems().add(about);
        fileMenu.getItems().add(Quit);
        toolMenu.getItems().add(CD);
        toolMenu.getItems().add(SU);
        menuBar.getMenus().addAll(fileMenu, toolMenu);

        fileChooser.setInitialDirectory(new File("."));

        openFile.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                citationList.readCitationFile(String.valueOf(file));
            }
        });
        saveFile.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Citation File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt")); // Optional: Add file extension filters
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                saveCitationsToFile(file);
            }
        });

        about.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setContentText("Version 1.0\nDeveloped by Bochen Wang");

            alert.showAndWait();
        });
        Quit.setOnAction(e -> {
            Platform.exit();
        });
        CD.setOnAction(event -> openCitationDetailsWindow());
        SU.setOnAction(event ->{
            Stage detailsStage = new Stage();
            Label l = new Label(userList.toString());
            VBox vbox = new VBox();
            vbox.getChildren().addAll(l);
            Scene scene = new Scene(vbox, 300, 250);

            // Set the scene to the stage and configure the stage
            detailsStage.setScene(scene);
            detailsStage.show();
        });
    }
    private static void saveCitationsToFile(File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            for (Citation citation : cList) { // Assuming citationList holds your Citation objects
                writer.println(citation.toString()); // Write each citation as a line in the file
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(); // Handle the error appropriately
        }
    }
    public static HashMap<String, String> readConfigFile() {
        HashMap<String, String> configData = new HashMap<>();
        try {
            Scanner fileScanner = new Scanner(new File("configuration.csv"));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    configData.put(parts[0].trim(), parts[1].trim());
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found!");
        }
        return configData;
    }
    private static void openCitationDetailsWindow() {
        // Create the new Stage for the citation details window
        Stage detailsStage = new Stage();
        detailsStage.setTitle("Citation Details");

        // Create the layout, for example with a GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(12);
        gridPane.setVgap(12);
        gridPane.setPadding(new Insets(12, 12, 12, 12));

        // Labels
        Label idLabel = new Label("ID Number");
        Label offenseLabel = new Label("Offense");
        Label descriptionLabel = new Label("Description");
        Label dateLabel = new Label("Date");
        Label firstLabel = new Label("First");
        Label lastLabel = new Label("Last");
        Label addressLabel = new Label("Address");
        Label phoneLabel = new Label("Phone #");
        Label officerIdLabel = new Label("Officer ID");
        Label statusLabel = new Label("Status");

        // Text Fields
        TextField idField = new TextField();
        TextField offenseField = new TextField();
        TextField descriptionField = new TextField();
        TextField dateField = new TextField();
        TextField firstField = new TextField();
        TextField lastField = new TextField();
        TextField addressField = new TextField();
        TextField phoneField = new TextField();
        TextField officerIdField = new TextField();
        TextField statusField = new TextField();
        statusField.setEditable(false); // Status field should not be editable

        // Buttons
        Button idSearchButton = new Button("Search");
        Button offenseSearchButton = new Button("Search");
        Button lastSearchButton = new Button("Search");
        Button oidSearchButton = new Button("Search");
        // ... Additional buttons for other fields

        Button createButton = new Button("Create Citation");
        Button deleteButton = new Button("Delete Citation");
        Button nextButton = new Button("Show Next Citation");
        Button clearButton = new Button("Clear all");


        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(idSearchButton, 2, 0);
        gridPane.add(offenseLabel, 0, 1);
        gridPane.add(offenseField, 1, 1);
        gridPane.add(offenseSearchButton, 2, 1);
        gridPane.add(descriptionLabel, 0, 2);
        gridPane.add(descriptionField, 1, 2);
        gridPane.add(dateLabel, 0, 3);
        gridPane.add(dateField, 1, 3);
        gridPane.add(firstLabel, 0, 4);
        gridPane.add(firstField, 1, 4);
        gridPane.add(lastLabel, 0, 5);
        gridPane.add(lastField, 1, 5);
        gridPane.add(lastSearchButton, 2, 5);
        gridPane.add(addressLabel, 0, 6);
        gridPane.add(addressField, 1, 6);
        gridPane.add(phoneLabel, 0, 7);
        gridPane.add(phoneField, 1, 7);
        gridPane.add(officerIdLabel, 0, 8);
        gridPane.add(officerIdField, 1, 8);
        gridPane.add(oidSearchButton, 2, 8);
// Add elements to the grid
        gridPane.add(createButton, 0, 9);
        gridPane.add(deleteButton, 1, 9);
        gridPane.add(nextButton, 2, 9);
        gridPane.add(statusLabel, 0, 10);
        gridPane.add(statusField, 1, 10);
        gridPane.add(clearButton, 2, 10);

        // Button Event Handlers
        // You will need to add the logic for what should happen when buttons are clicked
        idSearchButton.setOnAction(event -> {
            // Logic to search by ID
            int id = Integer.parseInt(idField.getText().trim());
            Citation c = citationList.search(id);
            if(c != null){
                idField.setText(String.valueOf(c.getNumber()));
                offenseField.setText(c.getTypeOfOffense());
                descriptionField.setText(c.getDescription());
                dateField.setText(c.getDate());
                firstField.setText(c.getPerson().getFirstName());
                lastField.setText(c.getPerson().getLastName());
                addressField.setText(c.getPerson().getAddress());
                phoneField.setText(c.getPerson().getPhoneNumber());
                officerIdField.setText(String.valueOf(c.getUserID()));
                statusField.setText("OK");

            }else{
                statusField.setText("Not found");
            }
        });

        offenseSearchButton.setOnAction(event -> {
            String of = offenseField.getText().trim();
            a = citationList.searchType(of);
            if(a != null){
                Citation c = a.get(0);
                idField.setText(String.valueOf(c.getNumber()));
                offenseField.setText(c.getTypeOfOffense());
                descriptionField.setText(c.getDescription());
                dateField.setText(c.getDate());
                firstField.setText(c.getPerson().getFirstName());
                lastField.setText(c.getPerson().getLastName());
                addressField.setText(c.getPerson().getAddress());
                phoneField.setText(c.getPerson().getPhoneNumber());
                officerIdField.setText(String.valueOf(c.getUserID()));
                statusField.setText("1");

            }else{
                statusField.setText("Not found");
            }

        });
        lastSearchButton.setOnAction(event -> {
            String of = lastField.getText().trim();
            a = citationList.search(of);
            if(a != null){
                Citation c = a.get(0);
                idField.setText(String.valueOf(c.getNumber()));
                offenseField.setText(c.getTypeOfOffense());
                descriptionField.setText(c.getDescription());
                dateField.setText(c.getDate());
                firstField.setText(c.getPerson().getFirstName());
                lastField.setText(c.getPerson().getLastName());
                addressField.setText(c.getPerson().getAddress());
                phoneField.setText(c.getPerson().getPhoneNumber());
                officerIdField.setText(String.valueOf(c.getUserID()));
                statusField.setText("1");

            }else{
                statusField.setText("Not found");
            }
        });
        oidSearchButton.setOnAction(event -> {
            int of = Integer.parseInt(officerIdField.getText().trim());
            a = citationList.searchUser(of);
            if(a != null){
                Citation c = a.get(0);
                idField.setText(String.valueOf(c.getNumber()));
                offenseField.setText(c.getTypeOfOffense());
                descriptionField.setText(c.getDescription());
                dateField.setText(c.getDate());
                firstField.setText(c.getPerson().getFirstName());
                lastField.setText(c.getPerson().getLastName());
                addressField.setText(c.getPerson().getAddress());
                phoneField.setText(c.getPerson().getPhoneNumber());
                officerIdField.setText(String.valueOf(c.getUserID()));
                statusField.setText("1");

            }else{
                statusField.setText("Not found");
            }

        });

        createButton.setOnAction(event -> {
            if(idField.getText().isEmpty() ||
                    offenseField.getText().isEmpty() ||
                    descriptionField.getText().isEmpty() ||
                    dateField.getText().isEmpty() ||
                    firstField.getText().isEmpty() ||
                    lastField.getText().isEmpty() ||
                    addressField.getText().isEmpty() ||
                    phoneField.getText().isEmpty() ||
                    officerIdField.getText().isEmpty()){
                statusField.setText("Not ok");
            }else{
            Citation c = new Citation();
            Person p = new Person();
            c.setNumber(Integer.parseInt(idField.getText().trim()));
            c.setDescription(descriptionField.getText().trim());
            c.setTypeOfOffense(offenseField.getText().trim());
            c.setDate(dateField.getText().trim());
            c.setUserID(Integer.parseInt(officerIdField.getText().trim()));
            p.setAddress(addressField.getText().trim());
            p.setFirstName(firstField.getText().trim());
            p.setLastName(lastField.getText().trim());
            p.setPhoneNumber(addressField.getText().trim());
            c.setPerson(p);
            citationList.add(c);
            statusField.setText("Done");}
        });
        nextButton.setOnAction(event -> {
            if(n < a.size()){
                Citation c = a.get(n);
                idField.setText(String.valueOf(c.getNumber()));
                offenseField.setText(c.getTypeOfOffense());
                descriptionField.setText(c.getDescription());
                dateField.setText(c.getDate());
                firstField.setText(c.getPerson().getFirstName());
                lastField.setText(c.getPerson().getLastName());
                addressField.setText(c.getPerson().getAddress());
                phoneField.setText(c.getPerson().getPhoneNumber());
                officerIdField.setText(String.valueOf(c.getUserID()));
                statusField.setText(String.valueOf(n));
                n = (n == a.size()) ? 1 : n + 1;
            }else{
                idField.clear();
                offenseField.clear();
                descriptionField.clear();
                dateField.clear();
                firstField.clear();
                lastField.clear();
                addressField.clear();
                phoneField.clear();
                officerIdField.clear();
                statusField.setText("No more");
            }
        });
        deleteButton.setOnAction(event -> {
            citationList.delete(Integer.parseInt(idField.getText().trim()));
            statusField.setText("Done");
            idField.clear();
            offenseField.clear();
            descriptionField.clear();
            dateField.clear();
            firstField.clear();
            lastField.clear();
            addressField.clear();
            phoneField.clear();
            officerIdField.clear();
        });
        // ... Event handlers for other buttons

        // Clear Button Logic
        clearButton.setOnAction(event -> {
            idField.clear();
            offenseField.clear();
            descriptionField.clear();
            dateField.clear();
            firstField.clear();
            lastField.clear();
            addressField.clear();
            phoneField.clear();
            officerIdField.clear();
            statusField.clear();
        });

        // Create Scene and set it to the stage
        Scene detailsScene = new Scene(gridPane, 400, 600);
        detailsStage.setScene(detailsScene);

        // Show the new window
        detailsStage.show();
    }

    // Main method
    public static void main(String[] args) {

        configData = readConfigFile();

        String inputFile = configData.getOrDefault("input file", "citations.csv");
        citationList.readCitationFile(inputFile);

        String userFile = configData.getOrDefault("user file", "users.csv");
        userList.readUserFile(userFile);


        launch(args);
    }
}
