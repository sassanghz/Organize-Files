package Driver;

import Movie.Movie;
import Part_1.MovieProcessor;
import Part_1.PartOne;
import Part_2.PartTwo;
import Part_3.PartThree;

public class Driver {

    
    /** 
     * @param args
     */
    public static void main(String[] args) {

        String part1_manifest = "src/DataBase/";

        String part2_manifest = MovieProcessor.doPart1(part1_manifest);

        System.out.println("Part 2 manifest: " + part2_manifest);

        String part3_manifest = PartTwo.doPart2(part2_manifest);

        System.out.println("Part 3 manifest: " + part3_manifest);

        Movie[][] all_movies = PartThree.doPart3(part3_manifest);

        if (all_movies != null) {
            PartThree.navigateMovieArrays(all_movies);
        } else {
            System.out.println("Error occurred during deserialization.");
        }
        
        return;
    }
}
