/*
 * GrepRegEx.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * HW 4.2
 * This program checks if input strings (from file or cmd prompt) match a pattern using
 * Pattern class in java.
 * All input strings are compared against all patterns but
 * the output shows only the ones that hold true
 *
 *The attached text input file has delimiter as ';' so in command prompt type
 * : "java GrepRegEx -d ’;’ -f input.txt"
 *
 *
 * @author      Deepika Kini
 * @author      Manasa Deshpande
 */
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;
import java.util.Scanner;

public class GrepRegEx {
    private static final String[] testCases = new String[10231];
    static String fileName = "-";
    static String delimiterString = "\n";

    /**
     * This method reads the words from txt file or std input
     *
     * @return open scanner object
     */
    private static Scanner readTextFromFile() {
        Scanner scanner = null;
        try {

            if (fileName.equals("-")) {
                System.out.println("Please enter test cases in prompt below:");
                scanner = new Scanner(System.in);
                System.out.print(">");
            } else {
                scanner = new Scanner(new File(fileName));
                scanner.useDelimiter(delimiterString);
            }
        } catch (Exception e) {
            System.out.println("ExceptionType occurred: " + e.getMessage());
            System.exit(1);
        }
        return scanner;
    }

    /**
     * This method reads the next string using scanner object
     *
     * @param scanner the object that mentions if data comes
     *               from txt or cmd line
     * @return the testcases for main() to take in
     */
    private static String nextString(Scanner scanner)   {
        String textValue = null;
        if ( scanner.hasNext() )    {
            textValue = scanner.next();
        } else {
            textValue = "-1";
        }
        return textValue;
    }

    /**
     * This method decodes the command line arguments
     * and stores values into global var
     *
     * @param args the array that holds the details
     */
    private static void parseArgs(String[] args) {
        for ( int index = 0; index < args.length; index += 2 )  {
            if ( args[index].equals("-f") ) {
                fileName = args[index + 1 ];
            }
            else if ( args[index].equals("-d") )    {
                delimiterString = args[index + 1 ];
            }
            else {
                System.out.println("Unrecognized argument -"  + args[index] + "=" );
                System.exit(1);
            }
        }
    }

    /**
     * This method works on palindrom. it basically decides if length isa legit
     * and calls the other palidrome function to check the characters
     *
     * @param text it holds the string value to be tested
     * @param palindromLength it holds the category under
     *                       which the text needs to be checked
     * @return the boolean value mentioning whether text is palindrome or not
     *          to main()
     */
    private static boolean checkForPalindromeLength(String text, int palindromLength){
        String pattern;
        boolean valid;
        if(palindromLength == 5)
            pattern = "[a-z]{4,5}";
        else
            pattern = "[a-z]{1,30}";

        valid = Pattern.matches(pattern, text);

        if (valid)
            valid = checkForPalindrome(text);

        return valid;
    }

    /**
     * This method works on palindrome as well. it creates a dynamic pattern
     * and checks if the values match in the front and back (iteratively)
     *
     * @param text it holds the string value to be tested
     *
     * @return the boolean value mentioning whether text is palindrome or not
     */
    private static boolean checkForPalindrome(String text){
        int lenText = text.length();
        String patternForPalindrome= "(";
        int flag = 0;

        //dynmically creating palindrome regex depending on length

        for (int index = 0; index < lenText / 2 ; index++ ) {
            patternForPalindrome += "([a-z])([a-z])";
        }
        //even length palindrome
        if (lenText % 2 == 0)
            patternForPalindrome += ")";
        else
            //odd length
            patternForPalindrome += "([a-z]))";



        Pattern patternCompiler = Pattern.compile(patternForPalindrome);
        Matcher patternMatcher = patternCompiler.matcher(text);
        //checking if values are same based on group index
        if (patternMatcher.find()) {
            //need to do it for group to be formed else error
            for (int index = 0; flag == 0 && index < lenText / 2 ; index ++ ) {
                if (! patternMatcher.group(index+2).
                        equals(patternMatcher.group(lenText - index + 1)))
                    flag = 1;
            }
        }
        return flag == 0 ? true: false;
    }

    /**
     * This method works on date format question.
     * It first checks for mmddyy and if not valid,
     * checks case of ddmmyy. it passes groups to the other method to keep it more modular
     *
     * @param text it holds the string value to be tested
     * @return the boolean value mentioning whether text is of the specified date format
     */
    private static boolean checkForDateFromat(String text){
        String PatternMMDDYY = "(([01])([0-9]))/([0123][0-9])/[0-9][0-9]";
        String PatternDDMMYY = "([0123][0-9])/(([01])([0-9]))/[0-9][0-9]";
        boolean valid = false;

        if (checkDateFormat(PatternMMDDYY, text, 1, 2, 3, 4)){
            valid = checkDateFormat(PatternMMDDYY, text, 1, 2, 3, 4);
        }
        else
            valid =  checkDateFormat(PatternDDMMYY, text, 2, 3, 4, 1);
        return valid;
    }

    /**
     * This method works on date format. it checks for all cases starting with cases of "00"
     * , boundary of number of days in particular months (28 is limit for feb and
     * no limit for apr)
     *
     * @param text                  it holds the string value to be tested
     * @param pattern               it holds either pattern of mmddyy or ddmmyy
     * @param monthGroup            it holds the 2 digits for month
     * @param monthFirstDigitGroup   it holds the 1st digit for month
     * @param monthSecondDigitGroup  it holds the 2nd digit for month
     * @param dayGroup               it holds the 2 digits for day
     * @return the boolean value mentioning whether text is conforming to date format
     */
    private static boolean checkDateFormat(String pattern, String text,  int monthGroup,
                                           int monthFirstDigitGroup,int monthSecondDigitGroup, int dayGroup ){
        Pattern patternCompiler = Pattern.compile(pattern);
        Matcher patternMatcher = patternCompiler.matcher(text);
        boolean valid = false;
        String month31Days = "01;03;05;07;08;10;12";
        //System.out.println(patternMatcher.find());
        if(patternMatcher.find()){
            //if values are not 00 for month/day and if both of them don't
            // go beyond 12 at the same time
            if(!patternMatcher.group(monthGroup).equals("00")
                    && !patternMatcher.group(dayGroup).equals("00")
                    && (Integer.parseInt(patternMatcher.group(dayGroup))<13
                    || Integer.parseInt(patternMatcher.group(monthGroup))<13))
            {

                //checks if months starting with '1' don't have second char more than 2
                if((patternMatcher.group(monthFirstDigitGroup).equals("1"))
                        && (Integer.parseInt(patternMatcher.group(monthSecondDigitGroup)) < 3 )){
                    valid = true;

                }

                if(month31Days.contains(patternMatcher.group(monthGroup))){
                    //checks for day value in 31day months
                    if (Integer.parseInt(patternMatcher.group(dayGroup)) <= 31) {
                        valid = true;
                    }
                }
                else if (!month31Days.contains(patternMatcher.group(monthGroup))
                        && !patternMatcher.group(monthGroup).equals("02")
                        && !patternMatcher.group(monthGroup).equals("04")){
                    //checks for day value in non-31 day months (except feb)
                    if ((Integer.parseInt(patternMatcher.group(dayGroup)) <= 30)){
                        valid = true;
                    }
                }
                else{
                    //checks for feb month day (value should be less than 29. not
                    // considering leap year case as per Slack)
                    if(valid  = (patternMatcher.group(monthGroup).equals("02") &&
                            Integer.parseInt(patternMatcher.group(dayGroup)) <= 28))
                        valid = true;
                    if(patternMatcher.group(monthGroup).equals("04")
                            || patternMatcher.group(dayGroup).equals("04"))
                        valid = true;

                }

                //System.out.println("valid" + valid);


            }
            else
                valid = false;
        }
        return valid;

    }

    /**
     * This method works on the last case
     *
     * @param text                  it holds the string value to be tested
     * @return the boolean value mentioning whether text is conforming to requirement
     */
    private static boolean checkForNumbersInBrackets(String text){
        String pattern = "^\\[(\\d)-\\d\\]\\|\\(\\1\\d\\)$";
        //need double back slash for compiler to understand esc Chars
        return Pattern.matches(pattern, text);

    }

    /**
     * This method works on the first case
     *
     * @param text                  it holds the string value to be tested
     * @return the boolean value mentioning whether text is conforming to requirement
     */
    private static boolean consonantsBetweenVowels(String text){
        String pattern = "a[a-z&&[^aeiou]]*e[a-z&&[^aeiou]]*i[a-z&&[^aeiou]]*o[a-z&&[^aeiou]]*u";
        return Pattern.matches(pattern, text);

    }


    /**
     * The main method that calls other methods sequentially
     *  @param args    command line arguments
     */
    public static void main(String args[]){
        parseArgs(args);

        Scanner scanner = readTextFromFile();
        String nextString = null;
        while ( !(( nextString =  nextString(scanner) ).equals("-1"))) {
            //System.out.println("input string:"+ nextString);
            if (consonantsBetweenVowels(nextString))
                System.out.println("1. vowel pattern stands true for  " +nextString );
            if (checkForPalindromeLength(nextString,5))
                System.out.println("2. palindrome pattern for length 4-5 stands true for  " + nextString );
            if (checkForPalindromeLength(nextString,30))
                System.out.println("3. palindrome pattern for length 1-30 stands true for  " + nextString );
            if (checkForDateFromat(nextString))
                System.out.println("4. date format check stands true for " + nextString);
            if (checkForNumbersInBrackets(nextString))
                System.out.println("5. numbers in bracket check stands true for " +nextString);

        }

    }// end of main
}//end of class