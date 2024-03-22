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

    public static void validateAndWriteRecord(String[] movieData) throws IOException, PartOneException, MissingFieldsException, MissingQuotesException, ExcessFieldsException, BadYearException, BadDurationException, BadRatingException, BadScoreException, BadGenreException{
        // Syntax check
        if (movieData.length != 10) {
            throw new MissingFieldsException();
        }

        if (!movieData[1].startsWith("\"") || !movieData[1].endsWith("\"")) {
            throw new MissingQuotesException();
        }

        if (movieData[1].split("\"").length > 3) {
            throw new ExcessFieldsException();
        }

        // Semantic check
        int year = Integer.parseInt(movieData[0]);
        if (year < 1990 || year > 1999) {
            throw new BadYearException();
        }

        int duration = Integer.parseInt(movieData[2]);
        if (duration < 0 || duration > 300) {
            throw new BadDurationException();
        }

        int score = Integer.parseInt(movieData[5]);
        if (score < 0 || score > 10) {
            throw new BadScoreException();
        }

        String rating = movieData[4];
        if (!isValidRating(rating)) {
            throw new BadRatingException();
        }

        String genre = movieData[3];
        if (!isValidGenre(genre)) {
            throw new BadGenreException();
        }
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
}
