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

     public static void doPart1(){
        
        BufferedReader reader = null;

        try{
            File folder = new File("DataBase/");
            reader = new BufferedReader(new FileReader(folder));

            int sizeLine = Integer.parseInt(reader.readLine());
            fileNames = new String[sizeLine];

            for(int i = 0; i < sizeLine; i++){
                fileNames[i] = reader.readLine();
            }

            reader.close();

            for(String fileName: fileNames){
                
                File inputFile = new File(fileName);

                if(inputFile.canRead()){
                    
                    BufferedReader fileReader = new BufferedReader(new FileReader(inputFile));
                    String line;

                    while((line = fileReader.readLine()) != null){
                        validateAndWriteRecord(line, fileName);
                    }
                    fileReader.close();
                }
            }
        }catch(Exception e){
            System.out.println("Error reading file: " + e.getMessage());
        }

     }

     public static void validateAndWriteRecord(String line, String fileName) throws IOException, BadGenreException, MissingFieldsException, ExcessFieldsException, MissingQuotesException, BadYearException, BadTitleException, BadScoreException, BadDurationException, BadRatingException, BadNameException{
        
        String genre = "";
        String[] movieRecords = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        try{
            if(movieRecords.length == 10){
                genre = findGenre(movieRecords[3]);
                if(!isValidGenre(genre)){
                    throw new BadGenreException("Semantic Error: " + genre + " is not a valid genre");
                }
                String writeToFile = OutputFile(genre);
                writeRecordToFile(writeToFile, line);
            }else if(movieRecords.length < 10){
                throw new MissingFieldsException("Syntax Error: Missing fields in record: " + line);
            }else if(movieRecords.length > 10){
                throw new ExcessFieldsException("Syntax Error: Excess fields in record: " + line);
            }else if(!movieRecords[1].startsWith("\"") || !movieRecords[1].endsWith("\"")){
                throw new MissingQuotesException("Syntax Error: Missing quotes in record: " + line);
            }

        }catch(BadYearException | BadTitleException | BadScoreException | BadDurationException | BadRatingException | BadNameException e){
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
    
    public static String findGenre(String movieRecords){
        if(movieRecords.startsWith("\"") && movieRecords.endsWith("\"")){
            return movieRecords.substring(1, movieRecords.length() - 1);
        }else{
            return movieRecords;
        }
    }

    private static boolean isValidGenre(String genre) {
        String[] validGenres = {"musical", "comedy", "animation", "adventure", "drama", "crime",
                "biography", "horror", "action", "documentary", "fantasy", "mystery", "sci-fi",
                "family", "romance", "thriller", "western"};
        for (String validGenre : validGenres) {
            if (validGenre.equals(validGenre)) {
                return true;
            }
        }
        return false;
    }

    public static String OutputFile(String genre){
        
        String outputFile;

        switch(genre){
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

    public static void writeRecordToFile(String writeToFile, String record) throws IOException{
        
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


