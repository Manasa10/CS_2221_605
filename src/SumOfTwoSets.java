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
 * This program determines if two subsets for a given set of integers exist
 * which can be added or subtracted in any combination to obtain the numbers
 * in the second set.
 *
 * @author      Deepika Kini
 * @author      Manasa Deshpande
 */

public class SumOfTwoSets {

    // the first subset
    static int target[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    // the second subset
    static int input[] = {2, 3, 7};
    //boolean variable to track whether a combination has been found
    static boolean someBool;

    /**
     * given the two subsets and their indices, this method computes
     * different combinations of the subset and returns true if the sum
     * adds up to the target number in the first subset, and false otherwise
     *
     * @param firstIndex   index of the first subset
     * @param sumPoint     index of array from where sum is computed
     * @param target       the number to be computed
     * @param sum          the sum upto the current indices
     * @param secondIndex  index of the second index where sum is computed
     * @return             true if a combination is found and false otherwise
     */

    static boolean sumSet(int firstIndex, int sumPoint, int target, int sum, int secondIndex){
        sum = input[sumPoint] + sum;
        if(sum == target)
            return true;
        else{
            int difference;
            for(int difIndex = 0; difIndex < input.length; difIndex++){
                difference = sum - input[difIndex];
                if (difference < 0)
                    difference = -difference;
                if(target == difference)
                    return true;
            }
            sumPoint++;
            if(sumPoint < input.length) {
                someBool = sumSet(firstIndex, sumPoint, target, sum, secondIndex);
                if (someBool)
                    return someBool;
            }
            secondIndex++;
            if(secondIndex < input.length) {
                someBool = sumSet(firstIndex, secondIndex, target, input[firstIndex], secondIndex);
                if (someBool)
                    return someBool;
            }
            firstIndex++;
            if(firstIndex < input.length) {
                someBool = sumSet(firstIndex, firstIndex, target, 0, firstIndex + 1);
                if (someBool)
                    return someBool;
            }
            return false;
        }
    }

    /**
     *
     * @param args    command line arguments (ignored)
     */
    public static void main(String args[]){
        boolean canBeComputed;
        for(int targetIndex = 0; targetIndex < target.length; targetIndex++){
            canBeComputed = false;
            canBeComputed = sumSet(0, 0, target[targetIndex], 0, 1);
            if(canBeComputed){
                System.out.println(target[targetIndex] + " can be computed");
            }
            else {
                System.out.println(target[targetIndex] + " cannot be computed");
            }
        }
    } // main
} // SumOfTwoSets
