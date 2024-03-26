package Driver;
import Movie.Movie;
import Part_1.PartOne;
import Part_2.PartTwo;
import Part_3.PartThree;

public class Driver {

    public static void main(String[] args) {

        String part1_manifest = "DataBase/";

        String part2_manifest = PartOne.doPart1(part1_manifest);
       
        Movie[][] all_movies = PartThree.do_part3("part3_manifest.txt");
        if (all_movies != null) {
            PartThree.navigateMovieArrays(all_movies);
        } else {
            System.out.println("Error occurred during deserialization.");
        }
        //PartTwo.doPart2();
        //String part1_manifest = "DataBase/";
        /*  String part2_manifest = do_part1(part1_manifest);
        String part3_manifest = do_part2(part2_manifest);
        do_part3(part3_manifest);
        return;*/
    }
}
