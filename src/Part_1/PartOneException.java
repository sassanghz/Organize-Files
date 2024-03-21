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

    public static void validateAndWriteRecord(String[] record, BufferedWriter[] genreWriters) throws IOException, PartOneException, MissingFieldsException, MissingQuotesException, ExcessFieldsException, BadYearException, BadDurationException, BadRatingException, BadScoreException, BadGenreException{
        // Syntax check
        if (record.length != 10) {
            throw new MissingFieldsException();
        }

        if (!record[1].startsWith("\"") || !record[1].endsWith("\"")) {
            throw new MissingQuotesException();
        }

        if (record[1].split("\"").length > 3) {
            throw new ExcessFieldsException();
        }

        // Semantic check
        int year = Integer.parseInt(record[0]);
        if (year < 1990 || year > 1999) {
            throw new BadYearException();
        }

        int duration = Integer.parseInt(record[2]);
        if (duration < 0 || duration > 300) {
            throw new BadDurationException();
        }

        int score = Integer.parseInt(record[5]);
        if (score < 0 || score > 10) {
            throw new BadScoreException();
        }

        String rating = record[4];
        if (!isValidRating(rating)) {
            throw new BadRatingException();
        }

        String genre = record[3];
        if (!isValidGenre(genre)) {
            throw new BadGenreException();
        }

        // Write valid record to the appropriate genre file
        BufferedWriter writer = genreWriters[getGenreIndex(genre)];
        writer.write(String.join(",", record)); // Write the record as CSV
        writer.write("\n"); // Add newline after each record
    }

    private static int getGenreIndex(String genre) {
        // Implement logic to map genre to index in genreWriters array
        return 0; // Example implementation, change as needed
    }

    private static boolean isValidRating(String rating) {
        return rating.equals("G") || rating.equals("PG") || rating.equals("PG-13") ||
                rating.equals("R") || rating.equals("NC-17") || rating.equals("Unrated");
    }

    private static boolean isValidGenre(String genre) {
        String[] validGenres = {"musical", "comedy", "animation", "adventure", "drama", "crime",
                "biography", "horror", "action", "documentary", "fantasy", "mystery", "sci-fi",
                "family", "romance", "thriller", "western"};
        for (String validGenre : validGenres) {
            if (genre.equals(validGenre)) {
                return true;
            }
        }
        return false;
    }

    public static void processInputFile(String inputFileName) {
        
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFileName))){
            FileWriter badRecordsWriter = null;
            try {
                badRecordsWriter = new FileWriter("bad_movie_records.txt");

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] record = line.split(",");
                        try {
                            BufferedWriter[] genreWriters = new BufferedWriter[17];
                            validateAndWriteRecord(record, genreWriters);
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
}
