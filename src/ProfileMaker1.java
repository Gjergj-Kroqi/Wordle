/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


 /*
ProfileMaker1.java
Names: Gjergj Kroqi & Luca Parisi
Description: CPT - Wordle | Profile Maker
Date Created: 05/27/2022
Date Last Modified: 06/13/2022
 */
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author Gjergj Kroqi & Luca Parisi
 */
public class ProfileMaker1 {

    private String username;
    private String password;

    static Scanner input = new Scanner(System.in);

    public ProfileMaker1(String username, String password) throws IOException {

        this.username = username;
        this.password = password;

        int counter = 1;
        int check = 0;

        try { // This will open the num file to check how many accounts exist. If there are no accounts, then check will be equal to 1.
            // If accounts do exist, then it will run the code below and update the number of accounts in the num file.
            FileReader fr = new FileReader("num.txt");
            Scanner s = new Scanner(fr);
            String line = s.nextLine();
            int lineNum = Integer.parseInt(line);

            counter += lineNum;

            FileWriter fw = new FileWriter("num.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(counter);
            pw.close();

        } catch (IOException e) {
            check = 1;

        }

        if (check == 1) { // If check is equal to 1, then a num file will be created and the number 1 will be added to it.
            // This is used to indicate that only one account currently exists. This code will only be run when the program is creating the first account.
            FileWriter fw = new FileWriter("num.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(counter);
            pw.close();
        }

        FileWriter fw = new FileWriter("profile" + counter + ".txt"); // This is used to create each new profile.
        PrintWriter pw = new PrintWriter(fw);
        pw.println(username);
        pw.println(password);
        pw.println(0);
        pw.close();

        FileWriter fw2 = new FileWriter("current_profile.txt"); // This keeps track of when a user creates a new account, and it saves that account as the current one being used.
        PrintWriter pw2 = new PrintWriter(fw2);
        pw2.println(counter);
        pw2.close();

    }

}
