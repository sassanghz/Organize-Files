package Part_1;

import java.io.FileWriter;
import java.io.IOException;

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
}
