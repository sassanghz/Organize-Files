package Driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import Part_1.Method;
import Part_1.PartOne;

public class Driver {

    public static void main(String[] args) {
        String part1_manifest = "DataBase/";

        PartOne.doPart1(part1_manifest);
        /*  String part2_manifest = do_part1(part1_manifest);
        String part3_manifest = do_part2(part2_manifest);
        do_part3(part3_manifest);
        return;*/
    }
}
