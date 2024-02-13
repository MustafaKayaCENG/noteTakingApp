package com.example.notetakingapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NoteTakingApplication extends Application {
    private final ObservableList<String> notes = FXCollections.observableArrayList();
    private int noteCounter = 1;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        ListView<String> listView = new ListView<>(notes);

        TextField noteTextField = new TextField();
        noteTextField.setPromptText("ADD SOME NOTES HERE ");
        noteTextField.setPrefWidth(400);
        noteTextField.setPrefHeight(35);


        Button addButton = new Button("Add");
        addButton.setPrefWidth(400);
        addButton.setWrapText(true);
        addButton.setOnAction(event -> {
            String note = noteTextField.getText();
            if (!note.isEmpty()) {
                notes.add(noteCounter+". "+note+"\n-------------------*-------------------*-------------------*-------------------*-------------------*-------------------*");
                noteCounter++;
                noteTextField.clear();
            }
        });

        Button removeButton = new Button("Remove");
        removeButton.setPrefWidth(400);
        removeButton.setWrapText(true);
        removeButton.setOnAction(actionEvent -> {
            String selectedNote = listView.getSelectionModel().getSelectedItem();
            if(selectedNote != null){
                notes.remove(selectedNote);
                noteCounter--;
            }
        });

        Button editButton = new Button("Edit note");
        editButton.setPrefWidth(400);
        editButton.setWrapText(true);
        editButton.setOnAction(event -> {
            String selectedNote = listView.getSelectionModel().getSelectedItem();
            if (selectedNote != null && !selectedNote.startsWith("-")) {
                int selectedIndex = listView.getSelectionModel().getSelectedIndex();
                String editedNote = noteTextField.getText();
                if (!editedNote.isEmpty()) {
                    notes.set(selectedIndex, (selectedIndex + 1) + ". " + editedNote+"\n-------------------*-------------------*-------------------*-------------------*-------------------*-------------------*");
                    noteTextField.clear();
                }
            }
        });

        HBox buttonBox = new HBox(10, addButton, editButton, removeButton);
        buttonBox.setPadding(new Insets(10));

        VBox topContainer = new VBox(10, noteTextField);
        VBox centerContainer = new VBox(10, listView);

        root.setCenter(centerContainer);
        root.setTop(topContainer);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Note Taking App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
