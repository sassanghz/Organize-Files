package Driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Part_1.Method;

public class Driver {
    
    public static void do_part1(String part1_manifest) {
        try (BufferedReader manifestReader = new BufferedReader(new FileReader(part1_manifest))) {
            String inputFileName;
            while ((inputFileName = manifestReader.readLine()) != null) {
                System.out.println("Reading input file: " + inputFileName);
                Method.processCSVLine(inputFileName); // Process each line as needed
            }
        } catch (IOException e) {
            System.err.println("Error reading part1_manifest.txt: " + e.getMessage());
        }
    }

    public static String do_part2(String part2_manifest) {
        return part2_manifest;
    }

    public static String do_part3(String part3_manifest) {
        return part3_manifest;
    }

    public static void main(String[] args) {
        String part1_manifest = "src/TextFile/part1_manifest.txt"; // Corrected path to the CSV file

        do_part1(part1_manifest);
        /*  String part2_manifest = do_part1(part1_manifest);
        String part3_manifest = do_part2(part2_manifest);
        do_part3(part3_manifest);
        return;*/
    }
}
