// -----------------------------------------------------
// Assignment 2
// Question: Driver
// Written by: Sassan Ghazi 40226489 and Alexander Hsu 40247307
// -----------------------------------------------------
package Driver;

import Movie.Movie;
import Part_1.MovieProcessor;
import Part_2.PartTwo;
import Part_3.PartThree;

/**
 * The main class that drives the execution of the program.
 */
public class Driver {

    /**
     * The main method that starts the program execution.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {

        // The path to the manifest file for Part 1.
        String part1_manifest = "src/DataBase/";

        // Process Part 1 and retrieve the manifest for Part 2.
        String part2_manifest = MovieProcessor.doPart1(part1_manifest);

        // Print Part 2 manifest.
        System.out.println("Part 2 manifest: " + part2_manifest);

        // Process Part 2 and retrieve the manifest for Part 3.
        String part3_manifest = PartTwo.doPart2(part2_manifest);

        // Print Part 3 manifest.
        System.out.println("Part 3 manifest: " + part3_manifest);

        // Perform Part 3 and obtain the array of movies.
        Movie[][] all_movies = PartThree.doPart3(part3_manifest);

        // Check if movies were successfully obtained.
        if (all_movies != null) {
            // Navigate through the movie arrays obtained in Part 3.
            PartThree.navigateMovieArrays(all_movies);
        } else {
            // Print an error message if an error occurred during deserialization.
            System.out.println("Error occurred during deserialization.");
        }
    }
}