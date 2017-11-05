package org.kondrak.tika;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kondrak.tika.context.ApplicationContext;
import org.kondrak.tika.events.LoadFileEventHandler;

public class Main extends Application {

//    private Desktop desktop = Desktop.getDesktop();
    private ApplicationContext context;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        context = new ApplicationContext(stage);
        stage.setTitle("Menu Sample");
        Scene scene = new Scene(new VBox(), 1024, 768);
        scene.setFill(Color.OLDLACE);

        context.getContentArea().setPrefHeight(600);
        ((VBox) scene.getRoot()).getChildren().addAll(buildMenu(context), new BorderPane(
                context.getContentArea(),
                new Button("Top"),
                new Button("Right"),
                new Button("Bottom"),
                context.getMetadataArea()
        ));

        stage.setScene(scene);
        stage.show();
    }

    private static MenuBar buildMenu(ApplicationContext context) {
        MenuBar menuBar = new MenuBar();

        Menu openItem = new Menu("Open...");
        openItem.setOnAction(new LoadFileEventHandler(context));

        // --- Menu File
        Menu menuFile = new Menu("File");
        menuFile.getItems().addAll(
                openItem,
                new Menu("Exit"));

        // --- Menu Edit
        Menu menuEdit = new Menu("Edit");

        // --- Menu View
        Menu menuView = new Menu("View");

        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        return menuBar;
    }
}
