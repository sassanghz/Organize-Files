package Part_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MovieProcessor {
    
    public static String doPart1(String part1_manifest){

        BufferedReader reader = null;
        FileWriter writer = null;
        FileWriter badWriter = null;
        String output = "part2_manifest.txt";
        String badOutput = "bad_movie_records.txt";

        try {
            writer = new FileWriter(output);
            badWriter = new FileWriter(badOutput);
            
            File directoryName = new File(part1_manifest);
            File[] files = directoryName.listFiles();
            if (files == null) {
                System.out.println("No files found in the specified directory.");
                return "";
            }
            
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".csv")) {
                    reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.trim().isEmpty()) {
                            if (validateAndWriteRecord(line)) {
                                writer.write(line + "\n");
                            } else {
                                badWriter.write(line + "\n");
                            }
                        }
                    }
                }
            }
            
            return output;
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
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

    private static boolean validateAndWriteRecord(String line){

        if(line == null || line.isEmpty()){
            writeToFile("bad_movie_records.txt","Empty record: " + line);
            return false;
        }

        String[] dataFields = line.split(",");

        if(dataFields.length != 10){
            writeToFile("bad_movie_records.txt","Incorrect number of data fields: " + line);
            return false;
        }

        boolean isValid = isValidRecord(dataFields);

        if(isValid){
            writeToFile("part2_manifest.txt", line);
        }else{
            writeToFile("bad_movie_records.txt", line);
        }

        return isValid;
    }

    private static boolean isValidRecord(String[] dataFields){

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

        try{
            int year = Integer.parseInt(dataFields[0]);
            if (year < 1990 || year > 1999) {
                writeError("Semantic Error:", String.join(",", dataFields));
                return false;
            }

        }catch(NumberFormatException e){
            writeError("Semantic Error:", String.join(",", dataFields));
            return false;
        }

        try{
            int duration = Integer.parseInt(dataFields[3]);

            if (duration < 30 || duration > 300) {
                writeError("Semantic Error:", String.join(",", dataFields));
                return false;
            }
        }catch(NumberFormatException e){
            writeError("Semantic Error:", String.join(",", dataFields));
            return false;
        }

        try{
            int score = Integer.parseInt(dataFields[5]);

            if(score <0 || score > 10){
                writeError("Semantic Error:", String.join(",", dataFields));
                return false;
            }
        }catch(NumberFormatException e){
            writeError("Semantic Error:", String.join(",", dataFields));
            return false;
        }

        try{
            // validate the title
            if(dataFields[1] == "" || dataFields[1] == null){
                writeError("Semantic Error:", String.join(",", dataFields));
                return false;
            }
        }catch(NumberFormatException e){
            writeError("Semantic Error:", String.join(",", dataFields));
            return false;
        }

        try{

            if(dataFields[6] == "" || dataFields[6] == null || dataFields[7] == "" || dataFields[7] == null || dataFields[8] == "" || dataFields[8] == null || dataFields[9] == "" || dataFields[9] == null){
                writeError("Semantic Error:", String.join(",", dataFields));
                return false;
            }
        }catch(NumberFormatException e){
            // to ensure that the error message is written to the file in case of either an exception or a condition is met
            writeError("Semantic Error:", String.join(",", dataFields));
            return false;
        }

        try{
            if(!isValidGenre(dataFields[3])){
                writeError("Semantic Error:", String.join(",", dataFields));
                return false;
            }
        }catch(NumberFormatException e){
            writeError("Semantic Error:", String.join(",", dataFields));
            return false;
        }

        try{
            if(!isValidRating(dataFields[4])){
                writeError("Semantic Error:", String.join(",", dataFields));
                return false;
            }
        }catch(NumberFormatException e){
            writeError("Semantic Error:", String.join(",", dataFields));
            return false;
        }

        return true;
        
    }

    private static void writeError(String error, String line){
        
        try{
            FileWriter writer = new FileWriter("bad_movie_records.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            bufferedWriter.write( error + " in record: " + line + "\n");
            bufferedWriter.write("Record: " + line + "\n");
            bufferedWriter.close();

        }catch(IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void writeToFile(String fileName, String line){
        
        try{
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            bufferedWriter.write(line + "\n");
            bufferedWriter.close();

        }catch(IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
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
