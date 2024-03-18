package Driver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

import Exceptions.BadDurationException;
import Exceptions.BadGenreException;
import Exceptions.BadRatingException;
import Exceptions.BadScoreException;
import Exceptions.BadYearException;
import Exceptions.ExcessFieldsException;
import Exceptions.MissingFieldsException;
import Exceptions.MissingQuotesException;
import Part_1.Method;
import Part_1.PartOneException;

public class Driver {
    
    
    public static String do_part1(String part1_manifest)  throws PartOneException, MissingFieldsException, MissingQuotesException, ExcessFieldsException, BadYearException, BadDurationException, BadRatingException, BadScoreException, BadGenreException{
        
        BufferedWriter badRecordsWriter = null; // WRITING THE BAD RECORDS TO A FILE
        BufferedWriter[] genreWriters = new BufferedWriter[17]; // WRITING THE GENRE RECORDS TO A FILE
        BufferedWriter part2_manifestWriter = null; // WRITING THE PART 2 MANIFEST TO A FILE
    
        try {
            badRecordsWriter = new BufferedWriter(new FileWriter("bad_movie_records.txt"));
    
            try (BufferedReader reader = new BufferedReader(new FileReader(part1_manifest))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    try (BufferedReader fileReader = new BufferedReader(new FileReader(line))) {
                        String lines;
                        while ((lines = fileReader.readLine()) != null) {
                            String[] record = lines.split(",");
                            if (record.length >= 6) {
                                try{
                                    /*
                                     * String genre = record[3].trim();
                                     * Method.writeRecordToGenreFile(record, genre);
                                     */
                                    PartOneException.validateAndWriteRecord(record, genreWriters);
                                } catch (MissingFieldsException | MissingQuotesException | ExcessFieldsException | BadYearException | BadDurationException | BadRatingException | BadScoreException | BadGenreException e) {
                                    // Write the bad record to the bad_records.txt file
                                    badRecordsWriter.write(lines + "\n"); // Write the entire line as invalid
                                    continue;
                                }
                                
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading file " + line + ": " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading part1_manifest.txt: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error initializing FileWriter: " + e.getMessage());
        } finally {
            // Close all FileWriter instances in the finally block
            try {
                if (badRecordsWriter != null) {
                    badRecordsWriter.close();
                }
                for (BufferedWriter writer : genreWriters) {
                    if (writer != null) {
                        writer.close();
                    }
                }
                if (part2_manifestWriter != null) {
                    part2_manifestWriter.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing FileWriter: " + e.getMessage());
            }
        }

        return "some string";
    }
    

    public static String do_part2(String part2_manifest) {
        
        return part2_manifest;
    }

    public static String do_part3(String part3_manifest) {
       
        return part3_manifest;
    }

    public static void main(String[] args) throws PartOneException, MissingFieldsException, MissingQuotesException, ExcessFieldsException, BadYearException, BadDurationException, BadRatingException, BadScoreException, BadGenreException {
        String part1_manifest = "src/Part_1/part1_manifest.txt"; // Corrected path to part1_manifest.txt
        String part2_manifest = do_part1(part1_manifest);
        String part3_manifest = do_part2(part2_manifest);
        do_part3(part3_manifest);
        return;
        
    }
}
