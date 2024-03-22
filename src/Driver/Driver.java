package Driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import Part_1.Method;
import Part_1.PartOne;

public class Driver {

    public static void do_part1(String part1_manifest) {

<<<<<<< HEAD
       /*
        * File folder = new File(part1_manifest);
=======
        File folder = new File(part1_manifest);
>>>>>>> 81b8be6958ba3ea79741596f529ed0e7cd0fe0e0
        File[] listOftextFiles = folder.listFiles();

        if (listOftextFiles != null) {
            for (File file : listOftextFiles) {
                if (file.isFile() && file.getName().endsWith(".csv")) {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));

                        FileWriter badRecordsWriter = new FileWriter("bad_movie_records.txt");
                        FileWriter part2_manifestWriter = new FileWriter("part2_manifest.txt");

                        String line = "";
                        while ((line = br.readLine()) != null) {
                            String inputFileName = line.trim();
                            Method.partitionMovies(inputFileName, badRecordsWriter, part2_manifestWriter);
                        }
                        System.out.println();
                        br.close();
                        badRecordsWriter.close();
                        part2_manifestWriter.close();

                    } catch (IOException e) {
                        System.out.println("Error reading file: " + file.getName());
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("No files found in the directory");
        }
        */
    }

    public static void main(String[] args) {
        String part1_manifest = "DataBase/";

<<<<<<< HEAD
        PartOne.doPart1(part1_manifest);
        /*  String part2_manifest = do_part1(part1_manifest);
        String part3_manifest = do_part2(part2_manifest);
        do_part3(part3_manifest);
        return;*/
=======
        do_part1(part1_manifest);
        /*
         * String part2_manifest = do_part1(part1_manifest);
         * String part3_manifest = do_part2(part2_manifest);
         * do_part3(part3_manifest);
         * return;
         */
>>>>>>> 81b8be6958ba3ea79741596f529ed0e7cd0fe0e0
    }
}
