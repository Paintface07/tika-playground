package org.kondrak.tika.events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.kondrak.tika.context.ApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;

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
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);

        TesseractOCRConfig config = new TesseractOCRConfig();
        PDFParserConfig pdfConfig = new PDFParserConfig();
        pdfConfig.setExtractInlineImages(true);

        ParseContext parseContext = new ParseContext();
        parseContext.set(TesseractOCRConfig.class, config);
        parseContext.set(PDFParserConfig.class, pdfConfig);
        //need to add this to make sure recursive parsing happens!
        parseContext.set(Parser.class, parser);

        File file = chooser.showOpenDialog(context.getStage());
        try(InputStream stream = new FileInputStream(file)) {
            Metadata metadata = new Metadata();

            Set<MediaType> types = parser.getSupportedTypes(parseContext);

            Iterator<MediaType> iter = types.iterator();
            while (iter.hasNext()) {
                MediaType type = iter.next();
                if(type.toString().contains("jpg") || type.toString().contains("jpeg") ||
                        type.toString().contains("gif") || type.toString().contains("png"))
                System.out.println(type + " " + type.getBaseType() + " " + type.getSubtype());
            }

            parser.parse(stream, handler, metadata, parseContext);

            context.getContentArea().setText(handler.toString());
            String[] metadataNames = metadata.names();
            String metadataString = "";
            for(String name : metadataNames) {
                String value = metadata.get(name);
                metadataString += name + ": " + value + "\n";
            }

            context.getMetadataArea().setText(metadataString);
            context.getOpenFileLabel().setText(file.getCanonicalPath());
        } catch(Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
}
