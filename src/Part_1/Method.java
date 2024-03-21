package Part_1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Method {
    
    public static void writeRecordToGenreFile(String[] record, FileWriter[] genreWriters) {
        String outputFileName = genreWriters + ".csv";
        try (FileWriter writer = new FileWriter(outputFileName, true)) {
            String line = String.join(",", record) + "\n";
            writer.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processCSVLine(String inputFileName) {
       try(Scanner scanner = new Scanner(inputFileName)) {
           while(scanner.hasNextLine()) {
               String line = scanner.nextLine();
               String[] record = line.split(",");
               System.out.println("Processing record: " + record);
           }
       }
    }
}
