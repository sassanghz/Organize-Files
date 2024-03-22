package Part_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Exceptions.MissingFieldsException;
import Exceptions.MissingQuotesException;
import Exceptions.ExcessFieldsException;
import Exceptions.BadYearException;
import Exceptions.BadDurationException;
import Exceptions.BadRatingException;
import Exceptions.BadScoreException;
import Exceptions.BadGenreException;


public class Method {

    public static void partitionMovies(String inputFileName, FileWriter badRecordsWriter, FileWriter manifestWriter){
       
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
       
            while ((line = reader.readLine()) != null) {
                String[] movieData = line.split(",");
       
                if (validateAndWriteRecord(movieData)) {
       
                    String genre = movieData[3].trim(); // Assuming genre is at index 3
                    FileWriter genreWriter = new FileWriter(genre + ".csv");
                    genreWriter.write(line + "\n");
                    genreWriter.close();
       
                } else {
                    badRecordsWriter.write(line + "\n");
                }
            }

            // Write the CSV file name to the manifest file
            String manifestEntry = inputFileName.substring(0, inputFileName.lastIndexOf('.')) + ".csv";
            manifestWriter.write(manifestEntry + "\n");
        } catch (IOException e) {
            System.err.println("Error processing input file: " + e.getMessage());
        } catch(PartOneException | MissingFieldsException | MissingQuotesException | ExcessFieldsException |
            BadYearException | BadDurationException | BadRatingException | BadScoreException |
            BadGenreException exception){
            System.err.println("Error processing input file: " + exception.getMessage());
        }
    }

    public static boolean validateAndWriteRecord(String[] movieData) throws IOException, PartOneException, MissingFieldsException, MissingQuotesException, ExcessFieldsException, BadYearException, BadDurationException, BadRatingException, BadScoreException, BadGenreException{
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

        return true;
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
}
