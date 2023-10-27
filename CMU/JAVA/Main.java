package com.example.lab13;

import NotePackage.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    private static NoteCollection noteCollection = new NoteCollection();

    // This is the current note counter
    private static int number = 1;

    // Buttons
    private static Button nextButton = new Button("Next"),
            clearButton = new Button("Clear"),
            newNoteButton = new Button("Add New Note");

    // Labels
    private static Label nameLabel = new Label("Name"),
            bodyLabel = new Label("Body"),
            fromLabel = new Label("From"),
            toLabel = new Label("To"),
            statusLabel = new Label("OK");

    // Text fields
    private static TextField nameField = new TextField(),
            bodyField = new TextField(),
            fromField = new TextField(),
            toField = new TextField();

    // HBoxes
    private static HBox nameBox = new HBox(),
            bodyBox = new HBox(),
            fromBox = new HBox(),
            toBox = new HBox(),
            buttonBox = new HBox();

    // VBoxes
    private static VBox noteBox = new VBox(),
            bottomBox = new VBox();

    // Menu item, menu, menu bar, file chooser
    private static MenuItem openFile = new MenuItem("Open");
    private static Menu menu = new Menu("File");
    private static MenuBar menuBar = new MenuBar();
    private static FileChooser fileChooser = new FileChooser();

    // The main layout manager
    private static BorderPane borderPane = new BorderPane();

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Create the note display
        setupNote();

        // Create the buttons and status field
        setupBottom();

        // Uncomment the next line after getting the menu and file 
        // chooser set up:
        //setUpMenu(primaryStage);
        setUpMenu(primaryStage);  // Set up the menu
        borderPane.setTop(menuBar);

        // Add the boxes to the layout manager
        borderPane.setCenter(noteBox);
        borderPane.setBottom(bottomBox);

        // Uncomment the next line after getting the menu working:
        //borderPane.setTop(menuBar);

        primaryStage.setTitle("Lab 15");
        // Adjust the size if needed
        primaryStage.setScene(new Scene(borderPane, 275, 200));
        primaryStage.show();
    }

    // Set up the note box
    public static void setupNote() {
        fromLabel.setMinWidth(80.0);
        toLabel.setMinWidth(80.0);
        nameLabel.setMinWidth(80.0);
        bodyLabel.setMinWidth(80.0);
        fromBox.getChildren().addAll(fromLabel, fromField);
        toBox.getChildren().addAll(toLabel, toField);
        nameBox.getChildren().addAll(nameLabel, nameField);
        bodyBox.getChildren().addAll(bodyLabel, bodyField);
        noteBox.getChildren().addAll(fromBox, toBox, nameBox, bodyBox);
    }

    // Set up the button box and the status field in the bottom box
    public static void setupBottom() {
        buttonBox.getChildren().addAll(nextButton, clearButton, newNoteButton);
        bottomBox.getChildren().addAll(buttonBox, statusLabel);

        // Put the clearButton event handler code here:
        clearButton.setOnAction(event -> {
            clearFields();
            statusLabel.setText("OK");
        });

        // Put the nextButton event handler code here:
        nextButton.setOnAction(event -> {
            if (noteCollection.getCount() > 0) {
                Note nextNote = noteCollection.getNoteByNumber(number);
                if (nextNote != null) {
                    fromField.setText(nextNote.getFrom());
                    toField.setText(nextNote.getTo());
                    nameField.setText(nextNote.getName());
                    bodyField.setText(nextNote.getBody());
                    statusLabel.setText("OK");
                } else {
                    // Handle the case where a note with the current number is not found
                    statusLabel.setText("Note not found");
                }

                // Cycle through the notes
                number = (number == noteCollection.getCount()) ? 1 : number + 1;
            } else {
                statusLabel.setText("No Notes Present");
            }
        });


        // Put the newNoteButton event handler code here:
        newNoteButton.setOnAction(event -> {
            String fromText = fromField.getText().trim();
            String toText = toField.getText().trim();
            String nameText = nameField.getText().trim();
            String bodyText = bodyField.getText().trim();

            // Check if any of the fields are empty
            if (fromText.isEmpty() || toText.isEmpty() || nameText.isEmpty() || bodyText.isEmpty()) {
                clearFields();
                statusLabel.setText("Note not added");
            } else {
                Note newNote = noteCollection.createNote("Memo",fromText, toText, nameText, bodyText);
                noteCollection.add(newNote);
                clearFields();
                statusLabel.setText("OK");
            }
        });

    }

    // Clear the note fields
    public static void clearFields() {
        fromField.setText("");
        toField.setText("");
        nameField.setText("");
        bodyField.setText("");
    }

    // Set up a File->Open menu with a file chooser
    public static void setUpMenu(Stage stage) {
        fileChooser.setInitialDirectory(new File("."));
        menu.getItems().add(openFile);
        menuBar.getMenus().add(menu);

        openFile.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                noteCollection.readNotes(file);
                statusLabel.setText("OK");
            } else {
                statusLabel.setText("File not opened");
            }
        });
    }


    public static void main(String[] args) {
        // Comment out the next line when you get the file chooser working
        //noteCollection.readNotes("notes.txt");
        launch(args);
    }
}
