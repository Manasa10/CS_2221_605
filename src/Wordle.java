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
 * This program runs the game wordle using a text file containing 5 character words.
 * After a word is used, it is eliminated from the file.
 * This file majorly uses String, Scanner and BufferedReader/writer classes
 *
 * @author      Deepika Kini
 * @author      Manasa Deshpande
 */

import java.io.*;
import java.util.Random;
import java.util.Scanner;



public class Wordle{
    static int soManyWordToPLayWith = 0; //will hold the no. of words in txt file
    static final String[] theWords = new String[10231]; //holds the words
    static final Scanner readGuess = new Scanner(System.in); //for using User input
    public static int indexOfAnswer = 0; //holds the line number of
    //randomly chosen word
    public static char[] output= new char[5]; //holds the hint string
    //provided to user



    /**
     * This method reads the words from txt file and stores in theWords []
     *
     * @param fileName the file name that contains strings used for
     *   testing the user (in current dir)
     */

    public static void readWordsFromFile(String fileName) {
        try (
                BufferedReader input = new BufferedReader( new FileReader(fileName));
        ) {
            String word;
            int counter = 0;
            while  ( ( theWords[counter++] = input.readLine() )  != null )
                soManyWordToPLayWith ++;
        }
        catch (Exception e) {
            System.out.println("ExceptionType occurred: " + e.getMessage() );
        }
    }


    /**
     * This method takes in user's 5 character input and validates it.
     * It uses Scanner class.
     * If user input is not 5 characters then it will
     * keep asking user for appropriate input.
     *
     * @return  the user input
     */

    public static String readUserInput() {
        String guess = "";

        do{
            System.out.println("Please provide 5 character word!");
            System.out.print("> ");
            guess = readGuess.nextLine().toLowerCase();
        }while(guess.length() != 5);

        return guess;
    }


    /**
     * This method takes a random word, indirectly, from txt file to test user
     *
     * @return the word to test user
     */

    public static String getWord() {
        indexOfAnswer = new Random().nextInt(soManyWordToPLayWith);
        return theWords[indexOfAnswer];
    }


    /**
     * This method helps to remove the Chosen word from txxt
     * file to avoid repetition.
     * This is done by creating a tmp file and storing all words
     *  except the chosen one, then renaming the file to fileName
     *
     * @param fileName the file name that contains strings used for
     *   testing the user (in current dir)
     */
    public static void removeWord(String fileName) {
        try{
            File originalFile =  new File(fileName);
            File tempFile = new File("5_words_tmp.txt");
            BufferedWriter output = new BufferedWriter(new FileWriter(tempFile));
            int counter = 0;


            for(int WordIterator=0;  WordIterator < soManyWordToPLayWith;
                WordIterator++){
                if(theWords[WordIterator] != null &&
                        theWords[WordIterator] != theWords[indexOfAnswer])
                    output.write(theWords[WordIterator]+"\n");
            }
            output.flush();
            output.close();
            tempFile.renameTo(originalFile);
        }
        catch(Exception e){
            System.out.println("ExceptionType occurred: " + e.getMessage() );

        }
    }


    /**
     * This is the main function that contains the logic of Wordle.
     * As per user input (guess) and the chosen word (answer), this function
     * iterates and compares characters. In case of multi character checks,
     * it uses countChar and multiCaseChar methods
     */

    public static void playWordle() {
        System.out.println("_ indicates the letter is in the word"
                +"but in the wrong spot.");
        System.out.println("* indicates the letter is in the word and correct spot.");
        System.out.println("x indicates the letter is not in the word.");
        System.out.println("Try to guess the word in 5 tries.");
        String answer;
//System.out.println(answer = getWord().toLowerCase() ); ********************
        answer = getWord().toLowerCase();//get a random word from the read file
        int countOfInputs = 0; //counts users inputs
        int correctAns = 0;   //correct answer flag
        String guess;
//char[] output= new char[5];
        int countInGuess;
        int countInAnswer;

        int maxCountOfInputs = 5;

        while(correctAns == 0 && countOfInputs < maxCountOfInputs ){
            for(int index = 0; index < output.length; index++)
                output[index] = 'a';
            System.out.println("GUESS #" + (countOfInputs+1) +" :");
            guess = readUserInput(); // reads user input

            countOfInputs++;

            System.out.print("\nHint : ");
            for(int charIterator = 0; charIterator < 5 ; charIterator++){
                if(output[charIterator] != '*' && output[charIterator] != '_'
                        && output[charIterator] != 'x'){
//avoiding overwritting the values already from multiCharCase method
                    if(guess.charAt(charIterator) == answer.charAt(charIterator))
// case where characters are same in correct index
                        output [charIterator] = '*';
                    else if(answer.indexOf(guess.charAt(charIterator)) > -1) {
//present somewhere in the answer string
                        countInGuess = countChar(guess, guess.charAt(charIterator));
                        countInAnswer = countChar(answer, guess.charAt(charIterator));
                        if(countInGuess <= countInAnswer)
//if char in guess in less/equal to answer, no problem faced
                            output [charIterator] = '_';
                        else
//avoid giving underscore to all positions of that character
                            multiCharCase(guess.charAt(charIterator), countInGuess,
                                    countInAnswer, guess, answer);
                    }
                    else
//when characters aren't present
                        output [charIterator] = 'x';
//****System.out.print(output[charIterator]);
                }
            }
            System.out.println(String.valueOf(output));
            if (String.valueOf(output).equals("*****"))
                correctAns = 1;
            System.out.println();
        }

        if(correctAns == 0)
            System.out.println("\n\nWrong answers entered. \nThe word is : "
                    +  answer.toUpperCase());
        else
            System.out.println("\nCorrect Answer!");
    }

    /**
     * This provides the times the character has come in a word.
     *
     * @param word    the word that we need to find in
     * @param character the character to check in the word
     * @return  the integer value that tells the frequency
     */
    public static int countChar(String word, char character){
        int count = 0;
        for(int index = 0; index < word.length(); index++)
        {
            if(word.charAt(index) == character)
                count++;
        }
        return count;
    }

    /**
     * This method checks cases where a character is present more the guess than answer
     * and assigns either '_' or 'x' depending on the case.
     * it first checks positions for where the characters are present in both strings
     *  at same locations. after that it provides remaining positions with underscore
     *  and the rest with cross
     *
     * @param character     the character having freq more in guess than answer
     * @param countInGuess the count of char in guess string
     * @param countInAnswer the count of char in answer string
     * @param guess the  guess string
     * @param answer the answer string
     */

    public static void multiCharCase(char character,  int countInGuess,
                                     int countInAnswer, String guess, String answer){
        int count = countInGuess - countInAnswer;
//System.out.println("\n"+count + " " + countInGuess + " " + countInAnswer);
        int index;
        for(index = 0; index < guess.length(); index++){
            if(guess.charAt(index) == character && answer.charAt(index) == character){
                output[index] = '*';
                count--;
            }
            //System.out.println("\ncount after loop"+count + " " );
        }
        index = 0;
        while(index < guess.length()){
            if(count>0 && guess.charAt(index) == character && output[index] != '*'){
                output[index] = '_';
                count--;
            }
//System.out.println("\ncount after loop"+count + " " );
            if(count<=0 && guess.charAt(index) == character && output[index] != '*')
                output[index] = 'x';

            index++;
        }
//System.out.println(String.valueOf(output));
    }

    /**
     * The main method that calls other methods sequentially
     *  @param args    command line arguments (ignored)
     */
    public static void main( String args[] ) {
        readWordsFromFile("src\\5_char_word.txt"); // reads the text file -
        //in the local directory
        playWordle();
        removeWord("C:\\Users\\LENOVO\\IdeaProjects\\CSCI_605\\src\\5_char_word.txt");
    }//main
}//Wordle class