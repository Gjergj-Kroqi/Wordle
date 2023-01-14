/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


 /*
ProfileFinder1.java
Names: Gjergj Kroqi & Luca Parisi
Description: CPT - Wordle | Profile Finder
Date Created: 05/27/2022
Date Last Modified: 06/13/2022
 */
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author Gjergj Kroqi & Luca Parisi
 */
public class ProfileFinder1 {

    static Scanner input = new Scanner(System.in);

    public static final String ANSI_RESET = "\u001B[0m"; // This is used to reset the colour of text.
    public static final String ANSI_RED = "\u001B[31m"; // This is used to display red text.
    public static final String ANSI_GREEN = "\u001B[32m"; // This is used to display green text.

    private String username;
    private String password;

    public ProfileFinder1(String username, String password) throws FileNotFoundException, IOException, InterruptedException {

        this.username = username;
        this.password = password;

        boolean correct = false;
        FileReader fr = new FileReader("num.txt");
        Scanner s = new Scanner(fr);
        String line = s.nextLine();

        int profileNum = Integer.parseInt(line);

        int counter = 1;

        while (true) { // This while loop checks all of the accounts in the system to see if the username and password that the user entered matches any of them.
            // If the username and password that the user entered matches one of the accounts, then the program will break out of the loop.
            try {
                FileReader fr2 = new FileReader("profile" + counter + ".txt");
                Scanner s2 = new Scanner(fr2);
                String correctUsername = s2.nextLine();

                if (username.equals(correctUsername)) { // Checks if the username that the user entered matches one of the usernames in the saved accounts.
                    String correctPassword = s2.nextLine();

                    if (password.equals(correctPassword)) { // Checks if the password that the user entered matches one of the passwords in the saved accounts.
                        correct = true;
                        FileWriter fw = new FileWriter("current_profile.txt"); // If the username and password are valid, then the account that was just logged in will become the current one.
                        PrintWriter pw = new PrintWriter(fw);
                        pw.println(counter);
                        pw.close();
                        break;
                    }
                }

                if (counter == profileNum) {
                    break;
                }
                counter++;
            } catch (IOException e) {

            }

        }

        if (correct == true) { // If the user enters a valid username and password, then they will be logged into the system.
            // The account_check file will then be updated to 1 and the loop will be exited in the main class.
            Thread.sleep(1000);
            System.out.println(ANSI_GREEN + "Login Successful." + ANSI_RESET);
            System.out.println("");
            System.out.println("- CURRENT PROFILE -");
            System.out.println("");
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            FileWriter fw = new FileWriter("account_check.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println("1");
            pw.close();
        } else { // If the user enters an invalid username and password, then they will not be logged into the system.
            // The account_check file will then be updated to 0 and the loop will continue in the main class, until the user enters a valid username and password. 
            Thread.sleep(1000);
            System.out.println(ANSI_RED + "Login Unsuccessful - incorrect Username or Password entered." + ANSI_RESET);
            FileWriter fw = new FileWriter("account_check.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println("0");
            pw.close();
        }

    }

}
