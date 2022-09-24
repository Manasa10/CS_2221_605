/*
 * Picture.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * This program implements a multiplayer game that allows players to guess
 * words and shows part of image based on how many correct guesses the player
 * has.
 *
 * @author      Deepika Kini
 * @author      Manasa Deshpande
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.IntStream;

public class Picture {

    //Number of players in the game
    static int numOfPlayers;
    // The words for each player
    static String playerAnswers[];
    // The images to be displayed for each player
    static int playerImages[];
    // The number of lines in the image for each player
    int lineNumbers[] = new int[5];
    // The image txt files
    static String allImages[] = {"src\\superman.txt", "src\\thor.txt"};
    // The string array that stores the images of each player
    String[][][] tempImage;
    // A temporary array that holds the image of each player during the game
    String[][] tempImageDupe;
    // Holds the correctly guessed char of each player
    static String playerGuess[][];
    // The number of correct guesses for each player
    static int correctGuess[];
    //will hold the no. of words in txt file
    static int numOfWords = 0;
    //holds the words
    static final String[] theWords = new String[10231];

    /**
     * This method reads the words from txt file and stores in theWords[]
     *
     * @param fileName the file name that contains strings used for
     *   testing the user (in current dir)
     */

    public static void readWordsFromFile(String fileName) {
        try (
                BufferedReader input = new BufferedReader(new FileReader(fileName));
        ) {
            while((theWords[numOfWords] = input.readLine()) != null){
                // Increasing the count of words
                numOfWords++;
            }
        }
        catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage() );
        }
    }

    /**
     * This method initializes the required arrays based on the number of
     * players
     *
     */
    public void initArrays(){
        playerImages = new int[numOfPlayers];
        playerAnswers = new String[numOfPlayers];
        playerGuess = new String[numOfPlayers][20];
        correctGuess = new int[numOfPlayers];
        // Initializing the string arrays that hold images assuming 120 lines
        // 80 characters in each line
        tempImage = new String[5][120][80];
        tempImageDupe = new String[120][80];
    }

    /**
     *
     * This method randomly assigns each player a word from theWords array,
     * initializes the correctly guessed char array to 0 for all players
     * An image is also chosen at random for each user and the initImages
     * is called to store the corresponding images.
     * The guessed string for each player is initialized to a string of
     * periods with length same as the answer
     *
     */
    public void chooseWords(){
        for(int index = 0; index < numOfPlayers; index++){
            correctGuess[index] = 0;
            playerAnswers[index] = theWords[genRandomNumber(numOfWords)];
            playerImages[index] = genRandomNumber(allImages.length);
            initImages(index);
            for(int guess = 0; guess < playerAnswers[index].length(); guess++){
                playerGuess[index][guess] = ".";
            }
        }
    }

    /**
     *
     * This method generates a random number within the given bound
     *
     * @param bound the maximum range within which number must be generated
     * @return the random number
     *
     */
    public static int genRandomNumber(int bound){
        int random = new Random().nextInt(bound);
        return random;
    }

    /**
     * This method reads the corresponding image file for each player and stores
     * the strings in the tempImage array of the corresponding player, It also
     * updates the number of lines in the image file
     *
     * @param player the index of the player
     *
     */
    public void initImages(int player){
        try{
            String line;
            // retrieving the image for the player
            String imageName = allImages[playerImages[player]];
            BufferedReader scanner = new BufferedReader( new FileReader(imageName));
            int lineNumber = 0;
            int index = 0;
            while ((line = scanner.readLine()) != null) {
                while(index < line.length()){
                    String img = String.valueOf(line.charAt(index));
                    tempImage[player][lineNumber][index] = img;
                    index++;
                }
                index = 0;
                // updating the number of lines in the file
                lineNumber++;
            }
            // updating the lineNumbers array
            lineNumbers[player] = lineNumber;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }

    /**
     * This method generates the partial image for each player based on the
     * number of correct guesses, displays the complete picture if player
     * has guessed the right answer
     *
     * @param player the index of the player
     * @param correctGuess the number of correct char guesses
     *
     */
    public void generateImage(int player, float correctGuess){
        int numLines = lineNumbers[player];
        // if not all characters have been guessed
        if(correctGuess != 1){
            int percentage = 80 - (int) (correctGuess * 80);
            IntStream intStream;
            int lineIndex = 0;
            for(int line = 0; line < numLines; line++){
                for(int col = 0; col < 80; col++){
                    // copying the image to a temporary array
                    tempImageDupe[line][col] = tempImage[player][line][col];
                }
            }
            while(lineIndex < numLines){
                // generates a stream of random integers within the range
                intStream = new Random().ints(percentage, 0, 80);
                Iterator iterator = intStream.iterator();
                while(iterator.hasNext()) {
                    int index = (int) iterator.next();
                    // Replacing the array contents with " " at indices in the intStream
                    tempImageDupe[lineIndex][index] = " ";
                }
                for(int index = 0; index < 80; index++){
                    // printing the partial image
                    System.out.print(tempImageDupe[lineIndex][index]);
                }
                System.out.println();
                lineIndex++;
            }
        }
        // all characters have been guessed correctly
        else{
            for(int lineIndex = 0; lineIndex < numLines; lineIndex++) {
                for (int index = 0; index < 80; index++) {
                    // printing the full image
                    System.out.print(tempImage[player][lineIndex][index]);
                }
                System.out.println();
            }
        }
    }

    /**
     * This method verifies if the guess the player made is present in the
     * chosen word, if the guess is correct, the count of correct guesses
     * is updated, based on the count, the generateImage is called
     *
     * @param player the index of the player
     * @param guess the current guess of the player
     *
     * @return true if player guessed all characters correctly, false otherwise
     *
     */
    public boolean checkGuess(int player, String guess){
        String answer = playerAnswers[player];
        for(int index = 0; index < answer.length(); index++){
            // checks if guess is present anywhere in answer string
            if(answer.charAt(index) == guess.charAt(0)){
                playerGuess[player][index] = guess;
                // incrementing correct number of guesses
                correctGuess[player]++;
            }
        }
        float percent = correctGuess[player]/(float) answer.length();
        generateImage(player, percent);
        System.out.print("\nPlayer " + (player + 1) + ": ");
        for(int index = 0; index < answer.length(); index++){
            System.out.print(playerGuess[player][index]);
        }
        // if number of correct guesses is equal to answer's length, word
        // has been guessed
        if(correctGuess[player] == answer.length()) {
            System.out.println("\nYou guessed correct!!");
            return true;
        }
        // player is yet to guess some characters
        else return false;
    }

    /**
     * This method passes the guess made by the player to the checkGuess method
     * and iterates the same for each player
     *
     */
    public void playGame(){
        boolean foundAnswer = false;
        Scanner getGuess = new Scanner(System.in);
        String guess="";
        while(!foundAnswer){
            for(int index = 0; index < numOfPlayers; index++){
                int answerLength = playerAnswers[index].length();
                if(!foundAnswer){
                    // prints progress until current iteration
                    System.out.print("\n\n>Player " + (index + 1) + " guess (");
                    for(int trial = 0; trial < answerLength; trial++){
                        System.out.print(playerGuess[index][trial]);
                    }
                    System.out.println("): ");
                    guess = getGuess.next().toLowerCase();
                    // If multiple characters are guessed
                    while (guess.length() != 1){
                        System.out.println("Enter 1 character> ");
                        guess = getGuess.next().toLowerCase();
                    }
                    foundAnswer = checkGuess(index, guess);
                }
            }
        }
    }

    /**
     * The main method that calls other methods sequentially
     *
     *  @param args    command line arguments (ignored)
     *
     */
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of players");
        // initializing number of players
        numOfPlayers = sc.nextInt();
        // updating theWords array
        readWordsFromFile("src\\words.txt");
        Picture picture = new Picture();
        // initializing the arrays based on number of players
        picture.initArrays();
        // Randomly chooses words and images for each player
        picture.chooseWords();
        picture.playGame();
    } // main
} // Picture
