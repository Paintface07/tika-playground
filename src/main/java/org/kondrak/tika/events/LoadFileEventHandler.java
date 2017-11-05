package org.kondrak.tika.events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.kondrak.tika.context.ApplicationContext;

import java.io.FileInputStream;
import java.io.InputStream;

public class LoadFileEventHandler implements EventHandler<ActionEvent> {

    private final ApplicationContext context;

    public LoadFileEventHandler(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void handle(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open");
        Tika tika = new Tika();
        try(InputStream stream = new FileInputStream(chooser.showOpenDialog(context.getStage()))) {
            Metadata metadata = new Metadata();
            context.getContentArea().setText(tika.parseToString(stream, metadata));
            String[] metadataNames = metadata.names();
            String metadataString = "";
            for(String name : metadataNames) {
                String value = metadata.get(name);
                metadataString += name + ": " + value + "\n";
            }

            context.getMetadataArea().setText(metadataString);
        } catch(Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
}
