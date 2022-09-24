/*
 * Coins.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * this program checks if there is a combination (max # of coins to be given)
 * to provide the required amount using the change in the pocket
 *
 * @author      Deepika Kini
 * @author      Manasa Deshpande
 */

class Coins{

    /**
     * Calculates combinations of coins and the sums and counts
     * for those combinations
     *
     * @param   myCoins              the array that holds the coins in pocket
     * @param	myCoinsI		     the iterator in Coins array
     * @param	combinationArrI   	 the index of combArr upto which the
     * 								 combinations are completed in prev loop
     * @param	combinationArr       the final combination array
     *
     * @return
     */

    static int[][] calcCombination(int [] myCoins, int myCoinsI,
                                   int combinationArrI, int [][] combinationArr){
        if(myCoinsI == myCoins.length){
            return combinationArr;
        } else{
            //copies all rows from 0 to 2^indexOfCombination for new element
            //to be added
            for (int j = 0; j < combinationArrI; j++) {
                for (int k = 0; k < combinationArr[j].length; k++) {
                    combinationArr[combinationArrI+j][k]=combinationArr[j][k];
                }
                //adds Coin to the newly created rows and computes sum,# of elements
                combinationArr[combinationArrI + j][myCoinsI + 1]=myCoins[myCoinsI];
                combinationArr[combinationArrI + j]
                        [combinationArr[j].length - 2] += myCoins[myCoinsI];
                combinationArr[combinationArrI + j][combinationArr[j].length - 1]++;
            }

            myCoinsI++; //increments to go to the next element
            //below stmt calculates the starting index for next round
            combinationArrI = calcPower(myCoinsI);
            calcCombination(myCoins, myCoinsI, combinationArrI, combinationArr);

            return combinationArr;
        }

    }

    /**
     * Calculates the power of 2 for size computation
     *
     * @param	  power_iterator  the iterator
     * @return  				  the final value
     */

    public static int calcPower(int power_iterator){
        int out = 2;
        for(int i = 0; i < power_iterator - 1; i++)
            out = out * 2;
        return out;
    }

    /**
     * checks if the payment is present and
     * if present, prints max # of coins case
     *
     * @param	 toPay   the payment
     * @param  output  the array
     *
     */

    public static void outputCheck(int toPay, int [][] output){
        //variables that help in checking the rows for max number of coin combo
        int maxCoins = 0;
        int indexMaxCoins =- 1;

        for (int i = 0; i < output.length; i++){
            if(output[i][output[i].length - 2] == toPay &&
                    output[i][output[i].length - 1] > maxCoins){
                indexMaxCoins = i;
                maxCoins=output[i][output[i].length-1];
            }
        }

        //prints row with max no of elements and sum=payment, if present
        if (indexMaxCoins > 0) {
            System.out.print(toPay + "  cents: \t\t yes; used coins = ");
            for(int i = 0; i < output[indexMaxCoins].length - 2; i++){
                if(output[indexMaxCoins][i] > 0)
                    System.out.print(output[indexMaxCoins][i] + " cents ");
            }
            System.out.println();
        } else
            System.out.println(toPay + "  cents: \t\t no");
    }



    /**
     * The main program.
     *
     * @param    args    command line arguments (ignored)
     */

    public static void main(String args[]){
        int[] myCoins = {1, 4, 4, 5, 8};
        int[] toPay = {1222, 12234};

        for(int payI = 0; payI < toPay.length; payI++) //iterates on payment array
        {
            // calculates the #combinations based on myCoins and initialises 2d array
            int noOfCombinations = calcPower(myCoins.length);

            //initialises the array of combinations
            //(size: number of combinations x myCoins).extra 3 columns :
            //to hold sum, number of elements and 0 column for singular coin combinations

            int[][] combinationArr = new int[noOfCombinations][myCoins.length + 3];
            combinationArr[1][1] = myCoins[0];
            combinationArr[1][myCoins.length + 1] = myCoins[0];
            combinationArr[1][myCoins.length + 2] = 1;

            //calling Combination function which starts with 2nd element of myCoins
            //and 3rd row of Combinations
            int[][] output = calcCombination(myCoins, 1, 2, combinationArr);
            //calling outputCheck() to provide output for each myPay element
            outputCheck(toPay[payI],output);
        }

    }//main

} //Coins


