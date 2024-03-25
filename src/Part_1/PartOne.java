package Part_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Exceptions.BadDurationException;
import Exceptions.BadGenreException;
import Exceptions.BadRatingException;
import Exceptions.BadScoreException;
import Exceptions.BadTitleException;
import Exceptions.BadYearException;
import Exceptions.ExcessFieldsException;
import Exceptions.MissingFieldsException;
import Exceptions.MissingQuotesException;
import Exceptions.BadNameException;

public class PartOne {

    /*
     * An array to store file names for further processing
     */

    public static String[] fileNames;

    public static void doPart1() {
        // String manifest = "part1_manifest.txt";
        // java.io.File maninfestFile = new java.io.File("src/Driver/"+manifest);

        // // todo : for loop, that reads the manifest file and reads the files in the
        // manifest file

        BufferedReader reader = null;
        String line = "";

        File directoryName = new File("src/DataBase/");

        try {
            // File folder = new File("src/Driver/part1_manifest.txt");
            int row = 0;
            for (int i = 0; i < directoryName.list().length; i++) {
                String count = String.valueOf(i);
                File folder = new File("src/DataBase/Movies199" + count + ".csv");

                reader = new BufferedReader(new FileReader(folder));

                while ((line = reader.readLine()) != null) {
                    // String[] values = line.split(",");

                    if (line.split("") != null) {
                        row++;
                        System.out.println(line);
                    }
                }
            }
            System.out.println("THE ROW IS : " + row);
            String[] badMovie = new String[row];
            String[] movieArray = new String[row];

            int goodMovieIndex = 0;
            int badMovieIndex = 0;
            for (int i = 0; i < directoryName.list().length; i++) {
                String count = String.valueOf(i);
                System.out.println(directoryName.list().length);
                File folder = new File("src/DataBase/Movies199" + count + ".csv");

                reader = new BufferedReader(new FileReader(folder));

                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");

                    if (line.split("") != null) {

                        if (values.length > 10) {
                            System.out.println("Hello ");
                        }
                        if (values.length == 10) {
                            // add code to check if movie has errors [use validateAndWriteRecord() method]
                            // and store it into movieArray[] if good, if bad it will go to badMovie[]

                            for (int j = 0; j < 10; j++) {
                                movieArray[goodMovieIndex] = line;
                            }
                            goodMovieIndex++;
                            System.out.println(line);
                        } else {
                            badMovie[badMovieIndex] = line;
                            badMovieIndex++;

                        }
                        System.out.println("CHECKING: " + values[3]); // testing
                    }
                }
            }
            System.out.println("MOVIE: " + movieArray[598]); // testing

            reader.close();

            // sorting the bad movies into it's file
            // for (String fileName : fileNames) {

            // File inputFile = new File(fileName);

            // if (inputFile.canRead()) {

            // BufferedReader fileReader = new BufferedReader(new FileReader(inputFile));

            // while ((line = fileReader.readLine()) != null) {
            // System.out.println("Program is validating and writing to record"); // testing
            // validateAndWriteRecord(line, fileName);
            // }
            // fileReader.close();
            // }
            // }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace(); // testing
        }

    }

    public static void validateAndWriteRecord(String line, String fileName) throws IOException, BadGenreException,
            MissingFieldsException, ExcessFieldsException, MissingQuotesException, BadYearException, BadTitleException,
            BadScoreException, BadDurationException, BadRatingException, BadNameException {

        String genre = "";
        String[] movieRecords = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        try {

            if (movieRecords.length == 10) {

                genre = findGenre(movieRecords[3]);

                if (!isValidGenre(genre)) {

                    throw new BadGenreException("Semantic Error: " + genre + " is not a valid genre");

                }

                String writeToFile = OutputFile(genre);
                writeRecordToFile(writeToFile, line);

            } else if (movieRecords.length < 10) {

                throw new MissingFieldsException("Syntax Error: Missing fields in record: " + line);

            } else if (movieRecords.length > 10) {

                throw new ExcessFieldsException("Syntax Error: Excess fields in record: " + line);

            } else if (!movieRecords[1].startsWith("\"") || !movieRecords[1].endsWith("\"")) {

                throw new MissingQuotesException("Syntax Error: Missing quotes in record: " + line);

            } else if (Integer.parseInt(movieRecords[0]) < 1990 || Integer.parseInt(movieRecords[0]) > 1999) {

                throw new BadYearException("Semantic Error: Year is not between 1990 and 1999 in record: " + line);

            } else if (Integer.parseInt(movieRecords[2]) < 0 || Integer.parseInt(movieRecords[2]) > 300) {

                throw new BadDurationException("Semantic Error: Duration is not between 0 and 300 in record: " + line);

            } else if (Double.parseDouble(movieRecords[5]) < 0 || Double.parseDouble(movieRecords[5]) > 10) {

                throw new BadScoreException("Semantic Error: Score is not between 0 and 10 in record: " + line);

            } else if (!isValidRating(movieRecords[4])) {

                throw new BadRatingException("Semantic Error: Rating is not valid in record: " + line);

            } else if (movieRecords[1].split("\"").length > 3) {

                throw new BadTitleException("Semantic Error: Excess fields in record: " + line);

            } else {

                throw new BadNameException("Semantic Error: Name is not valid in record: " + line);

            }

        } catch (BadYearException | BadTitleException | BadScoreException | BadDurationException | BadRatingException
                | BadNameException e) {

            String writeOutputToFile = "bad_movie_records.txt";
            FileWriter writer = new FileWriter(writeOutputToFile, true);
            BufferedWriter writeToBadFile = new BufferedWriter(writer);

            writeToBadFile.write("\n------------------------\n");
            writeToBadFile.write("Semantic Error:" + fileName);
            writeToBadFile.write("\n------------------------\n");
            writeToBadFile.write("\nRecord: " + line);
            writeToBadFile.write("\n------------------------\n");
            writeToBadFile.write("");
            writeToBadFile.close();
        }
    }

    private static boolean isValidRating(String rating) {

        return rating.equals("G") || rating.equals("PG") || rating.equals("PG-13") ||
                rating.equals("R") || rating.equals("NC-17") || rating.equals("Unrated");
    }

    public static String findGenre(String movieRecords) {
        if (movieRecords.startsWith("\"") && movieRecords.endsWith("\"")) {
            return movieRecords.substring(1, movieRecords.length() - 1);
        } else {
            return movieRecords;
        }
    }

    private static boolean isValidGenre(String genre) {
        String[] validGenres = { "musical", "comedy", "animation", "adventure", "drama", "crime",
                "biography", "horror", "action", "documentary", "fantasy", "mystery", "sci-fi",
                "family", "romance", "thriller", "western" };
        for (String validGenre : validGenres) {
            if (validGenre.equals(validGenre)) {
                return true;
            }
        }
        return false;
    }

    public static String OutputFile(String genre) {

        String outputFile;

        switch (genre) {
            case "musical":
                outputFile = "musical.txt";
                break;
            case "comedy":
                outputFile = "comedy.txt";
                break;
            case "animation":
                outputFile = "animation.txt";
                break;
            case "adventure":
                outputFile = "adventure.txt";
                break;
            case "drama":
                outputFile = "drama.txt";
                break;
            case "crime":
                outputFile = "crime.txt";
                break;
            case "biography":
                outputFile = "biography.txt";
                break;
            case "horror":
                outputFile = "horror.txt";
                break;
            case "action":
                outputFile = "action.txt";
                break;
            case "documentary":
                outputFile = "documentary.txt";
                break;
            case "fantasy":
                outputFile = "fantasy.txt";
                break;
            case "mystery":
                outputFile = "mystery.txt";
                break;
            case "sci-fi":
                outputFile = "sci-fi.txt";
                break;
            case "family":
                outputFile = "family.txt";
                break;
            case "romance":
                outputFile = "romance.txt";
                break;
            case "thriller":
                outputFile = "thriller.txt";
                break;
            case "western":
                outputFile = "western.txt";
                break;
            default:
                outputFile = "bad_movie_records.txt";
                break;
        }
        return outputFile;
    }

    public static void writeRecordToFile(String writeToFile, String record) throws IOException {

        try {
            FileWriter writer = new FileWriter(writeToFile, true);
            BufferedWriter writingToFile = new BufferedWriter(writer);
            writingToFile.write(record + "\n");
            writingToFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
