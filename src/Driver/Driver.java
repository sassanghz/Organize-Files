package Driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Exceptions.BadDurationException;
import Exceptions.BadGenreException;
import Exceptions.BadRatingException;
import Exceptions.BadScoreException;
import Exceptions.BadYearException;
import Exceptions.ExcessFieldsException;
import Exceptions.MissingFieldsException;
import Exceptions.MissingQuotesException;
import Part_1.PartOneException;

public class Driver {
    
    public static void do_part1(String part1_manifest) {
        try (BufferedReader manifestReader = new BufferedReader(new FileReader(part1_manifest))) {
            String inputFileName;
            while ((inputFileName = manifestReader.readLine()) != null) {
                System.out.println("Reading input file: " + inputFileName);
                PartOneException.processInputFile(inputFileName.trim());
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
        String part1_manifest = "src/Part_1/part1_manifest.txt"; // Corrected path to part1_manifest.txt

        do_part1(part1_manifest);
        /*  String part2_manifest = do_part1(part1_manifest);
        String part3_manifest = do_part2(part2_manifest);
        do_part3(part3_manifest);
        return;*/
    }
}
