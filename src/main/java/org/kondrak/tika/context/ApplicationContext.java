package org.kondrak.tika.context;

import javafx.stage.Stage;

import javafx.scene.control.TextArea;
import java.io.File;

public class ApplicationContext {

    private File currentFile;
    private final Stage stage;
    private TextArea contentArea;
    private TextArea metadataArea;

    public ApplicationContext(Stage stage) {
        TextArea contentArea = new TextArea();
        contentArea.setPrefHeight(700);
        contentArea.setPrefWidth(700);

        TextArea metadataArea = new TextArea();
        metadataArea.setPrefHeight(700);
        metadataArea.setMaxWidth(400);

        this.stage = stage;
        this.contentArea = contentArea;
        this.metadataArea = metadataArea;
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
}
