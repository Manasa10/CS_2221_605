/*
 * SumOfTwoSets.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * Given all but one positive n-bit integers in a random order, this program
 * determines the missing number if it is not present
 *
 * @author      Deepika Kini
 * @author      Manasa Deshpande
 */

import java.io.File;
import java.util.Scanner;

public class FindMissingNumber {

    //Initializing the variable where XOR result will be stored
    static long xorNumber = 0;

    /**
     * This method reads the numbers from a file or standard input based on the
     * arguments. Each input number is then XORed with the result of all XOR
     * operations that is stored in the xorNumber variable. The number stored
     * in xorNumber is the missing number, 0 if no number is missing
     *
     * @param args Command line arguments, can be the filename
     * @return
     */
    public static long readNumbersFromFile(String args[]){
        try {
            // Number in current input line
            long numberInLine;
            Scanner scanner = null;
            if ( args.length > 0 ) {
                // filename passed as parameter
                scanner = new Scanner(new File(args[0]));
            }
            else {
                scanner  = new Scanner( System.in);
                System.out.println(">");
            }
            String s;
            while (scanner.hasNext()){
                s = scanner.next();
                // Parsing input string as unsigned long
                // Using unsigned long, minimum value = 0, maximum = 2^64-1
                numberInLine = Long.parseUnsignedLong(s);
                // XORing the input number with result of all previous XOR operations
                xorNumber = numberInLine ^ xorNumber;
            }
            scanner.close();
            // return the result after XOR
            return xorNumber;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
            return 0;
        }
    }

    /**
     * The main program
     *
     * @param args filename containing n-bit integers (optional)
     */
    public static void main(String args[]){
        long missingNumber = readNumbersFromFile(args);
        // Checking if all numbers are present in list
        if(missingNumber == 0){
            System.out.println("List contains all n-bit integers");
        }
        else
            System.out.println("The missing number is: " + missingNumber);
    } // main
} // FindMissingNumber
