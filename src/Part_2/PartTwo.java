package Part_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Movie.Movie;

public class PartTwo {
    
    
    /** 
     * @param part2_manifest
     * @return String
     */
    public static String doPart2(String part2_manifest) {
        String resultMessage = "part3_manifest.txt";

        try {
            BufferedReader manifestReader = new BufferedReader(new FileReader(part2_manifest));
            BufferedWriter manifestWriter = new BufferedWriter(new FileWriter(resultMessage));

            String line;
            while ((line = manifestReader.readLine()) != null) {
                String genreFile = line.trim();
                Movie[] movies = loadMoviesFromFile(genreFile);

                String binaryFileName = genreFile.replace(".txt", ".ser");
                serializeMovies(movies, binaryFileName);

                manifestWriter.write(binaryFileName);
                manifestWriter.newLine();
            }

            manifestReader.close();
            manifestWriter.close();

            resultMessage = "Serialization successful!";
        } catch (IOException e) {
            e.printStackTrace();
            resultMessage = "Error occurred during serialization.";
        }

        return resultMessage;
    }

    
    /** 
     * @param genreFile
     * @return Movie[]
     * @throws IOException
     */
    private static Movie[] loadMoviesFromFile(String genreFile) throws IOException {
        
        BufferedReader reader = new BufferedReader(new FileReader(genreFile));
        int numMovies = countLines(genreFile);
        Movie[] movies = new Movie[numMovies];  

        String line;
        int index = 0;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String title = parts[1].trim();
            int year = Integer.parseInt(parts[0].trim());
            int duration = Integer.parseInt(parts[2].trim());
            String genres = parts[3].trim();
            String rating = parts[4].trim();
            double score = Double.parseDouble(parts[5].trim());
            String director = parts[6].trim();
            String actor1 = parts[7].trim();
            String actor2 = parts[8].trim();
            String actor3 = parts[9].trim();

            movies[index++] = new Movie(year, title, duration, genres, rating, score, director, actor1, actor2, actor3);
        }

        reader.close();
        return movies;
    }

    
    /** 
     * @param movies
     * @param fileName
     */
    private static void serializeMovies(Movie[] movies, String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /** 
     * @param fileName
     * @return int
     * @throws IOException
     */
    private static int countLines(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }
}
