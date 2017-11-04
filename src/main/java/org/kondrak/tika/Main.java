package org.kondrak.tika;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;

import java.io.*;

public class Main {

//    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("Please enter the number of the file to parse (or q to exit): ");
                String input = reader.readLine();

                if("q".equalsIgnoreCase(input)) {
                    System.exit(0);
                } else {
                    System.out.println("In catch-all!");
                    Tika tika = new Tika();
                    try(InputStream stream = new FileInputStream(new File(Main.class.getClassLoader().getResource(input + "." + input).getFile()))) {
                        Metadata metadata = new Metadata();
//                        BufferedReader r = (BufferedReader) tika.parse(stream, metadata);
//                        System.out.println(r.readLine());
                        System.out.println(tika.parseToString(stream));
                        System.out.println(tika.parse(stream, metadata));
                        String[] metadataNames = metadata.names();

                        for(String name : metadataNames) {
                            System.out.println(name + " : " + metadata.get(name));
                        }
                    } catch(Exception ex) {
                        System.out.println("Error: " + ex);
                    }
                }
            }
        } catch(Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
}
