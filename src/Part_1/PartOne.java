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

    public static String doPart1(String part1_manifest) {
        BufferedReader reader = null;
        FileWriter writer = null;
        String line = "";
        String output = "part2_manifest.txt";

        File directoryName = new File(part1_manifest);

        try {
            /*
             * // File folder = new File("src/Driver/part1_manifest.txt");
             * int row = 0;
             * for (int i = 0; i < directoryName.list().length; i++) {
             * String count = String.valueOf(i);
             * File folder = new File("src/DataBase/Movies199" + count + ".csv");
             * 
             * reader = new BufferedReader(new FileReader(folder));
             * 
             * while ((line = reader.readLine()) != null) {
             * if (line.split("") != null) {
             * row++;
             * System.out.println(line);
             * }
             * }
             * }
             */

            writer = new FileWriter("part2_manifest.txt");
            int row = 0;
            for (int i = 0; i < directoryName.list().length; i++) {
                String count = String.valueOf(i);
                File folder = new File("src/DataBase/Movies199" + count + ".csv");

                reader = new BufferedReader(new FileReader(folder));

                while ((line = reader.readLine()) != null) {
                    if (line.split("") != null) {
                        row++;
                        System.out.println(line);
                        output += line + "\n";
                        writer.write(line + "\n");// writing to the textfile
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
                    String[] categories = line.split(",");

                    if (line.split("") != null) {

                        if (categories.length == 10) {
                            System.out.println("CHECKING: " + categories[9]); // testing
                            if (validateAndWriteRecord(reader, movieArray, categories, row)) {
                                System.out.println("Program is validating and writing to record"); // testing
                                output += line + "\n";
                            }
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

                    }
                }
            }
            System.out.println("MOVIE: " + movieArray[598]); // testing

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
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace(); // testing
        } catch (BadGenreException | MissingFieldsException | ExcessFieldsException | MissingQuotesException
                | BadYearException | BadTitleException | BadScoreException | BadDurationException | BadRatingException
                | BadNameException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace(); // testing
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
                e.printStackTrace(); // testing
            }
        }

        // String is returned, which contains all the movie records that passed the
        // validation process and were written to the text file.
        return output;

    }

    public static boolean validateAndWriteRecord(BufferedReader reader, String[] movieArray, String[] categories,
            int goodMovieIndex)
            throws IOException, BadGenreException, MissingFieldsException,
            ExcessFieldsException, MissingQuotesException,
            BadYearException, BadTitleException, BadScoreException, BadDurationException,
            BadRatingException, BadNameException {

        String line = "";
        String[][] movieRecords = new String[goodMovieIndex][11];
        for (int i = 0; i < goodMovieIndex; i++) {
            movieRecords[i][0] = movieArray[i]; // copying the 1D array of movieArray into a 2D array of movieRecords
                                                // (which will also store the categories)
        }

        int numCategories = 0;
        for (int i = 0; i < goodMovieIndex; i++) {
            for (int j = 1; j < 10 + 1; j++) {
                movieRecords[i][j] = categories[numCategories];
                numCategories++;

                if (numCategories == 10) {
                    numCategories = 0;
                    break;
                }
            }
        }

        System.out.println("HEKLLHOELOHELHEEOHL: " + movieRecords[598][2]);

        try {
            for (int movieFile = 0; movieFile < goodMovieIndex; movieFile++) {
                for (int movieCategory = 1; movieCategory < 10 + 1; movieCategory++) {
                    boolean goodMovieLength = isValidMovieLength(movieRecords[movieFile]);

                    boolean goodMovieYear = isValidYear(movieRecords[movieFile][1]);

                    boolean goodMovieTitle = isValidTitle(movieRecords[movieFile][2]);

                    boolean goodMovieDuration = isValidDuration(movieRecords[movieFile][3]);

                    boolean goodMovieGenre = isValidGenre(movieRecords[movieFile][4]);

                    boolean goodMovieRating = isValidRating(movieRecords[movieFile][5]);

                    boolean goodMovieScore = isValidScore(movieRecords[movieFile][6]);

                    boolean goodMovieDirector = isValidDirector(movieRecords[movieFile][7]);

                    boolean goodMovieActor1 = isValidActor1(movieRecords[movieFile][8]);

                    boolean goodMovieActor2 = isValidActor2(movieRecords[movieFile][9]);

                    boolean goodMovieActor3 = isValidActor3(movieRecords[movieFile][10]);

                    if (goodMovieLength && goodMovieYear && goodMovieGenre && goodMovieDuration && goodMovieScore
                            && goodMovieRating && goodMovieTitle && goodMovieDirector && goodMovieActor1
                            && goodMovieActor2
                            && goodMovieActor3) {
                        String genre = findGenre(movieRecords[movieFile][3]);
                        String writeToFile = OutputFile(genre);
                        writeRecordToFile(writeToFile, line);

                    } else if (!goodMovieLength) {
                        throw new MissingFieldsException(
                                "Syntax Error: Missing fields in record: " + line);
                    } else if (!goodMovieYear) {
                        throw new BadYearException(
                                "Semantic Error: Year is not between 1990 and 1999 in record: " + line);
                    } else if (!goodMovieGenre) {
                        throw new BadGenreException(
                                "Semantic Error: " + movieRecords[movieFile][3] + " is not a valid genre");
                    } else if (!goodMovieDuration) {
                        throw new BadDurationException(
                                "Semantic Error: Duration is not between 0 and 300 in record: " + line);
                    } else if (!goodMovieScore) {
                        throw new BadScoreException("Semantic Error: Score is not between 0 and 10 in record: " + line);
                    } else if (!goodMovieRating) {
                        throw new BadRatingException("Semantic Error: Rating is not valid in record: " + line);
                    } else if (!goodMovieTitle) {
                        throw new BadTitleException("Semantic Error: Excess fields in record: " + line);
                    } else if (!goodMovieActor1) {
                        throw new BadNameException("Semantic Error: Name is not valid in record: " + line);
                    } else if (!goodMovieActor2) {
                        throw new BadNameException("Semantic Error: Name is not valid in record: " + line);
                    } else if (!goodMovieActor3) {
                        throw new BadNameException("Semantic Error: Name is not valid in record: " + line);
                    }

                }
            }
        } catch (BadYearException | BadTitleException | BadScoreException | BadDurationException | BadRatingException
                | BadNameException e) {

            String writeOutputToFile = "bad_movie_records.txt";
            FileWriter writer = new FileWriter(writeOutputToFile, true);
            BufferedWriter writeToBadFile = new BufferedWriter(writer);

            writeToBadFile.write("\n------------------------\n");
            writeToBadFile.write("Semantic Error:" + movieRecords[0][0]);
            writeToBadFile.write("\n------------------------\n");
            writeToBadFile.write("\nRecord: " + movieRecords[0][0]);
            writeToBadFile.write("\n------------------------\n");

            writeToBadFile.close();

            return false;

        }
        return true;
    }

    private static boolean isValidMovieLength(String[] movie) {
        if (movie.length == 10) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isValidYear(String movie) {
        String[] movieYears = { "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999" };
        for (String validYear : movieYears) {
            if (movie.equals(validYear)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private static boolean isValidTitle(String title) {
        if (title.startsWith("\"") && title.endsWith("\"")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isValidDuration(String duration) {
        if (duration == "") {
            return false;
        } else if (Integer.parseInt(duration) >= 0 && Integer.parseInt(duration) <= 300) {
            return true;
        } else {
            return false;
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

    private static boolean isValidRating(String rating) {
        return rating.equals("G") || rating.equals("PG") || rating.equals("PG-13") ||
                rating.equals("R") || rating.equals("NC-17") || rating.equals("Unrated");
    }

    private static boolean isValidScore(String score) {
        if (score.startsWith("\"") || score.endsWith("\"") || score == "") {
            return false;
        } else if (Double.parseDouble(score) >= 0 || Double.parseDouble(score) <= 10) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isValidDirector(String director) {
        if (director != null && !director.isEmpty() && director.matches("^[a-zA-Z]*$")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isValidActor1(String actor1) {

        if (actor1 != null && !actor1.isEmpty() && actor1.matches("^[a-zA-Z]*$")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isValidActor2(String actor2) {

        if (actor2 != null && !actor2.isEmpty() && actor2.matches("^[a-zA-Z]*$")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isValidActor3(String actor3) {

        if (actor3 != null && !actor3.isEmpty() && actor3.matches("^[a-zA-Z]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static String findGenre(String movieRecords) {
        if (movieRecords.startsWith("\"") && movieRecords.endsWith("\"")) {
            return movieRecords.substring(1, movieRecords.length() - 1);
        } else {
            return movieRecords;
        }
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
