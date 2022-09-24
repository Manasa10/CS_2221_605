/*
 * LongestCommonDNAsegment.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */


/**
 * This program computes the length of the longest sub-sequence which
 * is present in both DNA sequences
 *
 * @author      Manasa Deshpande
 * @author      Deepika Kini
 */

public class ManasaCoinsAssignment {

    static int coinsMatrix[][];  // 2 dimensional matrix of coins' combinations
    static int[] myCoins = { 1, 4, 4, 5, 8}; // available coins
    static int[] toPay =   { 9, 6, 4, 7, 8}; // the amounts to be paid
    static int sumArray[]; // array of sums of coins
    static int numberOfCoins[]; // array of number of coins for each sum

    /**
     * Given the base and the power, calculates the result for exponential
     *
     * @param    base    base of exponential calculation
     * @param    power   power indicates the int that base needs to be raised by
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
     * Given the index of the column and the length of the array, this method
     * creates the 2 dimensional array consisting of all possible combinations
     * of the available coins
     *
     * @param    index    the column index
     * @param    maxLength   the length of the 2 dimensional array
     *
     */

    public static void matrixCreator(int index, int maxLength){
        int counter;
        if(index!=0)
            matrixCreator(index - 1, maxLength);
        // difference is 2^n where n is the column index
        int difference = exponent(2, index + 1);
        //iterating the second half of the array for each index
        for(int i = exponent(2, index); i < maxLength; i = i + difference){
            // counter is the number of nonzero values in a single iteration
            counter = exponent(2, index) - 1;
            while (counter >= 0){
                coinsMatrix[i + counter][index] = myCoins[index];
                counter--;
            }
        }
    }

    /**
     * Given the number of available coins and the length of the array, this
     * method computes the sum of each combination of coins and stores the
     * results in the sum array. The number of coins in each combination is
     * stored in the numberOfCoins array
     *
     * @param    coinsLen    the column index
     * @param    matrixLength   the length of the 2 dimensional array
     *
     */

    public static void computeSumAndNumberArray(int matrixLength, int coinsLen){
        int sum;
        int coins;
        for (int rowIndex = 0; rowIndex < matrixLength; rowIndex++){
            sum = 0;
            coins = 0;
            for(int colIndex = 0; colIndex < coinsLen; colIndex++){
                sum = sum + coinsMatrix[rowIndex][colIndex];
                if(coinsMatrix[rowIndex][colIndex] != 0)
                    coins++;
            }
            sumArray[rowIndex] = sum;
            numberOfCoins[rowIndex] = coins;
        }
    }

    /**
     * For each amount to be paid, this method compares the amount with the
     * sum array and outputs the maximum number of coins used among all
     * matching combinations.
     *
     */

    public static void determinePay(){
        int maxNumberOfCoins;
        int coinsIndex = 0;
        boolean canBePaid;
        for(int index = 0; index < toPay.length; index++){
            maxNumberOfCoins = 0;
            canBePaid = false;
            for(int sumIndex = 0; sumIndex < sumArray.length; sumIndex++){
                if(toPay[index] == sumArray[sumIndex]){
                    canBePaid = true;
                    if(numberOfCoins[sumIndex] > maxNumberOfCoins) {
                        maxNumberOfCoins = numberOfCoins[sumIndex];
                        coinsIndex = sumIndex;
                    }
                }
            }
            if(canBePaid) {
                System.out.print(toPay[index] + " cents:  Yes; Used coins = ");
                for (int coinsLen = 0; coinsLen < myCoins.length; coinsLen++) {
                    if (coinsMatrix[coinsIndex][coinsLen] != 0)
                        System.out.print(coinsMatrix[coinsIndex][coinsLen]);
                    System.out.println(" cents ");
                }
                System.out.println();
            }
            else
                System.out.print("Cannot pay " + toPay[index]);
            System.out.println(" cents with available coins.");
        }
    }

    /**
     * The main program.
     *
     * @param    args    command line arguments (ignored)
     */

    public static void main(String args[]) {
        int coinsArrayLength = myCoins.length; // number of available coins
        // number of combinations
        int matrixLength = exponent(2, coinsArrayLength);
        // All possible coins' combinations
        coinsMatrix = new int[matrixLength][coinsArrayLength];
        // Sums of all coin combinations
        sumArray = new int[matrixLength];
        // Number of coins used in each combination
        numberOfCoins = new int[matrixLength];
        matrixCreator(coinsArrayLength - 1, matrixLength);
        computeSumAndNumberArray(matrixLength, coinsArrayLength);
        determinePay();
    }
}
