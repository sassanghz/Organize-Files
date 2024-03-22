package Part_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Exceptions.BadDurationException;
import Exceptions.BadGenreException;
import Exceptions.BadRatingException;
import Exceptions.BadScoreException;
import Exceptions.BadYearException;
import Exceptions.ExcessFieldsException;
import Exceptions.MissingFieldsException;
import Exceptions.MissingQuotesException;

public class PartOneException extends Exception {

    /*
     *  public static void processInputFile(String inputFileName) {
        
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFileName))){
            FileWriter badRecordsWriter = null;
            try {
                badRecordsWriter = new FileWriter("bad_movie_records.txt");

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] record = line.split(",");
                        try {
                            BufferedWriter[] genreWriters = new BufferedWriter[17];
                            validateAndWriteRecord(record);
                        } catch (MissingFieldsException | MissingQuotesException | ExcessFieldsException | BadYearException | BadDurationException | BadRatingException | BadScoreException | BadGenreException | PartOneException e) {
                            badRecordsWriter.write(line + "\n");
                        }
                }
            } finally {
                if (badRecordsWriter != null) {
                    badRecordsWriter.close();
                }
            }
            
        }catch(IOException e){
            System.err.println("Error reading file " + inputFileName + ": " + e.getMessage());
        }
    }
     */

}
