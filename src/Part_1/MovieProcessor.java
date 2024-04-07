// -----------------------------------------------------
// Assignment 2
// Question: Part 1
// Written by: Sassan Ghazi 40226489 and Alexander Hsu 40247307
// -----------------------------------------------------
package Part_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MovieProcessor {

     /**
     * Processes the movie records from a given directory and separates them into valid and invalid records.
     * Valid records are written to "part2_manifest.txt", and invalid records are written to "bad_movie_records.txt".
     *
     * @param part1_manifest The directory containing the movie records.
     * @return The output file name "part2_manifest.txt" if successful, an empty string otherwise.
     */
    public static void doPart1(String part1_manifest) {
        // Your existing code for file reading and writing
        FileWriter writer = null;
        FileWriter badWriter = null;
    
        try {
            writer = new FileWriter("part2_manifest.txt");
            badWriter = new FileWriter("bad_movie_records.txt");
    
            File directoryName = new File(part1_manifest);
            File[] files = directoryName.listFiles();
            if (files == null) {
                System.out.println("No files found in the specified directory.");
                return;
            }
    
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".csv")) {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.trim().isEmpty()) {
                            validateAndWriteRecord(line, writer, badWriter);
                        }
                    }
                    reader.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (badWriter != null) {
                    badWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    /**
     * Validates a movie record and writes it to the appropriate output file.
     *
     * @param line The movie record to validate.
     * @return True if the record is valid, false otherwise.
     */

     private static void validateAndWriteRecord(String line, FileWriter writer, FileWriter badWriter) {
        if (line == null || line.isEmpty()) {
            writeToFile(badWriter, "Empty record: " + line);
            return;
        }
    
        String[] dataFields = line.split(",");
        if (dataFields.length != 10) {
            writeToFile(badWriter, "Incorrect number of data fields: " + line);
            return;
        }
    
        boolean isValid = isValidRecord(dataFields);
    
        if (isValid) {
            String genre = dataFields[3].toLowerCase().trim();
            String genreCSVFile = genre + ".csv";
            writeToFile(writer, line, genreCSVFile);
        } else {
            writeToFile(badWriter, line);
        }
    }

    
    /** 
     * @param dataFields
     * @return boolean
     */
    private static boolean isValidRecord(String[] dataFields) {

        if (dataFields.length != 10) {
            writeError("Syntax Error: Missing or excess field(s)", String.join(",", dataFields));
            return false;
        }

        // Check if any field is missing quotes where required
        for (String field : dataFields) {
            if (field.isEmpty() || (field.charAt(0) == '"' && field.charAt(field.length() - 1) != '"')) {
                writeError("Syntax Error: Missing end quote in a quoted field", String.join(",", dataFields));
                return false;
            }
        }

        try {
            int year = Integer.parseInt(dataFields[0]);
            if (year >= 1990 || year <= 1999) {
                // writeError("Semantic Error:", String.join(",", dataFields));
                return true;
            }

        } catch (NumberFormatException e) {
            writeError("Semantic Error:", String.join(",", dataFields));
            return false;
        }

        try {
            int duration = Integer.parseInt(dataFields[3]);

            if (duration >= 30 || duration <= 300) {
                // writeError("Semantic Error:", String.join(",", dataFields));
                return true;
            }
        } catch (NumberFormatException e) {
            writeError("Semantic Error:", String.join(",", dataFields));
            return false;
        }

        try {
            int score = Integer.parseInt(dataFields[5]);

            if (score >= 0 || score <= 10) {
                // writeError("Semantic Error:", String.join(",", dataFields));
                return true;
            }
        } catch (NumberFormatException e) {
            writeError("Semantic Error:", String.join(",", dataFields));
            return false;
        }

        try {
            // validate the title
            if (dataFields[1] != "" || dataFields[1] != null
                    || !(dataFields[1].startsWith("\"") && dataFields[1].endsWith("\""))) {
                // writeError("Semantic Error:", String.join(",", dataFields));
                return true;
            }
        } catch (NumberFormatException e) {
            writeError("Semantic Error:", String.join(",", dataFields));
            return false;
        }

        try {

            if (dataFields[6] != "" || dataFields[6] != null || dataFields[7] != "" || dataFields[7] != null
                    || dataFields[8] != "" || dataFields[8] != null || dataFields[9] != "" || dataFields[9] != null) {
                // writeError("Semantic Error:", String.join(",", dataFields));
                return true;
            }

        } catch (NumberFormatException e) {
            // to ensure that the error message is written to the file in case of either an
            // exception or a condition is met
            writeError("Semantic Error:", String.join(",", dataFields));
            return false;
        }

        try {
            if (isValidGenre(dataFields[3])) {
                // writeError("Semantic Error:", String.join(",", dataFields));
                return true;
            }
        } catch (NumberFormatException e) {
            writeError("Semantic Error:", String.join(",", dataFields));
            return false;
        }

        try {
            if (isValidRating(dataFields[4])) {
                // writeError("Semantic Error:", String.join(",", dataFields));
                return true;
            }
        } catch (NumberFormatException e) {
            writeError("Semantic Error:", String.join(",", dataFields));
            return false;
        }

        return false;

    }

    
    /** 
     * @param error
     * @param line
     */
    private static void writeError(String error, String line) {

        try {
            FileWriter writer = new FileWriter("bad_movie_records.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(error + " in record: " + line + "\n");
            bufferedWriter.write("Record: " + line + "\n");
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    /** 
     * @param fileName
     * @param line
     */
    private static void writeToFile(FileWriter fileWriter, String line, String fileName) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(line + "\n");
            bufferedWriter.close();
    
            // Append the genre CSV file name to part2_manifest.txt
            FileWriter manifestWriter = new FileWriter("part2_manifest.txt", true);
            BufferedWriter manifestBufferedWriter = new BufferedWriter(manifestWriter);
            manifestBufferedWriter.write(fileName + "\n");
            manifestBufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Creates a file with the given file name.
     *
     * @param fileName The name of the file to create.
     */

    public static void createFile(String fileName) {
        try {
            File file = new File(fileName);

            file.createNewFile();

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

    
    /** 
     * @param genre
     * @return boolean
     */
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

    
    /** 
     * @param rating
     * @return boolean
     */
    private static boolean isValidRating(String rating) {
        if (rating == "" || rating == null) {
            return false;
        } else if (rating.equals("G") || rating.equals("PG") || rating.equals("PG-13") ||
                rating.equals("R") || rating.equals("NC-17") || rating.equals("Unrated")) {
            return true;
        } else {
            return false;
        }

    }

}