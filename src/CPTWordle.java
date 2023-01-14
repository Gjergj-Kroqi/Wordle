/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


 /*
CPTWordle.java
Names: Gjergj Kroqi & Luca Parisi
Description: CPT - Wordle
Date Created: 05/27/2022
Date Last Modified: 06/13/2022
 */
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author Gjergj Kroqi & Luca Parisi
 */
public class CPTWordle {

    public static final String ANSI_RESET = "\u001B[0m"; // This is used to reset the colour of text.
    public static final String ANSI_RED = "\u001B[31m"; // This is used to display red text.
    public static final String ANSI_YELLOW = "\u001B[33m"; // This is used to display yellow text.
    public static final String ANSI_GREEN = "\u001B[32m"; // This is used to display green text.

    static String enteredName; // This is used to save account usernames.
    static String enteredPassword; // This is used to save account passwords.
    static int sessionWins = 0; // This is used to save the number of wins achieved in each game session.

    static Scanner input = new Scanner(System.in);

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.InterruptedException
     */
    // This method is used to call upon the accountOptionsMenu method, which opens the account options menu.
    public static void main(String[] args) throws IOException, FileNotFoundException, InterruptedException {

        accountOptionsMenu();

    }

    // This method contains the main menu and the account options menu.
    // The account options menu allows the user to create a new account, log in to a previous one, or exit the program.
    // Creating a new account or logging into a previous one allows the user to start playing Wordle.
    public static void accountOptionsMenu() throws FileNotFoundException, IOException, InterruptedException {

        String accountOption;

        System.out.println(ANSI_YELLOW + " ____________________________________________________________" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|                                                            |" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                        -- WORDLE --                        " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|                                                            |" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  Hello and welcome to Wordle!                              " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|                                                            |" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  To begin, please select one of the options listed below.  " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|                                                            |" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                    -- ACCOUNT OPTIONS --                   " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|                                                            |" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  [1] Create a new Account                                  " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  [2] Login to a previous Account                           " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  [3] Exit the application                                  " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|____________________________________________________________|" + ANSI_RESET);
        System.out.println("");
        while (true) { // This while loop ensures that the user enters a valid input.
            System.out.print("Option: ");
            accountOption = input.nextLine();
            if (accountOption.equalsIgnoreCase("1") || accountOption.equalsIgnoreCase("2") || accountOption.equalsIgnoreCase("3")) {
                break;
            } else {
                System.out.println("");
                System.out.print(ANSI_RED + "Invalid input. Please try again." + ANSI_RESET);
                System.out.println("");
            }
            System.out.println("");
        }
        if (accountOption.equalsIgnoreCase("1")) { // If the user chooses option 1 from the account options menu, then they will be redirected to the sign up screen.
            // This allows them to create a new account.
            String strNum;
            int num = 0;
            try { // This is used to check how many accounts exist.
                // If the num file does not exist, then this code will not be run.
                FileReader ft = new FileReader("num.txt");
                Scanner st = new Scanner(ft);
                strNum = st.nextLine();
                num = Integer.parseInt(strNum);
            } catch (IOException e) {

            }
            int counter = 1;
            boolean usernameCheck = false;
            System.out.println(ANSI_YELLOW + "______________________________________________________________" + ANSI_RESET);
            System.out.println("");
            System.out.println("                         -- SIGN UP --                        ");
            System.out.println("");
            System.out.println(ANSI_RED + "NOTE: your Username and Password cannot exceed 10 characters" + ANSI_RESET);
            System.out.println(ANSI_RED + "(including spaces)." + ANSI_RESET);
            System.out.println("");
            System.out.print("Please create your Username: ");
            enteredName = input.nextLine();
            while (enteredName.length() > 10) { // This ensures that the user does not enter a username exceeding 10 characters (including spaces).
                System.out.println("");
                System.out.println(ANSI_RED + "Your Username cannot exceed 10 characters (including spaces)." + ANSI_RESET);
                System.out.println("");
                System.out.print("Please create your Username: ");
                enteredName = input.nextLine();
            }
            while (true) { // This while loop is used to check if the username that the user entered already exists on another account.
                // If it does already exist, then the user will be prompted to select a different username.
                while (counter <= num) { // This while loop only runs until the counter variable exceeds the number of accounts that exist.
                    FileReader ff = new FileReader("profile" + counter + ".txt");
                    Scanner ss = new Scanner(ff);
                    String line = ss.nextLine();
                    if (enteredName.equals(line)) {
                        usernameCheck = true;
                        break;
                    }
                    counter++;
                }
                if (usernameCheck == true) { // This informs the user that the username they entered is taken.
                    System.out.println("");
                    System.out.println(ANSI_RED + "That Username is currently taken." + ANSI_RESET);
                    System.out.println("");
                    System.out.print("Please create your Username: ");
                    enteredName = input.nextLine();
                    usernameCheck = false;
                } else {
                    break;
                }
            }
            System.out.print("Please create your Password: ");
            enteredPassword = input.nextLine();
            while (enteredPassword.length() > 10) { // This ensures that the user does not enter a password exceeding 10 characters (including spaces).
                System.out.println("");
                System.out.println(ANSI_RED + "Your Password cannot exceed 10 characters (including spaces)." + ANSI_RESET);
                System.out.println("");
                System.out.print("Please create your Password: ");
                enteredPassword = input.nextLine();
            }
            System.out.println("");
            System.out.println(ANSI_GREEN + "CREATING ACCOUNT..." + ANSI_RESET);
            System.out.println("");
            ProfileMaker1 profile1 = new ProfileMaker1(enteredName, enteredPassword); // This sends the data that the user entered to the ProfileMaker file, in order to create the new profile.
            Thread.sleep(1000);
            System.out.println(ANSI_GREEN + "Account Creation Successful." + ANSI_RESET);
            System.out.println("");
            System.out.println("- NEW PROFILE -");
            System.out.println("");
            System.out.println("Username: " + enteredName);
            System.out.println("Password: " + enteredPassword);
            sessionWins = 0;
        } else if (accountOption.equalsIgnoreCase("2")) { // If the user chooses option 2 from the account options menu, then they will be redirected to the sign in screen.
            // This allows them to log in to a previous account.
            File num = new File("num.txt");
            if (num.exists()) { // This code will only be run if the num file exists.
                while (true) {
                    System.out.println(ANSI_YELLOW + "______________________________________________________________" + ANSI_RESET);
                    System.out.println("");
                    System.out.println("                         -- SIGN IN --                        ");
                    System.out.println("");
                    System.out.println(ANSI_RED + "NOTE: your Username and Password cannot exceed 10 characters" + ANSI_RESET);
                    System.out.println(ANSI_RED + "(including spaces)." + ANSI_RESET);
                    System.out.println("");
                    System.out.print("Please enter your Username: ");
                    enteredName = input.nextLine();
                    while (enteredName.length() > 10) { // This ensures that the user does not enter a username exceeding 10 characters (including spaces).
                        System.out.println("");
                        System.out.println(ANSI_RED + "Your Username cannot exceed 10 characters (including spaces)." + ANSI_RESET);
                        System.out.println("");
                        System.out.print("Please enter your Username: ");
                        enteredName = input.nextLine();
                    }
                    System.out.print("Please enter your Password: ");
                    enteredPassword = input.nextLine();
                    while (enteredPassword.length() > 10) { // This ensures that the user does not enter a password exceeding 10 characters (including spaces).
                        System.out.println("");
                        System.out.println(ANSI_RED + "Your Password cannot exceed 10 characters (including spaces)." + ANSI_RESET);
                        System.out.println("");
                        System.out.print("Please enter your Password: ");
                        enteredPassword = input.nextLine();
                    }
                    System.out.println("");
                    System.out.println(ANSI_GREEN + "LOGGING IN..." + ANSI_RESET);
                    System.out.println("");
                    ProfileFinder1 profile1 = new ProfileFinder1(enteredName, enteredPassword); // This sends the data that the user entered to the ProfileFinder file, in order to make sure that the profile exists.
                    FileReader fr = new FileReader("account_check.txt");
                    Scanner s = new Scanner(fr);
                    String line = s.nextLine();
                    if (line.equals("1")) {
                        break;
                    }
                }
                sessionWins = 0;
            } else { // If the num file does not exist, then this means that no accounts exist in the system, so the user must make one first.
                System.out.println("");
                System.out.println(ANSI_RED + "You must create an account first." + ANSI_RESET);
                accountOptionsMenu(); // The user will then be redirected to the account options menu.
            }
        } else if (accountOption.equalsIgnoreCase("3")) { // If the user chooses option 3 from the account options menu, then the program will be exited.
            System.out.println("");
            System.out.println(ANSI_RED + "QUITTING..." + ANSI_RESET);
            System.out.println("");
            Thread.sleep(1000);
            System.out.println("Thank you for playing Wordle, have a nice day!");
            System.out.println(ANSI_YELLOW + "______________________________________________________________" + ANSI_RESET);
            System.out.println("");
            System.exit(0);
        }
        gameOptionsMenu(); // After choosing option 1, 2, or 3 from the account options menu, the user will be redirected to the game options menu.

    }

    // This method contains the game options menu.
    // The user will be redirected to this menu after selecting an option from the account options menu.
    // It allows the user to play the game, view the user manual, view the credits screen, or exit the program.
    public static void gameOptionsMenu() throws FileNotFoundException, IOException, InterruptedException {

        String gameOption, guess1 = "", guess2 = "", guess3 = "", guess4 = "", guess5 = "", guess6 = "";
        int guessesRemaining = 6, numWins = 0;

        System.out.println(ANSI_YELLOW + " ____________________________________________________________" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                    -- GAME OPTIONS --                      " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  Please select one of the options listed below.            " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  [1] Start playing                                         " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  [2] View the User Manual                                  " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  [3] View the Credits                                      " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  [4] Exit the application                                  " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|____________________________________________________________|" + ANSI_RESET);
        System.out.println("");
        while (true) { // This while loop ensures that the user enters a valid input.
            System.out.print("Option: ");
            gameOption = input.nextLine();
            if (gameOption.equalsIgnoreCase("1") || gameOption.equalsIgnoreCase("2") || gameOption.equalsIgnoreCase("3") || gameOption.equalsIgnoreCase("4")) {
                break;
            } else {
                System.out.println("");
                System.out.print(ANSI_RED + "Invalid input. Please try again." + ANSI_RESET);
                System.out.println("");
            }
            System.out.println("");
        }
        if (gameOption.equalsIgnoreCase("1")) { // If the user selects option 1 from the game options menu, then the game will begin and they can start playing.
            String s = "";
            String guess = "";
            String correctWord = (getWord(s));
            System.out.println("");
            System.out.println("                         -- WORDLE --                         ");
            System.out.println(ANSI_YELLOW + "______________________________________________________________" + ANSI_RESET);
            System.out.println("");
            System.out.println("-----");
            System.out.println("-----");
            System.out.println("-----");
            System.out.println("-----");
            System.out.println("-----");
            System.out.println("-----");

            for (int i = 0; i < 6; i++) { // The user has six tries to guess the word before losing.
                System.out.println("");
                System.out.print("Guess The Word: ");
                guess = input.nextLine();
                guessesRemaining--;
                int wordLength = (guess.length());

                while (wordLength < 5 || wordLength > 5 || guess.contains(" ")) { // This ensures that the user does not guess a word that is greater or less than 5 letters.
                    // It also ensures that the user's guess does not contain any spaces.
                    System.out.println("");
                    System.out.println(ANSI_RED + "Your guess must be 5 letters. Please try again." + ANSI_RESET);
                    System.out.println("");
                    System.out.print("Guess The Word: ");
                    guess = input.nextLine();
                    wordLength = (guess.length());
                }
                if (checkWord(guess, correctWord) == true) { // This checks if the user has won the game.
                    if (guess1.equals("")) { // This checks if the user won on their first attempt, and then prints the appropriate word grid to match it.
                        System.out.println("");
                        colors(correctWord.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("");
                        System.out.println("Genius");
                        System.out.println("");
                    } else if (guess2.equals("")) { // This checks if the user won on their second attempt, and then prints the appropriate word grid to match it.
                        System.out.println("");
                        colors(guess1.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(correctWord.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("");
                        System.out.println("Magnificent");
                        System.out.println("");
                    } else if (guess3.equals("")) { // This checks if the user won on their third attempt, and then prints the appropriate word grid to match it.
                        System.out.println("");
                        colors(guess1.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess2.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(correctWord.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("");
                        System.out.println("Impressive");
                        System.out.println("");
                    } else if (guess4.equals("")) { // This checks if the user won on their fourth attempt, and then prints the appropriate word grid to match it.
                        System.out.println("");
                        colors(guess1.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess2.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess3.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(correctWord.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("");
                        System.out.println("Splendid");
                        System.out.println("");
                    } else if (guess5.equals("")) { // This checks if the user won on their fifth attempt, and then prints the appropriate word grid to match it.
                        System.out.println("");
                        colors(guess1.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess2.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess3.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess4.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(correctWord.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        System.out.println("-----");
                        System.out.println("");
                        System.out.println("Great");
                        System.out.println("");
                    } else if (guess6.equals("")) { // This checks if the user won on their sixth attempt, and then prints the appropriate word grid to match it.
                        System.out.println("");
                        colors(guess1.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess2.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess3.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess4.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess5.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(correctWord.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        System.out.println("");
                        System.out.println("Phew");
                        System.out.println("");
                    }
                    System.out.print("Correct Word: ");
                    colors(correctWord.toUpperCase(), correctWord.toUpperCase()); // The correct word is displayed at the end of each game.
                    System.out.println("");
                    System.out.println("");
                    System.out.println(ANSI_GREEN + "Congratulations, you won! Well done!" + ANSI_RESET);
                    numWins++; // Adds one win to the account being used.
                    sessionWins++; // Adds one win to the current session.
                    System.out.println("");
                    System.out.println("Total Wins in this Session: " + sessionWins);
                    FileReader fm = new FileReader("current_profile.txt"); // Checks which account is currently being used.
                    Scanner z = new Scanner(fm);
                    String line1 = z.nextLine();
                    int lineNum1 = Integer.parseInt(line1);
                    int m1 = 1;
                    FileReader fd = new FileReader("profile" + lineNum1 + ".txt");
                    Scanner v = new Scanner(fd);
                    while (v.hasNextLine()) { // Prints out the total number of wins on the current account.
                        String accountWins = v.nextLine();
                        if (m1 == 3) {
                            System.out.println("Total Wins on this Account: " + (Integer.parseInt(accountWins) + 1));
                        }
                        m1++;
                    }
                    FileReader fr = new FileReader("current_profile.txt");
                    Scanner sr = new Scanner(fr);
                    String line = sr.nextLine();
                    int lineNum = Integer.parseInt(line);

                    FileReader fs = new FileReader("profile" + lineNum + ".txt"); // Determines the total number of wins on the current profile.
                    Scanner sw = new Scanner(fs);
                    for (int m = 1; m <= 3; m++) {
                        String line2 = sw.nextLine();
                        if (m == 3) {
                            numWins += Integer.parseInt(line2);
                        }
                    }
                    FileWriter fw = new FileWriter("profile" + lineNum + ".txt"); // Adds the total number of wins on the current profile to its respective file.
                    PrintWriter pw = new PrintWriter(fw);
                    for (int j = 0; j < 4; j++) {
                        if (j == 0) {
                            pw.println(enteredName);
                        }
                        if (j == 1) {
                            pw.println(enteredPassword);
                        }
                        if (j == 2) {
                            pw.println(numWins);
                        }

                    }
                    pw.close();
                    gameCompletedMenu(); // After a round of Wordle has been completed, the user will be redirected to the game completed menu.
                    break;

                }
                System.out.println("");
                switch (guessesRemaining) { // This updates the Wordle grid after each guess that the user makes.
                    case 6:
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        break;
                    case 5:
                        guess1 = guess;
                        colors(guess1.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        break;
                    case 4:
                        guess2 = guess;
                        colors(guess1.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess2.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        break;
                    case 3:
                        guess3 = guess;
                        colors(guess1.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess2.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess3.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        System.out.println("-----");
                        System.out.println("-----");
                        System.out.println("-----");
                        break;
                    case 2:
                        guess4 = guess;
                        colors(guess1.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess2.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess3.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess4.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        System.out.println("-----");
                        System.out.println("-----");
                        break;
                    case 1:
                        guess5 = guess;
                        colors(guess1.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess2.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess3.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess4.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess5.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        System.out.println("-----");
                        break;
                    case 0:
                        guess6 = guess;
                        colors(guess1.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess2.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess3.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess4.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess5.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        colors(guess6.toUpperCase(), correctWord.toUpperCase());
                        System.out.println("");
                        break;
                    default:
                        break;

                }

            }
            if (checkWord(guess, correctWord) == false) { // This checks if the user has lost the game.
                System.out.println("");
                System.out.println(":(");
                System.out.println("");
                System.out.print("Correct Word: ");
                colors(correctWord.toUpperCase(), correctWord.toUpperCase()); // The correct word is displayed at the end of each game.
                System.out.println("");
                System.out.println("");
                System.out.println(ANSI_RED + "Oh no, you lost! Better luck next time!" + ANSI_RESET);
                System.out.println("");
                System.out.println("Total Wins in this Session: " + sessionWins);
                FileReader fm = new FileReader("current_profile.txt");
                Scanner z = new Scanner(fm);
                String line1 = z.nextLine();
                int lineNum1 = Integer.parseInt(line1);
                int m1 = 1;
                FileReader fd = new FileReader("profile" + lineNum1 + ".txt");
                Scanner v = new Scanner(fd);
                while (v.hasNextLine()) { // Prints out the total number of wins on the current account.
                    String accountWins = v.nextLine();
                    if (m1 == 3) {
                        System.out.println("Total Wins on this Account: " + accountWins);
                    }
                    m1++;
                }
                gameCompletedMenu(); // After a round of Wordle has been completed, the user will be redirected to the game completed menu.
            }

        } else if (gameOption.equalsIgnoreCase("2")) { // If the user selects option 2 from the game options menu, then they will be shown the user manual.
            System.out.println(ANSI_YELLOW + " ____________________________________________________________" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                      -- USER MANUAL --                     " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  Hello and welcome to Wordle! This user manual will        " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  provide step-by-step instructions on how to use this      " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  application. You can refer to this information via the    " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  Game Options Menu, which can be accessed before starting  " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  a new game. Thankfully, Wordle is a very simple game to   " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  play, and it is suitable for all ages, so you should      " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  have no problem getting started. Now, let us begin.       " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "            - Section 1: Account Options Menu -             " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  Upon opening this application, you will be directed to    " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  the Account Options Menu, which will provide you with     " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  two different account options to choose from. You can     " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  either create a new account or log in to a previous one.  " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  Creating a new account or logging into a previous one     " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  will give you access to Wordle and keep track of the      " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  number of wins you achieve throughout each game. If you   " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  choose option 1, you will be prompted to enter a new      " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  username and password, both must be less than ten         " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  characters. Your new account will then be saved to the    " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  system and can be accessed each time that you play. If    " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  you choose option 2, then you will be prompted to enter   " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  the username and password of an already existing          " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  account. If the information that you enter is valid,      " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  then you will be logged in to your account and can        " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  continue using the application. Alongside the two         " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  account options, an additional option will allow you to   " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  exit the game. If you have had enough of Wordle and       " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  would like to quit, select option 3 in the Account        " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  Options Menu to close the application.                    " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "              - Section 2: Game Options Menu -              " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  After signing up or signing in, you will be redirected    " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  to the Game Options Menu, which will provide you with     " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  three different game options. You can either start        " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  playing, view the user manual, or view the credits. If    " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  you choose option 1, a new game will be started, and you  " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  will be given the opportunity to play. If you choose      " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  option 2, you will be redirected to the user manual you   " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  are currently reading. If you choose option 3, then you   " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  will be redirected to the credits screen, which will      " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  provide you with some background information regarding    " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  the development of Wordle. Much like the Account Options  " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  Menu, an additional option will allow you to exit the     " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  game, which can be found alongside the three game         " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  options. If you decide to quit, select option 4 in the    " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  Game Options Menu to close the application.               " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "               - Section 3: Wordle Gameplay -               " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  Now that you have a solid understanding of Wordle's       " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  features, we can begin to discuss the game itself. As     " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  the name suggests, Wordle is a game involving words. The  " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  objective is to guess a randomly selected five-letter     " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  word in just six attempts. At the beginning of each new   " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  game, a word is selected from a list at random, and then  " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  you are prompted to guess it, letter by letter. Each      " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  guess that you make yields a different result, based on   " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  whether it is correct or not. If you guess a letter       " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  incorrectly, then that letter will turn black. However,   " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  if you guess a letter correctly, it will turn yellow or   " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  green, depending on its position in the word. If it is    " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  not in the correct position in the word, it will turn     " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  yellow, but if it is in the correct position in the       " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  word, it will turn green. Note that the same letter can   " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  be used multiple times, meaning letters can be repeated   " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  within a word. If you can guess the word within six       " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  attempts, you will win the game, but if you fail to       " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  guess the word within six attempts, you will lose the     " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  game.                                                     " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "             - Section 4: Game Completed Menu -             " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  At the end of each run, you will be redirected to the     " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  Game Completed Menu, which will provide you with two      " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  different options to choose from. You can either return   " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  to the Main Menu/Account Options Menu or return to the    " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  Game Options Menu. You can also select option 3 to exit   " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  the application. No matter what choice you make, the      " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  game can be replayed as many times as you desire, so you  " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  can always come back for more if you ever get tired of    " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  playing. With all of this information in mind, you        " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  should now possess the knowledge required to play         " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  Wordle. If you ever feel stuck while playing the game,    " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  you can refer back to this manual to clear up any         " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  confusion. In the meantime, good luck and have fun!       " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "               -- WORDLE DEVELOPMENT TEAM --                " + ANSI_YELLOW + "|" + ANSI_RESET);

            System.out.println(ANSI_YELLOW + "|____________________________________________________________|" + ANSI_RESET);
            gameOptionsMenu();
        } else if (gameOption.equalsIgnoreCase("3")) { // If the user selects option 3 from the game options menu, then they will be shown the credits screen.
            System.out.println(ANSI_YELLOW + " ____________________________________________________________" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                        -- CREDITS --                       " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                         Wordle v1.0                        " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                       - DEVELOPED BY -                     " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                  Gjergj Kroqi & Luca Parisi                " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                       - DEVELOPED IN -                     " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                     Java NetBeans IDE 8.2                  " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                              &                             " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                 Java Apache NetBeans IDE 12.6              " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                       - DEVELOPED ON -                     " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                          06/13/2022                        " + ANSI_YELLOW + "|" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "|____________________________________________________________|" + ANSI_RESET);
            gameOptionsMenu();
        } else if (gameOption.equalsIgnoreCase("4")) { // If the user selects option 4 from the game options menu, then the program will be exited.
            System.out.println("");
            System.out.println(ANSI_RED + "QUITTING..." + ANSI_RESET);
            System.out.println("");
            Thread.sleep(1000);
            System.out.println("Thank you for playing Wordle, have a nice day!");
            System.out.println(ANSI_YELLOW + "______________________________________________________________" + ANSI_RESET);
            System.out.println("");
            System.exit(0);
        }
        System.out.println("");
    }

    // This methods opens a sizable list of five-letter words from a text file and then randomly selects one to use during gameplay.
    // The word that it selects is then returned.
    public static String getWord(String word) throws FileNotFoundException {
        FileReader fr = new FileReader("words.txt");
        Scanner reader = new Scanner(fr);

        int lineNum = (int) (Math.random() * (5757 - 1) + 1); // Generates a random number between 1 and 5757, since there are 5757 words in the text file.

        for (int i = 1; i <= lineNum; i++) {
            word = reader.nextLine();
        }

        System.out.println(ANSI_YELLOW + "______________________________________________________________" + ANSI_RESET);
        return word;

    }

    // This method checks if a guess made by the user matches the correct word.
    public static boolean checkWord(String guess, String correctWord) {

        if (guess.equalsIgnoreCase(correctWord)) {
            return true;
        } else {
            return false;
        }

    }

    // This method prints out the colours for each letter.
    public static void colors(String guess, String correctWord) {

        char[] inputWord = guess.toCharArray();
        char[] correctWordChar = correctWord.toCharArray();

        char[] answerTemp = correctWordChar;
        int[] colorForLetter = new int[5]; // 0 is grey, yellow is 1, and green is 2.

        for (int i = 0; i < 5; i++) { // This checks for a correct letter in a correct position (green).
            if (inputWord[i] == answerTemp[i]) {
                answerTemp[i] = '-';
                colorForLetter[i] = 2;
            } else {
            }
        }

        for (int j = 0; j < 5; j++) { // This checks for a correct letter in an incorrect position (yellow).
            for (int k = 0; k < 5; k++) {
                if (inputWord[j] == answerTemp[k] && colorForLetter[j] != 2) { // It only checks this if the letter is not green.
                    colorForLetter[j] = 1;
                    answerTemp[k] = '-';
                }
            }
        }

        for (int m = 0; m < 5; m++) { // This for loop goes through each letter in the word and prints out its respective colour.
            if (colorForLetter[m] == 0) {
                System.out.print(inputWord[m]);
            }
            if (colorForLetter[m] == 1) {
                System.out.print(ANSI_YELLOW + inputWord[m] + ANSI_RESET);
            }
            if (colorForLetter[m] == 2) {
                System.out.print(ANSI_GREEN + inputWord[m] + ANSI_RESET);
            }
        }

    }

    // This method contains the game completed menu.
    // This menu is displayed to the user at the end of each game.
    // It allows the user to return to the main menu/account options menu, to return to the game options menu, or to exit the program.
    public static void gameCompletedMenu() throws IOException, FileNotFoundException, InterruptedException {

        String gameCompletedOption;

        System.out.println(ANSI_YELLOW + " ____________________________________________________________" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                    -- GAME COMPLETED --                    " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  Please select one of the options listed below.            " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "                                                            " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  [1] Return to the Main Menu/Account Options               " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  [2] Return to Game Options                                " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|" + ANSI_RESET + "  [3] Exit the application                                  " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "|____________________________________________________________|" + ANSI_RESET);
        System.out.println("");
        while (true) { // This while loop ensures that the user enters a valid input.
            System.out.print("Option: ");
            gameCompletedOption = input.nextLine();
            if (gameCompletedOption.equalsIgnoreCase("1") || gameCompletedOption.equalsIgnoreCase("2") || gameCompletedOption.equalsIgnoreCase("3")) {
                break;
            } else {
                System.out.println("");
                System.out.print(ANSI_RED + "Invalid input. Please try again." + ANSI_RESET);
                System.out.println("");
            }
            System.out.println("");
        }
        if (gameCompletedOption.equalsIgnoreCase("1")) { // If the user selects option 1 from the game completed menu, then they will be sent back to the account options menu.
            accountOptionsMenu();
        } else if (gameCompletedOption.equalsIgnoreCase("2")) { // If the user selects option 2 from the game completed menu, then they will be sent back to the game options menu.
            gameOptionsMenu();
        } else if (gameCompletedOption.equalsIgnoreCase("3")) { // If the user selects option 3 from the game completed menu, then the program will be exited.
            System.out.println("");
            System.out.println(ANSI_RED + "QUITTING..." + ANSI_RESET);
            System.out.println("");
            Thread.sleep(1000);
            System.out.println("Thank you for playing Wordle, have a nice day!");
            System.out.println(ANSI_YELLOW + "______________________________________________________________" + ANSI_RESET);
            System.out.println("");
            System.exit(0);
        }
        input.nextLine();

    }

}
