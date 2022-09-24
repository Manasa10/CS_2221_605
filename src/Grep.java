/*
 * Grep.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * This program implements FSM/FA which are capable of recognizing
 * the patterns "^ab$", ".a+b.", ".ab.", '^[ab]c$", "^[ab]?c$", "^[ab]?c?$",
 *              "..\2\1"
 *
 * @author      Deepika Kini
 * @author      Manasa Deshpande
 */

import java.io.File;
import java.util.Scanner;

public class Grep {
    //will hold the no. of words in txt file
    private int numOfWords = 0;
    //holds the words
    private final String[] theWords = new String[10231];

    /**
     * This method reads the words from txt file or stdin and stores them in
     * theWords array
     *
     * @param args Command line arguments, can be the filename
     */
    private void readWordsFromFile(String args[]) {
        Scanner scanner;
        try {
            if ( args.length > 0 ) {
                // filename passed as parameter
                scanner = new Scanner(new File(args[0]));
            }
            else {
                scanner  = new Scanner( System.in);
                // The strings to be matched against the FSMs
                System.out.print("Enter strings > ");
            }
            while (scanner.hasNextLine()){
                theWords[numOfWords] = scanner.nextLine();
                numOfWords++;
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }

    /**
     * This method consists of the FSM to match strings against the pattern
     * ab
     *
     * @param charRepresentation the character array of input string
     * @return true if the FSM accepts input string, false otherwise
     */
    private boolean ab(char[] charRepresentation){
        int state = 0;
        boolean result = false;
        // iterating over each character in charRepresentation
        for(int index = 0; index < charRepresentation.length; index++){
            // the character at index
            char charRep = charRepresentation[index];
            if(charRep == 'a' && state == 0) {
                state++;
            }
            else if(charRep == 'b' && state == 1){
                result = true;
                state = 2;
            }
            else {
                // if above conditions do not match, then given string does not
                // match pattern
                result = false;
            }
        }
        return result;
    }

    /**
     * This method consists of the FSM to match strings against the pattern
     * .ab.
     *
     * @param charRepresentation the character array of input string
     * @return true if the FSM accepts input string, false otherwise
     */
    private boolean dotabdot(char[] charRepresentation){
        int state = 0;
        boolean result = false;
        // iterating over each character in charRepresentation
        for(int index = 0; index < charRepresentation.length; index++){
            char charRep = charRepresentation[index];
            // any character is accepted
            if(state == 0) {
                state = 1;
            }
            else if(state == 1 && charRep == 'a') {
                state = 2;
            }
            else if(state == 2 && charRep == 'b'){
                state = 3;
            }
            // any character is accepted
            else if(state == 3){
                state = 4;
                result = true;
            }
            else {
                // if above conditions do not match, then given string does not
                // match pattern
                return false;
            }
        }
        return result;
    }

    /**
     * This method consists of the FSM to match strings against the pattern
     * .a+b.
     *
     * @param charRepresentation the character array of input string
     * @return true if the FSM accepts input string, false otherwise
     */
    private boolean dotaplusbdot(char[] charRepresentation){
        int state = 0;
        boolean result = false;
        // iterating over each character in charRepresentation
        for(int index = 0; index < charRepresentation.length; index++){
            char charRep = charRepresentation[index];
            // any character is accepted
            if(state == 0) {
                state = 1;
            }
            // accepting at least one occurence of 'a'
            else if(state == 1 && charRep == 'a') {
                state = 2;
            }
            // accepting multiple occurrences of 'a'
            else if(state == 2 && charRep == 'a'){
                state = 2;
            }
            else if(state == 2 && charRep == 'b'){
                state = 3;
            }
            // any character is accepted
            else if(state == 3){
                state = 4;
                result = true;
            }
            else{
                // if above conditions do not match, then given string does not
                // match pattern
                return false;
            }
        }
        return result;
    }

    /**
     * This method consists of the FSM to match strings against the pattern
     * ^[ab]c$
     *
     * @param charRepresentation the character array of input string
     * @return true if the FSM accepts input string, false otherwise
     */
    private boolean aorbandc(char[] charRepresentation){
        int state = 0;
        boolean result = false;
        // iterating over each character in charRepresentation
        for(int index = 0; index < charRepresentation.length; index++){
            char charRep = charRepresentation[index];
            // accepting an 'a' in state 0
            if(charRep == 'a' && state == 0) {
                state = 1;
            }
            // accepting a 'b' in state 0
            else if(charRep == 'b' && state == 0){
                state = 1;
            }
            else if(charRep == 'c' && state == 1){
                result = true;
                state++;
            }
            else {
                // if above conditions do not match, then given string does not
                // match pattern
                return false;
            }
        }
        return result;
    }

    /**
     * This method consists of the FSM to match strings against the pattern
     * ^[ab]?c$
     *
     * @param charRepresentation the character array of input string
     * @return true if the FSM accepts input string, false otherwise
     */
    private boolean aorbquec(char[] charRepresentation){
        int state = 0;
        boolean result = false;
        // iterating over each character in charRepresentation
        for(int index = 0; index < charRepresentation.length; index++){
            char charRep = charRepresentation[index];
            // accepting an 'a' in state 0
            if(charRep == 'a' && state == 0) {
                state = 1;
            }
            // accepting a 'b' in state 0
            else if(charRep == 'b' && state == 0){
                state = 1;
            }
            // accepting an 'c' in state 0 (without 'a' or 'b') or with either
            // 'a' or 'b'
            else if(charRep == 'c' && (state == 0 || state == 1)){
                state = 2;
                result = true;
            }
            else {
                // if above conditions do not match, then given string does not
                // match pattern
                return false;
            }
        }
        return result;
    }

    /**
     * This method consists of the FSM to match strings against the pattern
     * ^[ab]?c?$
     *
     * @param charRepresentation the character array of input string
     * @return true if the FSM accepts input string, false otherwise
     */
    private boolean aorbquecque(char[] charRepresentation){
        int state = 0;
        boolean result = false;
        // iterating over each character in charRepresentation
        for(int index = 0; index < charRepresentation.length; index++){
            char charRep = charRepresentation[index];
            // accepts only one character 'a', 'b' in state 0
            if(state == 0 && (charRep == 'a' || charRep == 'b')) {
                state = 1;
                result = true;
            }
            // strings ending with 'c' match pattern
            else if((state == 1 || state == 0) && charRep == 'c'){
                state = 2;
                result = true;
            }
            else {
                result = false;
            }
        }
        return result;
    }

    /**
     * This method consists of the FSM to match strings against the pattern
     * ..\2\1
     *
     * @param charRepresentation the character array of input string
     * @return true if the FSM accepts input string, false otherwise
     */
    private boolean palindrome(char[] charRepresentation){
        int state = 0;
        boolean result = false;
        // iterating over each character in charRepresentation
        for(int index = 0; index < charRepresentation.length; index++) {
            char charRep = charRepresentation[index];
            // any character is accepted
            if (state == 0) {
                state = 1;
            }
            // any character is accepted
            else if (state == 1) {
                state = 2;
            }
            else if (state == 2) {
                // character at 3rd position matches character at 2nd position
                if (charRep == charRepresentation[index - 1]) {
                    result = true;
                    state = 3;
                } else {
                    return false;
                }
            }
            else if (state == 3) {
                // character at 4th position matches character at 1st position
                if (charRep == charRepresentation[index - 3]) {
                    result = true;
                    state = 4;
                } else {
                    return false;
                }
            }
            // if above conditions do not match, then given string does not
            // match pattern
            else {
                return false;
            }
        }
        return result;
    }

    /**
     * This method retrieves the words in theWords array and passes the
     * resulting character array to all pattern methods, prints the result
     */
    private void testFSM(){
        for(int index = 0; index < numOfWords; index++){
            String word = theWords[index];
            char[] array = word.toCharArray();
            System.out.println("String: " + word);
            System.out.println("Pattern: " + "^ab$       String: " + word + " " +
                                ab(array));
            System.out.println("Pattern: " + ".a+b.      String: " + word + " " +
                                dotaplusbdot(array));
            System.out.println("Pattern: " + ".ab.       String: " + word + " " +
                                dotabdot(array));
            System.out.println("Pattern: " + "^[ab]c$    String: " + word + " " +
                                aorbandc(array));
            System.out.println("Pattern: " + "^[ab]?c$   String: " + word + " " +
                                aorbquec(array));
            //should ac be accepted below????
            System.out.println("Pattern: " + "^[ab]?|c?$ String: " + word + " " +
                                aorbquecque(array));
            System.out.println("Pattern: " + "..\\2\\1     String: " + word +
                               " " + palindrome(array));
            System.out.println("=================================================");
        }
    }

    /**
     * The main method
     *
     *  @param args command line arguments, can be the filename containing
     *              input strings
     *
     */
    public static void main(String[] args){
        Grep grep = new Grep();
        grep.readWordsFromFile(args);
        grep.testFSM();
    } //main
} //Grep
