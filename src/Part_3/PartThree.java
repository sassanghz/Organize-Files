package Part_3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import Movie.Movie;

public class PartThree {
    
    private static int currentGenreIndex = -1;

    
    /** 
     * @param part3_manifest
     * @return Movie[][]
     */
    public static Movie[][] doPart3(String part3_manifest) {
        
        try{
            // Read the file names from part3_manifest.txt
            String[] fileNames = readManifest(part3_manifest);

            // Create a 2D array to store the deserialized Movie objects
            Movie[][] all_movies = new Movie[fileNames.length][];

            // Deserialize each binary file and store Movie objects in the array
            for (int i = 0; i < fileNames.length; i++) {
                String fileName = fileNames[i];
                FileInputStream fileIn = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Movie[] movieArray = (Movie[]) in.readObject();
                all_movies[i] = movieArray;
                in.close();
                fileIn.close();
            }

            return all_movies;
        }catch (Exception e) {
            System.out.println("Error during deserialization: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    
    /** 
     * @param manifestFileName
     * @return String[]
     * @throws IOException
     */
    private static String[] readManifest(String manifestFileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(manifestFileName));
        String line;
        int count = 0;
        while ((line = reader.readLine()) != null) {
            count++;
        }
        reader.close();

        String[] fileNames = new String[count];
        BufferedReader newReader = new BufferedReader(new FileReader(manifestFileName));
        int index = 0;
        while ((line = newReader.readLine()) != null) {
            fileNames[index] = line.trim();
            index++;
        }
        newReader.close();

        return fileNames;
    }

    
    /** 
     * @param all_movies
     */
    public static void navigateMovieArrays(Movie[][] all_movies) {
        int[] currentIndices = new int[all_movies.length]; // Array to store current indices for each genre
        int currentGenreIndex = -1; // Index of the currently selected genre

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----------------------------");
            System.out.println("Main Menu");
            System.out.println("----------------------------");
            System.out.println("s Select a movie array to navigate");
            System.out.println("n Navigate " + (currentGenreIndex != -1 ? all_movies[currentGenreIndex].length : 0) + " records");
            System.out.println("x Exit");
            System.out.println("----------------------------");
            System.out.print("Enter Your Choice: ");
            String choice = scanner.nextLine();

            switch (choice.toLowerCase()) {
                case "s":
                    
                    displayGenreMenu(all_movies);
                    
                    System.out.print("Enter Your Choice: ");
                    int genreChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                        
                    if (genreChoice >= 1 && genreChoice <= all_movies.length) {
                        /*
                         * If the user's input is valid, this line sets currentGenreIndex to the index of the selected genre in the all_movies array.
                         * Since array indices start from 0 and genreChoice starts from 1, we subtract 1 
                         */
                        currentGenreIndex = genreChoice - 1;
                        /*The current index for the previously selected genre needs to be reset to start
                        * from the beginning when navigating through the movies of the newly selected genre.
                         */
                        currentIndices[currentGenreIndex] = 0; // Reset current index for selected genre
            
                        System.out.println("Navigating " + getGenreName(genreChoice - 1) + " movies (" +
                        all_movies[currentGenreIndex].length + " records)");
            
                    } else {
            
                        System.out.println("Invalid genre choice.");
            
                    }
                    break;
            
                case "n":
            
                    if (currentGenreIndex != -1) {
            
                        navigateMovies(all_movies[currentGenreIndex], currentIndices, scanner);
            
                    } else {
            
                        System.out.println("Please select a movie array first.");
            
                    }
                    break;
            
                case "x":
            
                    System.out.println("Exiting navigation.");
                    return;
                
                default:
            
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    
    /** 
     * @param all_movies
     */
    private static void displayGenreMenu(Movie[][] all_movies) {
        System.out.println("----------------------------");
        System.out.println("Genre Sub-Menu");
        System.out.println("----------------------------");
        for (int i = 0; i < all_movies.length; i++) {
            System.out.println((i + 1) + " " + getGenreName(i) + " (" + (all_movies[i] != null ? all_movies[i].length : 0) + " movies)");
        }
        System.out.println((all_movies.length + 1) + " Exit");
        System.out.println("----------------------------");
    }

    
    /** 
     * @param i
     * @return String
     */
    private static String getGenreName(int i) {
        switch (i) {
            case 0: return "musical";
            case 1: return "comedy";
            case 2: return "animation";
            case 3: return "adventure";
            case 4: return "drama";
            case 5: return "crime";
            case 6: return "biography";
            case 7: return "horror";
            case 8: return "action";
            case 9: return "documentary";
            case 10: return "fantasy";
            case 11: return "mystery";
            case 12: return "sci-fi";
            case 13: return "family";
            case 14: return "western";
            case 15: return "romance";
            case 16: return "thriller";
            case 17: return "Exit";
            default: return "Invalid Genre";
        }
    }

    
    /** 
     * @param movies
     * @param currentIndices
     * @param scanner
     */
    private static void navigateMovies(Movie[] movies, int[] currentIndices, Scanner scanner) {
        int currentIndex = currentIndices[currentGenreIndex];
        int totalRecords = movies.length;
    
        while (true) {
            displayMovie(movies[currentIndex]);
    
            System.out.println("----------------------------");
            System.out.println("Navigating " + getGenreName(currentGenreIndex) +
                    " movies (" + totalRecords + " records)");
            System.out.println("Enter 'prev' to view previous records, 'next' to view next records, or 'exit' to quit:");
            System.out.print("Enter Your Choice: ");
            String input = scanner.nextLine();
    
            if (input.equalsIgnoreCase("prev")) {
                currentIndex = Math.max(0, currentIndex - 1);
            } else if (input.equalsIgnoreCase("next")) {
                currentIndex = Math.min(totalRecords - 1, currentIndex + 1);
            } else if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting navigation.");
                break;
            } else {
                try {
                    int move = Integer.parseInt(input); // Convert input to integer
                    if (move != 0) {
                        int newPosition = currentIndex + move;
                        if (newPosition >= 0 && newPosition < totalRecords) {
                            currentIndex = newPosition;
                        } else {
                            if (move < 0) {
                                System.out.println("BOF has been reached.");
                                currentIndex = Math.max(0, move - 1);
                            } else {
                                System.out.println("EOF has been reached.");
                                currentIndex = Math.min(totalRecords - 1, move - 1);
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter 'prev', 'next', or a valid number.");
                }
            }
    
            currentIndices[currentGenreIndex] = currentIndex;
        }
    }
    

    
    /** 
     * @param movie
     */
    private static void displayMovie(Movie movie) {
        System.out.println("----------------------------");
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Year: " + movie.getYear());
        System.out.println("Genre: " + movie.getGenre());
        System.out.println("Director: " + movie.getDirector());
        System.out.println("Rating: " + movie.getRating());
        System.out.println("Duration: " + movie.getDuration() + " minutes");
        System.out.println("Actors: " + movie.getActor1() + ", " + movie.getActor2() + ", " + movie.getActor3());
        System.out.println("----------------------------");
    }
}
