package org.kondrak.tika.context;

import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.scene.control.TextArea;
import java.io.File;

public class ApplicationContext {

    private File currentFile;
    private final Stage stage;
    private TextArea contentArea;
    private TextArea metadataArea;
    private Label openFileLabel;

    public ApplicationContext(Stage stage) {
        TextArea contentArea = new TextArea();
        contentArea.setPrefHeight(2160);
//        contentArea.setPrefWidth(700);

        TextArea metadataArea = new TextArea();
        metadataArea.setPrefHeight(2160);
        metadataArea.setMaxWidth(400);

        Label openFileLabel = new Label("<File Location Will Appear Here>");

        this.stage = stage;
        this.contentArea = contentArea;
        this.metadataArea = metadataArea;
        this.openFileLabel = openFileLabel;
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }

    public Stage getStage() {
        return stage;
    }

    public TextArea getContentArea() {
        return contentArea;
    }

    public TextArea getMetadataArea() {
        return metadataArea;
    }

    public Label getOpenFileLabel() {
        return openFileLabel;
    }
}
