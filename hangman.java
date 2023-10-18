import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;



public class Hangman {
    public static void main(String[] args) {
        
        System.out.println("Welcome to Kaylin's Hangman game!");
        System.out.println("Please type 0 if playing alone");
        System.out.println("Please type 1 if playing with friends");
        System.out.print("Enter your selection: ");
        Scanner input = new Scanner(System.in);
        int single_or_multi =  input.nextInt(); //single or multiplayer

        if (single_or_multi == 0) {
            solo(input);
        } 
        else if (single_or_multi == 1) {
            multi(input);
        } else  {
            input.close();
            throw new IllegalArgumentException("follow directions ya goof. " + single_or_multi + " was not a valid choice.");
        }

        int playAgain = -1; // variable will either be changed to one or zero

        do {
        System.out.println("Play again?");
        System.out.println("0 for no");
        System.out.println("1 for yes");
        System.out.print("Enter your selection: ");
        playAgain = input.nextInt();
        if (playAgain == 1) {
            System.out.println("");
            System.out.println("Welcome (back) to Kaylin's Hangman game!");
            System.out.println("Please type 0 if playing alone");
            System.out.println("Please type 1 if playing with friends");
            System.out.print("Enter your selection: ");
            single_or_multi = input.nextInt();
            if (single_or_multi == 0) {
                solo(input);
            } else if (single_or_multi == 1) {
                multi(input);
            } else {
            input.close();
            throw new IllegalArgumentException("follow directions ya goof. " + single_or_multi + " was not a valid choice.");
        }
        } 

        } while (playAgain == 1);

        if (playAgain != 0) {
            System.out.println("i'm closing the program anyway bro follow directions");
            }
        System.out.println("Thanks for playing!");
        
        input.close();
        
        System.exit(0);
    }

    /**
     * @param input - pre-initialized scanner
     * sets up the game if solo is selected
     */
    public static void solo(Scanner input) {
        String word = getRandomWord();
        playgame(word, input);
        
    }

    /**
     * @param input - pre-initialized scanner
     * sets up the game if multiplayer is selected
     */
    public static void multi(Scanner input) {
        String word = getword(input);
        playgame(word, input);
    }

    /**
     * @param input - pre-initialized scanner
     * @return a word inputted by the user, that was verified to be within the requirements
     * also prints a asterisk-wall to hide the word inputted 
     */
    public static String getword(Scanner input) {
        String word = "";
        System.out.println("");
        System.out.println("Enter a word for your friend to guess!");
        System.out.println("The word must be two characters minimun, 12 characters maximum, no spaces, and no special characters or numbers");
        System.out.println("Hide your screen!");
        System.out.print("Enter your word: ");
        word = input.next();
        word = word.toLowerCase();
        if (word.length() > 12) {                       // checking too long
            System.out.println("word is too long!");
            word = getword(input);
        }
        if (word.length() < 2) {                        // checking too short
            System.out.println("word is too short!");
            word = getword(input);
        }


        for (int i = 0; i < word.length(); i++) {       //checking for spaces 
            if (word.charAt(i) == ' ') {
                System.out.println("word cannot include a space!");
                word = getword(input);
            }    
        }

        for (int i = 0; i < word.length(); i++) {       //checking for non-aplha 
            String alpha = "abcdefghijklmnopqrstuvwxyz";
            char currChar = word.charAt(i);
            if (alpha.indexOf(currChar) == -1) {
                System.out.println("word cannot include anything that isn't in the alphabet!");
                word = getword(input);
            }
        }    
        System.out.println("Word accepted!");
        System.out.println("hiding terminal!!"); // hides terminal so that the next person can't see what was typed
        System.out.println("");
        hideTerminal();
        return word;
    }

    /**
     * @return random word from WORDS_ALIST
     */
    public static String getRandomWord() {
        String WORDS = "abruptly absurd abyss affix askew avenue awkward azure bagpipes bandwagon beekeeper bikini blitz blizzard boggle bookworm boxcar boxful buffalo buffoon buzzing buzzwords cobweb cockiness crypt cycle dizzying duplex dwarves embezzle equip exodus faking fishhook fixable flapjack flopping fluffiness flyby frazzled frizzled funny galaxy gizmo gnarly gossip grogginess haiku haphazard hyphen icebox injury ivory ivy jackpot jawbreaker jaywalk jazzy jelly jigsaw jinx jiujitsu jockey jogging joking joyful juicy jukebox jumbo kayak kazoo keyhole khaki kilobyte kiosk klutz knapsack lengths lucky luxury lymph matrix microwave mnemonic mystify nightclub nowadays nymph ovary oxidize oxygen pajamas peekaboo pixel psyche puppy puzzling quartz queue quiz quizzes rhythm scratch snazzy spark sphinx spritz squawk staff strength strengths stretch stronghold";
        String[] WORDS_ALIST = WORDS.split(" ");
        int wordsLength =  WORDS_ALIST.length;
        int randomNum = ThreadLocalRandom.current().nextInt(0, wordsLength);
        return WORDS_ALIST[randomNum];
    }


    /**
     * prints representation of start of hangman
     */
    public static void printStageZero() {
        System.out.println("    --------------------");
        System.out.println("    |                  |");
        System.out.println("    |    ");
        System.out.println("    |    ");
        System.out.println("    |    ");
        System.out.println("    |    ");
        System.out.println("    |    ");
        System.out.println("    |    ");
        System.out.println("    |    ");
        System.out.println("    |    ");
        System.out.println("    |    ");
        System.out.println("    |    ");
        System.out.println("----------");
        System.out.println("");
    }
    /**
     * prints hangman representation with one incorrect guess
     */
    public static void printStageOne() {
        System.out.println("    --------------------");
        System.out.println("    |                  |");
        System.out.println("    |                 ---    ");           
        System.out.println("    |               |     |  ");
        System.out.println("    |                 ---      ");
        System.out.println("    |                      ");
        System.out.println("    |                      ");
        System.out.println("    |                      ");
        System.out.println("    |                      ");
        System.out.println("    |                      ");
        System.out.println("    |                      ");
        System.out.println("    |                      ");
        System.out.println("----------");
        System.out.println("");
    }

    /**
     * prints hangman representation with two incorrect guesses
     */
    public static void printStageTwo() {
        System.out.println("    --------------------");
        System.out.println("    |                  |");
        System.out.println("    |                 ---    ");           
        System.out.println("    |               |     |  ");
        System.out.println("    |                 ---      ");
        System.out.println("    |                  |    ");
        System.out.println("    |                  |   ");
        System.out.println("    |                  |    ");
        System.out.println("    |                  |    ");
        System.out.println("    |                      ");
        System.out.println("    |                      ");
        System.out.println("    |                      ");
        System.out.println("----------");
        System.out.println("");
    }

    /**
     * prints hangman representation with three incorrect guesses
     */
    public static void printStageThree() {
        System.out.println("    --------------------");
        System.out.println("    |                  |");
        System.out.println("    |                 ---    ");           
        System.out.println("    |               |     |  ");
        System.out.println("    |                 ---      ");
        System.out.println("    |                  |    ");
        System.out.println("    |              ----|   ");
        System.out.println("    |                  |    ");
        System.out.println("    |                  |    ");
        System.out.println("    |                      ");
        System.out.println("    |                      ");
        System.out.println("    |                      ");
        System.out.println("----------");
        System.out.println("");
    }

    /**
     * prints hangman representation with four incorrect guesses
     */
    public static void printStageFour() {
        System.out.println("    --------------------");
        System.out.println("    |                  |");
        System.out.println("    |                 ---    ");           
        System.out.println("    |               |     |  ");
        System.out.println("    |                 ---      ");
        System.out.println("    |                  |    ");
        System.out.println("    |              ----|----   ");
        System.out.println("    |                  |    ");
        System.out.println("    |                  |    ");
        System.out.println("    |                      ");
        System.out.println("    |                      ");
        System.out.println("    |                      ");
        System.out.println("----------");
        System.out.println("");
    }

    /**
     * prints hangman representation with five incorrect guesses
     */
    public static void printStageFive() {
        System.out.println("    --------------------");
        System.out.println("    |                  |");
        System.out.println("    |                 ---    ");           
        System.out.println("    |               |     |  ");
        System.out.println("    |                 ---      ");
        System.out.println("    |                  |    ");
        System.out.println("    |              ----|----   ");
        System.out.println("    |                  |    ");
        System.out.println("    |                  |    ");
        System.out.println("    |                 /     ");
        System.out.println("    |                /      ");
        System.out.println("    |                      ");
        System.out.println("----------");
        System.out.println("");
    }

    /**
     * prints hangman representation with six incorrect guesses
     */
    public static void printStageSix() {
        System.out.println("    --------------------");
        System.out.println("    |                  |");
        System.out.println("    |                 ---    ");           
        System.out.println("    |               |     |  ");
        System.out.println("    |                 ---      ");
        System.out.println("    |                  |    ");
        System.out.println("    |              ----|----   ");
        System.out.println("    |                  |    ");
        System.out.println("    |                  |    ");
        System.out.println("    |                 / \\   ");
        System.out.println("    |                /   \\   ");
        System.out.println("    |                      ");
        System.out.println("----------");
        System.out.println("");
    }

    /**
     * prints kill stage hangman representation
     */
    public static void printStageKill() {
        System.out.println("    --------------------");
        System.out.println("    |                  |");
        System.out.println("    |                 ---    ");           
        System.out.println("    |               | x x |  ");
        System.out.println("    |                 ---      ");
        System.out.println("    |                  |    ");
        System.out.println("    |              ----|----   ");
        System.out.println("    |                  |    ");
        System.out.println("    |                  |    ");
        System.out.println("    |                 / \\   ");
        System.out.println("    |                /   \\   ");
        System.out.println("    |                      ");
        System.out.println("----------");
        System.out.println("");
    }

     /**
     * prints win stage hangman representation
     */
    public static void printStageWin() {
        System.out.println("                               ");
        System.out.println("            -------                    ");
        System.out.println("          |         |             ");
        System.out.println("          | 0     0 |              ");
        System.out.println("          |   \\_/   |             ");
        System.out.println("            -------                      ");
        System.out.println("               |                  ");
        System.out.println("         ------|------              ");
        System.out.println("               |                ");
        System.out.println("               |               ");
        System.out.println("              / \\               ");
        System.out.println("             /   \\               ");
        System.out.println("            /     \\                                    ");
        System.out.println("");
    }








    /**
     * @param word - the word to be guessed (hidden from this point forward)
     * @param input - pre-initialized scanner
     * 
     * plays the game in six stages, plus a win and kill stage. each of the siz stages has an associated hangman representation, which are related
     * to the number of incorrect guesses. 
     */
    public static void playgame(String word, Scanner input) {

        boolean WIN = false;
        char[] charUsed = new char[7]; //for what incorrect letters were already guessed
        for (int i = 0; i < charUsed.length; i++) {
            charUsed[i] = ' ';
        }
        String COPYWORD =  word;
        int stageCounter = 0;
        System.out.println("Time to play hangman!");
        System.out.println("");
        printStageZero();
        System.out.println("The word is " + word.length() + " letters!");
        System.out.println("");
        int printLines = word.length();
        do {
            System.out.print("___  ");
            printLines --;
        } while (printLines > 0);
        System.out.println("");
        System.out.println("");
        char[] wordRepArr = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            wordRepArr[i] = '-';
        }

                    //TIME TO GUESS
                        char guess;
                        int curr;
                        //String buffer;
                    //STAGE zero

        
        while ((stageCounter == 0) && (WIN == false)) {
            System.out.print("guess a letter: ");
            guess = input.next().charAt(0);
             if (word.indexOf(guess) == -1) {
                stageCounter = 1;
                System.out.println(guess + " is NOT a letter!");
                charUsed[stageCounter] = guess;
                System.out.println("");
            }
            if (word.indexOf(guess) != -1) {
                
                System.out.println("");
                do {
                    curr = word.indexOf(guess);
                    wordRepArr[curr] = guess;
                    word = word.substring(0, curr) + "0" + word.substring(curr + 1); 
                } while (word.indexOf(guess) != -1);

                printGuesser(wordRepArr);
                System.out.println("'" + guess + "' is a letter!");
                System.out.println("");
                System.out.println("");
            }

                WIN = isGuessed(wordRepArr);
        } 
        

                // STAGE ONE        
                    if (!WIN) {
                    printStageOne();
                    System.out.println("you have incorrectly guessed: " +  printUsed(charUsed));
                    System.out.println("");
                    printGuesser(wordRepArr);
                }


        while ((stageCounter == 1) && (WIN == false)) {
            System.out.print("guess a letter: ");
            guess = input.next().charAt(0);
             if (word.indexOf(guess) == -1) {
                stageCounter = 2;
                System.out.println(guess + " is NOT a letter!");
                charUsed[stageCounter] = guess;
                System.out.println("");
            }
            if (word.indexOf(guess) != -1) {
                
                System.out.println("");
                do {
                    curr = word.indexOf(guess);
                    wordRepArr[curr] = guess;
                    word = word.substring(0, curr) + "0" + word.substring(curr + 1); 
                } while (word.indexOf(guess) != -1);

                printGuesser(wordRepArr);
                System.out.println("'" + guess + "' is a letter!");
                System.out.println("");
                System.out.println("");
            }

                WIN = isGuessed(wordRepArr);
        }

                // STAGE TWO       
                if (!WIN) { 
                    printStageTwo();
                    System.out.println("you have incorrectly guessed: " +  printUsed(charUsed));
                    System.out.println("");
                    printGuesser(wordRepArr);
                }

        while ((stageCounter == 2) && (WIN == false)) {
            System.out.print("guess a letter: ");
            guess = input.next().charAt(0);
             if (word.indexOf(guess) == -1) {
                stageCounter = 3;
                System.out.println(guess + " is NOT a letter!");
                charUsed[stageCounter] = guess;
                System.out.println("");
            }
            if (word.indexOf(guess) != -1) {
                
                System.out.println("");
                do {
                    curr = word.indexOf(guess);
                    wordRepArr[curr] = guess;
                    word = word.substring(0, curr) + "0" + word.substring(curr + 1); 
                } while (word.indexOf(guess) != -1);

                printGuesser(wordRepArr);
                System.out.println("'" + guess + "' is a letter!");
                System.out.println("");
                System.out.println("");
            }

           
                WIN = isGuessed(wordRepArr);
                
        }


                 // STAGE THREE      
                    if (!WIN) {  
                    printStageThree();
                    System.out.println("you have incorrectly guessed: " +  printUsed(charUsed));
                    System.out.println("");
                    printGuesser(wordRepArr);
                
                }

        while ((stageCounter == 3) && (WIN == false)) {
            System.out.print("guess a letter: ");
            guess = input.next().charAt(0);
             if (word.indexOf(guess) == -1) {
                stageCounter = 4;
                System.out.println(guess + " is NOT a letter!");
                charUsed[stageCounter] = guess;
                System.out.println("");
            }
            if (word.indexOf(guess) != -1) {
                
                System.out.println("");
                do {
                    curr = word.indexOf(guess);
                    wordRepArr[curr] = guess;
                    word = word.substring(0, curr) + "0" + word.substring(curr + 1); 
                } while (word.indexOf(guess) != -1);

                printGuesser(wordRepArr);
                System.out.println("'" + guess + "' is a letter!");
                System.out.println("");
                System.out.println("");
            }

                WIN = isGuessed(wordRepArr);
    
        }

        // STAGE FOUR        
                printStageFour();
                System.out.println("you have incorrectly guessed: " +  printUsed(charUsed));
                System.out.println("");
                printGuesser(wordRepArr);

        while ((stageCounter == 4) && (WIN == false)) {
            System.out.print("guess a letter: ");
            guess = input.next().charAt(0);
             if (word.indexOf(guess) == -1) {
                stageCounter = 5;
                System.out.println(guess + " is NOT a letter!");
                charUsed[stageCounter] = guess;
                System.out.println("");
            }
            if (word.indexOf(guess) != -1) {
                
                System.out.println("");
                do {
                    curr = word.indexOf(guess);
                    wordRepArr[curr] = guess;
                    word = word.substring(0, curr) + "0" + word.substring(curr + 1); 
                } while (word.indexOf(guess) != -1);

                printGuesser(wordRepArr);
                System.out.println("'" + guess + "' is a letter!");
                System.out.println("");
                System.out.println("");
            }

                WIN = isGuessed(wordRepArr);
            
        }

                // STAGE FIVE         
                if (!WIN) {
                printStageFive();
                System.out.println("you have incorrectly guessed: " +  printUsed(charUsed));
                System.out.println("");
                printGuesser(wordRepArr);
                }

        while ((stageCounter == 5) && (WIN == false)) {
            System.out.print("guess a letter: ");
            guess = input.next().charAt(0);
             if (word.indexOf(guess) == -1) {
                stageCounter = 6;
                System.out.println(guess + " is NOT a letter!");
                charUsed[stageCounter] = guess;
                System.out.println("");
            }
            if (word.indexOf(guess) != -1) {
                
                System.out.println("");
                do {
                    curr = word.indexOf(guess);
                    wordRepArr[curr] = guess;
                    word = word.substring(0, curr) + "0" + word.substring(curr + 1); 
                } while (word.indexOf(guess) != -1);

                printGuesser(wordRepArr);
                System.out.println("'" + guess + "' is a letter!");
                charUsed[stageCounter] = guess;
                System.out.println("");
                System.out.println("");
            }

                WIN = isGuessed(wordRepArr);
        }

         if (WIN == true) {
        win(COPYWORD);
        stageCounter = 10;
        }

                // STAGE SIX  
                if (!WIN) {       
                    printStageSix();
                    System.out.println("you have incorrectly guessed: " +  printUsed(charUsed));
                    System.out.println("");
                    printGuesser(wordRepArr);
                }

        while ((stageCounter == 6) && (WIN == false)) {
            System.out.println("You have one last chance, but you have to get the whole word!");
            System.out.print("Your guess: ");
            String bigGuess = input.next();
            if (bigGuess.equals(COPYWORD)) {
                WIN = true;
                win(COPYWORD);
            }

            stageCounter = 7;
            
        } 


        if (WIN == false) {
            printStageKill();

            System.out.println("You lost!");
            System.out.println("");
            printGuesser(wordRepArr);
            System.out.println("");
            System.out.println("The word was: " + COPYWORD);
            System.out.println("");
            int statcount = 0;
            for (int i = 0; i < wordRepArr.length; i++) {
                if (wordRepArr[i] != '-') {
                    statcount++;
                }
            }

            System.out.println("You got " + (statcount * 1.0 / wordRepArr.length + "% of the word."));
            System.out.println("");
            

        }
    
    }


    /**
     * @param wordRep - an array that represents the characters of the word, replaced by dashes if that char is unguessed
     * @return boolean true if there are no dashes (the word is guessed) and false otherwise.
     */
    public static boolean isGuessed(char[] wordRep) {       // returns true if wordrep is no dashes, false otherwise
        for (int i = 0; i < wordRep.length; i++) {
            if (wordRep[i] == '-') {
                return false;
            }
        }
        

        return true;
    }

    /**
     * @param wordRep - an array that represents the characters of the word, replaced by dashes if that char is unguessed
     * prints the blanks and filled-in-blanks of a hangman game, i.e.:
     * word = sparatic
     *  _s_ ___ _a_ ___ _a_ _t_ ___ _c_
     */
    public static void printGuesser(char[] wordRep) {
        for (int i = 0; i < wordRep.length; i++) {
            if (wordRep[i] == '-') {
                System.out.print("___ ");
            } else {
                System.out.print("_" + wordRep[i] + "_ ");
            }
        }
        System.out.println("");

    }

    /**
     * @param word - takes in original word for print statement
     * prints a "win screen"
     */
    public static void win(String word) {
        printStageWin();
        
        System.out.println("Congrats! You won!");
        System.out.println("The word was " + word + "!");
        System.out.println("");
    }

    public static String printUsed(char[] charUsed) { 
        String toString = "";   
        for (int i = 0; i < charUsed.length; i++) {
            if (charUsed[i] != ' ') {
                toString += charUsed[i] + " ";
            }
        }
        return toString;
    }
    


    public static void hideTerminal() {
        System.out.println("   **      **                                    "); // H
        System.out.println("   **      **                                    ");
        System.out.println("   **      **                                  ");
        System.out.println("   **********                                          ");
        System.out.println("   **      **                                    ");
        System.out.println("   **      **                                    ");
        System.out.println("   **      **                                    ");
        System.out.println("   **      **                                    ");

        System.out.println("");
        System.out.println("      ****                                         ");
        System.out.println("    **    **                                      "); // A
        System.out.println("   **      **                                   ");
        System.out.println("   **      **                                    ");
        System.out.println("   **********                                    ");
        System.out.println("   **      **                                    ");
        System.out.println("   **      **                                    ");
        System.out.println("   **      **                                    ");

        System.out.println("                                             ");
        System.out.println("   **      **                                     "); // N
        System.out.println("   ** *    **                                        ");
        System.out.println("   **  *   **                                     ");
        System.out.println("   **   *  **                                      ");
        System.out.println("   **    * **                                     ");
        System.out.println("   **     ***                                     ");
        System.out.println("   **      **                                     ");

        System.out.println("                                             ");
        System.out.println("   **********                                          "); // g
        System.out.println("   **                                          ");
        System.out.println("   **                                          ");
        System.out.println("   **                                          ");
        System.out.println("   **   *****                                   ");
        System.out.println("   **      **                                    ");
        System.out.println("   **      **                                    ");
        System.out.println("   **********                                          ");



         System.out.println("                                             ");
        System.out.println("   **      **                                     "); // M
        System.out.println("   ** *  * **                                        ");
        System.out.println("   **  **  **                                     ");
        System.out.println("   **      **                                      ");
        System.out.println("   **      **                                     ");
        System.out.println("   **      **                                     ");
        System.out.println("   **      **                                     ");

         System.out.println("                                             ");
        System.out.println("      ****                                         ");
        System.out.println("    **    **                                      "); // A
        System.out.println("   **      **                                   ");
        System.out.println("   **      **                                    ");
        System.out.println("   **********                                    ");
        System.out.println("   **      **                                    ");
        System.out.println("   **      **                                    ");
        System.out.println("   **      **                                    ");
        System.out.println("   **      **                                       ");

         System.out.println("                                             ");
        System.out.println("   **      **                                     "); // N
        System.out.println("   ** *    **                                        ");
        System.out.println("   **  *   **                                     ");
        System.out.println("   **   *  **                                      ");
        System.out.println("   **    * **                                     ");
        System.out.println("   **     ***                                     ");
        System.out.println("   **      **                                     ");

    }


}
