/*
 * StringIntegerArrays.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * This program runs the string methods to compare, sort and extract substrings
 * Homework 3.1 uses Integer, String and Arrays (char) classes
 * 
 * Uptil line 54, we have taken code from Professor's notes for input values
 * No use of loops, conditional stmts in the section of question solving
 * 
 * Comments are added to Questions I to VII mentioning the outcome and 
 * reasons of outcomes for string comparison discussed by authors.
 *
 * @author      Deepika Kini
 * @author      Manasa Deshpande
 */

import java.lang.Integer;
import java.lang.String;
import java.util.Arrays;
class StringIntegerArrays{

   /**
   * The main program.
   *
   * @param    args    command line arguments (used in this homework)
   */
    
    public static void main(String[] args) {
        String aString, bString, cString, dString, eString, fString, gString, hString,
         iString;

        if (args.length == 1) {
            aString = "123425" + "25";
            bString = "12342525";
            cString = "5";
            dString = "25" + "2" + cString;
            eString = "a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z";
            fString = "A, B, C, D, E, F, G, H, I, J, K, *, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z";
            gString = aString + (bString + cString) + dString;
            hString = "2525" + "+" + "2525";
            iString = "2525+2525";
            System.out.println("#1 arg");
        } 
        else {
            aString = "12342" + new String("" + 5) + "25";
            bString = "12342525";
            cString = "5";
            dString = "" + "25" + "" + "2" + cString;
            eString = "b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z";
            fString = "B, C, D, E, F, G, H, I, J, K, *, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z";
            gString = ((aString + bString) + "" + 5) + dString;
            hString = "2525" + "+" + "2525";
            iString = "25" + (2 + 5) + "+2525"; // (2+5) gives 7 which is then concatenated
            System.out.println("#2 arg");
        }

        //****Questions I to VI
        System.out.println("I.   Is aString identical to bString : " 
                            + (aString == bString)); 
        // 1. True (same reference) 2. False (new keyword)

        System.out.println("II.  Is aString identical to dString : " 
                            + (aString == dString)); 
        // 1. False (different value + var concatenated)
        // 2. False (different value + var and new keyword)

        System.out.println("III. Is aString equal to bString     : " 
                            + aString.equals(bString));
        // 1. and 2. True since values are the same

        System.out.println("IV.  Is hString identical to iString : " 
                            + (hString == iString));
        // 1. True (same value and reference) 2. False (different values)
        System.out.println("V.   Is hString equal to iString     : " 
                            + hString.equals(iString));
        // 1. True (same value and reference) 2. False (different values)
        System.out.println("VI.  Is aString equal to dString     : " 
                            + aString.equals(dString) + "\n\n");
        // 1. and 2. False (the values are different)

        //****Question VII
        System.out.println("\n***************");
        System.out.println("VII. Extract from dString the string starting at the number " + 
                           "\n     defined by the first digit in aString \n     to" + 
                           " defined by the digit at the second in aString : " +
                dString.substring(Integer.parseInt(String.valueOf(aString.charAt(0))) - 1, 
                                  Integer.parseInt(String.valueOf(aString.charAt(1)))));
        // Assuming 1 in aString should mean it starts with character at index 0 in dString

        //valueOf() helps to convert char to String and parseInt() 
        //helps to convert String to int


        //Question VIII
        System.out.println("\n***************");
        System.out.println("\nVIII. Extracting the character of eString " +   
                            "corresponding \n      to position  of ’*’ on fString: "
                            + eString.charAt(fString.indexOf("*")));

        //Question IX
        System.out.println("\n***************");
        char[] chArray = bString.toCharArray();
        Arrays.sort(chArray); //overwrites char[]
        System.out.println("\nIX.   Sorting the digits in bString: " 
                            + String.valueOf(chArray) + "\n");
       // convert char array to string to avoid using for loop

        }             
}