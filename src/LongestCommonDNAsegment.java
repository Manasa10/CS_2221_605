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

public class LongestCommonDNAsegment {

    static int maxSequence;                 // maximum length of substring

    /**
     * Given the character arrays of the two DNA sequences, this method
     * computes the longest substring present in both sequences and stores
     * the value in maxSequence
     *
     * @param    firstArray    character array of the first DNA sequence
     * @param    secondArray   character array of the second DNA sequence
     *
     */

    public static void findCommonSegment(char firstArray[], char secondArray[]){
        int longestSequence;
        for(int i = 0; i < firstArray.length; i++){
            longestSequence = 0;
            for(int j = 0; j < secondArray.length; j++){
                if((i+j) < firstArray.length){ // check if array length exceeds
                    if(firstArray[i+j] == secondArray[j]){
                        longestSequence++;// increase count if characters match
                        if(longestSequence > maxSequence){
                            // update maximum count
                            maxSequence = longestSequence;
                        }
                    }
                    else{
                        longestSequence=0; // characters do not match
                    }
                }
            }
        }
    }

    /**
     * The main program.
     *
     * @param    args    command line arguments (ignored)
     */

    public static void main (String[] args){
        maxSequence = 0;
        char[] dnaTwo = {'t', 'a', 'c', 'g', 'g'}; // first character array
        char[] dnaOne = {'t', 'a', 'c', 'g', 'g'}; // second character array
        findCommonSegment(dnaOne, dnaTwo);
        // to compare last and first characters in arrays
        findCommonSegment(dnaTwo, dnaOne);
        System.out.println("longest sequence:"+maxSequence); // Prints length
    }

} // LongestCommonDNAsegment
