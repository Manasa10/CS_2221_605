/*
 * NumberEqualCube.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */


/**
 * This program computes whether a number equals the summation of the each
 * digit raised to the length of the number
 *
 * @author      Manasa Deshpande
 * @author      Deepika Kini
 */

public class NumberEqualCube {

    /**
     * Given the base and the power, calculates the result for exponential
     *
     * @param    base   base of exponential calculation
     * @param    power  power indicates the int that base needs to be raised by
     *
     * @return           output of the calculation
     */

    public static int exponent(int base, int power){
        int product = 1;

        for (int index = 0; index < power; index++){
            product = product * base;
        }
        return product;
    }

    /**
     * Given the number in char array and integer form, computes the sum
     * of each integer charater raised to the length (calls exponent function)
     * and then prints the number which hold true the calculation
     *
     * @param    nAsCharacters   the character array of the integer
     * @param    number             the number as an int
     *
     */

    public static void computeCube(char nAsCharacters[], int number){
        int sum = 0;
        int total = number;
        int numLength = nAsCharacters.length;

        while (number != 0) {
            // calling the exponent function for the last digit of the current run
            sum = (int) (sum + exponent((number % 10), numLength));
            // removing the last digit from the number for the next loop
            number = number / 10;
        }
        if (sum == total)
            // doing the check and printing if TRUE
            System.out.println("Found a match: "+ sum);
    }

    /**
     * The main program.
     *
     * @param    args    command line arguments (ignored)
     */

    public static void main(String args[]){
        // converting numbers ranging 1 to 99999 into char array and
        // sending this and the number itself to the method: ComputeCube
        for (int i = 1; i < 100000; i++) {
            char[] nAsCharacters = ("" + i).toCharArray();
            computeCube(nAsCharacters, i);
        }
    }
} //NumberEqualCube
